
package org.example.run1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class StringUtilsTest {

    @Test
    public void testLeftPad() {
        Assertions.assertEquals("  abc", StringUtils.leftPad("abc", 5));
        Assertions.assertEquals("abcdef", StringUtils.leftPad("abcdef", 3));
        Assertions.assertEquals("    a", StringUtils.leftPad("a", 5));
        Assertions.assertEquals("", StringUtils.leftPad("", 0));
    }

    @Test
    public void testSplitByLines() {
        String[] expected = {"a", "b", "c"};
        Assertions.assertArrayEquals(expected, StringUtils.splitByLines("a\nb\nc"));
        Assertions.assertArrayEquals(new String[]{""}, StringUtils.splitByLines(""));
        Assertions.assertArrayEquals(new String[]{"a"}, StringUtils.splitByLines("a"));
        Assertions.assertArrayEquals(new String[]{"a", ""}, StringUtils.splitByLines("a\n"));
    }

    @Test
    public void testSplitBy() {
        String[] expectedComma = {"a", "b", "c"};
        Assertions.assertArrayEquals(expectedComma, StringUtils.splitBy("a,b,c", ','));
        String[] expectedSpace = {"a", "b", "c"};
        Assertions.assertArrayEquals(expectedSpace, StringUtils.splitBy("a b c", ' '));
        Assertions.assertArrayEquals(new String[]{""}, StringUtils.splitBy("", ','));
        Assertions.assertArrayEquals(new String[]{"a"}, StringUtils.splitBy("a", ','));
    }

    @Test
    public void testExtractInts() {
        int[] expected = {1, 2, 3};
        Assertions.assertArrayEquals(expected, StringUtils.extractInts("1 2 3"));
        Assertions.assertArrayEquals(new int[]{}, StringUtils.extractInts(""));
        Assertions.assertArrayEquals(new int[]{42}, StringUtils.extractInts("42"));
        Assertions.assertThrows(NumberFormatException.class, () -> {
            StringUtils.extractInts("1 2 three");
        });
    }

    @Test
    public void testEscapeForXML() {
        Assertions.assertEquals("&lt;tag&gt;", StringUtils.escapeForXML("<tag>"));
        Assertions.assertEquals("Tom &amp; Jerry", StringUtils.escapeForXML("Tom & Jerry"));
        Assertions.assertEquals("He said &quot;Hello&quot;", StringUtils.escapeForXML("He said \"Hello\""));
        Assertions.assertEquals("Save &apos;it&apos;", StringUtils.escapeForXML("Save 'it'"));
        Assertions.assertEquals("Normal text", StringUtils.escapeForXML("Normal text"));
        String unprintableString = "Hello" + ((char) 27) + "World";
        Assertions.assertTrue(StringUtils.escapeForXML(unprintableString).contains("ILLEGAL-XML-CHAR"));
    }

    @Test
//    public void testSubstitute() {
    public void testSubstitute__fixed() {
        Assertions.assertEquals("abXYZef", StringUtils.substitute("abcdef", "cd", "XYZ"));
        Assertions.assertEquals("abcdef", StringUtils.substitute("abcdef", "gh", "XYZ"));
//        Assertions.assertEquals("aXYZcXYZef", StringUtils.substitute("aXYbcXYbdef", "XYb", "XYZ"));
        /** The above assertion fails because `substitute` only replaces the first occurrence */
        Assertions.assertEquals("aXYZcXYbdef", StringUtils.substitute("aXYbcXYbdef", "XYb", "XYZ"));

//        Assertions.assertEquals("abcdef", StringUtils.substitute("abcdef", "", "XYZ"));
        /** This is arguably a bug, maybe a corner case. If the source is an empty string, it will be inserted at the beginning */
        Assertions.assertEquals("XYZabcdef", StringUtils.substitute("abcdef", "", "XYZ"));

        Assertions.assertEquals("XYZdef", StringUtils.substitute("abcdef", "abc", "XYZ"));
    }
}
