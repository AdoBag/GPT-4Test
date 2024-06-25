
package org.example.run8;

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

//    @Test
//    public void testByteArrayToAltBase64() {
//        String input = "0123456789";
//        byte[] bytes = input.getBytes();
//        String base64Alt = Base64.byteArrayToAltBase64(bytes);
//        assertEquals("@T)IV\\/E@QI", base64Alt);
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM failed to work with the alternate scheme
     */
    @Test
    public void testByteArrayToAltBase64__fixed() {
        String input = "0123456789";
        byte[] bytes = input.getBytes();
        String base64Alt = Base64.byteArrayToAltBase64(bytes);
        assertEquals(":$%y:z@1;jc4<@==", base64Alt);
    }


//    @Test
//    public void testAltBase64ToByteArray() {
//        String base64Alt = "@T)IV\\/E@QI";
//        byte[] bytes = Base64.altBase64ToByteArray(base64Alt);
//        String output = new String(bytes);
//        assertEquals("0123456789", output);
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM failed to work with the alternate scheme
     */
    @Test
    public void testAltBase64ToByteArray__fixed() {
        String base64Alt = ":$%y:z@1;jc4<@==";
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

//    @Test
//    public void testInvalidAltBase64Character() {
//        String invalidAltBase64 = "!!!!";
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            Base64.altBase64ToByteArray(invalidAltBase64);
//        });
//        assertEquals("Illegal character !", exception.getMessage());
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM failed to work with the alternate scheme
     */
    @Test
    public void testInvalidAltBase64Character__fixed() {
        String invalidAltBase64 = "AAAA";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.altBase64ToByteArray(invalidAltBase64);
        });
        assertEquals("Illegal character A", exception.getMessage());
    }
}
