```java
package org.example.run1;

import org.example.dependencies.NamedStyle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

class OpMatcherTest {

    @Test
    void testMatchTemplateBeginWithTBEGIN1() {
        String text = "@template_begin(name, param1, param2)";
        String[] expected = {"name", "param1", "param2"};
        String[] actual = OpMatcher.matchTemplateBegin(text);
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testMatchTemplateBeginWithTBEGIN2() {
        String text = "@tbegin(name, param1, param2)";
        String[] expected = {"name", "param1", "param2"};
        String[] actual = OpMatcher.matchTemplateBegin(text);
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    void testMatchTemplateBeginNoMatch() {
        String text = "@begin(name, param1, param2)";
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
        assertFalse(OpMatcher.matchTemplateEnd("@end"));
    }

    @Test
    void testMatchTemplateParameterWithName() {
        String text = "#param";
        String expected = "param";
        String actual = OpMatcher.matchTemplateParameter(text);
        assertEquals(expected, actual);
    }

    @Test
    void testMatchTemplateParameterWithNumber() {
        String text = "#1";
        String expected = "1";
        String actual = OpMatcher.matchTemplateParameter(text);
        assertEquals(expected, actual);
    }

    @Test
    void testMatchTemplateParameterNoMatch() {
        String text = "param";
        String actual = OpMatcher.matchTemplateParameter(text);
        assertNull(actual);
    }

    @Test
    void testMatchTemplateNameTrue() {
        assertTrue(OpMatcher.matchTemplateName("TemplateName"));
        assertTrue(OpMatcher.matchTemplateName("templateName123"));
    }

    @Test
    void testMatchTemplateNameFalse() {
        assertFalse(OpMatcher.matchTemplateName("123TemplateName"));
        assertFalse(OpMatcher.matchTemplateName("Template Name"));
    }

    @Test
    void testMatchStyleWithoutParam() {
        String text = "@style(styleName)";
        NamedStyle expected = new NamedStyle("styleName", false);
        NamedStyle actual = OpMatcher.matchStyle(text);
        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
        assertFalse(actual.isFlag());
    }

    @Test
    void testMatchStyleWithParam() {
        String text = "@style(styleName, true)";
        NamedStyle expected = new NamedStyle("styleName", true);
        NamedStyle actual = OpMatcher.matchStyle(text);
        assertNotNull(actual);
        assertEquals(expected.getName(), actual.getName());
        assertTrue(actual.isFlag());
    }

    @Test
    void testMatchStyleNoMatch() {
        String text = "@style(name";
        NamedStyle actual = OpMatcher.matchStyle(text);
        assertNull(actual);
    }
}
```
