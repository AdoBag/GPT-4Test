
package org.example.run10;

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
    public void testEncodeBytes() {
        byte[] input = "Hello, World!".getBytes();
        char[] expectedOutput = "SGVsbG8sIFdvcmxkIQ==".toCharArray();
        assertArrayEquals(expectedOutput, Base64Coder.encode(input));
    }

    @Test
    public void testDecodeBytesFromString() {
        String input = "SGVsbG8sIFdvcmxkIQ==";
        byte[] expectedOutput = "Hello, World!".getBytes();
        assertArrayEquals(expectedOutput, Base64Coder.decode(input));
    }

    @Test
    public void testDecodeBytesFromCharArray() {
        char[] input = "SGVsbG8sIFdvcmxkIQ==".toCharArray();
        byte[] expectedOutput = "Hello, World!".getBytes();
        assertArrayEquals(expectedOutput, Base64Coder.decode(input));
    }

    @Test
    public void testEncodeEmptyString() {
        String input = "";
        String expectedOutput = "";
        assertEquals(expectedOutput, Base64Coder.encodeString(input));
    }

    @Test
    public void testDecodeEmptyString() {
        String input = "";
        String expectedOutput = "";
        assertEquals(expectedOutput, Base64Coder.decodeString(input));
    }

    @Test
    public void testDecodeInvalidBase64String() {
        String input = "SGVsbG8sIFdvcmxk!Q==";
        assertThrows(IllegalArgumentException.class, () -> Base64Coder.decodeString(input));
    }
}
