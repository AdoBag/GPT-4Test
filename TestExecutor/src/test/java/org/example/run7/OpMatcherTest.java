
package org.example.run7;

import org.example.dependencies.NamedStyle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OpMatcherTest {

    @Test
    void testMatchTemplateBeginValidInput() {
        String text = "@template_begin(param1, param2)";
        String[] expected = {"param1", "param2"};
        String[] result = OpMatcher.matchTemplateBegin(text);
        assertArrayEquals(expected, result);
    }

    @Test
    void testMatchTemplateBeginValidTbegin() {
        String text = "@tbegin(paramA, paramB)";
        String[] expected = {"paramA", "paramB"};
        String[] result = OpMatcher.matchTemplateBegin(text);
        assertArrayEquals(expected, result);
    }

    @Test
    void testMatchTemplateBeginNoMatch() {
        String text = "@template_start(param1, param2)";
        String[] result = OpMatcher.matchTemplateBegin(text);
        assertNull(result);
    }

    @Test
    void testMatchTemplateEndValid() {
        String text = "@template_end";
        assertTrue(OpMatcher.matchTemplateEnd(text));
    }

    @Test
    void testMatchTemplateEndValidTend() {
        String text = "@tend";
        assertTrue(OpMatcher.matchTemplateEnd(text));
    }

    @Test
    void testMatchTemplateEndInvalid() {
        String text = "@template_stop";
        assertFalse(OpMatcher.matchTemplateEnd(text));
    }

    @Test
    void testMatchTemplateParameterValidName() {
        String text = "#parameterName";
        String expected = "parameterName";
        String result = OpMatcher.matchTemplateParameter(text);
        assertEquals(expected, result);
    }

    @Test
    void testMatchTemplateParameterValidNumber() {
        String text = "#123";
        String expected = "123";
        String result = OpMatcher.matchTemplateParameter(text);
        assertEquals(expected, result);
    }

    @Test
    void testMatchTemplateParameterInvalid() {
        String text = "parameterName";
        String result = OpMatcher.matchTemplateParameter(text);
        assertNull(result);
    }

    @Test
    void testMatchTemplateNameValid() {
        String text = "templateName";
        assertTrue(OpMatcher.matchTemplateName(text));
    }

    @Test
    void testMatchTemplateNameInvalid() {
        String text = "templateName123!";
        assertFalse(OpMatcher.matchTemplateName(text));
    }

//    @Test
//    void testMatchStyleValid() {
//        String text = "@style(styleName)";
//        NamedStyle result = OpMatcher.matchStyle(text);
//        assertNotNull(result);
//        assertEquals("styleName", result.getName());
//        assertFalse(result.isFlag());
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM did not know the API of the NamedStyle class
     */
    @Test
    void testMatchStyleValid__fixed() {
        String text = "@style(styleName)";
        NamedStyle result = OpMatcher.matchStyle(text);
        assertNotNull(result);
        assertEquals("styleName", result.getName());
        assertFalse(result.hasParam());
    }

//    @Test
//    void testMatchStyleWithParamValid() {
//        String text = "@style(styleName, true)";
//        NamedStyle result = OpMatcher.matchStyle(text);
//        assertNotNull(result);
//        assertEquals("styleName", result.getName());
//        assertTrue(result.isFlag());
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM did not know the API of the NamedStyle class
     */
    @Test
    void testMatchStyleWithParamValid__fixed() {
        String text = "@style(styleName, true)";
        NamedStyle result = OpMatcher.matchStyle(text);
        assertNotNull(result);
        assertEquals("styleName", result.getName());
        assertTrue(result.hasParam());
    }

    @Test
    void testMatchStyleInvalid() {
        String text = "@styleStyleName)";
        NamedStyle result = OpMatcher.matchStyle(text);
        assertNull(result);
    }
}
