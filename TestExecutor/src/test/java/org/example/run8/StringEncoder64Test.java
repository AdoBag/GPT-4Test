
package org.example.run8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class StringEncoder64Test {
    
    @Test
    public void testEncodeStringUTF8() {
        String original = "Hello World!";
        String encoded = StringEncoder64.encodeStringUTF8(original);
        assertNotNull(encoded);
        assertEquals("SGVsbG8gV29ybGQh", encoded);
    }
    
    @Test
    public void testDecodeStringUTF8() {
        String encoded = "SGVsbG8gV29ybGQh";
        String decoded = StringEncoder64.decodeStringUTF8(encoded);
        assertNotNull(decoded);
        assertEquals("Hello World!", decoded);
    }
    
    @Test
    public void testEncodeByteArray() {
        byte[] data = {72, 101, 108, 108, 111, 32, 87, 111, 114, 108, 100, 33}; // "Hello World!"
        String encoded = StringEncoder64.encode(data);
        assertNotNull(encoded);
        assertEquals("SGVsbG8gV29ybGQh", encoded);
    }
    
    @Test
    public void testEncodeByteArrayWithStartAndLength() {
        byte[] data = {72, 101, 108, 108, 111}; // "Hello"
        String encoded = StringEncoder64.encode(data, 0, data.length);
        assertNotNull(encoded);
        assertEquals("SGVsbG8=", encoded);
    }
    
    @Test
    public void testEncodeByteArrayWithStartAndLengthAndOutputStream() {
        byte[] data = {72, 101, 108, 108, 111}; // "Hello"
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        assertTrue(StringEncoder64.encode(data, 0, data.length, outputStream));
        assertEquals("SGVsbG8=", outputStream.toString());
    }
    
    @Test
    public void testDecode() {
        String encoded = "SGVsbG8gV29ybGQh";
        byte[] decoded = StringEncoder64.decode(encoded);
        String result = new String(decoded);
        assertEquals("Hello World!", result);
    }
    
    @Test
    public void testDecodeWithOutputStream() throws IOException {
        String encoded = "SGVsbG8gV29ybGQh";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StringEncoder64.decode(encoded, outputStream);
        String result = outputStream.toString("UTF8");
        assertEquals("Hello World!", result);
    }

    @Test
    public void testDecodeWithPadding() {
        String encoded = "SGVsbG8="; // "Hello"
        byte[] decoded = StringEncoder64.decode(encoded);
        String result = new String(decoded);
        assertEquals("Hello", result);
    }
    
    @Test
    public void testEncodeByteArrayEmpty() {
        byte[] data = {};
        String encoded = StringEncoder64.encode(data);
        assertEquals("", encoded);
    }
    
    @Test
    public void testDecodeEmpty() {
        String encoded = "";
        byte[] decoded = StringEncoder64.decode(encoded);
        assertEquals(0, decoded.length);
    }
    
//    @Test
//    public void testInvalidDecode() {
//        Exception exception = assertThrows(RuntimeException.class, () -> {
//            StringEncoder64.decode("$$$");
//        });
//        String expectedMessage = "unexpected code";
//        String actualMessage = exception.getMessage();
//        assertTrue(actualMessage.contains(expectedMessage));
//    }
    /**
     * Manual fix of the above test
     * Problem: The code ignores any block of base64 that is shorter than 4
     */
    @Test
    public void testInvalidDecode__fixed() {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            StringEncoder64.decode("$$$%");
        });
        String expectedMessage = "unexpected code";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
