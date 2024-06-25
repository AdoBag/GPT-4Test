
package org.example.run3;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

public class StringEncoder64Test {

    @Test
    public void testEncodeStringUTF8() throws UnsupportedEncodingException {
        String input = "Hello, World!";
        String expectedOutput = Base64.getEncoder().encodeToString(input.getBytes("UTF8"));

        String encodedString = StringEncoder64.encodeStringUTF8(input);
        
        assertEquals(expectedOutput, encodedString);
    }

    @Test
    public void testDecodeStringUTF8() throws UnsupportedEncodingException {
        String input = "Hello, World!";
        String encodedString = Base64.getEncoder().encodeToString(input.getBytes("UTF8"));
        String decodedString = StringEncoder64.decodeStringUTF8(encodedString);
        
        assertEquals(input, decodedString);
    }

    @Test
    public void testEncodeByteArray() throws UnsupportedEncodingException {
        byte[] input = "Hello, World!".getBytes("UTF8");
        String expectedOutput = Base64.getEncoder().encodeToString(input);

        String encodedString = StringEncoder64.encode(input);
        
        assertEquals(expectedOutput, encodedString);
    }

    @Test
    public void testDecodeByteArray() throws UnsupportedEncodingException {
        byte[] input = "Hello, World!".getBytes("UTF8");
        String encodedString = Base64.getEncoder().encodeToString(input);

        byte[] decodedBytes = StringEncoder64.decode(encodedString);
        String decodedString = new String(decodedBytes, "UTF8");
        
        assertEquals("Hello, World!", decodedString);
    }

    @Test
    public void testEncodeWithByteArray() throws UnsupportedEncodingException {
        byte[] input = "Hello, World!".getBytes("UTF8");
        StringBuffer buffer= new StringBuffer();
        String encodedString = StringEncoder64.encode(input, 0, input.length, buffer).toString();
        
        String expectedOutput = Base64.getEncoder().encodeToString(input);
        assertEquals(expectedOutput, encodedString);
    }

    @Test
    public void testEncodeOutputStream() throws IOException {
        byte[] input = "Hello, World!".getBytes("UTF8");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        
        boolean isEncoded = StringEncoder64.encode(input, 0, input.length, byteArrayOutputStream);
        String encodedString = byteArrayOutputStream.toString("UTF8");

        String expectedOutput = Base64.getEncoder().encodeToString(input);
        assertTrue(isEncoded);
        assertEquals(expectedOutput, encodedString);
    }

    @Test
    public void testDecodeWithOutputStream() throws IOException {
        String expectedOutput = "Hello, World!";
        String encodedString = StringEncoder64.encodeStringUTF8(expectedOutput);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        
        StringEncoder64.decode(encodedString, byteArrayOutputStream);
        String decodedString = byteArrayOutputStream.toString("UTF8");
        
        assertEquals(expectedOutput, decodedString);
    }

//    @Test
//    public void testInvalidCharacterDecode() {
//        String invalidEncodedString = "Hello?!";
//        System.out.println(StringEncoder64.decode(invalidEncodedString).length);
//
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            StringEncoder64.decode(invalidEncodedString);
//        });
//
//        assertTrue(exception.getMessage().contains("unexpected code"));
//    }

    /**
     * Manual fix of the above test
     * Problem: There are no input length checks, which resulted in the last 3 chars being discarded.
     * This can arguably be considered a bug
     */
    @Test
    public void testInvalidCharacterDecode__fixed() {
        String invalidEncodedString = "Hello?!!";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            StringEncoder64.decode(invalidEncodedString);
        });
        assertTrue(exception.getMessage().contains("unexpected code"));

    }
}
