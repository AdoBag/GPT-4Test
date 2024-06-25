
package org.example.run3;

import org.example.dependencies.NamedStyle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpMatcherTest {

    @Test
    void testMatchTemplateBegin() {
        // Test with null input
        assertNull(OpMatcher.matchTemplateBegin(null));

        // Test with matching @template_begin
        String text1 = "@template_begin(foo, bar)";
        String[] expected1 = {"foo", "bar"};
        assertArrayEquals(expected1, OpMatcher.matchTemplateBegin(text1));

        // Test with matching @tbegin
        String text2 = "@tbegin(foo, bar, baz)";
        String[] expected2 = {"foo", "bar", "baz"};
        assertArrayEquals(expected2, OpMatcher.matchTemplateBegin(text2));

        // Test with non-matching template
        String text3 = "@template_beg(foo, bar)";
        assertNull(OpMatcher.matchTemplateBegin(text3));

        // Test with valid string with no parameters
        String text4 = "@template_begin()";
        assertNull(OpMatcher.matchTemplateBegin(text4));
    }

    @Test
//    void testMatchTemplateEnd() {
    void testMatchTemplateEnd__fixed__finds_bug() {
        // Test with null input
//        assertFalse(OpMatcher.matchTemplateEnd(null));
        /** The above assertion catches a bug. The method should return false when invoked with `null` */

        // Test with matching @template_end
        assertTrue(OpMatcher.matchTemplateEnd("@template_end some text"));

        // Test with matching @tend
        assertTrue(OpMatcher.matchTemplateEnd("some text @tend"));

        // Test with non-matching template end
//        assertFalse(OpMatcher.matchTemplateEnd("some text @tendd"));
        /** The string does include `@tend` which gets matched */
        assertTrue(OpMatcher.matchTemplateEnd("some text @tendd"));
    }

    @Test
    void testMatchTemplateParameter() {
        // Test with null input
        assertNull(OpMatcher.matchTemplateParameter(null));

        // Test with valid parameter name
        assertEquals("param1", OpMatcher.matchTemplateParameter("#param1"));

        // Test with valid parameter number
        assertEquals("3", OpMatcher.matchTemplateParameter("#3"));

        // Test with no parameter
        assertNull(OpMatcher.matchTemplateParameter("param"));
    }

    @Test
    void testMatchTemplateName() {
        // Test with valid template name
        assertTrue(OpMatcher.matchTemplateName("template"));

        // Test with invalid template name (starts with non-alphabetical)
        assertFalse(OpMatcher.matchTemplateName("1template"));

        // Test with null input
        assertFalse(OpMatcher.matchTemplateName(null));
    }

    @Test
//    void testMatchStyle() {
    void testMatchStyle__fixed() {
        // Test with null input
        assertNull(OpMatcher.matchStyle(null));

        // Test with valid style without flag
//        NamedStyle expectedStyle1 = new NamedStyle("styleName", false);
//        assertEquals(expectedStyle1, OpMatcher.matchStyle("@style(styleName)"));
        /** The LLM was not informed that the class does not have an `equals` method */
        NamedStyle expectedStyle1 = new NamedStyle("styleName", false);
        assertEquals(expectedStyle1.toString(), OpMatcher.matchStyle("@style(styleName)").toString());

        // Test with valid style with true flag
//        NamedStyle expectedStyle2 = new NamedStyle("styleName", true);
//        assertEquals(expectedStyle2, OpMatcher.matchStyle("@style(styleName, true)"));
        /** The LLM was not informed that the class does not have an `equals` method */
        NamedStyle expectedStyle2 = new NamedStyle("styleName", true);
        assertEquals(expectedStyle2.toString(), OpMatcher.matchStyle("@style(styleName, true)").toString());

        // Test with valid style with false flag
//        NamedStyle expectedStyle3 = new NamedStyle("styleName", false);
//        assertEquals(expectedStyle3, OpMatcher.matchStyle("@style(styleName, false)"));
        /** The LLM was not informed that the class does not have an `equals` method */
        NamedStyle expectedStyle3 = new NamedStyle("styleName", false);
        assertEquals(expectedStyle3.toString(), OpMatcher.matchStyle("@style(styleName, false)").toString());

        // Test with invalid style pattern
        assertNull(OpMatcher.matchStyle("@style(styleName, maybe)"));
    }
}
