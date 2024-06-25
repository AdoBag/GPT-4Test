
package org.example.run4;

import org.example.dependencies.NamedStyle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OpMatcherTest {

    @Test
    void testMatchTemplateBegin_WithTemplateBegin1() {
        String input = "@template_begin(name, param1, param2)";
        String[] result = OpMatcher.matchTemplateBegin(input);
        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals("name", result[0]);
        assertEquals("param1", result[1]);
        assertEquals("param2", result[2]);
    }

    @Test
    void testMatchTemplateBegin_WithTemplateBegin2() {
        String input = "@tbegin(name, param1, param2)";
        String[] result = OpMatcher.matchTemplateBegin(input);
        assertNotNull(result);
        assertEquals(3, result.length);
        assertEquals("name", result[0]);
        assertEquals("param1", result[1]);
        assertEquals("param2", result[2]);
    }

    @Test
    void testMatchTemplateBegin_NoMatch() {
        String input = "@template_beg(name, param1, param2)";
        String[] result = OpMatcher.matchTemplateBegin(input);
        assertNull(result);
    }

    @Test
    void testMatchTemplateEnd_WithTemplateEnd() {
        String input1 = "@template_end";
        String input2 = "@tend";
        assertTrue(OpMatcher.matchTemplateEnd(input1));
        assertTrue(OpMatcher.matchTemplateEnd(input2));
    }

    @Test
    void testMatchTemplateEnd_NoMatch() {
        String input = "@template_ed";
        assertFalse(OpMatcher.matchTemplateEnd(input));
    }

    @Test
    void testMatchTemplateParameter_NameParameter() {
        String input = "This is a #param1.";
        String result = OpMatcher.matchTemplateParameter(input);
        assertEquals("param1", result);
    }

    @Test
    void testMatchTemplateParameter_NumberParameter() {
        String input = "This is a #1.";
        String result = OpMatcher.matchTemplateParameter(input);
        assertEquals("1", result);
    }

    @Test
    void testMatchTemplateParameter_NoParameter() {
        String input = "This is a parameter.";
        String result = OpMatcher.matchTemplateParameter(input);
        assertNull(result);
    }

    @Test
    void testMatchTemplateName_ValidName() {
        String input = "TemplateName123";
        assertTrue(OpMatcher.matchTemplateName(input));
    }

    @Test
    void testMatchTemplateName_InvalidName() {
        String input = "123TemplateName";
        assertFalse(OpMatcher.matchTemplateName(input));
    }

//    @Test
//    void testMatchStyle_WithoutParam() {
//        String input = "@style(styleName)";
//        NamedStyle result = OpMatcher.matchStyle(input);
//        assertNotNull(result);
//        assertEquals("styleName", result.getName());
//        assertFalse(result.isFlag());
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM did not know the API of the NamedStyle class
     */
    @Test
    void testMatchStyle_WithoutParam__fixed() {
        String input = "@style(styleName)";
        NamedStyle result = OpMatcher.matchStyle(input);
        assertNotNull(result);
        assertEquals("styleName", result.getName());
        assertFalse(result.hasParam());
    }

//    @Test
//    void testMatchStyle_WithParam() {
//        String input = "@style(styleName, true)";
//        NamedStyle result = OpMatcher.matchStyle(input);
//        assertNotNull(result);
//        assertEquals("styleName", result.getName());
//        assertTrue(result.isFlag());
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM did not know the API of the NamedStyle class
     */
    @Test
    void testMatchStyle_WithParam__fixed() {
        String input = "@style(styleName, true)";
        NamedStyle result = OpMatcher.matchStyle(input);
        assertNotNull(result);
        assertEquals("styleName", result.getName());
        assertTrue(result.hasParam());
    }

    @Test
    void testMatchStyle_NoMatch() {
        String input = "@styleName(styleName)";
        NamedStyle result = OpMatcher.matchStyle(input);
        assertNull(result);
    }
}
