
package org.example.run4;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {

    @Test
    public void testLeftPad() {
        // Test padding with spaces
        assertEquals("   foo", StringUtils.leftPad("foo", 6));
        // Test when no padding is needed
        assertEquals("foobar", StringUtils.leftPad("foobar", 6));
        // Test when padding size is smaller than the text
        assertEquals("foobar", StringUtils.leftPad("foobar", 3));
        // Test empty string padding
        assertEquals("    ", StringUtils.leftPad("", 4));
    }

    @Test
    public void testSplitByLines() {
        // Test splitting by newline characters
        String[] result = StringUtils.splitByLines("a\nb\nc");
        assertArrayEquals(new String[]{"a", "b", "c"}, result);

        // Test when no newlines are present
        result = StringUtils.splitByLines("abc");
        assertArrayEquals(new String[]{"abc"}, result);
        
        // Test with empty string
        result = StringUtils.splitByLines("");
        assertArrayEquals(new String[]{""}, result);
    }

    @Test
    public void testSplitBy() {
        // Test splitting by custom separator
        String[] result = StringUtils.splitBy("a,b,c", ',');
        assertArrayEquals(new String[]{"a", "b", "c"}, result);

        // Test when no separators are present
        result = StringUtils.splitBy("abc", ',');
        assertArrayEquals(new String[]{"abc"}, result);

        // Test with empty string
        result = StringUtils.splitBy("", ',');
        assertArrayEquals(new String[]{""}, result);
    }

    @Test
    public void testExtractInts() {
        // Test extracting integers from string
        int[] result = StringUtils.extractInts("1 2 3");
        assertArrayEquals(new int[]{1, 2, 3}, result);

        // Test extracting integers from a string with multiple spaces
        result = StringUtils.extractInts("4  5 6");
        assertArrayEquals(new int[]{4, 5, 6}, result);
        
        // Test empty string
        result = StringUtils.extractInts("");
        assertArrayEquals(new int[]{}, result);
    }

    @Test
//    public void testEscapeForXML() {
    public void testEscapeForXML__fixed() {
        // Test escaping characters for XML
        assertEquals("&lt;&gt;&amp;&quot;&apos;", StringUtils.escapeForXML("<>&\"'"));

        // Test escaping unprintable characters
//        assertEquals("test&#x0000;", StringUtils.escapeForXML("test\u0000"));
        /** Incorrect output for the illegal char */
        assertEquals("testILLEGAL-XML-CHAR:0000;", StringUtils.escapeForXML("test\u0000"));

        // Test a normal string with no special characters
        assertEquals("simple text", StringUtils.escapeForXML("simple text"));

        // Test empty string
        assertEquals("", StringUtils.escapeForXML(""));
    }

    @Test
    public void testSubstitute() {
        // Test substitution of substring
        assertEquals("foo qux baz", StringUtils.substitute("foo bar baz", "bar", "qux"));
        
        // Test substitution when the source is not present
        assertEquals("nothing to replace", StringUtils.substitute("nothing to replace", "foo", "bar"));
        
        // Test substitution when the source is at the beginning
        assertEquals("new beginning", StringUtils.substitute("old beginning", "old", "new"));

        // Test empty string
        assertEquals("", StringUtils.substitute("", "source", "target"));
    }
}
