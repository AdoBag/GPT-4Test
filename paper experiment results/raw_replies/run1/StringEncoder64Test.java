```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class StringEncoder64Test {

    @Test
    public void testEncodeStringUTF8() {
        String input = "Hello, World!";
        String expected = "SGVsbG8sIFdvcmxkIQ==";
        String result = StringEncoder64.encodeStringUTF8(input);
        assertEquals(expected, result);
    }

    @Test
    public void testDecodeStringUTF8() {
        String input = "SGVsbG8sIFdvcmxkIQ==";
        String expected = "Hello, World!";
        String result = StringEncoder64.decodeStringUTF8(input);
        assertEquals(expected, result);
    }

    @Test
    public void testEncodeBytes() {
        byte[] input = "Hello, World!".getBytes();
        String expected = "SGVsbG8sIFdvcmxkIQ==";
        String result = StringEncoder64.encode(input);
        assertEquals(expected, result);
    }

    @Test
    public void testDecode() throws UnsupportedEncodingException {
        String input = "SGVsbG8sIFdvcmxkIQ==";
        byte[] expected = "Hello, World!".getBytes("UTF-8");
        byte[] result = StringEncoder64.decode(input);
        assertArrayEquals(expected, result);
    }

    @Test
    public void testEncodeWithOutputStream() {
        byte[] input = "Hello, World!".getBytes();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        boolean success = StringEncoder64.encode(input, 0, input.length, outputStream);
        assertTrue(success);
        String expected = "SGVsbG8sIFdvcmxkIQ==";
        assertEquals(expected, new String(outputStream.toByteArray()));
    }

    @Test
    public void testEncodeWithStringBuffer() {
        byte[] input = "Hello, World!".getBytes();
        StringBuffer buffer = new StringBuffer();
        StringBuffer result = StringEncoder64.encode(input, 0, input.length, buffer);
        String expected = "SGVsbG8sIFdvcmxkIQ==";
        assertEquals(expected, result.toString());
    }

    @Test
    public void testDecodeWithOutputStream() {
        String input = "SGVsbG8sIFdvcmxkIQ==";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            StringEncoder64.decode(input, outputStream);
        } catch (IOException e) {
            fail("IOException thrown: " + e.getMessage());
        }
        String expected = "Hello, World!";
        assertEquals(expected, new String(outputStream.toByteArray()));
    }
}
```