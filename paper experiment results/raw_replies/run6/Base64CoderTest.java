```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base64CoderTest {

    @Test
    public void testEncodeString() {
        String input = "Hello, World!";
        String expectedOutput = "SGVsbG8sIFdvcmxkIQ==";
        assertEquals(expectedOutput, Base64Coder.encodeString(input));
    }

    @Test
    public void testDecodeString() {
        String input = "SGVsbG8sIFdvcmxkIQ==";
        String expectedOutput = "Hello, World!";
        assertEquals(expectedOutput, Base64Coder.decodeString(input));
    }

    @Test
    public void testEncodeByteArray() {
        byte[] input = "Hello, World!".getBytes();
        char[] expectedOutput = "SGVsbG8sIFdvcmxkIQ==".toCharArray();
        assertArrayEquals(expectedOutput, Base64Coder.encode(input));
    }

    @Test
    public void testEncodeByteArrayWithLength() {
        byte[] input = "Hello, World!".getBytes();
        char[] expectedOutput = "SGVsbG8sIFdvcmxkIQ==".toCharArray();
        assertArrayEquals(expectedOutput, Base64Coder.encode(input, input.length));
    }

    @Test
    public void testDecodeByteArray() {
        char[] input = "SGVsbG8sIFdvcmxkIQ==".toCharArray();
        byte[] expectedOutput = "Hello, World!".getBytes();
        assertArrayEquals(expectedOutput, Base64Coder.decode(input));
    }

    @Test
    public void testInvalidBase64Length() {
        String input = "SGVsbG8sIFdvcmxkIQ="; // Invalid length
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decode(input);
        });
        assertEquals("Length of Base64 encoded input string is not a multiple of 4.", exception.getMessage());
    }

    @Test
    public void testInvalidBase64Character() {
        String input = "SGVsbG8sIFdvcmxkIQ?$"; // Invalid character '$'
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decode(input);
        });
        assertEquals("Illegal character in Base64 encoded data.", exception.getMessage());
    }
}
```

This test suite for `Base64Coder` covers the following:
- Encoding and decoding strings and byte arrays.
- Handling errors for invalid Base64 encoded string length.
- Handling errors for illegal characters in the Base64 encoded string.