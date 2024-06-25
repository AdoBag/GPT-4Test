
package org.example.run5;

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

//    @Test
//    public void testByteArrayToAltBase64() {
//        String original = "0123456789";
//        byte[] bytes = original.getBytes();
//        String encoded = Base64.byteArrayToAltBase64(bytes);
//        assertEquals("$Dh3BugOyw;", encoded);
//    }

    /**
     * Manual fix of the above test
     * Problem: The LLM failed to work with the alternate scheme
     */
    @Test
    public void testByteArrayToAltBase64__fixed() {
        String original = "0123456789";
        byte[] bytes = original.getBytes();
        String encoded = Base64.byteArrayToAltBase64(bytes);
        assertEquals(":$%y:z@1;jc4<@==", encoded);
    }

//    @Test
//    public void testAltBase64ToByteArray() {
//        String encoded = "$Dh3BugOyw;";
//        byte[] decodedBytes = Base64.altBase64ToByteArray(encoded);
//        String decodedString = new String(decodedBytes);
//        assertEquals("0123456789", decodedString);
//    }

    /**
     * Manual fix of the above test
     * Problem: Input was not a multiple of 4 and it was incorrect
     */
    @Test
    public void testAltBase64ToByteArray__fixed() {
        String encoded = ":$%y:z@1;jc4<@==";
        byte[] decodedBytes = Base64.altBase64ToByteArray(encoded);
        String decodedString = new String(decodedBytes);
        assertEquals("0123456789", decodedString);
    }

//    @Test
//    public void testBase64ToByteArrayInvalidLength() {
//        String invalidEncoded = "MDEyMzQ1";
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            Base64.base64ToByteArray(invalidEncoded);
//        });
//        assertEquals("String length must be a multiple of four.", exception.getMessage());
//    }

    /**
     * Manual fix of the above test
     * Problem: The input was a valid
     */
    @Test
    public void testBase64ToByteArrayInvalidLength__fixed() {
        String invalidEncoded = "MDEyMzQ";
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.base64ToByteArray(invalidEncoded);
        });
        assertEquals("String length must be a multiple of four.", exception.getMessage());
    }

//    @Test
//    public void testAltBase64ToByteArrayInvalidCharacter() {
//        String invalidEncoded = "$Dh3B?gOyw;";  // '?' is invalid for alt Base64
//        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
//            Base64.altBase64ToByteArray(invalidEncoded);
//        });
//        assertEquals("Illegal character ?", exception.getMessage());
//    }

    /**
     * Manual fix of the above test
     * Problem: The LLM failed to work with the alternate scheme. And the input was not a multiple of 4
     */
    @Test
    public void testAltBase64ToByteArrayInvalidCharacter__fixed() {
        String invalidEncoded = "$Dh3b?gOyw;;";  // 'D' is invalid for alt Base64
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.altBase64ToByteArray(invalidEncoded);
        });
        assertEquals("Illegal character D", exception.getMessage());
    }
}
