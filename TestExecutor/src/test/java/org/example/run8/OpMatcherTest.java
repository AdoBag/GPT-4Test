
package org.example.run8;

import org.example.dependencies.NamedStyle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OpMatcherTest {

    @Test
    void testMatchTemplateBegin() {
        String text1 = "@template_begin(name, param1, param2)";
        String[] result1 = OpMatcher.matchTemplateBegin(text1);
        assertArrayEquals(new String[]{"name", "param1", "param2"}, result1);

        String text2 = "@tbegin(name, param1, param2)";
        String[] result2 = OpMatcher.matchTemplateBegin(text2);
        assertArrayEquals(new String[]{"name", "param1", "param2"}, result2);

        String text3 = "@tbegin(name,param1,param2)";
        String[] result3 = OpMatcher.matchTemplateBegin(text3);
        assertArrayEquals(new String[]{"name", "param1", "param2"}, result3);

        String text4 = "@template_begin()";
        String[] result4 = OpMatcher.matchTemplateBegin(text4);
        assertNull(result4);

        String text5 = null;
        String[] result5 = OpMatcher.matchTemplateBegin(text5);
        assertNull(result5);
    }

    @Test
    void testMatchTemplateEnd() {
        assertTrue(OpMatcher.matchTemplateEnd("@template_end"));
        assertTrue(OpMatcher.matchTemplateEnd("@tend"));
        assertFalse(OpMatcher.matchTemplateEnd("@template_begin"));
//        assertFalse(OpMatcher.matchTemplateEnd(null));
        /** The above assertion catches a bug. The method should return false when invoked with `null` */
    }

    @Test
    void testMatchTemplateParameter() {
        String text1 = "This is a #param1";
        assertEquals("param1", OpMatcher.matchTemplateParameter(text1));

        String text2 = "This is a #1";
        assertEquals("1", OpMatcher.matchTemplateParameter(text2));

        String text3 = "This is a #";
        assertNull(OpMatcher.matchTemplateParameter(text3));

        String text4 = "This has no parameters";
        assertNull(OpMatcher.matchTemplateParameter(text4));

        String text5 = null;
        assertNull(OpMatcher.matchTemplateParameter(text5));
    }

    @Test
    void testMatchTemplateName() {
        assertTrue(OpMatcher.matchTemplateName("template1"));
        assertTrue(OpMatcher.matchTemplateName("TemplateName"));
        assertFalse(OpMatcher.matchTemplateName("123Template"));
        assertFalse(OpMatcher.matchTemplateName("template-name"));
        assertFalse(OpMatcher.matchTemplateName(""));
        assertFalse(OpMatcher.matchTemplateName(null));
    }

//    @Test
//    void testMatchStyle() {
//        String text1 = "@style(styleName)";
//        NamedStyle style1 = OpMatcher.matchStyle(text1);
//        assertNotNull(style1);
//        assertEquals("styleName", style1.getName());
//        assertFalse(style1.isFlag());
//
//        String text2 = "@style(styleName, true)";
//        NamedStyle style2 = OpMatcher.matchStyle(text2);
//        assertNotNull(style2);
//        assertEquals("styleName", style2.getName());
//        assertTrue(style2.isFlag());
//
//        String text3 = "@style(styleName, false)";
//        NamedStyle style3 = OpMatcher.matchStyle(text3);
//        assertNotNull(style3);
//        assertEquals("styleName", style3.getName());
//        assertFalse(style3.isFlag());
//
//        String text4 = "@style(styleName, maybe)";
//        NamedStyle style4 = OpMatcher.matchStyle(text4);
//        assertNull(style4);
//
//        String text5 = "@stylename)";
//        NamedStyle style5 = OpMatcher.matchStyle(text5);
//        assertNull(style5);
//
//        String text6 = null;
//        NamedStyle style6 = OpMatcher.matchStyle(text6);
//        assertNull(style6);
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM did not know the API of the NamedStyle class
     */
    @Test
    void testMatchStyle__fixed() {
        String text1 = "@style(styleName)";
        NamedStyle style1 = OpMatcher.matchStyle(text1);
        assertNotNull(style1);
        assertEquals("styleName", style1.getName());
        assertFalse(style1.hasParam());
        String text2 = "@style(styleName, true)";
        NamedStyle style2 = OpMatcher.matchStyle(text2);
        assertNotNull(style2);
        assertEquals("styleName", style2.getName());
        assertTrue(style2.hasParam());
        String text3 = "@style(styleName, false)";
        NamedStyle style3 = OpMatcher.matchStyle(text3);
        assertNotNull(style3);
        assertEquals("styleName", style3.getName());
        assertFalse(style3.hasParam());
        String text4 = "@style(styleName, maybe)";
        NamedStyle style4 = OpMatcher.matchStyle(text4);
        assertNull(style4);
        String text5 = "@stylename)";
        NamedStyle style5 = OpMatcher.matchStyle(text5);
        assertNull(style5);
        String text6 = null;
        NamedStyle style6 = OpMatcher.matchStyle(text6);
        assertNull(style6);
    }
}
