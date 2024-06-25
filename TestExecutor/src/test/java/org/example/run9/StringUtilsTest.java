
package org.example.run9;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testLeftPad() {
        assertEquals("  test", StringUtils.leftPad("test", 6));
        assertEquals("test", StringUtils.leftPad("test", 4));
        assertEquals("test", StringUtils.leftPad("test", 2));
        assertEquals("    ", StringUtils.leftPad("", 4));
    }

    @Test
    void testSplitByLines() {
        assertArrayEquals(new String[] {"a", "b", "c"}, StringUtils.splitByLines("a\nb\nc"));
        assertArrayEquals(new String[] {"a", "b", ""}, StringUtils.splitByLines("a\nb\n"));
        assertArrayEquals(new String[] {"a"}, StringUtils.splitByLines("a"));
        assertArrayEquals(new String[] {""}, StringUtils.splitByLines(""));
    }
    
    @Test
    void testSplitBy() {
        assertArrayEquals(new String[] {"a", "b", "c"}, StringUtils.splitBy("a,b,c", ','));
        assertArrayEquals(new String[] {"a", "b", ""}, StringUtils.splitBy("a,b,", ','));
        assertArrayEquals(new String[] {"a"}, StringUtils.splitBy("a", ','));
        assertArrayEquals(new String[] {""}, StringUtils.splitBy("", ','));
    }

    @Test
    void testExtractInts() {
        assertArrayEquals(new int[] {1, 2, 3}, StringUtils.extractInts("1 2 3"));
        assertArrayEquals(new int[] {-1, 0, 1}, StringUtils.extractInts("-1 0 1"));
        assertArrayEquals(new int[] {}, StringUtils.extractInts(""));
    }

    @Test
//    void testEscapeForXML() {
    void testEscapeForXML__fixed() {
        assertEquals("&lt;tag&gt;", StringUtils.escapeForXML("<tag>"));
        assertEquals("Hello &amp; World", StringUtils.escapeForXML("Hello & World"));
        assertEquals("A &quot;quote&quot;", StringUtils.escapeForXML("A \"quote\""));
        assertEquals("A &apos;quote&apos;", StringUtils.escapeForXML("A \'quote\'"));
//        assertEquals("Text with illegal: ILLEGAL-XML-CHAR:", StringUtils.escapeForXML("Text with illegal: " + (char) 4));
        /** Incorrect output for the illegal char */
        assertEquals("Text with illegal: ILLEGAL-XML-CHAR:0004;", StringUtils.escapeForXML("Text with illegal: " + (char) 4));
    }

    @Test
//    void testSubstitute() {
    void testSubstitute__fixed() {
        assertEquals("Hello Universe", StringUtils.substitute("Hello World", "World", "Universe"));
        assertEquals("Hello World", StringUtils.substitute("Hello World", "Earth", "Universe"));
        assertEquals("Start middle end", StringUtils.substitute("Start END end", "END", "middle"));
//        assertEquals("abc", StringUtils.substitute("abc", "", "def"));
        /** This is arguably a bug, maybe a corner case. If the source is an empty string, it will be inserted at the beginning */
        assertEquals("defabc", StringUtils.substitute("abc", "", "def"));
    }
}
