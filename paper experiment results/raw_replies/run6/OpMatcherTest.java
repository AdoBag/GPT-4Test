```java
package org.example.run1;

import org.example.dependencies.NamedStyle;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class OpMatcherTest {

    @Test
    void testMatchTemplateBegin() {
        String input = "@template_begin(name, param1, param2)";
        String[] expected = {"name", "param1", "param2"};
        String[] result = OpMatcher.matchTemplateBegin(input);
        assertArrayEquals(expected, result);

        input = "@tbegin(name, param1, param2)";
        result = OpMatcher.matchTemplateBegin(input);
        assertArrayEquals(expected, result);

        input = "@template_begin(name,param1,param2)";
        result = OpMatcher.matchTemplateBegin(input);
        assertArrayEquals(expected, result);

        input = "@tbegin(name,param1,param2)";
        result = OpMatcher.matchTemplateBegin(input);
        assertArrayEquals(expected, result);

        input = "no match";
        result = OpMatcher.matchTemplateBegin(input);
        assertNull(result);
    }

    @Test
    void testMatchTemplateEnd() {
        assertTrue(OpMatcher.matchTemplateEnd("@template_end"));
        assertTrue(OpMatcher.matchTemplateEnd("@tend"));
        assertFalse(OpMatcher.matchTemplateEnd("no match"));
        assertFalse(OpMatcher.matchTemplateEnd(null));
    }

    @Test
    void testMatchTemplateParameter() {
        assertEquals("param", OpMatcher.matchTemplateParameter("#param"));
        assertEquals("1", OpMatcher.matchTemplateParameter("#1"));
        assertEquals("10", OpMatcher.matchTemplateParameter("#10"));
        assertNull(OpMatcher.matchTemplateParameter("no match"));
        assertNull(OpMatcher.matchTemplateParameter(null));
    }

    @Test
    void testMatchTemplateName() {
        assertTrue(OpMatcher.matchTemplateName("templateName"));
        assertFalse(OpMatcher.matchTemplateName("123InvalidName"));
        assertFalse(OpMatcher.matchTemplateName(""));
        assertFalse(OpMatcher.matchTemplateName(null));
    }

    @Test
    void testMatchStyle() {
        NamedStyle expected = new NamedStyle("example", false);
        NamedStyle result = OpMatcher.matchStyle("@style(example)");
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.isFlag(), result.isFlag());

        expected = new NamedStyle("example", true);
        result = OpMatcher.matchStyle("@style(example, true)");
        assertEquals(expected.getName(), result.getName());
        assertEquals(expected.isFlag(), result.isFlag());

        result = OpMatcher.matchStyle("no match");
        assertNull(result);

        result = OpMatcher.matchStyle(null);
        assertNull(result);
    }
}
```