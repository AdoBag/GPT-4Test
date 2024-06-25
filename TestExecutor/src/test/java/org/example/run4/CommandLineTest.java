
package org.example.run4;

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
    void testAddOption() {
        assertTrue(commandLine.add("help", null));
        assertTrue(commandLine.isSwitch("help"));
    }

    @Test
    void testAddParameter() {
        assertTrue(commandLine.add("file", "test.txt"));
        assertTrue(commandLine.isParameter("file"));
        assertEquals("test.txt", commandLine.value("file"));
    }

    @Test
    void testOverwriteOption() {
        assertTrue(commandLine.add("verbose", null));
        assertTrue(commandLine.add("verbose", null, true));
        assertTrue(commandLine.isSwitch("verbose"));
    }

    @Test
    void testOverwriteParameter() {
        assertTrue(commandLine.add("level", "info"));
        assertTrue(commandLine.add("level", "debug", true));
        assertEquals("debug", commandLine.value("level"));
    }

    @Test
    void testNotOverwriteOption() {
        assertTrue(commandLine.add("debug", null));
        assertFalse(commandLine.add("debug", null, false));
        assertTrue(commandLine.isSwitch("debug"));
    }

    @Test
    void testNotOverwriteParameter() {
        assertTrue(commandLine.add("timeout", "30"));
        assertFalse(commandLine.add("timeout", "60", false));
        assertEquals("30", commandLine.value("timeout"));
    }

    @Test
//    void testCommandLineProcessing() {
    void testCommandLineProcessing__fixed() {
        String[] args = {"-save", "output.txt", "-verbose", "-timeout", "45"};
        CommandLine cmdLine = new CommandLine(args);

//        assertTrue(cmdLine.isSwitch("save"));
        /** `-save` has a value after it. Hence, it is an option and not a switch */
        assertFalse(cmdLine.isSwitch("save"));

        assertTrue(cmdLine.isSwitch("verbose"));
        assertTrue(cmdLine.isParameter("timeout"));
        assertEquals("output.txt", cmdLine.value("save"));
        assertEquals("45", cmdLine.value("timeout"));
    }

    @Test
    void testDefaultValues() {
        assertTrue(commandLine.add("mode", "production"));
        assertEquals("production", commandLine.value("mode"));
        assertEquals("default", commandLine.value("nonExistent", "default"));
    }

    @Test
    void testExists() {
        assertTrue(commandLine.add("test", "value"));
        assertTrue(commandLine.exists("test"));
        assertFalse(commandLine.exists("nonexistent"));
    }
}
