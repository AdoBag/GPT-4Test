```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Base64Test {

    @Test
    void testAltBase64ToByteArray_validInput() {
        String base64String = "IQ=="; // "!" => iq==
        byte[] expectedBytes = new byte[]{33};
        byte[] actualBytes = Base64.altBase64ToByteArray(base64String);
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    void testAltBase64ToByteArray_invalidLength() {
        String base64String = "IQA"; // Length not multiple of 4
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Base64.altBase64ToByteArray(base64String),
            "Expected altBase64ToByteArray() to throw, but it didn't"
        );
        assertEquals("String length must be a multiple of four.", thrown.getMessage());
    }

    @Test
    void testAltBase64ToByteArray_invalidChar() {
        String base64String = "IQ@="; // '@' not valid
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Base64.altBase64ToByteArray(base64String),
            "Expected altBase64ToByteArray() to throw, but it didn't"
        );
        assertEquals("Illegal character @", thrown.getMessage());
    }

    @Test
    void testBase64ToByteArray_validInput() {
        String base64String = "MTIzNDU="; // "12345" => "MTIzNDU="
        byte[] expectedBytes = new byte[]{49, 50, 51, 52, 53};
        byte[] actualBytes = Base64.base64ToByteArray(base64String);
        assertArrayEquals(expectedBytes, actualBytes);
    }

    @Test
    void testBase64ToByteArray_invalidLength() {
        String base64String = "MTIzNDU"; // Length not multiple of 4
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Base64.base64ToByteArray(base64String),
            "Expected base64ToByteArray() to throw, but it didn't"
        );
        assertEquals("String length must be a multiple of four.", thrown.getMessage());
    }

    @Test
    void testBase64ToByteArray_invalidChar() {
        String base64String = "MTIz@DU="; // '@' not valid
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> Base64.base64ToByteArray(base64String),
            "Expected base64ToByteArray() to throw, but it didn't"
        );
        assertEquals("Illegal character @", thrown.getMessage());
    }

    @Test
    void testByteArrayToAltBase64_basic() {
        byte[] byteArray = new byte[]{33}; // '!' => IQ==
        String expectedBase64 = "IQ==";
        String actualBase64 = Base64.byteArrayToAltBase64(byteArray);
        assertEquals(expectedBase64, actualBase64);
    }

    @Test
    void testByteArrayToBase64_basic() {
        byte[] byteArray = new byte[]{49, 50, 51, 52, 53}; // "12345" => MTIzNDU=
        String expectedBase64 = "MTIzNDU=";
        String actualBase64 = Base64.byteArrayToBase64(byteArray);
        assertEquals(expectedBase64, actualBase64);
    }

    @Test
    void testMain() {
        String[] args = {};
        Base64.main(args);
        // Main prints to system out, check manually or mock System.out if automation is needed
    }
}
```