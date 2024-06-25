```java
package org.example.run1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineTest {

    private CommandLine commandLine;

    @BeforeEach
    void setUp() {
        commandLine = new CommandLine();
    }

    @Test
    void testConstructorWithArgs() {
        String[] args = {"-option1", "value1", "-option2", "value2", "-switch"};
        commandLine = new CommandLine(args);

        // Test if the switches and parameters are correctly added
        assertTrue(commandLine.isParameter("option1"));
        assertEquals("value1", commandLine.value("option1"));
        assertTrue(commandLine.isParameter("option2"));
        assertEquals("value2", commandLine.value("option2"));
        assertTrue(commandLine.isSwitch("switch"));
    }

    @Test
    void testExists() {
        commandLine.add("option1", "value1");
        commandLine.add("switch", null);

        assertTrue(commandLine.exists("option1"));
        assertTrue(commandLine.exists("switch"));
        assertFalse(commandLine.exists("nonexistent"));
    }

    @Test
    void testIsSwitch() {
        commandLine.add("switch", null);
        commandLine.add("option1", "value1");

        assertTrue(commandLine.isSwitch("switch"));
        assertFalse(commandLine.isSwitch("option1"));
    }

    @Test
    void testIsParameter() {
        commandLine.add("option1", "value1");
        commandLine.add("switch", null);

        assertTrue(commandLine.isParameter("option1"));
        assertFalse(commandLine.isParameter("switch"));
    }

    @Test
    void testValue() {
        commandLine.add("option1", "value1");

        assertEquals("value1", commandLine.value("option1"));
        assertNull(commandLine.value("nonexistent"));
    }

    @Test
    void testValueWithDefault() {
        commandLine.add("option1", "value1");

        assertEquals("value1", commandLine.value("option1"));
        assertEquals("default", commandLine.value("nonexistent", "default"));
    }

    @Test
    void testAdd() {
        assertTrue(commandLine.add("option1", "value1"));
        assertEquals("value1", commandLine.value("option1"));

        assertTrue(commandLine.add("switch", null));
        assertTrue(commandLine.isSwitch("switch"));

        // Test overwrite
        assertTrue(commandLine.add("option1", "newValue1"));
        assertEquals("newValue1", commandLine.value("option1"));

        // Test not overwrite
        assertFalse(commandLine.add("option1", "notOverwritten", false));
        assertEquals("newValue1", commandLine.value("option1"));

        // Check the switch addition with overwrite flag
        assertTrue(commandLine.add("switch", null, false));
        assertTrue(commandLine.isSwitch("switch"));

        // Try to add parameter with null value and overwrite false; ensure it still added as switch
        assertTrue(commandLine.add("switch2", null, false));
        assertTrue(commandLine.isSwitch("switch2"));
    }
}
```