
package org.example.run5;

import org.example.dependencies.NamedStyle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpMatcherTest {

    @Test
    void testMatchTemplateBeginWithTBEGIN1() {
        String text = "@template_begin(name, param1, param2)";
        String[] expected = {"name", "param1", "param2"};
        String[] actual = OpMatcher.matchTemplateBegin(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testMatchTemplateBeginWithTBEGIN2() {
        String text = "@tbegin(name, param1, param2)";
        String[] expected = {"name", "param1", "param2"};
        String[] actual = OpMatcher.matchTemplateBegin(text);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testMatchTemplateBeginInvalid() {
        String text = "@template_start(name, param1, param2)";
        String[] actual = OpMatcher.matchTemplateBegin(text);
        assertNull(actual);
    }

    @Test
    void testMatchTemplateEndTrue() {
        assertTrue(OpMatcher.matchTemplateEnd("@template_end"));
        assertTrue(OpMatcher.matchTemplateEnd("@tend"));
    }

    @Test
    void testMatchTemplateEndFalse() {
        assertFalse(OpMatcher.matchTemplateEnd("@template_stop"));
    }

    @Test
    void testMatchTemplateParameterByName() {
        String text = "Some text with a #param";
        String expected = "param";
        String actual = OpMatcher.matchTemplateParameter(text);
        assertEquals(expected, actual);
    }

    @Test
    void testMatchTemplateParameterByNumber() {
        String text = "Some text with a #1";
        String expected = "1";
        String actual = OpMatcher.matchTemplateParameter(text);
        assertEquals(expected, actual);
    }

    @Test
    void testMatchTemplateParameterNotFound() {
        String text = "Some text without a parameter";
        String actual = OpMatcher.matchTemplateParameter(text);
        assertNull(actual);
    }

    @Test
    void testMatchTemplateNameValid() {
        assertTrue(OpMatcher.matchTemplateName("TemplateName"));
    }

    @Test
    void testMatchTemplateNameInvalid() {
        assertFalse(OpMatcher.matchTemplateName("Template Name"));
    }

//    @Test
//    void testMatchStyleWithoutParam() {
//        String text = "@style(styleName)";
//        NamedStyle expected = new NamedStyle("styleName", false);
//        NamedStyle actual = OpMatcher.matchStyle(text);
//        assertEquals(expected, actual);
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM did not know the API of the NamedStyle class
     */
    @Test
    void testMatchStyleWithoutParam__fixed() {
        String text = "@style(styleName)";
        NamedStyle expected = new NamedStyle("styleName", false);
        NamedStyle actual = OpMatcher.matchStyle(text);
        assertEquals(expected.toString(), actual.toString());
    }

//    @Test
//    void testMatchStyleWithParam() {
//        String text = "@style(styleName, true)";
//        NamedStyle expected = new NamedStyle("styleName", true);
//        NamedStyle actual = OpMatcher.matchStyle(text);
//        assertEquals(expected, actual);
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM did not know the API of the NamedStyle class
     */
    @Test
    void testMatchStyleWithParam__fixed() {
        String text = "@style(styleName, true)";
        NamedStyle expected = new NamedStyle("styleName", true);
        NamedStyle actual = OpMatcher.matchStyle(text);
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    void testMatchStyleInvalid() {
        String text = "@sty(styleName)";
        NamedStyle actual = OpMatcher.matchStyle(text);
        assertNull(actual);
    }
}
