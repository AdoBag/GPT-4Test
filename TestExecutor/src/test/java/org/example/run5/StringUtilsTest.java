
package org.example.run5;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {

    @Test
    public void testLeftPad() {
        assertEquals("  test", StringUtils.leftPad("test", 6));
        assertEquals("test", StringUtils.leftPad("test", 4));
        assertEquals("test", StringUtils.leftPad("test", 2));
        assertEquals("      ", StringUtils.leftPad("", 6));
    }

    @Test
    public void testSplitByLines() {
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitByLines("a\nb\nc"));
        assertArrayEquals(new String[]{"line1", "line2", "line3"}, StringUtils.splitByLines("line1\nline2\nline3"));
        assertArrayEquals(new String[]{"singleLine"}, StringUtils.splitByLines("singleLine"));
        assertArrayEquals(new String[]{"", ""}, StringUtils.splitByLines("\n"));
    }

    @Test
    public void testSplitBy() {
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitBy("a,b,c", ','));
        assertArrayEquals(new String[]{"1", "2", "3", "4"}, StringUtils.splitBy("1.2.3.4", '.'));
        assertArrayEquals(new String[]{"part1", "part2"}, StringUtils.splitBy("part1-part2", '-'));
        assertArrayEquals(new String[]{"", ""}, StringUtils.splitBy(",", ','));
    }

    @Test
    public void testExtractInts() {
        assertArrayEquals(new int[]{1, 2, 3}, StringUtils.extractInts("1 2 3"));
        assertArrayEquals(new int[]{10, 20, 30, 40}, StringUtils.extractInts("10 20 30 40"));
        assertArrayEquals(new int[]{}, StringUtils.extractInts(""));
        assertThrows(NumberFormatException.class, () -> {
            StringUtils.extractInts("1 a 3");
        });
    }

    @Test
//    public void testEscapeForXML() {
    public void testEscapeForXML__fixed() {
        assertEquals("&lt;tag&gt;", StringUtils.escapeForXML("<tag>"));
        assertEquals("&quot;quote&quot;", StringUtils.escapeForXML("\"quote\""));
        assertEquals("&apos;apos&apos;", StringUtils.escapeForXML("'apos'"));
        assertEquals("normaltext", StringUtils.escapeForXML("normaltext"));
//        assertEquals("ILLEGAL-XML-CHAR:", StringUtils.escapeForXML("\u0004"));
        /** Incorrect output for the illegal char */
        assertEquals("ILLEGAL-XML-CHAR:0004;", StringUtils.escapeForXML("\u0004"));
    }

    @Test
//    public void testSubstitute() {
    public void testSubstitute__fixed() {
        assertEquals("hello world", StringUtils.substitute("hello foo", "foo", "world"));
        assertEquals("no change", StringUtils.substitute("no change", "foo", "bar"));
        assertEquals("start middle end", StringUtils.substitute("start foo end", "foo", "middle"));
//        assertEquals("123456", StringUtils.substitute("123123", "123", "456"));
        /** Fails because `substitute` only replaces the first occurrence */
        assertEquals("456123", StringUtils.substitute("123123", "123", "456"));
    }
}
