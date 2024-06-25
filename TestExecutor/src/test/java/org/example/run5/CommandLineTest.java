
package org.example.run5;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandLineTest {

    private CommandLine commandLineWithArgs;
    private CommandLine commandLineWithoutArgs;

    @BeforeEach
    public void setUp() {
        String[] args = {"-option1", "value1", "-option2", "-switch1", "-option3", "value3"};
        commandLineWithArgs = new CommandLine(args);
        commandLineWithoutArgs = new CommandLine();
    }

    @Test
    public void testExistsWithOption() {
        assertTrue(commandLineWithArgs.exists("option1"));
        assertTrue(commandLineWithArgs.exists("switch1"));
        assertFalse(commandLineWithArgs.exists("nonexistent"));
    }

    @Test
    public void testIsSwitch() {
        assertTrue(commandLineWithArgs.isSwitch("switch1"));
        assertFalse(commandLineWithArgs.isSwitch("option1"));
    }

    @Test
    public void testIsParameter() {
        assertTrue(commandLineWithArgs.isParameter("option1"));
        assertFalse(commandLineWithArgs.isParameter("switch1"));
        assertFalse(commandLineWithArgs.isParameter("nonexistent"));
    }

    @Test
    public void testValue() {
        assertEquals("value1", commandLineWithArgs.value("option1"));
        assertNull(commandLineWithArgs.value("switch1"));
        assertNull(commandLineWithArgs.value("nonexistent"));
    }

    @Test
    public void testValueWithDefault() {
        assertEquals("value1", commandLineWithArgs.value("option1", "default"));
        assertEquals("default", commandLineWithArgs.value("nonexistent", "default"));
    }

    @Test
    public void testAddOption() {
        commandLineWithoutArgs.add("option4", "value4");
        assertTrue(commandLineWithoutArgs.exists("option4"));
        assertEquals("value4", commandLineWithoutArgs.value("option4"));
    }

    @Test
    public void testAddOptionWithOverwrite() {
        commandLineWithArgs.add("option1", "newValue1", true);
        assertEquals("newValue1", commandLineWithArgs.value("option1"));
    }

    @Test
    public void testAddOptionWithoutOverwrite() {
        assertFalse(commandLineWithArgs.add("option1", "newValue1", false));
        assertEquals("value1", commandLineWithArgs.value("option1"));
    }

    @Test
    public void testAddSwitch() {
        commandLineWithoutArgs.add("switch2", null);
        assertTrue(commandLineWithoutArgs.isSwitch("switch2"));
    }

    @Test
    public void testProcessCommandLine() {
        String[] args = {"-newopt1", "val1", "-newopt2", "-newswitch1", "-newopt3", "val3"};
        CommandLine cmd = new CommandLine(args);

        assertTrue(cmd.exists("newopt1"));
        assertEquals("val1", cmd.value("newopt1"));

        assertTrue(cmd.isSwitch("newswitch1"));
        assertNull(cmd.value("newswitch1"));

        assertEquals("val3", cmd.value("newopt3"));
    }
}
