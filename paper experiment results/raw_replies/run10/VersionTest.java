```java
package org.example.run1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for the Version class.
 */
public class VersionTest {

    @Test
    public void testCompareToEqualVersions() {
        Version version1 = new Version("1.0.0");
        Version version2 = new Version("1.0.0");
        assertEquals(0, version1.compareTo(version2));
    }

    @Test
    public void testCompareToDifferentMajorVersion() {
        Version version1 = new Version("1.0.0");
        Version version2 = new Version("2.0.0");
        assertTrue(version1.compareTo(version2) < 0);
        assertTrue(version2.compareTo(version1) > 0);
    }

    @Test
    public void testCompareToDifferentMinorVersion() {
        Version version1 = new Version("1.1.0");
        Version version2 = new Version("1.2.0");
        assertTrue(version1.compareTo(version2) < 0);
        assertTrue(version2.compareTo(version1) > 0);
    }

    @Test
    public void testCompareToDifferentPatchVersion() {
        Version version1 = new Version("1.1.1");
        Version version2 = new Version("1.1.2");
        assertTrue(version1.compareTo(version2) < 0);
        assertTrue(version2.compareTo(version1) > 0);
    }

    @Test
    public void testCompareToDifferentLengthVersions() {
        Version version1 = new Version("1.0");
        Version version2 = new Version("1.0.0");
        assertTrue(version1.compareTo(version2) < 0);
        assertTrue(version2.compareTo(version1) > 0);
    }

    @Test
    public void testEquals() {
        Version version1 = new Version("1.0.0");
        Version version2 = new Version("1.0.0");
        Version version3 = new Version("1.2.0");
        assertTrue(version1.equals(version2));
        assertFalse(version1.equals(version3));
    }

    @Test
    public void testHashCodeEqualVersions() {
        Version version1 = new Version("1.0.0");
        Version version2 = new Version("1.0.0");
        assertEquals(version1.hashCode(), version2.hashCode());
    }

    @Test
    public void testHashCodeDifferentVersions() {
        Version version1 = new Version("1.0.0");
        Version version2 = new Version("1.2.0");
        assertNotEquals(version1.hashCode(), version2.hashCode());
    }

    @Test
    public void testToString() {
        Version version = new Version("1.0.0");
        assertEquals("1.0.0", version.toString());
    }

    @Test(expected = NumberFormatException.class)
    public void testInvalidVersionFormat() {
        new Version("1.a.0");
    }

    @Test
    public void testCompareWithNullVersion() {
        Version version = new Version("1.0.0");
        assertNotEquals(null, version);
    }
}
```