```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringEncoder64Test {

    @Test
    public void testEncodeStringUTF8() {
        String original = "Hello, World!";
        String encoded = StringEncoder64.encodeStringUTF8(original);
        assertEquals("SGVsbG8sIFdvcmxkIQ==", encoded);
    }

    @Test
    public void testDecodeStringUTF8() {
        String encoded = "SGVsbG8sIFdvcmxkIQ==";
        String decoded = StringEncoder64.decodeStringUTF8(encoded);
        assertEquals("Hello, World!", decoded);
    }

    @Test
    public void testEncodeByteArray() {
        byte[] original = {72, 101, 108, 108, 111};
        String encoded = StringEncoder64.encode(original);
        assertEquals("SGVsbG8=", encoded);
    }

    @Test
    public void testEncodeByteArrayWithStartAndLen() {
        byte[] original = {0, 0, 72, 101, 108, 108, 111, 0, 0};
        String encoded = StringEncoder64.encode(original, 2, 5);
        assertEquals("SGVsbG8=", encoded);
    }

    @Test
    public void testEncodeByteArrayWithStartAndLenAndBuffer() {
        byte[] original = {0, 0, 72, 101, 108, 108, 111, 0, 0};
        StringBuffer buf = new StringBuffer();
        StringBuffer encoded = StringEncoder64.encode(original, 2, 5, buf);
        assertEquals("SGVsbG8=", encoded.toString());
    }

    @Test
    public void testDecode() {
        String encoded = "SGVsbG8=";
        byte[] decoded = StringEncoder64.decode(encoded);
        byte[] expected = {72, 101, 108, 108, 111};
        assertArrayEquals(expected, decoded);
    }

    @Test
    public void testDecodeWithInvalidCharacter() {
        String encoded = "SGVsbG8*"; // '*' is not a valid base64 character
        Exception exception = assertThrows(RuntimeException.class, () -> {
            StringEncoder64.decode(encoded);
        });
        String expectedMessage = "unexpected code: *";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testEncodeNullOutputStream() {
        byte[] data = {72, 101, 108, 108, 111};
        Exception exception = assertThrows(NullPointerException.class, () -> {
            StringEncoder64.encode(data, 0, data.length, null);
        });
    }

    @Test
    public void testEncodeEmptyByteArray() {
        byte[] emptyData = {};
        String encoded = StringEncoder64.encode(emptyData);
        assertEquals("", encoded);
    }

    @Test
    public void testEncodeSingleByte() {
        byte[] singleByte = {72}; // 'H'
        String encoded = StringEncoder64.encode(singleByte);
        assertEquals("SA==", encoded);
    }
}
```