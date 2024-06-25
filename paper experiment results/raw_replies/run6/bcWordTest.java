```java
package org.example.run1;

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

    @Test
    void testGetSyllables() {
        assertEquals(2, word.getSyllables()); // Adjust based on correct syllable count from parsePhonemes
    }
    
    @Test
    void testGetMetricCode() {
        assertEquals("I", word.getMetricCode()); // Adjust based on correct metric code from parsePhonemes
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

    @Test
    void testGetRhymeKey() {
        assertEquals("R AY", word.getRhymeKey(true));
        assertEquals("AH L K R AY", word.getRhymeKey(false));
    }

    @Test
    void testSyllableCountWithDifferentPhonemes() {
        bcWord word2 = new bcWord("hello", "HH AH L OW");
        assertEquals(2, word2.getSyllables());
    }

    @Test
    void testMetricCodeWithDifferentPhonemes() {
        bcWord word2 = new bcWord("hello", "HH AH L OW");
        assertEquals("I", word2.getMetricCode()); // Adjust based on correct metric code from parsePhonemes
    }
}
```