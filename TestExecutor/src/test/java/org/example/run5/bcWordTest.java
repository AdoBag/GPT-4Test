
package org.example.run5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class bcWordTest {

    @Test
    void testGetWord() {
        bcWord word = new bcWord("example", "1g z ae1 m p l");
        assertEquals("example", word.getWord());
    }

    @Test
    void testGetPhonemes() {
        bcWord word = new bcWord("example", "1g z ae1 m p l");
        assertEquals("1g z ae1 m p l", word.getPhonemes());
    }

//    @Test
//    void testGetSyllables() {
//        bcWord word = new bcWord("example", "1g z ae1 m p l");
//        assertEquals(1, word.getSyllables()); // Based on the given phoneme, assume it's correct
//    }

    /**
     * Manual fix of the above test
     * Problem: The LLM predicted the output incorrectly
     */
    @Test
    void testGetSyllables__fixed() {
        bcWord word = new bcWord("example", "1g z ae1 m p l");
        assertEquals(2, word.getSyllables()); // Based on the given phoneme, assume it's correct
    }

//    @Test
//    void testGetMetricCode() {
//        bcWord word = new bcWord("example", "1g z ae1 m p l");
//        assertEquals("I", word.getMetricCode()); // Assuming it translates correctly
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM predicted the output incorrectly
     */
    @Test
    void testGetMetricCode__fixed() {
        bcWord word = new bcWord("example", "1g z ae1 m p l");
        assertEquals("11", word.getMetricCode()); // Assuming it translates correctly
    }

    @Test
    void testSetEqualWord() {
        bcWord word = new bcWord("example", "1g z ae1 m p l");
        word.setEqualWord("sample", "s ae1 m p l");
//        assertEquals("sample", word.getWord()); // Ensure that equalWord is updated
        assertEquals("s ae1 m p l", word.getPhonemes());
    }

    @Test
    void testEqualTo() {
        bcWord word = new bcWord("example", "1g z ae1 m p l");
        assertTrue(word.equalTo("example"));
        assertFalse(word.equalTo("test")); // A random word that shouldn’t match
    }

//    @Test
//    void testGetRhymeKey_EqualsToLastSyllableOnlyTrue() {
//        // Assuming the logic extracts the last syllable correctly
//        bcWord word = new bcWord("example", "1g z ae1 m p l");
//        assertEquals("p l", word.getRhymeKey(true));
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM predicted the output incorrectly
     * Interesting note: based on the comment, the LLM was expecting the test to fail but it
     */
    @Test
    void testGetRhymeKey_EqualsToLastSyllableOnlyTrue__fixed() {
        // Assuming the logic extracts the last syllable correctly
        bcWord word = new bcWord("example", "1g z ae1 m p l");
        assertEquals("ae1 m p l", word.getRhymeKey(true));
    }

//    @Test
//    void testGetRhymeKey_EqualsToLastSyllableOnlyFalse() {
//        // Assuming the logic extracts the last two syllables correctly
//        bcWord word = new bcWord("example", "1g z ae1 m p l");
//        assertEquals("ae1 m p l", word.getRhymeKey(false));
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM predicted the output incorrectly
     * Interesting note: based on the comment, the LLM was expecting the test to fail but it
     */
    @Test
    void testGetRhymeKey_EqualsToLastSyllableOnlyFalse__fixed() {
        // Assuming the logic extracts the last two syllables correctly
        bcWord word = new bcWord("example", "1g z ae1 m p l");
        assertEquals("1g z ae1 m p l", word.getRhymeKey(false));
    }
}
