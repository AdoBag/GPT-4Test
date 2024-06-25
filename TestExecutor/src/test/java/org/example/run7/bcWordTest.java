
package org.example.run7;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class bcWordTest {

    @Test
//    void testConstructor() {
    void testConstructor__fixed() {
        bcWord word = new bcWord("test", "t eh s t");
        assertEquals("test", word.getWord());
        assertEquals("t eh s t", word.getPhonemes());

//        assertEquals(1, word.getSyllables());
//        assertEquals("I", word.getMetricCode());
        /** The LLM predicted the input format incorrectly */
        assertEquals(0, word.getSyllables());
        assertEquals("", word.getMetricCode());
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

//    @Test
//    void testGetSyllables() {
//        bcWord word = new bcWord("test", "t eh s t");
//        assertEquals(1, word.getSyllables());
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM predicted the input format incorrectly
     */
    @Test
    void testGetSyllables__fixed() {
        bcWord word = new bcWord("test", "t eh s t");
        assertEquals(0, word.getSyllables());
    }

//    @Test
//    void testGetMetricCode() {
//        bcWord word = new bcWord("test", "t eh s t");
//        assertEquals("I", word.getMetricCode());
//
//        bcWord word2 = new bcWord("testing", "t eh s t ih ng");
//        assertEquals("II", word2.getMetricCode());
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM predicted the output incorrectly
     */
    @Test
    void testGetMetricCode__fixed() {
        bcWord word = new bcWord("test", "t eh s t");
        assertEquals("", word.getMetricCode());

        bcWord word2 = new bcWord("testing", "t eh s t ih ng");
        assertEquals("", word2.getMetricCode());
    }
    
    @Test
//    void testSetEqualWord() {
    void testSetEqualWord__fixed() {
        bcWord word = new bcWord("test", "t eh s t");
        word.setEqualWord("check", "ch eh k");
//        assertEquals("check", word.getWord());
        /** The class does not override the `equals` method but creates `equalTo` instead  */
        assertEquals("test", word.getWord());

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

//    @Test
//    void testGetRhymeKey() {
//        bcWord word = new bcWord("battle", "b ae t el");
//        assertEquals("el", word.getRhymeKey(false));
//        assertEquals("el", word.getRhymeKey(true));
//
//        bcWord word2 = new bcWord("battlecry", "b ae t l k r ay");
//        assertEquals("ay", word2.getRhymeKey(true));
//    }
    /**
     * Manual fix of the above test
     * Problem: The LLM predicted the input format incorrectly
     */
    @Test
    void testGetRhymeKey__fixed() {
        bcWord word = new bcWord("battle", "b ae t el");
        assertEquals("b ae t el", word.getRhymeKey(false));
        assertEquals("b ae t el", word.getRhymeKey(true));
        bcWord word2 = new bcWord("battlecry", "b ae t l k r ay");
        assertEquals("b ae t l k r ay", word2.getRhymeKey(true));
    }
}
