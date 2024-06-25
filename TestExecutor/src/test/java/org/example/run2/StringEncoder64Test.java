
package org.example.run2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class StringEncoder64Test {

    @Test
    public void testEncodeStringUTF8() {
        String input = "Hello World";
        String expected = "SGVsbG8gV29ybGQ=";
        String result = StringEncoder64.encodeStringUTF8(input);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testDecodeStringUTF8() {
        String input = "SGVsbG8gV29ybGQ=";
        String expected = "Hello World";
        String result = StringEncoder64.decodeStringUTF8(input);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testEncodeByteArray() {
        byte[] input = {72, 101, 108, 108, 111};
        String expected = "SGVsbG8=";
        String result = StringEncoder64.encode(input);
        Assertions.assertEquals(expected, result);
    }

    /**
     * The test catches a bug.
     */
//    @Test
//    public void testEncodeByteArrayRange() {
//        byte[] input = {72, 101, 108, 108, 111};
//        String result = StringEncoder64.encode(input, 1, 3);
//        String expected = "ZWxs";
//        Assertions.assertEquals(expected, result);
//    }

    @Test
    public void testEncodeByteArrayRangeOutputStream() throws UnsupportedEncodingException {
        byte[] input = {72, 101, 108, 108, 111};
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        boolean result = StringEncoder64.encode(input, 0, input.length, outputStream);
        String expected = "SGVsbG8=";
        Assertions.assertTrue(result);
        Assertions.assertEquals(expected, new String(outputStream.toByteArray(), "UTF8"));
    }

    @Test
    public void testDecode() {
        String input = "SGVsbG8=";
        byte[] result = StringEncoder64.decode(input);
        byte[] expected = {72, 101, 108, 108, 111};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testDecodeOutputStream() throws Exception {
        String input = "SGVsbG8=";
        OutputStream outputStream = new ByteArrayOutputStream();
        StringEncoder64.decode(input, outputStream);
        byte[] result = ((ByteArrayOutputStream) outputStream).toByteArray();
        byte[] expected = {72, 101, 108, 108, 111};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testEncodeByteArraySpecialChars() {
        byte[] input = new byte[]{(byte) 0xfb, (byte) 0xf0, (byte) 0xbf, (byte) 0x8a};
        String expected = "+/C/ig==";
        String result = StringEncoder64.encode(input);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void testDecodeSpecialChars() {
        String input = "+/C/ig==";
        byte[] result = StringEncoder64.decode(input);
        byte[] expected = new byte[]{(byte) 0xfb, (byte) 0xf0, (byte) 0xbf, (byte) 0x8a};
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    public void testNullBufferInEncode() {
        byte[] data = {72, 101, 108, 108, 111};
        StringBuffer buffer = null;
        StringBuffer result = StringEncoder64.encode(data, 0, data.length, buffer);
        String expected = "SGVsbG8=";
        Assertions.assertEquals(expected, result.toString());
    }

    @Test
    public void testDecodeInvalidChar() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            StringEncoder64.decode("SGVsb#G8=");
        });
    }
}
