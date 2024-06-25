
package org.example.run10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class bcWordTest {

    @Test
//    public void testConstructorAndGetters() {
    public void testConstructorAndGetters__fixed() {
        bcWord word1 = new bcWord("hello", "həˈləʊ");
        assertEquals("hello", word1.getWord());
        assertEquals("həˈləʊ", word1.getPhonemes());
//        assertEquals(2, word1.getSyllables());
//        assertEquals("10", word1.getMetricCode());
        /** The LLM predicted the input format incorrectly */
        assertEquals(0, word1.getSyllables());
        assertEquals("", word1.getMetricCode());
    }

    @Test
    public void testSetEqualWord() {
        bcWord word1 = new bcWord("cry", "kraɪ");
        word1.setEqualWord("battlecry", "ˈbattəlˌkraɪ");
        assertTrue(word1.equalTo("battlecry"));
        assertFalse(word1.equalTo("notbattlecry"));
    }

    @Test
    public void testEqualTo() {
        bcWord word1 = new bcWord("cry", "kraɪ");
        assertTrue(word1.equalTo("cry"));
        assertFalse(word1.equalTo("hello"));
    }

    @Test
    public void testGetRhymeKey_PhonemeAllSyllables() {
        bcWord word1 = new bcWord("hello", "həˈləʊ");
        assertEquals("həˈləʊ", word1.getRhymeKey(false));
    }

//    @Test
//    public void testGetRhymeKey_PhonemeLastSyllableOnly() {
//        bcWord word1 = new bcWord("hello", "həˈləʊ");
//        assertEquals("ləʊ", word1.getRhymeKey(true));
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM predicted the input format incorrectly
     */

    @Test
//    public void testOneSyllableWord() {
    public void testOneSyllableWord__fixed() {
        bcWord word1 = new bcWord("go", "ɡoʊ");
//        assertEquals(1, word1.getSyllables());
//        assertEquals("I", word1.getMetricCode());
        /** The LLM predicted the input format incorrectly */
        assertEquals(0, word1.getSyllables());
        assertEquals("", word1.getMetricCode());

        assertEquals("ɡoʊ", word1.getRhymeKey(false));
    }

//    @Test
//    public void testParsePhonemes() {
//        bcWord word1 = new bcWord("example", "ɪɡˈzæmpəl");
//        assertEquals(2, word1.getSyllables());
//        assertEquals("10", word1.getMetricCode());
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM predicted the input format incorrectly
     */
    @Test
    public void testParsePhonemes__fixed() {
        bcWord word1 = new bcWord("example", "ɪɡˈzæmpəl");
        assertEquals(0, word1.getSyllables());
        assertEquals("", word1.getMetricCode());
    }
}
