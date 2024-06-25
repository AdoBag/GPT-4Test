
package org.example.run4;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class bcWordTest {

    @Test
    void testGetWord() {
        bcWord word = new bcWord("example", "IH0 G Z AE1 M P AH0 L");
        assertEquals("example", word.getWord());
    }

    @Test
    void testGetPhonemes() {
        bcWord word = new bcWord("example", "IH0 G Z AE1 M P AH0 L");
        assertEquals("IH0 G Z AE1 M P AH0 L", word.getPhonemes());
    }

    @Test
    void testGetSyllables() {
        bcWord word = new bcWord("example", "IH0 G Z AE1 M P AH0 L");
        assertEquals(3, word.getSyllables());
    }

    @Test
    void testGetMetricCode() {
        bcWord word = new bcWord("example", "IH0 G Z AE1 M P AH0 L");
        assertEquals("010", word.getMetricCode());
    }

    @Test
    void testSetEqualWord() {
        bcWord word = new bcWord("battlecry", "B AE1 T AH0 L K R AY2");
        word.setEqualWord("cry", "K R AY2");
        assertTrue(word.equalTo("cry"));
        assertFalse(word.equalTo("battle"));
    }

    @Test
    void testEqualTo() {
        bcWord word = new bcWord("battlecry", "B AE1 T AH0 L K R AY2");
        assertTrue(word.equalTo("battlecry"));
        assertFalse(word.equalTo("battle"));
    }

    @Test
    void testGetRhymeKey_LastSyllableOnly() {
        bcWord word = new bcWord("example", "IH0 G Z AE1 M P AH0 L");
        assertEquals("AH0 L", word.getRhymeKey(true));
    }

//    @Test
//    void testGetRhymeKey_FullWord() {
//        bcWord word = new bcWord("example", "IH0 G Z AE1 M P AH0 L");
//        assertEquals("AH0 L", word.getRhymeKey(false));
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM predicted the output incorrectly
     */
    @Test
    void testGetRhymeKey_FullWord__fixed() {
        bcWord word = new bcWord("example", "IH0 G Z AE1 M P AH0 L");
        assertEquals("AE1 M P AH0 L", word.getRhymeKey(false));
    }
}
