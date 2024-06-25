```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VersionTest {
  
    @Test
    public void testVersionCreation() {
        Version version = new Version("1.2.3");
        assertEquals("1.2.3", version.toString());
    }

    @Test
    public void testCompareToWithEqualVersions() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.2.3");
        assertEquals(0, version1.compareTo(version2));
    }

    @Test
    public void testCompareToWithDifferentMajorVersion() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("2.0.0");
        assertTrue(version1.compareTo(version2) < 0);
    }

    @Test
    public void testCompareToWithDifferentMinorVersion() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.3.0");
        assertTrue(version1.compareTo(version2) < 0);
    }

    @Test
    public void testCompareToWithDifferentPatchVersion() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.2.4");
        assertTrue(version1.compareTo(version2) < 0);
    }

    @Test
    public void testCompareToWithNullVersion() {
        Version version = new Version("1.2.3");
        assertThrows(NullPointerException.class, () -> version.compareTo(null));
    }

    @Test
    public void testCompareToWithDifferentLengthVersions() {
        Version version1 = new Version("1.2");
        Version version2 = new Version("1.2.0");
        assertEquals(-1, version1.compareTo(version2));
    }

    @Test
    public void testEqualsWithEqualVersions() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.2.3");
        assertTrue(version1.equals(version2));
    }

    @Test
    public void testEqualsWithDifferentVersions() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.2.4");
        assertFalse(version1.equals(version2));
    }

    @Test
    public void testEqualsWithNull() {
        Version version = new Version("1.2.3");
        assertFalse(version.equals(null));
    }

    @Test
    public void testEqualsWithDifferentObject() {
        Version version = new Version("1.2.3");
        assertFalse(version.equals("1.2.3"));
    }

    @Test
    public void testHashCode() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.2.3");
        assertEquals(version1.hashCode(), version2.hashCode());
    }

    @Test
    public void testToString() {
        Version version = new Version("1.2.3");
        assertEquals("1.2.3", version.toString());
    }
}
```

This test suite uses JUnit 5 to ensure the `Version` class functionality works correctly. It covers version creation, comparison, equality, hash code, and string representation.