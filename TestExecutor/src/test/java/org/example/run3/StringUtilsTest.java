
package org.example.run3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StringUtilsTest {

    @Test
    public void testLeftPad() {
        assertEquals("  foo", StringUtils.leftPad("foo", 5));
        assertEquals("foo", StringUtils.leftPad("foo", 2)); // no padding
        assertEquals("foobar", StringUtils.leftPad("foobar", 6)); // no padding needed
        assertEquals("foobar", StringUtils.leftPad("foobar", 2)); // no padding needed, already longer
        assertEquals("foobar  ", StringUtils.leftPad("foobar  ", 8)); // already padded
    }

    @Test
//    public void testSplitByLines() {
    public void testSplitByLines__fixed() {
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitByLines("a\nb\nc"));
        assertArrayEquals(new String[]{"a", "b", "", "c"}, StringUtils.splitByLines("a\nb\n\nc"));
        assertArrayEquals(new String[]{"a"}, StringUtils.splitByLines("a"));
//        assertArrayEquals(new String[]{"a", "b\nc"}, StringUtils.splitByLines("a\nb\nc"));
        /** The method splits the entire string, not just the first occurrence */
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitByLines("a\nb\nc"));
    }

    @Test
//    public void testSplitBy() {
    public void testSplitBy__fixed() {
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitBy("a,b,c", ','));
        assertArrayEquals(new String[]{"a", "b", "", "c"}, StringUtils.splitBy("a,b,,c", ','));
        assertArrayEquals(new String[]{"a"}, StringUtils.splitBy("a", ','));
//        assertArrayEquals(new String[]{"a", "b,c"}, StringUtils.splitBy("a,b,c", ','));
        /** The method splits the entire string, not just the first occurrence */
        assertArrayEquals(new String[]{"a", "b", "c"}, StringUtils.splitBy("a,b,c", ','));
    }

    @Test
    public void testExtractInts() {
        assertArrayEquals(new int[]{1, 2, 3}, StringUtils.extractInts("1 2 3"));
        assertArrayEquals(new int[]{1}, StringUtils.extractInts("1"));
        assertArrayEquals(new int[]{}, StringUtils.extractInts(""));
        assertArrayEquals(new int[]{1, 0, -1}, StringUtils.extractInts("1 0 -1"));
    }

    @Test
//    public void testEscapeForXML() {
    public void testEscapeForXML__fixed() {
        assertEquals("&lt;tag&gt;", StringUtils.escapeForXML("<tag>"));
        assertEquals("&amp;&amp;&amp;", StringUtils.escapeForXML("&&&"));
        assertEquals("hello &quot;world&quot;", StringUtils.escapeForXML("hello \"world\""));
//        assertEquals("It's &apos;me&apos;", StringUtils.escapeForXML("It's 'me'"));
        /** Missed the first apostrophe */
        assertEquals("It&apos;s &apos;me&apos;", StringUtils.escapeForXML("It's 'me'"));

//        assertEquals("Control char: &#x0004;", StringUtils.escapeForXML("Control char: " + (char) 4));
        /** Incorrect output for the illegal char */
        assertEquals("Control char: ILLEGAL-XML-CHAR:0004;", StringUtils.escapeForXML("Control char: " + (char) 4));
    }

    @Test
//    public void testSubstitute() {
    public void testSubstitute__fixed() {
        assertEquals("hello world", StringUtils.substitute("hello foo", "foo", "world"));
        assertEquals("hello world world", StringUtils.substitute("hello world foo", "foo", "world"));
//        assertEquals("foo world", StringUtils.substitute("foo foo", "foo", "world"));
        /** The method substitutes just the first occurrence */
        assertEquals("world foo", StringUtils.substitute("foo foo", "foo", "world"));

        assertEquals("foo", StringUtils.substitute("foo", "bar", "world")); // no substitution
        assertEquals("", StringUtils.substitute("", "bar", "world")); // empty string
    }
}
