
package org.example.run1;

import org.example.dependencies.NamedStyle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpMatcherTest {

    @Test
    void testMatchTemplateBegin() {
        String text1 = "@template_begin(name, param1, param2)";
        String[] expected1 = {"name", "param1", "param2"};
        assertArrayEquals(expected1, OpMatcher.matchTemplateBegin(text1));

        String text2 = "@tbegin(name, param1, param2)";
        String[] expected2 = {"name", "param1", "param2"};
        assertArrayEquals(expected2, OpMatcher.matchTemplateBegin(text2));

        String text3 = "no match here";
        assertNull(OpMatcher.matchTemplateBegin(text3));

        String text4 = "@template_begin(param1,param2)";
        String[] expected4 = {"param1", "param2"};
        assertArrayEquals(expected4, OpMatcher.matchTemplateBegin(text4));
    }

    @Test
//    void testMatchTemplateEnd() {
    void testMatchTemplateEnd__finds_bug() {
        assertTrue(OpMatcher.matchTemplateEnd("@template_end"));
        assertTrue(OpMatcher.matchTemplateEnd("@tend"));
        assertFalse(OpMatcher.matchTemplateEnd("no match here"));
        //assertFalse(OpMatcher.matchTemplateEnd(null));
        /** The above assertion catches a bug. The method should return false when invoked with `null` */
    }

    @Test
    void testMatchTemplateParameter() {
        assertEquals("param1", OpMatcher.matchTemplateParameter("#param1"));
        assertEquals("1", OpMatcher.matchTemplateParameter("#1"));
        assertNull(OpMatcher.matchTemplateParameter("no match here"));
        assertNull(OpMatcher.matchTemplateParameter(null));
    }

    @Test
    void testMatchTemplateName() {
        assertTrue(OpMatcher.matchTemplateName("TemplateName"));
        assertFalse(OpMatcher.matchTemplateName("123Invalid"));
        assertFalse(OpMatcher.matchTemplateName("Invalid!Name"));
        assertFalse(OpMatcher.matchTemplateName(null));
    }

    @Test
//    void testMatchStyle() {
    void testMatchStyle__fixed() {
        NamedStyle style1 = new NamedStyle("StyleName", false);
        //assertEquals(style1, OpMatcher.matchStyle("@style(StyleName)"));
        /** The LLM was not informed that the class does not have an `equals` method */
        assertEquals(style1.toString(), OpMatcher.matchStyle("@style(StyleName)").toString());

        NamedStyle style2 = new NamedStyle("StyleName", true);
        //assertEquals(style2, OpMatcher.matchStyle("@style(StyleName, true)"));
        /** The LLM was not informed that the class does not have an `equals` method */
        assertEquals(style2.toString(), OpMatcher.matchStyle("@style(StyleName, true)").toString());

        NamedStyle style3 = new NamedStyle("StyleName", false);
        //assertEquals(style3, OpMatcher.matchStyle("@style(StyleName, false)"));
        /** The LLM was not informed that the class does not have an `equals` method */
        assertEquals(style3.toString(), OpMatcher.matchStyle("@style(StyleName, false)").toString());

        assertNull(OpMatcher.matchStyle("no match here"));
        assertNull(OpMatcher.matchStyle(null));
    }
}
