
package org.example.run6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class bcWordTest {

    private bcWord word;

    @BeforeEach
    void setUp() {
        // Example word and phonemes for testing.
        word = new bcWord("battlecry", "B AE T AH L K R AY");
    }

    @Test
    void testGetWord() {
        assertEquals("battlecry", word.getWord());
    }

    @Test
    void testGetPhonemes() {
        assertEquals("B AE T AH L K R AY", word.getPhonemes());
    }

//    @Test
//    void testGetSyllables() {
//        assertEquals(2, word.getSyllables()); // Adjust based on correct syllable count from parsePhonemes
//    }
    /**
     * Manual fix of the above test
     * Problem: In this run the LLM did not understand the input format of this class
     * Interesting note: based on the comment, the LLM was expecting the test to fail but it
     */
    @Test
    void testGetSyllables__fixed() {
        assertEquals(0, word.getSyllables()); // Adjust based on correct syllable count from parsePhonemes
    }
    
//    @Test
//    void testGetMetricCode() {
//        assertEquals("I", word.getMetricCode()); // Adjust based on correct metric code from parsePhonemes
//    }
    /**
     * Manual fix of the above test
     * Problem: In this run the LLM did not understand the input format of this class
     * Interesting note: based on the comment, the LLM was expecting the test to fail but it
     */
    @Test
    void testGetMetricCode__fixed() {
        assertEquals("", word.getMetricCode()); // Adjust based on correct metric code from parsePhonemes
    }

    @Test
    void testSetEqualWord() {
        word.setEqualWord("cry", "K R AY");
        assertTrue(word.equalTo("cry"));
    }

    @Test
    void testEqualTo() {
        bcWord anotherWord = new bcWord("battlecry", "B AE T AH L K R AY");
        assertTrue(word.equalTo("battlecry"));
        assertFalse(word.equalTo("different"));
    }

//    @Test
//    void testGetRhymeKey() {
//        assertEquals("R AY", word.getRhymeKey(true));
//        assertEquals("AH L K R AY", word.getRhymeKey(false));
//    }
    /**
     * Manual fix of the above test
     * Problem: In this run the LLM did not understand the input format of this class
     */
    @Test
    void testGetRhymeKey__fixed() {
        assertEquals("B AE T AH L K R AY", word.getRhymeKey(true));
        assertEquals("B AE T AH L K R AY", word.getRhymeKey(false));
    }

//    @Test
//    void testSyllableCountWithDifferentPhonemes() {
//        bcWord word2 = new bcWord("hello", "HH AH L OW");
//        assertEquals(2, word2.getSyllables());
//    }
    /**
     * Manual fix of the above test
     * Problem: In this run the LLM did not understand the input format of this class
     */
    @Test
    void testSyllableCountWithDifferentPhonemes__fixed() {
        bcWord word2 = new bcWord("hello", "HH AH L OW");
        assertEquals(0, word2.getSyllables());
    }

//    @Test
//    void testMetricCodeWithDifferentPhonemes() {
//        bcWord word2 = new bcWord("hello", "HH AH L OW");
//        assertEquals("I", word2.getMetricCode()); // Adjust based on correct metric code from parsePhonemes
//    }
    /**
     * Manual fix of the above test
     * Problem: In this run the LLM did not understand the input format of this class.
     * Interesting note: based on the comment, the LLM was expecting the test to fail but it
     */
    @Test
    void testMetricCodeWithDifferentPhonemes__fixed() {
        bcWord word2 = new bcWord("hello", "HH AH L OW");
        assertEquals("", word2.getMetricCode()); // Adjust based on correct metric code from parsePhonemes
    }
}
