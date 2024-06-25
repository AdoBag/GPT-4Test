
package org.example.run7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandLineTest {

    @Test
    public void testExistsOption() {
        String[] args = {"-option1"};
        CommandLine cmd = new CommandLine(args);
        assertTrue(cmd.exists("option1"));
    }

    @Test
    public void testExistsParameter() {
        String[] args = {"-param1", "value1"};
        CommandLine cmd = new CommandLine(args);
        assertTrue(cmd.exists("param1"));
    }

    @Test
    public void testIsSwitch() {
        String[] args = {"-switch"};
        CommandLine cmd = new CommandLine(args);
        assertTrue(cmd.isSwitch("switch"));
    }

    @Test
    public void testIsParameter() {
        String[] args = {"-param", "value"};
        CommandLine cmd = new CommandLine(args);
        assertTrue(cmd.isParameter("param"));
    }

    @Test
    public void testValue() {
        String[] args = {"-param", "value"};
        CommandLine cmd = new CommandLine(args);
        assertEquals("value", cmd.value("param"));
    }

    @Test
    public void testValueDefault() {
        String[] args = {};
        CommandLine cmd = new CommandLine(args);
        assertEquals("default", cmd.value("param", "default"));
    }

    @Test
    public void testAddOption() {
        CommandLine cmd = new CommandLine();
        assertTrue(cmd.add("option", null));
        assertTrue(cmd.isSwitch("option"));
    }

    @Test
    public void testAddParameter() {
        CommandLine cmd = new CommandLine();
        assertTrue(cmd.add("param", "value"));
        assertEquals("value", cmd.value("param"));
    }

    @Test
    public void testOverwriteOption() {
        CommandLine cmd = new CommandLine();
        assertTrue(cmd.add("option", null));
        assertTrue(cmd.add("option", null, true));
        assertTrue(cmd.isSwitch("option"));
    }

    @Test
    public void testOverwriteParameter() {
        CommandLine cmd = new CommandLine();
        assertTrue(cmd.add("param", "value1"));
        assertTrue(cmd.add("param", "value2", true));
        assertEquals("value2", cmd.value("param"));
    }

    @Test
    public void testNoOverwriteOption() {
        CommandLine cmd = new CommandLine();
        assertTrue(cmd.add("option", null));
        assertFalse(cmd.add("option", null, false));
        assertTrue(cmd.isSwitch("option"));
    }

    @Test
    public void testNoOverwriteParameter() {
        CommandLine cmd = new CommandLine();
        assertTrue(cmd.add("param", "value1"));
        assertFalse(cmd.add("param", "value2", false));
        assertEquals("value1", cmd.value("param"));
    }
}
