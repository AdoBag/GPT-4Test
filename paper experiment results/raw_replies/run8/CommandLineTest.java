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
    public void testDefaultConstructor() {
        assertNotNull(commandLine);
    }

    @Test
    public void testExists() {
        commandLine.add("option1", null);
        commandLine.add("param1", "value1");
        
        assertTrue(commandLine.exists("option1"));
        assertTrue(commandLine.exists("param1"));
        assertFalse(commandLine.exists("option2"));
    }

    @Test
    public void testIsSwitch() {
        commandLine.add("option1", null);
        
        assertTrue(commandLine.isSwitch("option1"));
        assertFalse(commandLine.isSwitch("param1"));
    }

    @Test
    public void testIsParameter() {
        commandLine.add("param1", "value1");
        
        assertTrue(commandLine.isParameter("param1"));
        assertFalse(commandLine.isParameter("option1"));
    }

    @Test
    public void testValue() {
        commandLine.add("param1", "value1");
        
        assertEquals("value1", commandLine.value("param1"));
        assertNull(commandLine.value("option1"));
    }

    @Test
    public void testValueWithDefault() {
        commandLine.add("param1", "value1");
        
        assertEquals("value1", commandLine.value("param1", "default"));
        assertEquals("default", commandLine.value("option1", "default"));
    }

    @Test
    public void testAdd() {
        assertTrue(commandLine.add("option1", null));
        assertTrue(commandLine.add("param1", "value1"));
        
        // Overwrite should succeed
        assertTrue(commandLine.add("param1", "newValue", true));
        assertEquals("newValue", commandLine.value("param1"));
        
        // No overwrite should fail
        assertFalse(commandLine.add("param1", "anotherValue", false));
        assertEquals("newValue", commandLine.value("param1"));
    }

    @Test
    public void testProcessCommandLine() {
        String[] args = {"-option1", "-param1", "value1", "-option2", "value2"};
        CommandLine cmd = new CommandLine(args);
        
        assertTrue(cmd.isSwitch("option1"));
        assertTrue(cmd.isSwitch("option2"));
        assertTrue(cmd.isParameter("param1"));
        
        assertNull(cmd.value("option1"));
        assertEquals("value1", cmd.value("param1"));
        assertEquals("value2", cmd.value("option2"));
    }

    @Test
    public void testProcessCommandLineWithOnlySwitches() {
        String[] args = {"-option1", "-option2", "-option3"};
        CommandLine cmd = new CommandLine(args);
        
        assertTrue(cmd.isSwitch("option1"));
        assertTrue(cmd.isSwitch("option2"));
        assertTrue(cmd.isSwitch("option3"));
        
        assertNull(cmd.value("option1"));
        assertNull(cmd.value("option2"));
        assertNull(cmd.value("option3"));
    }

    @Test
    public void testProcessCommandLineWithOnlyParams() {
        String[] args = {"-param1", "value1", "-param2", "value2"};
        CommandLine cmd = new CommandLine(args);
        
        assertTrue(cmd.isParameter("param1"));
        assertTrue(cmd.isParameter("param2"));
        
        assertEquals("value1", cmd.value("param1"));
        assertEquals("value2", cmd.value("param2"));
    }

    @Test
    public void testMixedCommandLine() {
        String[] args = {"-option1", "-param1", "value1", "-option2"};
        CommandLine cmd = new CommandLine(args);
        
        assertTrue(cmd.isSwitch("option1"));
        assertTrue(cmd.isSwitch("option2"));
        assertTrue(cmd.isParameter("param1"));
        
        assertNull(cmd.value("option1"));
        assertEquals("value1", cmd.value("param1"));
        assertNull(cmd.value("option2"));
    }
}
```