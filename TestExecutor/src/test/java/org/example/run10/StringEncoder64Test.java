
package org.example.run10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class StringEncoder64Test {

    @Test
    public void testEncodeStringUTF8() {
        String input = "Test encoding";
        String expectedOutput = "VGVzdCBlbmNvZGluZw==";  // Known base64 output
        String result = StringEncoder64.encodeStringUTF8(input);
        
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testDecodeStringUTF8() {
        String input = "VGVzdCBlbmNvZGluZw==";  // Known base64 input
        String expectedOutput = "Test encoding";
        String result = StringEncoder64.decodeStringUTF8(input);
        
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testEncodeByteArray() {
        byte[] input = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        String expectedOutput = "SGVsbG8sIFdvcmxkIQ==";  // Known base64 output
        String result = StringEncoder64.encode(input);
        
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testEncodeByteArrayWithOffsets() {
        byte[] input = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        String expectedOutput = "SGVsbA==";  // Known base64 output for "Hell"
        StringBuffer buffer = new StringBuffer();
        String result = StringEncoder64.encode(input, 0, 4, buffer).toString();
        
        assertEquals(expectedOutput, result);
    }

    @Test
    public void testDecodeByteArray() {
        String input = "SGVsbG8sIFdvcmxkIQ==";  // Known base64 input
        byte[] output = StringEncoder64.decode(input);
        String expectedOutput = "Hello, World!";
        
        assertEquals(expectedOutput, new String(output, StandardCharsets.UTF_8));
    }

    @Test
    public void testEncodeWithOutputStream() throws Exception {
        byte[] input = "Hello".getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        boolean success = StringEncoder64.encode(input, 0, input.length, outputStream);
        
        assertTrue(success);
        String output = outputStream.toString(StandardCharsets.UTF_8.name());
        String expectedOutput = "SGVsbG8=";
        
        assertEquals(expectedOutput, output);
    }

    @Test
    public void testDecodeWithOutputStream() throws Exception {
        String input = "SGVsbG8sIFdvcmxkIQ==";  // Known base64 input
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        StringEncoder64.decode(input, outputStream);
        
        String expectedOutput = "Hello, World!";
        
        assertEquals(expectedOutput, new String(outputStream.toByteArray(), StandardCharsets.UTF_8));
    }

    @Test
    public void testDecodeInvalidInput_ShouldThrowException() {
        String invalidInput = "Invalid@@Input";
        
        Exception exception = assertThrows(RuntimeException.class, () -> {
            StringEncoder64.decode(invalidInput);
        });
        
        String expectedMessage = "unexpected code:";
        String actualMessage = exception.getMessage();
        
        assertTrue(actualMessage.startsWith(expectedMessage));
    }
}
