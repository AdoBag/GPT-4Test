```java
package org.example.run1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Base64CoderTest {

    @Test
    public void testEncodeString() {
        String original = "Hello, World!";
        String encoded = Base64Coder.encodeString(original);
        assertEquals("SGVsbG8sIFdvcmxkIQ==", encoded);
    }

    @Test
    public void testDecodeString() {
        String encoded = "SGVsbG8sIFdvcmxkIQ==";
        String decoded = Base64Coder.decodeString(encoded);
        assertEquals("Hello, World!", decoded);
    }

    @Test
    public void testEncodeByteArray() {
        byte[] original = "Hello, World!".getBytes();
        char[] encoded = Base64Coder.encode(original);
        assertArrayEquals("SGVsbG8sIFdvcmxkIQ==".toCharArray(), encoded);
    }

    @Test
    public void testEncodeByteArrayWithLen() {
        byte[] original = "Hello, World!".getBytes();
        char[] encoded = Base64Coder.encode(original, original.length);
        assertArrayEquals("SGVsbG8sIFdvcmxkIQ==".toCharArray(), encoded);
    }

    @Test
    public void testDecodeStringToByteArray() {
        String encoded = "SGVsbG8sIFdvcmxkIQ==";
        byte[] decoded = Base64Coder.decode(encoded);
        assertArrayEquals("Hello, World!".getBytes(), decoded);
    }

    @Test
    public void testDecodeCharArrayToByteArray() {
        char[] encoded = "SGVsbG8sIFdvcmxkIQ==".toCharArray();
        byte[] decoded = Base64Coder.decode(encoded);
        assertArrayEquals("Hello, World!".getBytes(), decoded);
    }

    @Test
    public void testInvalidBase64StringLength() {
        String encoded = "SGVsbG8sIFdvcmxkIQ"; // Invalid length
        assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decode(encoded);
        });
    }

    @Test
    public void testInvalidBase64Character() {
        String encoded = "SGVsbG8s@IFdvcmxkIQ=="; // Invalid character '@'
        assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decode(encoded);
        });
    }

    @Test
    public void testEmptyStringEncode() {
        String original = "";
        String encoded = Base64Coder.encodeString(original);
        assertEquals("", encoded);
    }

    @Test
    public void testEmptyStringDecode() {
        String encoded = "";
        String decoded = Base64Coder.decodeString(encoded);
        assertEquals("", decoded);
    }

    @Test
    public void testNullInputEncode() {
        assertThrows(NullPointerException.class, () -> {
            Base64Coder.encodeString(null);
        });
    }

    @Test
    public void testNullInputDecode() {
        assertThrows(NullPointerException.class, () -> {
            Base64Coder.decodeString(null);
        });
    }
}
```