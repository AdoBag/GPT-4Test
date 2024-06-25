
package org.example.run8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VersionTest {

    @Test
    void testVersionEquality() {
        Version version1 = new Version("2.1.4");
        Version version2 = new Version("2.1.4");
        assertEquals(version1, version2);
        assertEquals(version1.hashCode(), version2.hashCode());
    }

    @Test
    void testVersionInequality() {
        Version version1 = new Version("2.1.4");
        Version version2 = new Version("2.1.10");
        assertNotEquals(version1, version2);
        assertNotEquals(version1.hashCode(), version2.hashCode());
    }

    @Test
    void testVersionComparison_LessThan() {
        Version version1 = new Version("2.1.4");
        Version version2 = new Version("2.1.10");
        assertTrue(version1.compareTo(version2) < 0);
    }

    @Test
    void testVersionComparison_GreaterThan() {
        Version version1 = new Version("2.1.10");
        Version version2 = new Version("2.1.4");
        assertTrue(version1.compareTo(version2) > 0);
    }

    @Test
    void testVersionComparison_Equal() {
        Version version1 = new Version("2.1.10");
        Version version2 = new Version("2.1.10");
        assertTrue(version1.compareTo(version2) == 0);
    }

    @Test
    void testDifferentLengths_LessThan() {
        Version version1 = new Version("2.1");
        Version version2 = new Version("2.1.1");
        assertTrue(version1.compareTo(version2) < 0);
    }

    @Test
    void testDifferentLengths_GreaterThan() {
        Version version1 = new Version("2.1.1");
        Version version2 = new Version("2.1");
        assertTrue(version1.compareTo(version2) > 0);
    }

    @Test
    void testToString() {
        Version version = new Version("2.1.4");
        assertEquals("2.1.4", version.toString());
    }

    @Test
    void testVersionWithDifferentSeparators() {
        Version version1 = new Version("2-1-4");
        Version version2 = new Version("2.1.4");
        assertEquals(version1, version2);
        assertEquals(version1.hashCode(), version2.hashCode());
    }

    @Test
    void testEmptyVersion() {
        Version version = new Version("");
        assertEquals("", version.toString());
        assertEquals(0, version.hashCode());
    }

    @Test
    void testNullVersion() {
        Version version = new Version(null);
        assertNull(version.toString());
        assertEquals(0, version.hashCode());
    }
}
