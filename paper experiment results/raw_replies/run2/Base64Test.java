```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Base64Test {

    @Test
    void testAltBase64ToByteArray() {
        String base64 = "YTEyMw=="; // "a123" in alt Base64
        byte[] expected = new byte[]{'a', '1', '2', '3'};
        assertArrayEquals(expected, Base64.altBase64ToByteArray(base64));
    }

    @Test
    void testBase64ToByteArray() {
        String base64 = "MDEyMzQ1Njc4OQ=="; // "0123456789" in standard Base64
        byte[] expected = new byte[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        assertArrayEquals(expected, Base64.base64ToByteArray(base64));
    }

    @Test
    void testByteArrayToAltBase64() {
        byte[] input = new byte[]{'a', '1', '2', '3'};
        String expected = "YTEyMw=="; // "a123" in alt Base64
        assertEquals(expected, Base64.byteArrayToAltBase64(input));
    }

    @Test
    void testByteArrayToBase64() {
        byte[] input = new byte[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String expected = "MDEyMzQ1Njc4OQ=="; // "0123456789" in standard Base64
        assertEquals(expected, Base64.byteArrayToBase64(input));
    }

    @Test
    void testInvalidBase64Length() {
        String invalidBase64 = "abc"; // Not a multiple of 4
        assertThrows(IllegalArgumentException.class, () -> Base64.base64ToByteArray(invalidBase64));
    }

    @Test
    void testInvalidBase64Character() {
        String invalidBase64 = "#####"; // invalid characters for both base64 schemes
        assertThrows(IllegalArgumentException.class, () -> Base64.base64ToByteArray(invalidBase64));
    }

    @Test
    void testByteArrayToAltBase64Padding() {
        byte[] input = new byte[]{'A', 'B'}; // "QUJC" will have a padding of one "=="
        String expected = "QUJE"; // considering the alternative characters used
        assertEquals(expected, Base64.byteArrayToAltBase64(input));
    }

    @Test
    void testByteArrayToBase64Padding() {
        byte[] input = new byte[]{'A', 'B'}; // "QUJC" will have a padding of one "=="
        String expected = "QUI="; // Standard base64 encoding
        assertEquals(expected, Base64.byteArrayToBase64(input));
    }
    
    @Test
    void testBase64EncodedDecodedEquivalence() {
        String originalString = "Hello, World!";
        byte[] encodedBytes = Base64.byteArrayToBase64(originalString.getBytes()).getBytes();
        byte[] decodedBytes = Base64.base64ToByteArray(new String(encodedBytes));
        assertArrayEquals(originalString.getBytes(), decodedBytes);
    }
    
    @Test
    void testAltBase64EncodedDecodedEquivalence() {
        String originalString = "Hello, AltBase64!";
        byte[] encodedBytes = Base64.byteArrayToAltBase64(originalString.getBytes()).getBytes();
        byte[] decodedBytes = Base64.altBase64ToByteArray(new String(encodedBytes));
        assertArrayEquals(originalString.getBytes(), decodedBytes);
    }
}
```