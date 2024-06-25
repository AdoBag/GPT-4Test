
package org.example.run6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

class StringEncoder64Test {

    @Test
    void testEncodeStringUTF8() throws UnsupportedEncodingException {
        String input = "Hello World!";
        String expected = "SGVsbG8gV29ybGQh";
        String actual = StringEncoder64.encodeStringUTF8(input);
        assertEquals(expected, actual);
    }

    @Test
    void testDecodeStringUTF8() throws UnsupportedEncodingException {
        String input = "SGVsbG8gV29ybGQh";
        String expected = "Hello World!";
        String actual = StringEncoder64.decodeStringUTF8(input);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithDataLength() {
        byte[] input = {72, 101, 108, 108, 111}; // "Hello" in bytes
        String expected = "SGVsbG8=";
        String actual = StringEncoder64.encode(input, 0, input.length);
        assertEquals(expected, actual);
    }

    @Test
    void testEncodeWithOutputStream() {
        byte[] input = {72, 101, 108, 108, 111}; // "Hello" in bytes
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        boolean success = StringEncoder64.encode(input, 0, input.length, outputStream);
        String expected = "SGVsbG8=";
        assertTrue(success);
        assertEquals(expected, new String(outputStream.toByteArray()));
    }

    @Test
    void testDecode() {
        String input = "SGVsbG8gV29ybGQh";
        byte[] expected = "Hello World!".getBytes();
        byte[] actual = StringEncoder64.decode(input);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testDecodeWithOutputStream() throws IOException {
        String input = "SGVsbG8gV29ybGQh";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StringEncoder64.decode(input, outputStream);
        byte[] expected = "Hello World!".getBytes();
        byte[] actual = outputStream.toByteArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    void testDecodeSingleCharacter() {
        assertEquals(0, StringEncoder64.decode('A'));
        assertEquals(26, StringEncoder64.decode('a'));
        assertEquals(52, StringEncoder64.decode('0'));
        assertEquals(62, StringEncoder64.decode('+'));
        assertEquals(63, StringEncoder64.decode('/'));
        assertEquals(0, StringEncoder64.decode('='));
    }

    @Test
    void testEncodeEdgeCases() {
        byte[] emptyInput = {};
        assertEquals("", StringEncoder64.encode(emptyInput));
        
        byte[] oneCharInput = {72};
        assertEquals("SA==", StringEncoder64.encode(oneCharInput));
        
        byte[] twoCharInput = {72, 101};
        assertEquals("SGU=", StringEncoder64.encode(twoCharInput));
    }
}
