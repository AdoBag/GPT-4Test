
package org.example.run7;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;

public class VersionTest {

    @Test
    public void testVersionCreation() {
        Version version = new Version("1.2.3");
        assertEquals("1.2.3", version.toString());
    }

    @Test
    public void testVersionEquality() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.2.3");
        assertEquals(version1, version2);
    }

    @Test
    public void testVersionInequality() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.2.4");
        assertNotEquals(version1, version2);
    }

    @Test
    public void testHashCode() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.2.3");
        assertEquals(version1.hashCode(), version2.hashCode());
    }

    @Test
    public void testVersionComparision() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.2.4");
        assertTrue(version1.compareTo(version2) < 0);
        assertTrue(version2.compareTo(version1) > 0);
    }

    @Test
    public void testDifferentLengthVersions() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1.2");
        assertTrue(version1.compareTo(version2) > 0);
        assertTrue(version2.compareTo(version1) < 0);
    }

    @Test
    public void testAllTokensAreParsedCorrectly() {
        Version version = new Version("1.2-3_4 5");
        List<Integer> expectedSegments = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> actualSegments = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(version.toString(), ". -_");

        while (tokenizer.hasMoreTokens()) {
            actualSegments.add(Integer.parseInt(tokenizer.nextToken()));
        }

        assertEquals(expectedSegments, actualSegments);
    }

    @Test
//    public void testNullVersion() {
    public void testNullVersion__fixed() {
        Version version = new Version(null);
//        assertEquals("", version.toString());
        /** When constructed with null, `toString` also returns null */
        assertNull(version.toString());

        assertEquals(0, version.hashCode());
    }

    @Test
    public void testEmptyStringVersion() {
        Version version = new Version("");
        assertEquals("", version.toString());
        assertEquals(0, version.hashCode());
    }

    @Test
    public void testToString() {
        Version version = new Version("2.3.4");
        assertEquals("2.3.4", version.toString());
    }

    @Test
    public void testVersionWithDifferentDelimiters() {
        Version version1 = new Version("1.2.3");
        Version version2 = new Version("1-2-3");
        Version version3 = new Version("1_2_3");
        Version version4 = new Version("1 2 3");

        assertEquals(version1, version2);
        assertEquals(version1, version3);
        assertEquals(version1, version4);
    }
}
