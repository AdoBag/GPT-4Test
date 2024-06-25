```java
package org.example.run1;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class bcWordTest {

   private bcWord word1;
   private bcWord word2;
   private bcWord word3;

   @Before
   public void setUp() {
      word1 = new bcWord("battlecry", "B AH0 T AH0 L K R AY1");
      word2 = new bcWord("cry", "K R AY1");
      word3 = new bcWord("examination", "IH0 G Z AE2 M AH0 N EY1 SH AH0 N");
   }

   @Test
   public void testGetWord() {
      assertEquals("battlecry", word1.getWord());
      assertEquals("cry", word2.getWord());
      assertEquals("examination", word3.getWord());
   }

   @Test
   public void testGetPhonemes() {
      assertEquals("B AH0 T AH0 L K R AY1", word1.getPhonemes());
      assertEquals("K R AY1", word2.getPhonemes());
      assertEquals("IH0 G Z AE2 M AH0 N EY1 SH AH0 N", word3.getPhonemes());
   }

   @Test
   public void testGetSyllables() {
      assertEquals(3, word1.getSyllables());
      assertEquals(1, word2.getSyllables());
      assertEquals(5, word3.getSyllables());
   }
   
   @Test
   public void testGetMetricCode() {
      assertEquals("000I", word1.getMetricCode());
      assertEquals("I", word2.getMetricCode());
      assertEquals("0Z0I0", word3.getMetricCode());
   }
   
   @Test
   public void testSetEqualWord() {
      word1.setEqualWord("test", "T EH1 S T");
      assertEquals("test", word1.getWord());
      assertEquals("T EH1 S T", word1.getPhonemes());
   }

   @Test
   public void testEqualTo() {
      assertTrue(word1.equalTo("battlecry"));
      assertFalse(word1.equalTo("cry"));
      assertTrue(word2.equalTo("cry"));
      assertFalse(word2.equalTo("battlecry"));
   }

   @Test
   public void testGetRhymeKey() {
      assertEquals("AY1", word1.getRhymeKey(true));
      assertEquals("R AY1", word1.getRhymeKey(false));
      assertEquals("AY1", word2.getRhymeKey(true));
      assertEquals("R AY1", word2.getRhymeKey(false));
      assertEquals("EY1 SH AH0 N", word3.getRhymeKey(true));
      assertEquals("AH0 N EY1 SH AH0 N", word3.getRhymeKey(false));
   }
}
```