```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base64CoderTest {

    @Test
    public void testEncodeString() {
        String input = "Hello World!";
        String expected = "SGVsbG8gV29ybGQh";
        String actual = Base64Coder.encodeString(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testDecodeString() {
        String input = "SGVsbG8gV29ybGQh";
        String expected = "Hello World!";
        String actual = Base64Coder.decodeString(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEncode() {
        byte[] input = "Hello World!".getBytes();
        char[] expected = "SGVsbG8gV29ybGQh".toCharArray();
        char[] actual = Base64Coder.encode(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testDecode() {
        String encoded = "SGVsbG8gV29ybGQh";
        byte[] expected = "Hello World!".getBytes();
        byte[] actual = Base64Coder.decode(encoded);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testEncode_withPadding() {
        byte[] input = "Test".getBytes();
        char[] expected = "VGVzdA==".toCharArray();
        char[] actual = Base64Coder.encode(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testDecode_withPadding() {
        String input = "VGVzdA==";
        byte[] expected = "Test".getBytes();
        byte[] actual = Base64Coder.decode(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void testDecode_invalidBase64_throwsException() {
        String invalidBase64 = "SGVsbG8gV29ybGQ!"; // contains invalid character '!'
        assertThrows(IllegalArgumentException.class, () -> Base64Coder.decode(invalidBase64));
    }

    @Test
    public void testDecode_incorrectLength_throwsException() {
        String invalidBase64 = "SGVsbG8gV29ybGQ"; // length not multiple of 4
        assertThrows(IllegalArgumentException.class, () -> Base64Coder.decode(invalidBase64));
    }

    @Test
    public void testEmptyStringEncoding() {
        String input = "";
        String expected = "";
        String actual = Base64Coder.encodeString(input);
        assertEquals(expected, actual);
    }

    @Test
    public void testEmptyStringDecoding() {
        String input = "";
        String expected = "";
        String actual = Base64Coder.decodeString(input);
        assertEquals(expected, actual);
    }
}
```