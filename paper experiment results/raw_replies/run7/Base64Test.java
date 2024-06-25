```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Base64Test {

    @Test
    void testAltBase64ToByteArrayWithValidInput() {
        String base64Str = "!\"#$%&'(),-.:;<>";
        byte[] expectedOutput = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        assertArrayEquals(expectedOutput, Base64.altBase64ToByteArray(base64Str));
    }

    @Test
    void testAltBase64ToByteArrayWithInvalidLength() {
        String base64Str = "!\"#$%";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.altBase64ToByteArray(base64Str);
        });
        assertEquals("String length must be a multiple of four.", exception.getMessage());
    }

    @Test
    void testAltBase64ToByteArrayWithIllegalCharacter() {
        String base64Str = "!\"#$%&'(),-.:;<>";
        base64Str += '$'; // Illegal character in altBase64

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.altBase64ToByteArray(base64Str);
        });
        assertTrue(exception.getMessage().startsWith("Illegal character"));
    }

    @Test
    void testBase64ToByteArrayWithValidInput() {
        String base64Str = "MDEyMzQ1Njc4OQ==";
        byte[] expectedOutput = "0123456789".getBytes();
        assertArrayEquals(expectedOutput, Base64.base64ToByteArray(base64Str));
    }

    @Test
    void testBase64ToByteArrayWithInvalidLength() {
        String base64Str = "MDEyMzQ1";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.base64ToByteArray(base64Str);
        });
        assertEquals("String length must be a multiple of four.", exception.getMessage());
    }

    @Test
    void testBase64ToByteArrayWithIllegalCharacter() {
        String base64Str = "MDEyMzQ1Njc8='";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.base64ToByteArray(base64Str);
        });
        assertTrue(exception.getMessage().startsWith("Illegal character"));
    }

    @Test
    void testByteArrayToAltBase64() {
        byte[] byteArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        String expectedOutput = "!\"#$%&'(),-.:;<>";
        assertEquals(expectedOutput, Base64.byteArrayToAltBase64(byteArray));
    }

    @Test
    void testByteArrayToBase64() {
        byte[] byteArray = "0123456789".getBytes();
        String expectedOutput = "MDEyMzQ1Njc4OQ==";
        assertEquals(expectedOutput, Base64.byteArrayToBase64(byteArray));
    }

    @Test
    void testInverseOperationsWithBase64() {
        String originalString = "0123456789";
        byte[] byteArray = originalString.getBytes();
        String base64String = Base64.byteArrayToBase64(byteArray);
        byte[] resultArray = Base64.base64ToByteArray(base64String);
        assertArrayEquals(byteArray, resultArray);
        assertEquals(originalString, new String(resultArray));
    }

    @Test
    void testInverseOperationsWithAltBase64() {
        byte[] byteArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        String altBase64String = Base64.byteArrayToAltBase64(byteArray);
        byte[] resultArray = Base64.altBase64ToByteArray(altBase64String);
        assertArrayEquals(byteArray, resultArray);
    }
}
```