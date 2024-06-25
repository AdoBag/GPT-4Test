```java
package org.example.run1;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class StringEncoder64Test {

    @Test
    void testEncodeStringUTF8() {
        String input = "Hello, World!";
        String expectedOutput = "SGVsbG8sIFdvcmxkIQ==";
        assertEquals(expectedOutput, StringEncoder64.encodeStringUTF8(input));
    }

    @Test
    void testDecodeStringUTF8() {
        String input = "SGVsbG8sIFdvcmxkIQ==";
        String expectedOutput = "Hello, World!";
        assertEquals(expectedOutput, StringEncoder64.decodeStringUTF8(input));
    }

    @Test
    void testEncodeByteArray() {
        byte[] input = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        String expectedOutput = "SGVsbG8sIFdvcmxkIQ==";
        assertEquals(expectedOutput, StringEncoder64.encode(input));
    }

    @Test
    void testEncodeByteArrayWithOffsets() {
        byte[] input = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        String expectedOutput = "SGVsbG8sIFdvcmxkIQ==";
        assertEquals(expectedOutput, StringEncoder64.encode(input, 0, input.length));
    }

    @Test
    void testEncodeByteArrayWithBuffer() {
        byte[] input = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        StringBuffer buffer = new StringBuffer();
        String expectedOutput = "SGVsbG8sIFdvcmxkIQ==";
        assertEquals(expectedOutput, StringEncoder64.encode(input, 0, input.length, buffer).toString());
    }

    @Test
    void testEncodeByteArrayWithOutputStream() throws IOException {
        byte[] input = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String expectedOutput = "SGVsbG8sIFdvcmxkIQ==";

        assertTrue(StringEncoder64.encode(input, 0, input.length, outputStream));
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    void testDecode() {
        String input = "SGVsbG8sIFdvcmxkIQ==";
        byte[] expectedOutput = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        assertArrayEquals(expectedOutput, StringEncoder64.decode(input));
    }

    @Test
    void testDecodeWithOutputStream() throws IOException {
        String input = "SGVsbG8sIFdvcmxkIQ==";
        byte[] expectedOutput = "Hello, World!".getBytes(StandardCharsets.UTF_8);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        StringEncoder64.decode(input, outputStream);
        assertArrayEquals(expectedOutput, outputStream.toByteArray());
    }

    @Test
    void testUnsupportedEncodingExceptionEncode() {
        String input = "Hello, World!";
        try {
            assertNull(StringEncoder64.encodeStringUTF8(input));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testUnsupportedEncodingExceptionDecode() {
        String input = "SGVsbG8sIFdvcmxkIQ==";
        try {
            assertNull(StringEncoder64.decodeStringUTF8(input));
        } catch (Exception e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    void testDecodeIllegalCharacter() {
        String input = "SGVsbG8sIFdvcmxkIQ=#";
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            StringEncoder64.decode(input);
        });
        assertEquals("unexpected code: #", exception.getMessage());
    }
}
```