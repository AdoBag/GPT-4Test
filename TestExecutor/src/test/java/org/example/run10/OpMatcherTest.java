
package org.example.run10;

import org.example.dependencies.NamedStyle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OpMatcherTest {

    @Test
    void testMatchTemplateBegin_withValidTemplateBegin1() {
        String text = "@template_begin(name, param1, param2)";
        String[] result = OpMatcher.matchTemplateBegin(text);
        assertArrayEquals(new String[]{"name", "param1", "param2"}, result);
    }

    @Test
    void testMatchTemplateBegin_withValidTemplateBegin2() {
        String text = "@tbegin(name, param1, param2)";
        String[] result = OpMatcher.matchTemplateBegin(text);
        assertArrayEquals(new String[]{"name", "param1", "param2"}, result);
    }

    @Test
    void testMatchTemplateBegin_withInvalidTemplate() {
        String text = "@template_begin(name, param1, param2";
        String[] result = OpMatcher.matchTemplateBegin(text);
        assertNull(result);
    }

    @Test
    void testMatchTemplateEnd_withTemplateEnd1() {
        assertTrue(OpMatcher.matchTemplateEnd("@template_end"));
    }

    @Test
    void testMatchTemplateEnd_withTemplateEnd2() {
        assertTrue(OpMatcher.matchTemplateEnd("@tend"));
    }

    @Test
    void testMatchTemplateEnd_withInvalidText() {
        assertFalse(OpMatcher.matchTemplateEnd("random text"));
    }

    @Test
    void testMatchTemplateParameter_withNamedParameter() {
        String text = "#param1";
        String result = OpMatcher.matchTemplateParameter(text);
        assertEquals("param1", result);
    }

    @Test
    void testMatchTemplateParameter_withNumberedParameter() {
        String text = "#1";
        String result = OpMatcher.matchTemplateParameter(text);
        assertEquals("1", result);
    }

    @Test
    void testMatchTemplateParameter_withInvalidParameter() {
        String text = "param1";
        String result = OpMatcher.matchTemplateParameter(text);
        assertNull(result);
    }

    @Test
    void testMatchTemplateName_withValidName() {
        String text = "TemplateName1";
        assertTrue(OpMatcher.matchTemplateName(text));
    }

    @Test
    void testMatchTemplateName_withInvalidName() {
        String text = "Template Name";
        assertFalse(OpMatcher.matchTemplateName(text));
    }

//    @Test
//    void testMatchStyle_withStyleNoParams() {
//        String text = "@style(StyleName)";
//        NamedStyle result = OpMatcher.matchStyle(text);
//        assertNotNull(result);
//        assertEquals("StyleName", result.getName());
//        assertFalse(result.getFlag());
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM did not know the API of the NamedStyle class
     */
    @Test
    void testMatchStyle_withStyleNoParams__fixed() {
        String text = "@style(StyleName)";
        NamedStyle result = OpMatcher.matchStyle(text);
        assertNotNull(result);
        assertEquals("StyleName", result.getName());
        assertFalse(result.hasParam());
    }

//    @Test
//    void testMatchStyle_withStyleAndParamsTrue() {
//        String text = "@style(StyleName, true)";
//        NamedStyle result = OpMatcher.matchStyle(text);
//        assertNotNull(result);
//        assertEquals("StyleName", result.getName());
//        assertTrue(result.getFlag());
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM did not know the API of the NamedStyle class
     */
    @Test
    void testMatchStyle_withStyleAndParamsTrue__fixed() {
        String text = "@style(StyleName, true)";
        NamedStyle result = OpMatcher.matchStyle(text);
        assertNotNull(result);
        assertEquals("StyleName", result.getName());
        assertTrue(result.hasParam());
    }

//    @Test
//    void testMatchStyle_withStyleAndParamsFalse() {
//        String text = "@style(StyleName, false)";
//        NamedStyle result = OpMatcher.matchStyle(text);
//        assertNotNull(result);
//        assertEquals("StyleName", result.getName());
//        assertFalse(result.getFlag());
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM did not know the API of the NamedStyle class
     */
    @Test
    void testMatchStyle_withStyleAndParamsFalse__fixed() {
        String text = "@style(StyleName, false)";
        NamedStyle result = OpMatcher.matchStyle(text);
        assertNotNull(result);
        assertEquals("StyleName", result.getName());
        assertFalse(result.hasParam());
    }

    @Test
    void testMatchStyle_withInvalidStyle() {
        String text = "@style(StyleName, maybe)";
        NamedStyle result = OpMatcher.matchStyle(text);
        assertNull(result);
    }
}
