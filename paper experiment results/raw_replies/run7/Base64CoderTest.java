```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Base64CoderTest {

    @Test
    public void testEncodeString() {
        String input = "hello world";
        String expectedOutput = "aGVsbG8gd29ybGQ=";
        assertEquals(expectedOutput, Base64Coder.encodeString(input));
    }

    @Test
    public void testDecodeString() {
        String input = "aGVsbG8gd29ybGQ=";
        String expectedOutput = "hello world";
        assertEquals(expectedOutput, Base64Coder.decodeString(input));
    }

    @Test
    public void testEncodeEmptyString() {
        String input = "";
        String expectedOutput = "";
        assertEquals(expectedOutput, Base64Coder.encodeString(input));
    }

    @Test
    public void testDecodeEmptyString() {
        String input = "";
        String expectedOutput = "";
        assertEquals(expectedOutput, Base64Coder.decodeString(input));
    }

    @Test
    public void testEncodeBytes() {
        byte[] input = {104, 101, 108, 108, 111}; // "hello"
        char[] expectedOutput = {'a', 'G', 'V', 's', 'b', 'G', '8'};
        assertArrayEquals(expectedOutput, Base64Coder.encode(input));
    }

    @Test
    public void testDecodeBytes() {
        char[] input = {'a', 'G', 'V', 's', 'b', 'G', '8'};
        byte[] expectedOutput = {104, 101, 108, 108, 111}; // "hello"
        assertArrayEquals(expectedOutput, Base64Coder.decode(input));
    }

    @Test
    public void testEncodeWithPadding() {
        byte[] input = {104, 101}; // "he"
        char[] expectedOutput = {'a', 'G', 'U', '='};
        assertArrayEquals(expectedOutput, Base64Coder.encode(input, input.length));
    }

    @Test
    public void testDecodeWithPadding() {
        char[] input = {'a', 'G', 'U', '='};
        byte[] expectedOutput = {104, 101}; // "he"
        assertArrayEquals(expectedOutput, Base64Coder.decode(input));
    }

    @Test
    public void testDecodeInvalidInputLength() {
        char[] input = {'a', 'G', 'V', 's', 'b'};
        assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decode(input);
        });
    }

    @Test
    public void testDecodeIllegalCharacter() {
        char[] input = {'a', 'G', 'V', 's', '&', 'G', '8', '='};
        assertThrows(IllegalArgumentException.class, () -> {
            Base64Coder.decode(input);
        });
    }
}
```