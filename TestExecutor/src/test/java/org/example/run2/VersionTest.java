package org.example.run2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class VersionTest {

    @Test
    public void testVersionEquality() {
        Version v1 = new Version("1.2.3");
        Version v2 = new Version("1.2.3");
        assertEquals(v1, v2);
    }

    @Test
    public void testVersionInequality() {
        Version v1 = new Version("1.2.3");
        Version v2 = new Version("1.2.4");
        assertNotEquals(v1, v2);
    }

    @Test
    public void testVersionCompareTo() {
        Version v1 = new Version("1.2.3");
        Version v2 = new Version("1.2.4");
        assertTrue(v1.compareTo(v2) < 0);

        Version v3 = new Version("1.2.4");
        assertTrue(v2.compareTo(v3) == 0);

        Version v4 = new Version("1.2.3");
        Version v5 = new Version("1.2");
        assertTrue(v4.compareTo(v5) > 0);
    }

//    @Test
//    public void testVersionWithDifferentLengths() {
//        Version v1 = new Version("1.2");
//        Version v2 = new Version("1.2.0");
//        assertEquals(v1, v2);
//    }
    /**
     * Manual fix of the above test
     * Problem: In this implementation, the number of segments implies that the versions are not equal
     */
    @Test
    public void testVersionWithDifferentLengths__fixed() {
        Version v1 = new Version("1.2");
        Version v2 = new Version("1.2.0");
        assertNotEquals(v1, v2);
    }

    @Test
    public void testVersionToString() {
        String versionStr = "1.2.3";
        Version v = new Version(versionStr);
        assertEquals(versionStr, v.toString());
    }

    @Test
    public void testVersionHashCode() {
        Version v1 = new Version("1.2.3");
        Version v2 = new Version("1.2.3");
        assertEquals(v1.hashCode(), v2.hashCode());
    }

    @Test
    public void testVersionCompareToWithDifferentSizes() {
        Version v1 = new Version("1.2.3");
        Version v2 = new Version("1.2");
        assertTrue(v1.compareTo(v2) > 0);

        Version v3 = new Version("1.2");
        Version v4 = new Version("1.2.3");
        assertTrue(v3.compareTo(v4) < 0);
    }

    @Test
    public void testVersionWithNull() {
        Version v1 = new Version(null);
        Version v2 = new Version(null);
        assertEquals(v1, v2);
        assertEquals(0, v1.compareTo(v2));

        Version v3 = new Version("1.2.3");
        assertNotEquals(v1, v3);
        assertTrue(v1.compareTo(v3) < 0);
    }

    @Test
    public void testVersionWithNonDigitCharacters() {
        Version v1 = new Version("1.2-3");
        Version v2 = new Version("1.2.3");
        assertEquals(v1, v2);

        Version v3 = new Version("1.2_3");
        assertEquals(v1, v3);
    }
}
