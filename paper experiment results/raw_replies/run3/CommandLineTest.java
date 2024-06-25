```java
package org.example.run1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandLineTest {
    private CommandLine commandLine;

    @BeforeEach
    public void setUp() {
        commandLine = new CommandLine();
    }

    @Test
    public void testEmptyCommandLine() {
        assertFalse(commandLine.exists("nonexistent"));
        assertFalse(commandLine.isSwitch("nonexistent"));
        assertFalse(commandLine.isParameter("nonexistent"));
        assertNull(commandLine.value("nonexistent"));
        assertEquals("default", commandLine.value("nonexistent", "default"));
    }

    @Test
    public void testAddSwitch() {
        commandLine.add("option", null);
        assertTrue(commandLine.isSwitch("option"));
        assertFalse(commandLine.isParameter("option"));
        assertNull(commandLine.value("option"));
        assertEquals("default", commandLine.value("option", "default"));
    }

    @Test
    public void testAddParameter() {
        commandLine.add("param", "value");
        assertTrue(commandLine.isParameter("param"));
        assertFalse(commandLine.isSwitch("param"));
        assertEquals("value", commandLine.value("param"));
        assertEquals("value", commandLine.value("param", "default"));
    }

    @Test
    public void testOverwriteSwitch() {
        commandLine.add("option", null);
        commandLine.add("option", null, false);
        assertTrue(commandLine.isSwitch("option"));

        boolean result = commandLine.add("option", "newvalue", false);
        assertFalse(result);
        assertNull(commandLine.value("option"));
    }

    @Test
    public void testOverwriteParameter() {
        commandLine.add("param", "value");
        commandLine.add("param", "newvalue", true);
        assertEquals("newvalue", commandLine.value("param"));
        
        boolean result = commandLine.add("param", "anotherValue", false);
        assertFalse(result);
        assertEquals("newvalue", commandLine.value("param"));
    }

    @Test
    public void testProcessCommandLineEmpty() {
        CommandLine cmd = new CommandLine(new String[]{});
        assertFalse(cmd.exists("option"));
    }

    @Test
    public void testProcessCommandLineSwitch() {
        CommandLine cmd = new CommandLine(new String[]{"-option"});
        assertTrue(cmd.isSwitch("option"));
        assertNull(cmd.value("option"));
    }

    @Test
    public void testProcessCommandLineParameter() {
        CommandLine cmd = new CommandLine(new String[]{"-param", "value"});
        assertTrue(cmd.isParameter("param"));
        assertEquals("value", cmd.value("param"));
    }

    @Test
    public void testProcessCommandLineMultiple() {
        CommandLine cmd = new CommandLine(new String[]{"-option1", "-param", "value", "-option2"});
        assertTrue(cmd.isSwitch("option1"));
        assertTrue(cmd.isParameter("param"));
        assertEquals("value", cmd.value("param"));
        assertTrue(cmd.isSwitch("option2"));
    }

    @Test
    public void testProcessCommandLineMixed() {
        CommandLine cmd = new CommandLine(new String[]{"-first", "val1", "-second", "val2"});
        assertTrue(cmd.isParameter("first"));
        assertEquals("val1", cmd.value("first"));
        assertTrue(cmd.isParameter("second"));
        assertEquals("val2", cmd.value("second"));
    }
}
```