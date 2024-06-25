
package org.example.run10;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class Base64Test {

//    @Test
//    public void testAltBase64ToByteArray() {
//        String base64String = "YWJj";
//        byte[] expectedBytes = new byte[]{97, 98, 99}; // "abc" in bytes
//        byte[] resultBytes = Base64.altBase64ToByteArray(base64String);
//        assertArrayEquals(expectedBytes, resultBytes);
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM failed to work with the alternate scheme
     */
    @Test
    public void testAltBase64ToByteArray__fixed() {
        String base64String = "}{,j";
        byte[] expectedBytes = new byte[]{97, 98, 99}; // "abc" in bytes
        byte[] resultBytes = Base64.altBase64ToByteArray(base64String);
        assertArrayEquals(expectedBytes, resultBytes);
    }

    @Test
    public void testBase64ToByteArray() {
        String base64String = "MTIz";
        byte[] expectedBytes = new byte[]{49, 50, 51}; // "123" in bytes
        byte[] resultBytes = Base64.base64ToByteArray(base64String);
        assertArrayEquals(expectedBytes, resultBytes);
    }

//    @Test
//    public void testByteArrayToAltBase64() {
//        byte[] byteArray = new byte[]{101, 102, 103}; // "efg" in bytes
//        String expectedBase64String = "ZWZn";
//        String resultBase64String = Base64.byteArrayToAltBase64(byteArray);
//        assertEquals(expectedBase64String, resultBase64String);
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM failed to work with the alternate scheme
     */
    @Test
    public void testByteArrayToAltBase64__fixed() {
        byte[] byteArray = new byte[]{101, 102, 103}; // "efg" in bytes
        String expectedBase64String = "~{~n";
        String resultBase64String = Base64.byteArrayToAltBase64(byteArray);
        assertEquals(expectedBase64String, resultBase64String);
    }

    @Test
    public void testByteArrayToBase64() {
        byte[] byteArray = new byte[]{52, 53, 54}; // "456" in bytes
        String expectedBase64String = "NDU2";
        String resultBase64String = Base64.byteArrayToBase64(byteArray);
        assertEquals(expectedBase64String, resultBase64String);
    }

//    @Test
//    public void testMain() {
//        // Method main's behavior is to print out the base64 encode-decode of "0123456789"
//        // We can capture System.out and assert the outputs
//        String[] args = null;
//        PrintStream originalOut = System.out;
//        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outContent));
//
//        Base64.main(args);
//
//        String expectedOutput = "MDEyMzQ1Njc4OQ==\n0123456789\n";
//        assertEquals(expectedOutput, outContent.toString());
//
//        System.setOut(originalOut); // Restore original System.out
//    }
    /**
     * Manual fix of the above test
     * Problem: Windows uses different line separators. This would've passed on Unix-based OS
     */
    @Test
    public void testMain__fixed() {
        // Method main's behavior is to print out the base64 encode-decode of "0123456789"
        // We can capture System.out and assert the outputs
        String[] args = null;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Base64.main(args);
        String separator = System.getProperty("line.separator"); // Different on different systems
        String expectedOutput = "MDEyMzQ1Njc4OQ==" + separator + "0123456789" + separator;
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(originalOut); // Restore original System.out
    }

    @Test
    void testInvalidBase64Input() {
        String invalidBase64String = "InvalidString==";
        
        assertThrows(IllegalArgumentException.class, () -> {
            Base64.base64ToByteArray(invalidBase64String);
        });
    }
    
    @Test
    void testInvalidAltBase64Input() {
        String invalidAltBase64String = "InvalidString==";
        
        assertThrows(IllegalArgumentException.class, () -> {
            Base64.altBase64ToByteArray(invalidAltBase64String);
        });
    }
}
