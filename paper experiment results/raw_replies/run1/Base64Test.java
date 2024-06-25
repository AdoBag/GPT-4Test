```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base64Test {

    @Test
    public void testBase64EncodingDecoding() {
        String original = "0123456789";
        byte[] originalBytes = original.getBytes();

        String encoded = Base64.byteArrayToBase64(originalBytes);
        byte[] decodedBytes = Base64.base64ToByteArray(encoded);

        assertEquals(original, new String(decodedBytes));
    }

    @Test
    public void testAltBase64EncodingDecoding() {
        String original = "hello world";
        byte[] originalBytes = original.getBytes();

        String encoded = Base64.byteArrayToAltBase64(originalBytes);
        byte[] decodedBytes = Base64.altBase64ToByteArray(encoded);

        assertEquals(original, new String(decodedBytes));
    }

    @Test
    public void testEmptyStringEncoding() {
        String original = "";
        byte[] originalBytes = original.getBytes();

        String encoded = Base64.byteArrayToBase64(originalBytes);
        byte[] decodedBytes = Base64.base64ToByteArray(encoded);

        assertEquals(original, new String(decodedBytes));
    }

    @Test
    public void testSpecialCharactersEncoding() {
        String original = "!@#$%^&*()_+-={}[]|:;<>,.?/~`";
        byte[] originalBytes = original.getBytes();

        String encoded = Base64.byteArrayToBase64(originalBytes);
        byte[] decodedBytes = Base64.base64ToByteArray(encoded);

        assertEquals(original, new String(decodedBytes));
    }

    @Test
    public void testIllegalCharacterDecoding() {
        String invalidBase64 = "abcd$%==";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.base64ToByteArray(invalidBase64);
        });
        assertTrue(exception.getMessage().contains("Illegal character"));
    }

    @Test
    public void testStringLengthNotMultipleOfFour() {
        String invalidBase64 = "abcde";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.base64ToByteArray(invalidBase64);
        });
        assertTrue(exception.getMessage().contains("String length must be a multiple of four."));
    }
}
```