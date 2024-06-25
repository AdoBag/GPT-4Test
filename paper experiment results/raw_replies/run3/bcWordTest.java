```java
package org.example.run1;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class bcWordTest {

    private bcWord word;

    @BeforeEach
    void setUp() {
        word = new bcWord("example", "IH0 G Z AE1 M P L");
    }

    @Test
    void testGetWord() {
        assertEquals("example", word.getWord());
    }

    @Test
    void testGetPhonemes() {
        assertEquals("IH0 G Z AE1 M P L", word.getPhonemes());
    }

    @Test
    void testGetSyllables() {
        assertEquals(2, word.getSyllables());
    }
    
    @Test
    void testGetMetricCode() {
        assertEquals("01", word.getMetricCode());
    }

    @Test
    void testSetEqualWord() {
        word.setEqualWord("test", "T EH1 S T");
        assertTrue(word.equalTo("test"));
        assertFalse(word.equalTo("example"));
    }

    @Test
    void testEqualTo() {
        assertTrue(word.equalTo("example"));
        assertFalse(word.equalTo("test"));
    }

    @Test
    void testGetRhymeKey() {
        assertEquals("AE1 M P L", word.getRhymeKey(false));
        assertEquals("AE1 M P L", word.getRhymeKey(true));
    }

    @Test
    void testSingleSyllableWord() {
        bcWord singleSyllableWord = new bcWord("dog", "D AO1 G");
        assertEquals("AO1 G", singleSyllableWord.getRhymeKey(false));
        assertEquals(1, singleSyllableWord.getSyllables());
        assertEquals("I", singleSyllableWord.getMetricCode());
    }

    @Test
    void testNoSyllablePhonemes() {
        bcWord noSyllableWord = new bcWord("grrrr", "G R R R R");
        assertEquals("", noSyllableWord.getRhymeKey(false));
        assertEquals(0, noSyllableWord.getSyllables());
        assertEquals("", noSyllableWord.getMetricCode());
    }
}
```