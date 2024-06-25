
package org.example.run9;

import org.example.dependencies.NamedStyle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpMatcherTest {

    @Test
    void testMatchTemplateBegin() {
        String text1 = "@template_begin(name,param1,param2)";
        String text2 = "@tbegin(name, param1, param2)";

        String[] result1 = OpMatcher.matchTemplateBegin(text1);
        String[] result2 = OpMatcher.matchTemplateBegin(text2);

        assertArrayEquals(new String[]{"name", "param1", "param2"}, result1);
        assertArrayEquals(new String[]{"name", "param1", "param2"}, result2);

        String text3 = "some random @tbegin(name,param1) text";
        String[] result3 = OpMatcher.matchTemplateBegin(text3);
        assertArrayEquals(new String[]{"name", "param1"}, result3);

        String text4 = "no match text";
        String[] result4 = OpMatcher.matchTemplateBegin(text4);
        assertNull(result4);
    }

    @Test
    void testMatchTemplateEnd() {
        assertTrue(OpMatcher.matchTemplateEnd("@template_end"));
        assertTrue(OpMatcher.matchTemplateEnd("some random @tend text"));
        assertFalse(OpMatcher.matchTemplateEnd("some random text"));
    }

    @Test
    void testMatchTemplateParameter() {
        assertEquals("param1", OpMatcher.matchTemplateParameter("#param1"));
        assertEquals("2", OpMatcher.matchTemplateParameter("#2"));
        assertNull(OpMatcher.matchTemplateParameter("non-matching text"));
    }

    @Test
    void testMatchTemplateName() {
        assertTrue(OpMatcher.matchTemplateName("TemplateName"));
        assertFalse(OpMatcher.matchTemplateName("Invalid Name!"));
        assertFalse(OpMatcher.matchTemplateName(null));
    }

    @Test
//    void testMatchStyle() {
    void testMatchStyle__fixed() {
        NamedStyle namedStyle1 = new NamedStyle("bold", false);
        NamedStyle namedStyle2 = new NamedStyle("italic", true);

//        assertEquals(namedStyle1, OpMatcher.matchStyle("@style(bold)"));
//        assertEquals(namedStyle2, OpMatcher.matchStyle("@style(italic, true)"));
        /** The LLM was not informed that the class does not have an `equals` method */
        assertEquals(namedStyle1.toString(), OpMatcher.matchStyle("@style(bold)").toString());
        assertEquals(namedStyle2.toString(), OpMatcher.matchStyle("@style(italic, true)").toString());

        assertNull(OpMatcher.matchStyle("non-matching text"));
    }
}
