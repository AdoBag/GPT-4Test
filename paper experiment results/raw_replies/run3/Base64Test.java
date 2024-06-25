```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Base64Test {

    @Test
    void testAltBase64ToByteArray() {
        String altBase64 = "!\"#$%&'()*,-.:;<>";
        byte[] expectedBytes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 62, 9, 10, 11, 12, 13, 14};
        assertArrayEquals(expectedBytes, Base64.altBase64ToByteArray(altBase64));
    }

    @Test
    void testBase64ToByteArray() {
        String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        byte[] expectedBytes = {
            0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
            32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47,
            48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63
        };
       assertArrayEquals(expectedBytes, Base64.base64ToByteArray(base64));
    }

    @Test
    void testByteArrayToAltBase64() {
        byte[] byteArray = {0, 1, 2, 3, 4, 5, 6};
        String expectedAltBase64 = "!\"#$%&'";
        assertEquals(expectedAltBase64, Base64.byteArrayToAltBase64(byteArray));
    }

    @Test
    void testByteArrayToBase64() {
        byte[] byteArray = {0, 1, 2, 3, 4, 5, 6};
        String expectedBase64 = "AAECAwQFBg==";
        assertEquals(expectedBase64, Base64.byteArrayToBase64(byteArray));
    }

    @Test
    void testMain() {
        String[] args = {};
        Base64.main(args);
    }

    @Test
    void testInvalidStringLength() {
        String invalidLength = "ABCDEFG";
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Base64.base64ToByteArray(invalidLength),
            "Expected to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("String length must be a multiple of four."));
    }

    @Test
    void testIllegalCharacterBase64() {
        String invalidChar = "A@CD";
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Base64.base64ToByteArray(invalidChar),
            "Expected to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Illegal character @"));
    }

    @Test
    void testIllegalCharacterAltBase64() {
        String invalidChar = "!@#%";
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Base64.altBase64ToByteArray(invalidChar),
            "Expected to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Illegal character @"));
    }
}
```