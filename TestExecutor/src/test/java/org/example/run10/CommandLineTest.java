
package org.example.run10;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineTest {

    private CommandLine commandLine;
    private String[] args;

    @BeforeEach
    void setUp() {
        args = new String[]{"-switch1", "param1", "-switch2", "param2", "-switch3"};
        commandLine = new CommandLine(args);
    }

    @Test
//    void testExists() {
    void testExists__fixed() {
        assertTrue(commandLine.exists("switch1"));
        assertTrue(commandLine.exists("switch2"));
        assertTrue(commandLine.exists("switch3"));
//        assertTrue(commandLine.exists("param1"));
//        assertTrue(commandLine.exists("param2"));
        /** `param1` and `param2` are arguments and not options not switches */
        assertFalse(commandLine.exists("param1"));
        assertFalse(commandLine.exists("param2"));

        assertFalse(commandLine.exists("nonexistent"));
    }

    @Test
//    void testIsSwitch() {
    void testIsSwitch__fixed() {
        //assertTrue(commandLine.isSwitch("switch1"));
        //assertTrue(commandLine.isSwitch("switch2"));
        /** `switch1` and `switch2` have values after them. Hence, they are not switches */
        assertFalse(commandLine.isSwitch("switch1"));
        assertFalse(commandLine.isSwitch("switch2"));

        assertTrue(commandLine.isSwitch("switch3"));
        assertFalse(commandLine.isSwitch("param1"));
        assertFalse(commandLine.isSwitch("param2"));
    }

    @Test
//    void testIsParameter() {
    void testIsParameter__fixed() {
//        assertFalse(commandLine.isParameter("switch1"));
//        assertFalse(commandLine.isParameter("switch2"));
        /** `switch1` and `switch2` have values after them. Hence, they are parameters */
        assertTrue(commandLine.isParameter("switch1"));
        assertTrue(commandLine.isParameter("switch2"));

        assertFalse(commandLine.isParameter("switch3"));
//        assertTrue(commandLine.isParameter("param1"));
//        assertTrue(commandLine.isParameter("param2"));
        /** `param1` and `param2` are parameter values but not parameters */
        assertFalse(commandLine.isParameter("param1"));
        assertFalse(commandLine.isParameter("param2"));
    }

    @Test
//    void testValue() {
    void testValue__fixed() {
        //assertNull(commandLine.value("switch1"));
        /** `switch1` and `switch2` are parameters with values */
        assertNotNull(commandLine.value("switch1"));

        assertNull(commandLine.value("switch3"));
        assertEquals("param1", commandLine.value("switch1"));
        assertEquals("param2", commandLine.value("switch2"));
    }

    @Test
//    void testValueWithDefault() {
    void testValueWithDefault__fixed() {
        //assertNull(commandLine.value("switch1"));
        /** `switch1` and `switch2` are parameters with values */
        assertNotNull(commandLine.value("switch1"));

        assertNull(commandLine.value("switch3"));
        assertEquals("default", commandLine.value("switch3", "default"));
        assertEquals("param1", commandLine.value("switch1", "default"));
    }

    @Test
    void testAddSwitch() {
        CommandLine cmd = new CommandLine();
        assertTrue(cmd.add("newSwitch", null));
        assertTrue(cmd.isSwitch("newSwitch"));
    }

    @Test
    void testAddParameter() {
        CommandLine cmd = new CommandLine();
        assertTrue(cmd.add("newParam", "value"));
        assertTrue(cmd.isParameter("newParam"));
        assertEquals("value", cmd.value("newParam"));
    }

    @Test
    void testAddWithOverwriteTrue() {
        CommandLine cmd = new CommandLine();
        cmd.add("sameName", "initialValue", true);
        assertTrue(cmd.add("sameName", "newValue", true));
        assertEquals("newValue", cmd.value("sameName"));
    }

    @Test
    void testAddWithOverwriteFalse() {
        CommandLine cmd = new CommandLine();
        cmd.add("sameName", "initialValue", false);
        assertFalse(cmd.add("sameName", "newValue", false));
        assertEquals("initialValue", cmd.value("sameName"));
    }

    @Test
    void testAddOptionOnlyIfNotExists() {
        CommandLine cmd = new CommandLine();
        assertTrue(cmd.add("uniqueSwitch", null));
        assertTrue(cmd.isSwitch("uniqueSwitch"));
        assertFalse(cmd.add("uniqueSwitch", null, false));
    }
}
