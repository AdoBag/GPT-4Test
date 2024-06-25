```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base64Test {

    @Test
    public void testByteArrayToBase64() {
        String input = "0123456789";
        byte[] bytes = input.getBytes();
        String base64 = Base64.byteArrayToBase64(bytes);
        assertEquals("MDEyMzQ1Njc4OQ==", base64);
    }

    @Test
    public void testBase64ToByteArray() {
        String base64 = "MDEyMzQ1Njc4OQ==";
        byte[] bytes = Base64.base64ToByteArray(base64);
        String output = new String(bytes);
        assertEquals("0123456789", output);
    }

    @Test
    public void testByteArrayToAltBase64() {
        String input = "0123456789";
        byte[] bytes = input.getBytes();
        String base64Alt = Base64.byteArrayToAltBase64(bytes);
        assertEquals("@T)IV\\/E@QI", base64Alt);
    }

    @Test
    public void testAltBase64ToByteArray() {
        String base64Alt = "@T)IV\\/E@QI";
        byte[] bytes = Base64.altBase64ToByteArray(base64Alt);
        String output = new String(bytes);
        assertEquals("0123456789", output);
    }

    @Test
    public void testInvalidBase64Length() {
        String invalidBase64 = "A";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.base64ToByteArray(invalidBase64);
        });
        assertEquals("String length must be a multiple of four.", exception.getMessage());
    }

    @Test
    public void testInvalidBase64Character() {
        String invalidBase64 = "!!!!";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.base64ToByteArray(invalidBase64);
        });
        assertEquals("Illegal character !", exception.getMessage());
    }

    @Test
    public void testInvalidAltBase64Character() {
        String invalidAltBase64 = "!!!!";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.altBase64ToByteArray(invalidAltBase64);
        });
        assertEquals("Illegal character !", exception.getMessage());
    }
}
```

This test suite covers the following:

1. Basic functionality of encoding a byte array to a Base64 string.
2. Decoding a Base64 string back to its original byte array.
3. Encoding a byte array to an alternative Base64 string.
4. Decoding an alternative Base64 string back to its original byte array.
5. Handling improper Base64 input with invalid lengths.
6. Handling improper Base64 input with invalid characters.
7. Handling improper alternative Base64 input with invalid characters.