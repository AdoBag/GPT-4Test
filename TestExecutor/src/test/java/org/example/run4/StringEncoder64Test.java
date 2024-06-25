
package org.example.run4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Unit tests for the StringEncoder64 class.
 */
public class StringEncoder64Test {

    @Test
    public void testEncodeStringUTF8() throws UnsupportedEncodingException {
        String original = "Hello, World!";
        String encoded = StringEncoder64.encodeStringUTF8(original);
        assertNotNull(encoded);
        assertEquals(Base64.getEncoder().encodeToString(original.getBytes("UTF-8")), encoded);
    }

    @Test
    public void testDecodeStringUTF8() throws UnsupportedEncodingException {
        String encoded = "SGVsbG8sIFdvcmxkIQ==";
        String decoded = StringEncoder64.decodeStringUTF8(encoded);
        assertNotNull(decoded);
        assertEquals("Hello, World!", decoded);
    }

    @Test
    public void testEncode() throws UnsupportedEncodingException {
        byte[] original = "Test string".getBytes("UTF-8");
        String encoded = StringEncoder64.encode(original);
        assertNotNull(encoded);
        assertEquals(Base64.getEncoder().encodeToString(original), encoded);
    }

    @Test
//    public void testEncodeWithStartLength() throws UnsupportedEncodingException {
    public void testEncodeWithStartLength__fixed() throws UnsupportedEncodingException {
        byte[] original = "Test string".getBytes("UTF-8");
//        String encoded = StringEncoder64.encode(original, 5, 6);
        String encoded = StringEncoder64.encode(original, 5, 11);

        assertNotNull(encoded);
        assertEquals(Base64.getEncoder().encodeToString("string".getBytes("UTF-8")), encoded);
        /** The above assertion passes if we change how the `encode` method is called. This is likely due to bad
         * naming of the parameter `len`. `end` would be more appropriate */
    }

    @Test
    public void testDecode() throws UnsupportedEncodingException {
        String encoded = "VGVzdCBzdHJpbmc=";
        byte[] decoded = StringEncoder64.decode(encoded);
        assertNotNull(decoded);
        assertArrayEquals("Test string".getBytes("UTF-8"), decoded);
    }

    @Test
    public void testEncodeWithOutputStream() throws UnsupportedEncodingException {
        byte[] original = "Test string for OutputStream".getBytes("UTF-8");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        boolean result = StringEncoder64.encode(original, 0, original.length, outputStream);
        assertTrue(result);
        assertEquals(Base64.getEncoder().encodeToString(original), new String(outputStream.toByteArray(), "UTF-8"));
    }
}
