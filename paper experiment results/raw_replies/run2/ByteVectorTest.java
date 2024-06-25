```java
package org.example.run1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ByteVectorTest {

    @Test
    public void testConstructorDefaultSize() {
        ByteVector byteVector = new ByteVector();
        assertEquals(64, byteVector.data.length);
        assertEquals(0, byteVector.length);
    }

    @Test
    public void testConstructorInitialSize() {
        ByteVector byteVector = new ByteVector(128);
        assertEquals(128, byteVector.data.length);
        assertEquals(0, byteVector.length);
    }

    @Test
    public void testPutByte() {
        ByteVector byteVector = new ByteVector();
        byteVector.putByte(1);
        assertEquals(1, byteVector.length);
        assertEquals(1, byteVector.data[0]);
    }

    @Test
    public void testPut11() {
        ByteVector byteVector = new ByteVector();
        byteVector.put11(1, 2);
        assertEquals(2, byteVector.length);
        assertEquals(1, byteVector.data[0]);
        assertEquals(2, byteVector.data[1]);
    }

    @Test
    public void testPutShort() {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(256);
        assertEquals(2, byteVector.length);
        assertEquals(1, byteVector.data[0]);
        assertEquals(0, byteVector.data[1]);
    }

    @Test
    public void testPut12() {
        ByteVector byteVector = new ByteVector();
        byteVector.put12(1, 256);
        assertEquals(3, byteVector.length);
        assertEquals(1, byteVector.data[0]);
        assertEquals(1, byteVector.data[1]);
        assertEquals(0, byteVector.data[2]);
    }

    @Test
    public void testPutInt() {
        ByteVector byteVector = new ByteVector();
        byteVector.putInt(1);
        assertEquals(4, byteVector.length);
        assertEquals(0, byteVector.data[0]);
        assertEquals(0, byteVector.data[1]);
        assertEquals(0, byteVector.data[2]);
        assertEquals(1, byteVector.data[3]);
    }

    @Test
    public void testPutLong() {
        ByteVector byteVector = new ByteVector();
        byteVector.putLong(1L);
        assertEquals(8, byteVector.length);
        for (int i = 0; i < 7; ++i) {
            assertEquals(0, byteVector.data[i]);
        }
        assertEquals(1, byteVector.data[7]);
    }

    @Test
    public void testPutUTF8() {
        ByteVector byteVector = new ByteVector();
        String testString = "test";
        byteVector.putUTF8(testString);
        assertEquals(2 + testString.length(), byteVector.length);
        assertEquals(0, byteVector.data[0]);
        assertEquals(4, byteVector.data[1]);
        assertEquals('t', byteVector.data[2]);
        assertEquals('e', byteVector.data[3]);
        assertEquals('s', byteVector.data[4]);
        assertEquals('t', byteVector.data[5]);
    }

    @Test
    public void testPutByteArray() {
        ByteVector byteVector = new ByteVector();
        byte[] testArray = {1, 2, 3, 4};
        byteVector.putByteArray(testArray, 0, testArray.length);
        assertEquals(testArray.length, byteVector.length);
        for (int i = 0; i < testArray.length; ++i) {
            assertEquals(testArray[i], byteVector.data[i]);
        }
    }

    @Test
    public void testEnlarge() {
        ByteVector byteVector = new ByteVector(1);
        byteVector.putByte(1);
        assertEquals(1, byteVector.length);
        byteVector.putByte(2);
        assertEquals(2, byteVector.length);
        assertTrue(byteVector.data.length > 1);
    }
}
```