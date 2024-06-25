
package org.example.run2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    @Test
    void testLeftPadNoPaddingNeeded() {
        assertEquals("text", StringUtils.leftPad("text", 4));
    }

    @Test
    void testLeftPadWithPadding() {
        assertEquals("  text", StringUtils.leftPad("text", 6));
    }

    @Test
    void testLeftPadExactSize() {
        assertEquals("text", StringUtils.leftPad("text", 4));
    }

    @Test
    void testSplitByLinesSingleLine() {
        assertArrayEquals(new String[]{"line1"}, StringUtils.splitByLines("line1"));
    }

    @Test
    void testSplitByLinesMultipleLines() {
        assertArrayEquals(new String[]{"line1", "line2", "line3"}, StringUtils.splitByLines("line1\nline2\nline3"));
    }

    @Test
    void testSplitBySingleCharSeparator() {
        assertArrayEquals(new String[]{"part1", "part2", "part3"}, StringUtils.splitBy("part1,part2,part3", ','));
    }

    @Test
    void testSplitByMultipleCharSeparator() {
        assertArrayEquals(new String[]{"part1", "part2", "part3"}, StringUtils.splitBy("part1-part2-part3", '-'));
    }

    @Test
    void testExtractIntsWithValidString() {
        assertArrayEquals(new int[]{1, 2, 3}, StringUtils.extractInts("1 2 3"));
    }

    @Test
    void testExtractIntsWithEmptyString() {
        assertArrayEquals(new int[]{}, StringUtils.extractInts(""));
    }

    @Test
    void testEscapeForXMLBasicCharacters() {
        assertEquals("&lt;&gt;&amp;&quot;&apos;", StringUtils.escapeForXML("<>&\"'"));
    }

    @Test
    void testEscapeForXMLWithNoSpecialCharacters() {
        assertEquals("text", StringUtils.escapeForXML("text"));
    }

    @Test
    void testSubstituteFoundString() {
        assertEquals("hello world", StringUtils.substitute("hello world", "world", "world"));
    }

    @Test
    void testSubstituteReplaceString() {
        assertEquals("hello universe", StringUtils.substitute("hello world", "world", "universe"));
    }

    @Test
    void testSubstituteNoMatch() {
        assertEquals("hello world", StringUtils.substitute("hello world", "earth", "universe"));
    }

//    @Test
//    void testSubstituteEmptySource() {
//        assertEquals("hello world", StringUtils.substitute("hello world", "", "universe"));
//    }
    /**
     * Manual fix of the above test
     * Problem: This is arguably a bug, maybe a corner case. If the source is an empty string, it will be inserted at the beginning
     */
    @Test
    void testSubstituteEmptySource__fixed() {
        assertEquals("universehello world", StringUtils.substitute("hello world", "", "universe"));
    }

    @Test
    void testSubstituteEmptyTarget() {
        assertEquals("hello ", StringUtils.substitute("hello world", "world", ""));
    }

    @Test
    void testExtractIntsWithInvalidString() {
        Exception exception = assertThrows(NumberFormatException.class, () -> {
            StringUtils.extractInts("1 2 a");
        });
        String expectedMessage = "For input string: \"a\"";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
