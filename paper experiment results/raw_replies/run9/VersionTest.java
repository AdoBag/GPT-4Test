```java
package org.example.run1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class VersionTest {

    @Test
    public void testVersionEquality() {
        Version v1 = new Version("2.1.4");
        Version v2 = new Version("2.1.4");
        assertEquals(v1, v2);
    }

    @Test
    public void testVersionInequality() {
        Version v1 = new Version("2.1.4");
        Version v2 = new Version("2.1.10");
        assertNotEquals(v1, v2);
    }

    @Test
    public void testVersionComparisonLessThan() {
        Version v1 = new Version("2.1.4");
        Version v2 = new Version("2.1.10");
        assertTrue(v1.compareTo(v2) < 0);
    }

    @Test
    public void testVersionComparisonGreaterThan() {
        Version v1 = new Version("2.1.10");
        Version v2 = new Version("2.1.4");
        assertTrue(v1.compareTo(v2) > 0);
    }

    @Test
    public void testHashCodeConsistency() {
        Version v1 = new Version("2.1.4");
        Version v2 = new Version("2.1.4");
        assertEquals(v1.hashCode(), v2.hashCode());
    }

    @Test
    public void testToStringMethod() {
        Version v = new Version("2.1.4");
        assertEquals("2.1.4", v.toString());
    }

    @Test
    public void testVersionWithDifferentDelimiters() {
        Version v1 = new Version("2-1-4");
        Version v2 = new Version("2.1.4");
        Version v3 = new Version("2_1_4");
        assertEquals(v1, v2);
        assertEquals(v2, v3);
        assertEquals(v1, v3);
    }

    @Test
    public void testVersionWithNull() {
        Version v1 = new Version(null);
        Version v2 = new Version(null);
        assertEquals(v1, v2);
    }

    @Test
    public void testCompareWithDifferentLengths() {
        Version v1 = new Version("1.2");
        Version v2 = new Version("1.2.0");
        assertEquals(0, v1.compareTo(v2));

        Version v3 = new Version("1.2.1");
        assertTrue(v1.compareTo(v3) < 0);
        assertTrue(v3.compareTo(v1) > 0);
    }
}
```