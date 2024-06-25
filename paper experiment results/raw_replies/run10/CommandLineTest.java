```java
package org.example.run1;

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
    void testExists() {
        assertTrue(commandLine.exists("switch1"));
        assertTrue(commandLine.exists("switch2"));
        assertTrue(commandLine.exists("switch3"));
        assertTrue(commandLine.exists("param1"));
        assertTrue(commandLine.exists("param2"));
        assertFalse(commandLine.exists("nonexistent"));
    }

    @Test
    void testIsSwitch() {
        assertTrue(commandLine.isSwitch("switch1"));
        assertTrue(commandLine.isSwitch("switch2"));
        assertTrue(commandLine.isSwitch("switch3"));
        assertFalse(commandLine.isSwitch("param1"));
        assertFalse(commandLine.isSwitch("param2"));
    }

    @Test
    void testIsParameter() {
        assertFalse(commandLine.isParameter("switch1"));
        assertFalse(commandLine.isParameter("switch2"));
        assertFalse(commandLine.isParameter("switch3"));
        assertTrue(commandLine.isParameter("param1"));
        assertTrue(commandLine.isParameter("param2"));
    }

    @Test
    void testValue() {
        assertNull(commandLine.value("switch1"));
        assertNull(commandLine.value("switch3"));
        assertEquals("param1", commandLine.value("switch1"));
        assertEquals("param2", commandLine.value("switch2"));
    }

    @Test
    void testValueWithDefault() {
        assertNull(commandLine.value("switch1"));
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
```

In this test suite, we are covering the following scenarios:

1. **Constructor functionality**: `CommandLine` is correctly initialized with command line args.
2. **Existence checks**: Tests for the presence of switches and parameters using `exists`, `isSwitch`, and `isParameter` methods.
3. **Value retrieval**: Tests fetching values with and without default values.
4. **Adding options and parameters**: Tests adding new switches and parameters with and without overwrite capability.

Make sure to include the JUnit 5 dependency in your project to run these tests.