
package org.example.run3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VersionTest {

    @Test
    public void testVersionEquality() {
        Version version1 = new Version("2.1.4");
        Version version2 = new Version("2.1.4");
        assertEquals(version1, version2);
    }

    @Test
    public void testVersionComparisonLessThan() {
        Version version1 = new Version("2.1.4");
        Version version2 = new Version("2.1.10");
        assertTrue(version1.compareTo(version2) < 0);
    }

    @Test
    public void testVersionComparisonGreaterThan() {
        Version version1 = new Version("2.1.10");
        Version version2 = new Version("2.1.4");
        assertTrue(version1.compareTo(version2) > 0);
    }

    @Test
    public void testVersionComparisonEqual() {
        Version version1 = new Version("2.1.4");
        Version version2 = new Version("2.1.4");
        assertTrue(version1.compareTo(version2) == 0);
    }

    @Test
    public void testVersionComparisonDifferentLengths() {
        Version version1 = new Version("2.1.4.1");
        Version version2 = new Version("2.1.4");
        assertTrue(version1.compareTo(version2) > 0);

        Version version3 = new Version("2.1");
        Version version4 = new Version("2.1.4");
        assertTrue(version3.compareTo(version4) < 0);
    }

    @Test
    public void testVersionWithDifferentDelimiters() {
        Version version1 = new Version("2.1-4");
        Version version2 = new Version("2.1.4");
        assertEquals(version1, version2);
    }

    @Test
    public void testVersionHashCode() {
        Version version1 = new Version("2.1.4");
        Version version2 = new Version("2.1.4");
        assertEquals(version1.hashCode(), version2.hashCode());
    }

    @Test
    public void testAsString() {
        Version version = new Version("2.1.4");
        assertEquals("2.1.4", version.toString());
    }

    @Test
    public void testEqualsWithNull() {
        Version version1 = new Version("2.1.4");
        assertFalse(version1.equals(null));
    }

    @Test
    public void testEqualsWithDifferentClass() {
        Version version1 = new Version("2.1.4");
        assertFalse(version1.equals("2.1.4"));
    }
}
