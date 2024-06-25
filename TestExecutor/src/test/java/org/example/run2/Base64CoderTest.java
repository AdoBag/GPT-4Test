
package org.example.run2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.nio.charset.StandardCharsets;

public class Base64CoderTest {

    private final String sampleText = "Hello, World!";
    private final String base64EncodedText = "SGVsbG8sIFdvcmxkIQ==";

    @Test
    public void testEncodeString() {
        String encoded = Base64Coder.encodeString(sampleText);
        assertEquals(base64EncodedText, encoded);
    }

    @Test
    public void testDecodeString() {
        String decoded = Base64Coder.decodeString(base64EncodedText);
        assertEquals(sampleText, decoded);
    }

    @Test
    public void testEncodeByteArray() {
        byte[] input = sampleText.getBytes(StandardCharsets.UTF_8);
        char[] encoded = Base64Coder.encode(input);
        assertEquals(base64EncodedText, new String(encoded));
    }

    @Test
    public void testDecodeByteArray() {
        char[] input = base64EncodedText.toCharArray();
        byte[] decoded = Base64Coder.decode(input);
        assertEquals(sampleText, new String(decoded));
    }

    @Test
    public void testEncodeByteArrayWithLength() {
        byte[] input = sampleText.getBytes(StandardCharsets.UTF_8);
        char[] encoded = Base64Coder.encode(input, input.length);
        assertEquals(base64EncodedText, new String(encoded));
    }

    @Test
    public void testDecodeWithInvalidLength() {
        String invalidBase64 = "A";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decode(invalidBase64);
        });
        assertEquals("Length of Base64 encoded input string is not a multiple of 4.", exception.getMessage());
    }

//    @Test
//    public void testDecodeWithIllegalCharacter() {
//        String invalidBase64 = "SGVsbG8s$IFdvcm$xIQ==";
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            Base64Coder.decode(invalidBase64);
//        });
//        assertEquals("Illegal character in Base64 encoded data.", exception.getMessage());
//    }

    /**
     * Manual fix of the above test
     * Problem: the string length is checked first, so the code did not even reach the exception
     */
    @Test
    public void testDecodeWithIllegalCharacter__fixed() {
        String invalidBase64 = "aaaSGVsbG8s$IFdvcm$xIQ==";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decode(invalidBase64);
        });
        assertEquals("Illegal character in Base64 encoded data.", exception.getMessage());
    }
}
