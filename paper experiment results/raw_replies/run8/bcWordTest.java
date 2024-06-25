```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class bcWordTest {

    @Test
    void testConstructorAndGetters() {
        String word = "battlecry";
        String phonemes = "B AE T L K R AY";
        bcWord bcWord = new bcWord(word, phonemes);

        assertEquals(word, bcWord.getWord());
        assertEquals(phonemes, bcWord.getPhonemes());
        assertEquals(3, bcWord.getSyllables());
        assertEquals("OI", bcWord.getMetricCode()); // B AE (0), T L (1), K R (I)
    }

    @Test
    void testSetEqualWord() {
        String word = "battlecry";
        String phonemes = "B AE T L K R AY";
        bcWord bcWord = new bcWord(word, phonemes);

        String newWord = "cry";
        String newPhonemes = "K R AY";
        bcWord.setEqualWord(newWord, newPhonemes);

        assertTrue(bcWord.equalTo("cry"));
        assertTrue(bcWord.equalTo(newWord));
        assertFalse(bcWord.equalTo("battlecry"));
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
    void testParsePhonemes() {
        String word = "amplify";
        String phonemes = "AE M P L IH F AY";
        bcWord bcWord = new bcWord(word, phonemes);

        assertEquals(3, bcWord.getSyllables());
        assertEquals("OIZ", bcWord.getMetricCode());
    }

    @Test
    void testGetRhymeKey() {
        bcWord bcWord1 = new bcWord("battlecry", "B AE T L K R AY");
        assertEquals("AY", bcWord1.getRhymeKey(true));

        bcWord bcWord2 = new bcWord("amplify", "AE M P L IH F AY");
        assertEquals("F AY", bcWord2.getRhymeKey(true));
        
        bcWord bcWord3 = new bcWord("beautiful", "B Y UW T IH F AH L");
        assertEquals("F AH L", bcWord3.getRhymeKey(false));
        assertEquals("AH L", bcWord3.getRhymeKey(true));
    }
}
```