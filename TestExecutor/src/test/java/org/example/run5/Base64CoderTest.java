
package org.example.run5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Base64CoderTest {

    @Test
    void testEncodeString() {
        String input = "Hello World!";
        String expected = "SGVsbG8gV29ybGQh";
        assertEquals(expected, Base64Coder.encodeString(input));
        
        input = "";
        expected = "";
        assertEquals(expected, Base64Coder.encodeString(input));
    }

    @Test
    void testEncodeByteArray() {
        byte[] input = "Hello World!".getBytes();
        char[] expected = "SGVsbG8gV29ybGQh".toCharArray();
        assertArrayEquals(expected, Base64Coder.encode(input));
        
        input = new byte[]{};
        expected = new char[]{};
        assertArrayEquals(expected, Base64Coder.encode(input));
    }
    
//    @Test
//    void testEncodeWithLength() {
//        byte[] input = "Hello World!".getBytes();
//        char[] expected = "SGVsbG8gV29ybA==".toCharArray();
//        assertArrayEquals(expected, Base64Coder.encode(input, 11));
//    }

    /**
     * Manual fix of the above test
     * Problem: Incorrect string expected
     */
    @Test
    void testEncodeWithLength__fixed() {
        byte[] input = "Hello World!".getBytes();
        char[] expected = "SGVsbG8gV29ybGQ=".toCharArray();
        assertArrayEquals(expected, Base64Coder.encode(input, 11));
    }

    @Test
    void testDecodeString() {
        String input = "SGVsbG8gV29ybGQh";
        String expected = "Hello World!";
        assertEquals(expected, Base64Coder.decodeString(input));
        
        input = "";
        expected = "";
        assertEquals(expected, Base64Coder.decodeString(input));
    }

    @Test
    void testDecode() {
        String input = "SGVsbG8gV29ybGQh";
        byte[] expected = "Hello World!".getBytes();
        assertArrayEquals(expected, Base64Coder.decode(input));
    }
    
    @Test
    void testDecodeCharArray() {
        char[] input = "SGVsbG8gV29ybGQh".toCharArray();
        byte[] expected = "Hello World!".getBytes();
        assertArrayEquals(expected, Base64Coder.decode(input));
    }
    
    @Test
    void testDecodeWithPadding() {
        char[] input = "SGVsbG8gV29ybA==".toCharArray();
        byte[] expected = "Hello Worl".getBytes();
        assertArrayEquals(expected, Base64Coder.decode(input));
    }

    @Test
    void testDecodeInvalidInputLength() {
        char[] input = "SGVsbG8gV29ybGQ".toCharArray();  // Invalid length, not a multiple of 4
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decode(input);
        });
        assertEquals("Length of Base64 encoded input string is not a multiple of 4.", exception.getMessage());
    }
    
//    @Test
//    void testDecodeInvalidCharacters() {
//        char[] input = "SGVsbG8gV29ybGQ!=".toCharArray();  // Invalid character '!'
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            Base64Coder.decode(input);
//        });
//        assertEquals("Illegal character in Base64 encoded data.", exception.getMessage());
//    }

    /**
     * Manual fix of the above test
     * Problem: the string length is checked first, so the code did not even reach the exception
     */
    @Test
    void testDecodeInvalidCharacters__fixed() {
        char[] input = "SGVsbG8gV29ybG!=".toCharArray();  // Invalid character '!'
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decode(input);
        });
        assertEquals("Illegal character in Base64 encoded data.", exception.getMessage());
    }


//    @Test
//    void testConstructorIsPrivate() throws Exception {
//        java.lang.reflect.Constructor<Base64Coder> constructor = Base64Coder.class.getDeclaredConstructor();
//        assertTrue(java.lang.reflect.Modifier.isPrivate(constructor.getModifiers()));
//        constructor.setAccessible(true);
//        assertThrows(IllegalAccessException.class, () -> constructor.newInstance());
//    }
    /**
     * Manual fix of the above test
     * Problem: the test set the constructor to be accessible, so invoking it did not cause an exception
     * Comment: The constructor is there only as a dummy anyway
     */
    @Test
    void testConstructorIsPrivate__fixed() throws Exception {
        java.lang.reflect.Constructor<Base64Coder> constructor = Base64Coder.class.getDeclaredConstructor();
        assertTrue(java.lang.reflect.Modifier.isPrivate(constructor.getModifiers()));
    }
}
