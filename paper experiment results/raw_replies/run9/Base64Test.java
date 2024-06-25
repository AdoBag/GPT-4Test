```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base64Test {

    @Test
    public void testByteArrayToBase64() {
        String input = "Hello World!";
        byte[] byteArray = input.getBytes();
        String base64Encoded = Base64.byteArrayToBase64(byteArray);
        assertEquals("SGVsbG8gV29ybGQh", base64Encoded);
    }

    @Test
    public void testBase64ToByteArray() {
        String base64Encoded = "SGVsbG8gV29ybGQh";
        byte[] byteArray = Base64.base64ToByteArray(base64Encoded);
        String output = new String(byteArray);
        assertEquals("Hello World!", output);
    }

    @Test
    public void testByteArrayToAltBase64() {
        String input = "Hello World!";
        byte[] byteArray = input.getBytes();
        String altBase64Encoded = Base64.byteArrayToAltBase64(byteArray);
        // Check if the alternate base64 encoding is correct
        assertEquals("q\"r$%t'v)y,a-.:<@[^_~albo", altBase64Encoded);
    }

    @Test
    public void testAltBase64ToByteArray() {
        String altBase64Encoded = "q\"r$%t'v)y,a-.:<@[^_~albo";
        byte[] byteArray = Base64.altBase64ToByteArray(altBase64Encoded);
        String output = new String(byteArray);
        assertEquals("Hello World!", output);
    }

    @Test
    public void testInvalidBase64String() {
        String invalidBase64 = "SGVsbG8gV29ybGQ!"; // '!' is an invalid character for standard Base64
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.base64ToByteArray(invalidBase64);
        });
        assertTrue(exception.getMessage().contains("Illegal character"));
    }

    @Test
    public void testEmptyStringBase64() {
        byte[] byteArray = Base64.base64ToByteArray("");
        assertArrayEquals(new byte[0], byteArray);
        
        String base64 = Base64.byteArrayToBase64(new byte[0]);
        assertEquals("", base64);
    }

    @Test
    public void testPaddingInBase64() {
        String input = "any carnal pleasure.";
        byte[] byteArray = input.getBytes();
        String base64Encoded = Base64.byteArrayToBase64(byteArray);
        // The expected padding characters when encoding this specific input
        assertEquals("YW55IGNhcm5hbCBwbGVhc3VyZS4=", base64Encoded);
    }

    @Test
    public void testNonPaddingBase64() {
        String input = "any carnal pleasure";
        byte[] byteArray = input.getBytes();
        String base64Encoded = Base64.byteArrayToBase64(byteArray);
        // The expected result without padding characters
        assertEquals("YW55IGNhcm5hbCBwbGVhc3VyZQ==", base64Encoded);
    }

    @Test
    public void testNonMultipleOfFourLengthBase64() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Base64.base64ToByteArray("SGVsbG8"); // Length not a multiple of four
        });
        assertTrue(exception.getMessage().contains("String length must be a multiple of four."));
    }

    @Test
    public void testValidCharacterAltBase64() {
        String input = "Hello!";
        String base64Encoded = Base64.byteArrayToAltBase64(input.getBytes());
        assertNotNull(base64Encoded);
    }
}
```
