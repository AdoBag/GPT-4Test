```java
package org.example.run1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        Version v2 = new Version("2.1.5");
        assertNotEquals(v1, v2);
    }

    @Test
    public void testVersionComparisonEqual() {
        Version v1 = new Version("2.1.4");
        Version v2 = new Version("2.1.4");
        assertEquals(0, v1.compareTo(v2));
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
    public void testVersionDifferentLength() {
        Version v1 = new Version("2.1");
        Version v2 = new Version("2.1.0");
        assertTrue(v1.compareTo(v2) < 0);

        Version v3 = new Version("2.1.1");
        Version v4 = new Version("2.1");
        assertTrue(v3.compareTo(v4) > 0);
    }

    @Test
    public void testVersionWithDifferentSeparators() {
        Version v1 = new Version("2-1-4");
        Version v2 = new Version("2.1.4");
        assertEquals(v1, v2);

        Version v3 = new Version("2_1_4");
        assertEquals(v1, v3);
    }

    @Test
    public void testHashCodeConsistency() {
        Version v1 = new Version("2.1.4");
        Version v2 = new Version("2.1.4");
        assertEquals(v1.hashCode(), v2.hashCode());
    }

    @Test
    public void testToString() {
        Version v1 = new Version("2.1.4");
        assertEquals("2.1.4", v1.toString());
    }

    @Test
    public void testNullVersion() {
        Version v1 = new Version(null);
        Version v2 = new Version(null);
        assertEquals(v1, v2);
    }

    @Test
    public void testEmptyVersion() {
        Version v1 = new Version("");
        Version v2 = new Version("");
        assertEquals(v1, v2);
    }
}
```