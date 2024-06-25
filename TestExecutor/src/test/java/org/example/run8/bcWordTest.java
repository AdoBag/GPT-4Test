
package org.example.run8;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class bcWordTest {

    @Test
//    void testConstructorAndGetters() {
    void testConstructorAndGetters__fixed() {
        String word = "battlecry";
        String phonemes = "B AE T L K R AY";
        bcWord bcWord = new bcWord(word, phonemes);

        assertEquals(word, bcWord.getWord());
        assertEquals(phonemes, bcWord.getPhonemes());
//        assertEquals(3, bcWord.getSyllables());
//        assertEquals("OI", bcWord.getMetricCode()); // B AE (0), T L (1), K R (I)
        /** The LLM predicted the input format incorrectly */
        assertEquals(0, bcWord.getSyllables());
        assertEquals("", bcWord.getMetricCode()); // B AE (0), T L (1), K R (I)
    }

    @Test
//    void testSetEqualWord() {
    void testSetEqualWord__fixed() {
        String word = "battlecry";
        String phonemes = "B AE T L K R AY";
        bcWord bcWord = new bcWord(word, phonemes);

        String newWord = "cry";
        String newPhonemes = "K R AY";
        bcWord.setEqualWord(newWord, newPhonemes);

        assertTrue(bcWord.equalTo("cry"));
        assertTrue(bcWord.equalTo(newWord));
//        assertFalse(bcWord.equalTo("battlecry"));
        /** The LLM failed to work with this method */
        assertTrue(bcWord.equalTo("battlecry"));
    }
    
    @Test
    void testEqualTo() {
        String word = "battlecry";
        String phonemes = "B AE T L K R AY";
        bcWord bcWord = new bcWord(word, phonemes);

        assertTrue(bcWord.equalTo("battlecry"));
        assertFalse(bcWord.equalTo("cry"));

        bcWord.setEqualWord("cry", "K R AY");
        assertTrue(bcWord.equalTo("cry"));
        assertTrue(bcWord.equalTo("battlecry"));
    }

    @Test
//    void testParsePhonemes() {
    void testParsePhonemes__fixed() {
        String word = "amplify";
        String phonemes = "AE M P L IH F AY";
        bcWord bcWord = new bcWord(word, phonemes);

//        assertEquals(3, bcWord.getSyllables());
//        assertEquals("OIZ", bcWord.getMetricCode());
        /** The LLM predicted the input format incorrectly */
        assertEquals(0, bcWord.getSyllables());
        assertEquals("", bcWord.getMetricCode());
    }

//    @Test
//    void testGetRhymeKey() {
//        bcWord bcWord1 = new bcWord("battlecry", "B AE T L K R AY");
//        assertEquals("AY", bcWord1.getRhymeKey(true));
//
//        bcWord bcWord2 = new bcWord("amplify", "AE M P L IH F AY");
//        assertEquals("F AY", bcWord2.getRhymeKey(true));
//
//        bcWord bcWord3 = new bcWord("beautiful", "B Y UW T IH F AH L");
//        assertEquals("F AH L", bcWord3.getRhymeKey(false));
//        assertEquals("AH L", bcWord3.getRhymeKey(true));
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM predicted the input format incorrectly
     */
    @Test
    void testGetRhymeKey__fixed() {
        bcWord bcWord1 = new bcWord("battlecry", "B AE T L K R AY");
        assertEquals("B AE T L K R AY", bcWord1.getRhymeKey(true));
        bcWord bcWord2 = new bcWord("amplify", "AE M P L IH F AY");
        assertEquals("AE M P L IH F AY", bcWord2.getRhymeKey(true));
        bcWord bcWord3 = new bcWord("beautiful", "B Y UW T IH F AH L");
        assertEquals("B Y UW T IH F AH L", bcWord3.getRhymeKey(false));
        assertEquals("B Y UW T IH F AH L", bcWord3.getRhymeKey(true));
    }
}
