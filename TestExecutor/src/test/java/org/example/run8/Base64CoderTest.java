
package org.example.run8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Base64CoderTest {

    @Test
    void testEncodeString() {
        String input = "Hello, World!";
        String expected = "SGVsbG8sIFdvcmxkIQ==";
        String actual = Base64Coder.encodeString(input);
        
        assertEquals(expected, actual);
    }

    @Test
    void testEncode() {
        byte[] input = "Hello, World!".getBytes();
        char[] expected = "SGVsbG8sIFdvcmxkIQ==".toCharArray();
        char[] actual = Base64Coder.encode(input);
        
        assertArrayEquals(expected, actual);
    }

    @Test
    void testEncodeWithLength() {
        byte[] input = "Hello, World!".getBytes();
        char[] expected = "SGVsbG8sIFdvcmxkIQ==".toCharArray();
        char[] actual = Base64Coder.encode(input, input.length);
        
        assertArrayEquals(expected, actual);
    }

    @Test
    void testDecodeString() {
        String input = "SGVsbG8sIFdvcmxkIQ==";
        String expected = "Hello, World!";
        String actual = Base64Coder.decodeString(input);
        
        assertEquals(expected, actual);
    }

    @Test
    void testDecode() {
        String input = "SGVsbG8sIFdvcmxkIQ==";
        byte[] expected = "Hello, World!".getBytes();
        byte[] actual = Base64Coder.decode(input);
        
        assertArrayEquals(expected, actual);
    }

    @Test
    void testDecodeCharArray() {
        char[] input = "SGVsbG8sIFdvcmxkIQ==".toCharArray();
        byte[] expected = "Hello, World!".getBytes();
        byte[] actual = Base64Coder.decode(input);
        
        assertArrayEquals(expected, actual);
    }

    @Test
    void testEncodeDecodeCycle() {
        String original = "Sample text for Base64 encoding and decoding.";
        String encoded = Base64Coder.encodeString(original);
        String decoded = Base64Coder.decodeString(encoded);

        assertEquals(original, decoded);
    }

    @Test
//    void testDecodeInvalidBase64() {
    void testDecodeInvalidBase64__fixed() {
//        String invalidBase64 = "This is not Base64!";
        String invalidBase64 = "This is not Base64!!";

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decodeString(invalidBase64);
        });

        /** The failing assertion. Fixed by changing the length of the input to be valid */
        assertEquals("Illegal character in Base64 encoded data.", thrown.getMessage());
    }

//    @Test
//    void testDecodeInvalidLengthBase64() {
//        String invalidLengthBase64 = "SGVsbG8="; // Length not a multiple of 4
//
//        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
//            Base64Coder.decodeString(invalidLengthBase64);
//        });
//
//        assertEquals("Length of Base64 encoded input string is not a multiple of 4.", thrown.getMessage());
//    }

    /**
     * Manual fix of the above test
     * Problem: The length was actually a multiple of 4, thus, valid
     */
    @Test
    void testDecodeInvalidLengthBase64__fixed() {
        String invalidLengthBase64 = "SGVsbG8=="; // Length not a multiple of 4
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decodeString(invalidLengthBase64);
        });
        assertEquals("Length of Base64 encoded input string is not a multiple of 4.", thrown.getMessage());
    }
}
