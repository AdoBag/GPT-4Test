
package org.example.run9;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base64CoderTest {

    @Test
    public void testEncodeString() {
        String input = "Hello World";
        String expected = "SGVsbG8gV29ybGQ=";
        String encoded = Base64Coder.encodeString(input);
        assertEquals(expected, encoded);
    }

    @Test
    public void testDecodeString() {
        String input = "SGVsbG8gV29ybGQ=";
        String expected = "Hello World";
        String decoded = Base64Coder.decodeString(input);
        assertEquals(expected, decoded);
    }

    @Test
    public void testEncodeBytes() {
        byte[] input = "Hello World".getBytes();
        String expected = "SGVsbG8gV29ybGQ=";
        char[] encoded = Base64Coder.encode(input);
        assertEquals(expected, new String(encoded));
    }

    @Test
    public void testDecodeBytes() {
        String input = "SGVsbG8gV29ybGQ=";
        byte[] expected = "Hello World".getBytes();
        byte[] decoded = Base64Coder.decode(input);
        assertArrayEquals(expected, decoded);
    }

    @Test
    public void testEncodeEmptyString() {
        String input = "";
        String expected = "";
        String encoded = Base64Coder.encodeString(input);
        assertEquals(expected, encoded);
    }

    @Test
    public void testDecodeEmptyString() {
        String input = "";
        String expected = "";
        String decoded = Base64Coder.decodeString(input);
        assertEquals(expected, decoded);
    }

    @Test
    public void testInvalidBase64String() {
        String input = "Invalid*Base64";
        assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decodeString(input);
        });
    }

//    @Test
//    public void testDecodeInvalidLengthString() {
//        String input = "WfA="; // Invalid length for Base64
//        assertThrows(IllegalArgumentException.class, () -> {
//            Base64Coder.decodeString(input);
//        });
//    }
    /**
     * Manual fix of the above test
     * Problem: The length was actually valid
     */
    @Test
    public void testDecodeInvalidLengthString__fixed() {
        String input = "WfA=="; // Invalid length for Base64
        assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decodeString(input);
        });
    }

    @Test
    public void testEncodePadding() {
        byte[] input = "Man".getBytes();  // "Man" should result in padding "=.."
        String expected = "TWFu";
        char[] encoded = Base64Coder.encode(input);
        assertEquals(expected, new String(encoded));
    }

    @Test
    public void testDecodePadding() {
        String input = "TWFu";   // "Man" encoded with Base64
        byte[] expected = "Man".getBytes();
        byte[] decoded = Base64Coder.decode(input);
        assertArrayEquals(expected, decoded);
    }
}
