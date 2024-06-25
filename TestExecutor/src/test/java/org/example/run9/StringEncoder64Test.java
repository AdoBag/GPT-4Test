
package org.example.run9;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

public class StringEncoder64Test {

    @Test
    public void testEncodeStringUTF8() throws UnsupportedEncodingException {
        String original = "hello world";
        String encoded = StringEncoder64.encodeStringUTF8(original);
        String expected = Base64.getEncoder().encodeToString(original.getBytes("UTF-8"));
        assertEquals(expected, encoded);
    }

    @Test
    public void testDecodeStringUTF8() throws UnsupportedEncodingException {
        String original = "hello world";
        String encoded = Base64.getEncoder().encodeToString(original.getBytes("UTF-8"));
        String decoded = StringEncoder64.decodeStringUTF8(encoded);
        assertEquals(original, decoded);
    }

    @Test
    public void testEncodeByteArray() throws UnsupportedEncodingException {
        byte[] data = "hello world".getBytes("UTF-8");
        String encoded = StringEncoder64.encode(data);
        String expected = Base64.getEncoder().encodeToString(data);
        assertEquals(expected, encoded);
    }

    @Test
    public void testEncodeByteArrayWithStartAndLen() throws UnsupportedEncodingException {
        byte[] data = "hello world".getBytes("UTF-8");
        String encoded = StringEncoder64.encode(data, 0, data.length);
        String expected = Base64.getEncoder().encodeToString(data);
        assertEquals(expected, encoded);
    }

    @Test
    public void testEncodeByteArrayToOutputStream() throws UnsupportedEncodingException {
        byte[] data = "hello world".getBytes("UTF-8");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        boolean success = StringEncoder64.encode(data, 0, data.length, outputStream);
        assertTrue(success);
        String encoded = new String(outputStream.toByteArray());
        String expected = Base64.getEncoder().encodeToString(data);
        assertEquals(expected, encoded);
    }

    @Test
    public void testDecode() throws UnsupportedEncodingException {
        String original = "hello world";
        String encoded = Base64.getEncoder().encodeToString(original.getBytes("UTF-8"));
        byte[] decodedBytes = StringEncoder64.decode(encoded);
        String decoded = new String(decodedBytes, "UTF-8");
        assertEquals(original, decoded);
    }

    @Test
    public void testDecodeToOutputStream() throws UnsupportedEncodingException {
        String original = "hello world";
        String encoded = Base64.getEncoder().encodeToString(original.getBytes("UTF-8"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            StringEncoder64.decode(encoded, outputStream);
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
        String decoded = new String(outputStream.toByteArray(), "UTF-8");
        assertEquals(original, decoded);
    }
}
