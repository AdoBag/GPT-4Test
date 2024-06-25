```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class bcWordTest {

    @Test
    void testGetWord() {
        bcWord word = new bcWord("battle", "B AE1 T AH0 L");
        assertEquals("battle", word.getWord());
    }

    @Test
    void testGetPhonemes() {
        bcWord word = new bcWord("battle", "B AE1 T AH0 L");
        assertEquals("B AE1 T AH0 L", word.getPhonemes());
    }

    @Test
    void testGetSyllables() {
        bcWord word = new bcWord("battle", "B AE1 T AH0 L");
        assertEquals(2, word.getSyllables());
        
        word = new bcWord("cry", "K R AY1");
        assertEquals(1, word.getSyllables());
    }

    @Test
    void testGetMetricCode() {
        bcWord word = new bcWord("battle", "B AE1 T AH0 L");
        assertEquals("10", word.getMetricCode());
        
        word = new bcWord("cry", "K R AY1");
        assertEquals("I", word.getMetricCode());
    }

    @Test
    void testSetEqualWord() {
        bcWord word = new bcWord("battlecry", "B AE1 T AH0 L K R AY1");
        word.setEqualWord("cry", "K R AY1");
        
        assertTrue(word.equalTo("cry"));
        assertTrue(word.equalTo("battlecry"));
        assertFalse(word.equalTo("battle"));
    }

    @Test
    void testEqualTo() {
        bcWord word = new bcWord("Battle", "B AE1 T AH0 L");

        assertTrue(word.equalTo("battle"));
        assertFalse(word.equalTo("cry"));
    }

    @Test
    void testGetRhymeKey() {
        bcWord word = new bcWord("battlecry", "B AE1 T AH0 L K R AY1");

        // Check the last syllable only
        assertEquals("K R AY1", word.getRhymeKey(true));

        // Check the last two syllables
        assertEquals("T AH0 L K R AY1", word.getRhymeKey(false));
    }
}
```