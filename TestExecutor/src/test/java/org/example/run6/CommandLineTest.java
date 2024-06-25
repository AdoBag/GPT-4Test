
package org.example.run6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CommandLineTest {

    private CommandLine cmdLine;

    @BeforeEach
    public void setUp() {
        cmdLine = new CommandLine();
    }

    @Test
    public void testDefaultConstructor() {
        assertNotNull(cmdLine);
        assertFalse(cmdLine.exists("test"));
    }

    @Test
//    public void testConstructorWithArgs() {
    public void testConstructorWithArgs__fixed() {
        String[] args = {"-switch1", "-switch2", "param1", "-switch3", "param2"};
        cmdLine = new CommandLine(args);

        assertTrue(cmdLine.exists("switch1"));
        assertTrue(cmdLine.isSwitch("switch1"));
        assertTrue(cmdLine.exists("switch2"));
//        assertTrue(cmdLine.isSwitch("switch2"));
        /** `-switch2` has a value after it. Hence, it is an option and not a switch */
        assertFalse(cmdLine.isSwitch("switch2"));

        assertFalse(cmdLine.isSwitch("param1"));
        assertEquals("param1", cmdLine.value("switch2"));
        assertTrue(cmdLine.exists("switch3"));
//        assertTrue(cmdLine.isSwitch("switch3"));
        /** `-switch3` has a value after it. Hence, it is an option and not a switch */
        assertFalse(cmdLine.isSwitch("switch3"));

        assertEquals("param2", cmdLine.value("switch3"));
    }

    @Test
    public void testExists() {
        cmdLine.add("switch1", null);
        assertTrue(cmdLine.exists("switch1"));
        assertFalse(cmdLine.exists("switch2"));
    }

    @Test
    public void testIsSwitch() {
        cmdLine.add("switch1", null);
        assertTrue(cmdLine.isSwitch("switch1"));
        assertFalse(cmdLine.isSwitch("switch2"));
    }

    @Test
    public void testIsParameter() {
        cmdLine.add("param1", "value1");
        assertTrue(cmdLine.isParameter("param1"));
        assertFalse(cmdLine.isParameter("param2"));
    }

    @Test
    public void testValue() {
        cmdLine.add("param1", "value1");
        assertEquals("value1", cmdLine.value("param1"));
        assertNull(cmdLine.value("param2"));
    }

    @Test
    public void testValueWithDefault() {
        cmdLine.add("param1", "value1");
        assertEquals("value1", cmdLine.value("param1", "default"));
        assertEquals("default", cmdLine.value("param2", "default"));
    }

    @Test
    public void testAddOption() {
        assertTrue(cmdLine.add("switch1", null));
        assertTrue(cmdLine.isSwitch("switch1"));
        assertFalse(cmdLine.isParameter("switch1"));
    }

    @Test
    public void testAddParameter() {
        assertTrue(cmdLine.add("param1", "value1"));
        assertTrue(cmdLine.isParameter("param1"));
        assertEquals("value1", cmdLine.value("param1"));
    }

    @Test
    public void testAddParameterOverwrite() {
        assertTrue(cmdLine.add("param1", "value1"));
        assertEquals("value1", cmdLine.value("param1"));
        assertTrue(cmdLine.add("param1", "newValue1", true));
        assertEquals("newValue1", cmdLine.value("param1"));
    }

    @Test
    public void testAddParameterNoOverwrite() {
        assertTrue(cmdLine.add("param1", "value1"));
        assertEquals("value1", cmdLine.value("param1"));
        assertFalse(cmdLine.add("param1", "newValue1", false));
        assertEquals("value1", cmdLine.value("param1"));
    }

    @Test
    public void testProcessCommandLine() {
        String[] args = {"-switch1", "-param1", "value1", "-switch2"};
        cmdLine = new CommandLine(args);

        assertTrue(cmdLine.exists("switch1"));
        assertTrue(cmdLine.isSwitch("switch1"));
        assertTrue(cmdLine.exists("param1"));
        assertTrue(cmdLine.isParameter("param1"));
        assertEquals("value1", cmdLine.value("param1"));
        assertTrue(cmdLine.exists("switch2"));
        assertTrue(cmdLine.isSwitch("switch2"));
    }
}
