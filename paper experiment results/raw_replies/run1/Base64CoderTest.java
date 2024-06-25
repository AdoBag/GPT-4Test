```java
package org.example.run1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Base64CoderTest {

    @Test
    public void testEncodeString() {
        String input = "Hello, World!";
        String expected = "SGVsbG8sIFdvcmxkIQ==";
        Assertions.assertEquals(expected, Base64Coder.encodeString(input));
    }

    @Test
    public void testDecodeString() {
        String input = "SGVsbG8sIFdvcmxkIQ==";
        String expected = "Hello, World!";
        Assertions.assertEquals(expected, Base64Coder.decodeString(input));
    }

    @Test
    public void testEncodeAndDecodeString() {
        String original = "This is a test string.";
        String encoded = Base64Coder.encodeString(original);
        String decoded = Base64Coder.decodeString(encoded);
        Assertions.assertEquals(original, decoded);
    }

    @Test
    public void testEncodeByteArray() {
        byte[] input = "Test".getBytes();
        char[] expected = "VGVzdA==".toCharArray();
        Assertions.assertArrayEquals(expected, Base64Coder.encode(input));
    }

    @Test
    public void testDecodeByteArray() {
        String input = "VGVzdA==";
        byte[] expected = "Test".getBytes();
        Assertions.assertArrayEquals(expected, Base64Coder.decode(input));
    }

    @Test
    public void testInvalidDecodeString() {
        String invalidBase64 = "InvalidBase64==";
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decodeString(invalidBase64);
        });
    }

    @Test
    public void testInvalidLengthDecode() {
        String invalidLengthBase64 = "SGVsbG8="; // not a multiple of 4
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decode(String.valueOf(invalidLengthBase64));
        });
    }
}
```