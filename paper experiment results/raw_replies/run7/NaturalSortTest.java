```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NaturalSortTest {

    @Test
    public void testCompare() {
        assertEquals(-1, NaturalSort.compare("Photo 7.jpeg", "Photo 17.jpeg"));
        assertEquals(1, NaturalSort.compare("Photo 17.jpeg", "Photo 7.jpeg"));
        assertEquals(0, NaturalSort.compare("Photo 7.jpeg", "Photo 7.jpeg"));
        assertEquals(-1, NaturalSort.compare("file 007.txt", "file 008.txt"));
        assertEquals(1, NaturalSort.compare("file 009.txt", "file 008.txt"));
        assertEquals(-1, NaturalSort.compare("image9.png", "image10.png"));
        assertEquals(1, NaturalSort.compare("image20.png", "image10.png"));
    }

    @Test
    public void testCompareIgnoreCase() {
        assertEquals(-1, NaturalSort.compareIgnoreCase("Photo 7.jpeg", "photo 17.jpeg"));
        assertEquals(1, NaturalSort.compareIgnoreCase("photo 17.jpeg", "Photo 7.jpeg"));
        assertEquals(0, NaturalSort.compareIgnoreCase("Photo 7.jpeg", "photo 7.jpeg"));
        assertEquals(0, NaturalSort.compareIgnoreCase("file 007.txt", "FILE 007.txt"));
        assertEquals(-1, NaturalSort.compareIgnoreCase("file 007.txt", "FILE 008.txt"));
        assertEquals(1, NaturalSort.compareIgnoreCase("file 009.txt", "FILE 008.txt"));
        assertEquals(-1, NaturalSort.compareIgnoreCase("image12.png", "IMAGE20.png"));
        assertEquals(1, NaturalSort.compareIgnoreCase("image20.png", "IMAGE10.png"));
    }

    @Test
    public void testNaturalComparator() {
        List<String> list = new ArrayList<>();
        list.add("Photo 17.jpeg");
        list.add("Photo 7.jpeg");
        list.add("Photo 10.jpeg");
        list.add("Photo 2.jpeg");
        
        Collections.sort(list, new NaturalSort.NaturalComparator());
        
        assertEquals("Photo 2.jpeg", list.get(0));
        assertEquals("Photo 7.jpeg", list.get(1));
        assertEquals("Photo 10.jpeg", list.get(2));
        assertEquals("Photo 17.jpeg", list.get(3));
    }

    @Test
    public void testCINaturalComparator() {
        List<String> list = new ArrayList<>();
        list.add("Photo 17.jpeg");
        list.add("photo 7.jpeg");
        list.add("Photo 10.jpeg");
        list.add("photo 2.jpeg");
        
        Collections.sort(list, new NaturalSort.CINaturalComparator());
        
        assertEquals("photo 2.jpeg", list.get(0));
        assertEquals("photo 7.jpeg", list.get(1));
        assertEquals("Photo 10.jpeg", list.get(2));
        assertEquals("Photo 17.jpeg", list.get(3));
    }
}
```