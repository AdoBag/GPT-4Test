```java
package org.example.run1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class Base64Test {

    @Test
    public void testAltBase64ToByteArray() {
        String input = "QmFzZTY0IHRlc3Q=";
        byte[] expectedOutput = "Base64 test".getBytes();
        byte[] actualOutput = Base64.altBase64ToByteArray(input);
        assertArrayEquals(expectedOutput, actualOutput, "The output byte array does not match the expected one for alt base64.");
    }

    @Test
    public void testBase64ToByteArray() {
        String input = "MDEyMzQ1Njc4OQ==";
        byte[] expectedOutput = "0123456789".getBytes();
        byte[] actualOutput = Base64.base64ToByteArray(input);
        assertArrayEquals(expectedOutput, actualOutput, "The output byte array does not match the expected one for base64.");
    }

    @Test
    public void testByteArrayToAltBase64() {
        byte[] input = "Base64 test".getBytes();
        String expectedOutput = "QmFzZTY0IHRlc3Q=";
        String actualOutput = Base64.byteArrayToAltBase64(input);
        assertEquals(expectedOutput, actualOutput, "The output base64 string does not match the expected one for alt base64.");
    }

    @Test
    public void testByteArrayToBase64() {
        byte[] input = "0123456789".getBytes();
        String expectedOutput = "MDEyMzQ1Njc4OQ==";
        String actualOutput = Base64.byteArrayToBase64(input);
        assertEquals(expectedOutput, actualOutput, "The output base64 string does not match the expected one for base64.");
    }

    @Test
    public void testAltBase64ToByteArrayWithInvalidString() {
        String input = "Invalid===";
        assertThrows(IllegalArgumentException.class, () -> {
            Base64.altBase64ToByteArray(input);
        }, "Expected to throw IllegalArgumentException for invalid alt base64 string.");
    }

    @Test
    public void testBase64ToByteArrayWithInvalidString() {
        String input = "Invalid==";
        assertThrows(IllegalArgumentException.class, () -> {
            Base64.base64ToByteArray(input);
        }, "Expected to throw IllegalArgumentException for invalid base64 string.");
    }

    @Test
    public void testByteArrayToBase64WithEmptyArray() {
        byte[] input = new byte[0];
        String expectedOutput = "";
        String actualOutput = Base64.byteArrayToBase64(input);
        assertEquals(expectedOutput, actualOutput, "The output base64 string does not match the expected one for an empty input byte array.");
    }

    @Test
    public void testByteArrayToAltBase64WithEmptyArray() {
        byte[] input = new byte[0];
        String expectedOutput = "";
        String actualOutput = Base64.byteArrayToAltBase64(input);
        assertEquals(expectedOutput, actualOutput, "The output alt base64 string does not match the expected one for an empty input byte array.");
    }
}
```