
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandLineTest {

    @Test
    public void testConstructorWithArgs() {
        String[] args = {"-opt1", "value1", "-opt2"};
        CommandLine cmd = new CommandLine(args);

        assertTrue(cmd.isParameter("opt1"));
        assertEquals("value1", cmd.value("opt1"));

        assertTrue(cmd.isSwitch("opt2"));
        assertNull(cmd.value("opt2"));

        assertFalse(cmd.exists("opt3"));
    }

    @Test
    public void testConstructorWithoutArgs() {
        CommandLine cmd = new CommandLine();
        assertFalse(cmd.exists("opt1"));
    }

    @Test
    public void testExists() {
        CommandLine cmd = new CommandLine();
        cmd.add("opt1", "value1");
        cmd.add("opt2", null);

        assertTrue(cmd.exists("opt1"));
        assertTrue(cmd.exists("opt2"));
        assertFalse(cmd.exists("opt3"));
    }

    @Test
    public void testIsSwitch() {
        CommandLine cmd = new CommandLine();
        cmd.add("opt1", null);

        assertTrue(cmd.isSwitch("opt1"));
        assertFalse(cmd.isSwitch("opt2"));
    }

    @Test
    public void testIsParameter() {
        CommandLine cmd = new CommandLine();
        cmd.add("opt1", "value1");

        assertTrue(cmd.isParameter("opt1"));
        assertFalse(cmd.isParameter("opt2"));
    }

    @Test
    public void testValue() {
        CommandLine cmd = new CommandLine();
        cmd.add("opt1", "value1");
        cmd.add("opt2", null);

        assertEquals("value1", cmd.value("opt1"));
        assertNull(cmd.value("opt2"));
    }

    @Test
    public void testValueWithDefault() {
        CommandLine cmd = new CommandLine();
        cmd.add("opt1", "value1");

        assertEquals("value1", cmd.value("opt1", "default"));
        assertEquals("default", cmd.value("opt2", "default"));
    }

    @Test
    public void testAddWithOption() {
        CommandLine cmd = new CommandLine();
        assertTrue(cmd.add("opt1", null));
        assertTrue(cmd.isSwitch("opt1"));
    }

    @Test
    public void testAddWithParameter() {
        CommandLine cmd = new CommandLine();
        assertTrue(cmd.add("opt1", "value1"));
        assertTrue(cmd.isParameter("opt1"));
        assertEquals("value1", cmd.value("opt1"));
    }

    @Test
    public void testAddWithOverwrite() {
        CommandLine cmd = new CommandLine();
        assertTrue(cmd.add("opt1", "value1"));
        assertTrue(cmd.add("opt1", "newValue", true));
        assertEquals("newValue", cmd.value("opt1"));
    }

    @Test
    public void testAddWithoutOverwrite() {
        CommandLine cmd = new CommandLine();
        assertTrue(cmd.add("opt1", "value1"));
        assertFalse(cmd.add("opt1", "newValue", false));
        assertEquals("value1", cmd.value("opt1"));
    }

    @Test
    public void testProcessCommandLine() {
        String[] args = {"-opt1", "value1", "-opt2", "value2", "-opt3"};
        CommandLine cmd = new CommandLine(args);

        assertTrue(cmd.isParameter("opt1"));
        assertEquals("value1", cmd.value("opt1"));

        assertTrue(cmd.isParameter("opt2"));
        assertEquals("value2", cmd.value("opt2"));

        assertTrue(cmd.isSwitch("opt3"));
        assertNull(cmd.value("opt3"));
    }

    @Test
    public void testMixedOptionsAndParameters() {
        String[] args = {"-opt1", "value1", "-opt2", "-opt3", "value3"};
        CommandLine cmd = new CommandLine(args);

        assertTrue(cmd.isParameter("opt1"));
        assertEquals("value1", cmd.value("opt1"));

        assertTrue(cmd.isSwitch("opt2"));
        assertNull(cmd.value("opt2"));

        assertTrue(cmd.isParameter("opt3"));
        assertEquals("value3", cmd.value("opt3"));
    }
}
