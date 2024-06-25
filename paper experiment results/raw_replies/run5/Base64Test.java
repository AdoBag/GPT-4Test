```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base64Test {

    @Test
    public void testByteArrayToBase64() {
        String original = "0123456789";
        byte[] bytes = original.getBytes();
        String encoded = Base64.byteArrayToBase64(bytes);
        assertEquals("MDEyMzQ1Njc4OQ==", encoded);
    }

    @Test
    public void testBase64ToByteArray() {
        String encoded = "MDEyMzQ1Njc4OQ==";
        byte[] decodedBytes = Base64.base64ToByteArray(encoded);
        String decodedString = new String(decodedBytes);
        assertEquals("0123456789", decodedString);
    }

    @Test
    public void testByteArrayToAltBase64() {
        String original = "0123456789";
        byte[] bytes = original.getBytes();
        String encoded = Base64.byteArrayToAltBase64(bytes);
        assertEquals("$Dh3BugOyw;", encoded);
    }

    @Test
    public void testAltBase64ToByteArray() {
        String encoded = "$Dh3BugOyw;";
        byte[] decodedBytes = Base64.altBase64ToByteArray(encoded);
        String decodedString = new String(decodedBytes);
        assertEquals("0123456789", decodedString);
    }

    @Test
    public void testBase64ToByteArrayInvalidLength() {
        String invalidEncoded = "MDEyMzQ1";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.base64ToByteArray(invalidEncoded);
        });
        assertEquals("String length must be a multiple of four.", exception.getMessage());
    }

    @Test
    public void testAltBase64ToByteArrayInvalidCharacter() {
        String invalidEncoded = "$Dh3B?gOyw;";  // '?' is invalid for alt Base64
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.altBase64ToByteArray(invalidEncoded);
        });
        assertEquals("Illegal character ?", exception.getMessage());
    }
}
```