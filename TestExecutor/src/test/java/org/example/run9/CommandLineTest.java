
package org.example.run9;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandLineTest {

    @Test
    public void testConstructorWithArgs() {
        String[] args = {"-option1", "value1", "-option2", "-switch"};
        CommandLine commandLine = new CommandLine(args);

        assertTrue(commandLine.exists("option1"));
        assertEquals("value1", commandLine.value("option1"));
        assertTrue(commandLine.exists("option2"));
        assertNull(commandLine.value("option2")); // since it's a switch
        assertTrue(commandLine.isSwitch("switch"));
    }

    @Test
    public void testDefaultConstructor() {
        CommandLine commandLine = new CommandLine();
        assertNotNull(commandLine);
    }

    @Test
    public void testExists() {
        CommandLine commandLine = new CommandLine();
        commandLine.add("option1", "value1");
        assertTrue(commandLine.exists("option1"));
        assertFalse(commandLine.exists("option2"));
    }

    @Test
    public void testIsSwitch() {
        CommandLine commandLine = new CommandLine();
        commandLine.add("switch", null);
        assertTrue(commandLine.isSwitch("switch"));
        assertFalse(commandLine.isSwitch("option1"));
    }

    @Test
    public void testIsParameter() {
        CommandLine commandLine = new CommandLine();
        commandLine.add("option1", "value1");
        assertTrue(commandLine.isParameter("option1"));
        assertFalse(commandLine.isParameter("switch"));
    }

    @Test
    public void testValue() {
        CommandLine commandLine = new CommandLine();
        commandLine.add("option1", "value1");
        assertEquals("value1", commandLine.value("option1"));
        assertNull(commandLine.value("nonexistentOption"));
    }

    @Test
    public void testValueWithDefaultValue() {
        CommandLine commandLine = new CommandLine();
        commandLine.add("option1", "value1");
        assertEquals("value1", commandLine.value("option1", "default"));
        assertEquals("default", commandLine.value("option2", "default"));
    }

    @Test
//    public void testAddWithOverwriteFalse() {
    public void testAddWithOverwriteFalse__fixed() {
        CommandLine commandLine = new CommandLine();
        commandLine.add("option1", "value1");
//        assertTrue(commandLine.add("option1", "value2", false));
        /** There is already a value after `option1`, thus, the following function call should return false. */
        assertFalse(commandLine.add("option1", "value2", false));

        assertEquals("value1", commandLine.value("option1"));
    }

    @Test
    public void testAddWithOverwriteTrue() {
        CommandLine commandLine = new CommandLine();
        commandLine.add("option1", "value1");
        assertTrue(commandLine.add("option1", "value2", true));
        assertEquals("value2", commandLine.value("option1"));
    }

    @Test
    public void testAddSwitch() {
        CommandLine commandLine = new CommandLine();
        commandLine.add("switch", null);
        assertTrue(commandLine.isSwitch("switch"));
        assertNull(commandLine.value("switch"));
    }

    @Test
    public void testAddParameter() {
        CommandLine commandLine = new CommandLine();
        commandLine.add("option1", "value1");
        assertTrue(commandLine.isParameter("option1"));
        assertEquals("value1", commandLine.value("option1"));
    }
}
