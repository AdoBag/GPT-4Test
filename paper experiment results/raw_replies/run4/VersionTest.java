```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VersionTest {

    @Test
    void testVersionEquality() {
        Version version1 = new Version("1.0.0");
        Version version2 = new Version("1.0.0");
        assertEquals(version1, version2);
    }

    @Test
    void testVersionInequality() {
        Version version1 = new Version("1.0.0");
        Version version2 = new Version("1.0.1");
        assertNotEquals(version1, version2);
    }

    @Test
    void testVersionCompareToEqual() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.2.3");
        assertEquals(0, version1.compareTo(version2));
    }

    @Test
    void testVersionCompareToLessThan() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.2.4");
        assertTrue(version1.compareTo(version2) < 0);
    }

    @Test
    void testVersionCompareToGreaterThan() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.2.2");
        assertTrue(version1.compareTo(version2) > 0);
    }

    @Test
    void testVersionCompareDifferentLengths() {
        Version version1 = new Version("1.2");
        Version version2 = new Version("1.2.0");
        assertTrue(version1.compareTo(version2) < 0);

        Version version3 = new Version("1.2.1");
        assertTrue(version3.compareTo(version2) > 0);
    }

    @Test
    void testVersionWithDifferentNumberSegments() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.2.3.4");
        assertTrue(version1.compareTo(version2) < 0);
    }

    @Test
    void testVersionHashCodeConsistency() {
        Version version1 = new Version("1.0.0");
        Version version2 = new Version("1.0.0");
        assertEquals(version1.hashCode(), version2.hashCode());
    }

    @Test
    void testVersionToString() {
        String versionStr = "1.2.3";
        Version version = new Version(versionStr);
        assertEquals(versionStr, version.toString());
    }

    @Test
    void testVersionNull() {
        Version version = new Version(null);
        assertEquals(0, version.hashCode());
        assertTrue(version.compareTo(new Version(null)) == 0);
    }

    @Test
    void testVersionWithAdditionalSeparators() {
        Version version1 = new Version("1.2-3");
        Version version2 = new Version("1.2.3");
        assertEquals(version1, version2);

        Version version3 = new Version("1_2_3");
        assertEquals(version1, version3);
    }
}
```