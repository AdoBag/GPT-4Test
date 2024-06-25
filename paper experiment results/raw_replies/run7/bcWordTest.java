```java
package org.example.run1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class bcWordTest {

    @Test
    void testConstructor() {
        bcWord word = new bcWord("test", "t eh s t");
        assertEquals("test", word.getWord());
        assertEquals("t eh s t", word.getPhonemes());
        assertEquals(1, word.getSyllables());
        assertEquals("I", word.getMetricCode());
    }

    @Test
    void testGetWord() {
        bcWord word = new bcWord("test", "t eh s t");
        assertEquals("test", word.getWord());
    }

    @Test
    void testGetPhonemes() {
        bcWord word = new bcWord("test", "t eh s t");
        assertEquals("t eh s t", word.getPhonemes());
    }

    @Test
    void testGetSyllables() {
        bcWord word = new bcWord("test", "t eh s t");
        assertEquals(1, word.getSyllables());
    }

    @Test
    void testGetMetricCode() {
        bcWord word = new bcWord("test", "t eh s t");
        assertEquals("I", word.getMetricCode());
        
        bcWord word2 = new bcWord("testing", "t eh s t ih ng");
        assertEquals("II", word2.getMetricCode());
    }
    
    @Test
    void testSetEqualWord() {
        bcWord word = new bcWord("test", "t eh s t");
        word.setEqualWord("check", "ch eh k");
        assertEquals("check", word.getWord());
        assertEquals("ch eh k", word.getPhonemes());
    }

    @Test
    void testEqualTo() {
        bcWord word = new bcWord("battlecry", "b ae t l k r ay");
        assertTrue(word.equalTo("battlecry"));
        assertFalse(word.equalTo("cry"));
        
        word.setEqualWord("cry", "k r ay");
        assertTrue(word.equalTo("cry"));
    }

    @Test
    void testGetRhymeKey() {
        bcWord word = new bcWord("battle", "b ae t el");
        assertEquals("el", word.getRhymeKey(false));
        assertEquals("el", word.getRhymeKey(true));
        
        bcWord word2 = new bcWord("battlecry", "b ae t l k r ay");
        assertEquals("ay", word2.getRhymeKey(true));
    }
}
```