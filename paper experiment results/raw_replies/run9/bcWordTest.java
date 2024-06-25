```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class bcWordTest {

    @Test
    void testGetWord() {
        bcWord word = new bcWord("battle", "B AE T L");
        assertEquals("battle", word.getWord());
    }

    @Test
    void testGetPhonemes() {
        bcWord word = new bcWord("fight", "F AY T");
        assertEquals("F AY T", word.getPhonemes());
    }

    @Test
    void testGetSyllables() {
        bcWord word = new bcWord("battle", "B AE T L");
        assertEquals(2, word.getSyllables());
    }

    @Test
    void testGetMetricCode() {
        bcWord word = new bcWord("battle", "B AE T L");
        assertEquals("O", word.getMetricCode());
        
        bcWord word2 = new bcWord("defend", "D IH F EH N D");
        assertEquals("O", word2.getMetricCode());
    }

    @Test
    void testSetEqualWord() {
        bcWord word = new bcWord("cry", "K R AY");
        word.setEqualWord("cry", "K R AY");
        assertEquals("cry", word.getWord());
        assertEquals("K R AY", word.getPhonemes());
    }

    @Test
    void testEqualTo() {
        bcWord word = new bcWord("battle", "B AE T L");
        assertTrue(word.equalTo("battle"));
        assertTrue(word.equalTo("BATTLE"));
        assertFalse(word.equalTo("fight"));
    }

    @Test
    void testGetRhymeKey() {
        bcWord word = new bcWord("battle", "B AE T L");
        assertEquals("T L", word.getRhymeKey(true));
        assertEquals("B AE T L", word.getRhymeKey(false));

        bcWord word2 = new bcWord("defend", "D IH F EH N D");
        assertEquals("EH N D", word2.getRhymeKey(true));
    }
    
    @Test
    void testSingleSyllableWords() {
        bcWord word = new bcWord("win", "W IH N");
        assertEquals(1, word.getSyllables());
        assertEquals("I", word.getMetricCode());
        assertEquals("IH N", word.getRhymeKey(true));
    }
    
    @Test
    void testTwoSyllableWords() {
        bcWord word = new bcWord("combat", "K AH M B AE T");
        assertEquals(2, word.getSyllables());
        assertEquals("O", word.getMetricCode());
        assertEquals("AH M B AE T", word.getRhymeKey(false));
    }

    @Test
    void testMultipleSyllableWords() {
        bcWord word = new bcWord("strategy", "S T R AE T AH J IY");
        assertEquals(3, word.getSyllables());
        assertEquals("O", word.getMetricCode());
        assertEquals("AE T AH J IY", word.getRhymeKey(false));
    }
}
```