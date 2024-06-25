```java
package org.example.run1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByteVectorTest {

    @Test
    void testDefaultConstructor() {
        ByteVector byteVector = new ByteVector();
        assertEquals(0, byteVector.length);
        assertEquals(64, byteVector.data.length);
    }

    @Test
    void testConstructorWithInitialSize() {
        ByteVector byteVector = new ByteVector(128);
        assertEquals(0, byteVector.length);
        assertEquals(128, byteVector.data.length);
    }

    @Test
    void testPutByte() {
        ByteVector byteVector = new ByteVector();
        byteVector.putByte(0x12);
        assertEquals(1, byteVector.length);
        assertEquals((byte) 0x12, byteVector.data[0]);
    }

    @Test
    void testPut11() {
        ByteVector byteVector = new ByteVector();
        byteVector.put11(0x12, 0x34);
        assertEquals(2, byteVector.length);
        assertEquals((byte) 0x12, byteVector.data[0]);
        assertEquals((byte) 0x34, byteVector.data[1]);
    }

    @Test
    void testPutShort() {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(0x1234);
        assertEquals(2, byteVector.length);
        assertEquals((byte) 0x12, byteVector.data[0]);
        assertEquals((byte) 0x34, byteVector.data[1]);
    }

    @Test
    void testPut12() {
        ByteVector byteVector = new ByteVector();
        byteVector.put12(0x12, 0x1234);
        assertEquals(3, byteVector.length);
        assertEquals((byte) 0x12, byteVector.data[0]);
        assertEquals((byte) 0x12, byteVector.data[1]);
        assertEquals((byte) 0x34, byteVector.data[2]);
    }

    @Test
    void testPutInt() {
        ByteVector byteVector = new ByteVector();
        byteVector.putInt(0x12345678);
        assertEquals(4, byteVector.length);
        assertEquals((byte) 0x12, byteVector.data[0]);
        assertEquals((byte) 0x34, byteVector.data[1]);
        assertEquals((byte) 0x56, byteVector.data[2]);
        assertEquals((byte) 0x78, byteVector.data[3]);
    }

    @Test
    void testPutLong() {
        ByteVector byteVector = new ByteVector();
        byteVector.putLong(0x123456789ABCDEF0L);
        assertEquals(8, byteVector.length);
        assertEquals((byte) 0x12, byteVector.data[0]);
        assertEquals((byte) 0x34, byteVector.data[1]);
        assertEquals((byte) 0x56, byteVector.data[2]);
        assertEquals((byte) 0x78, byteVector.data[3]);
        assertEquals((byte) 0x9A, byteVector.data[4]);
        assertEquals((byte) 0xBC, byteVector.data[5]);
        assertEquals((byte) 0xDE, byteVector.data[6]);
        assertEquals((byte) 0xF0, byteVector.data[7]);
    }

    @Test
    void testPutUTF8() {
        ByteVector byteVector = new ByteVector();
        byteVector.putUTF8("test");
        assertEquals(6, byteVector.length);
        assertEquals((byte) 0, byteVector.data[0]);
        assertEquals((byte) 4, byteVector.data[1]);
        assertEquals((byte) 't', byteVector.data[2]);
        assertEquals((byte) 'e', byteVector.data[3]);
        assertEquals((byte) 's', byteVector.data[4]);
        assertEquals((byte) 't', byteVector.data[5]);
    }

    @Test
    void testPutByteArray() {
        ByteVector byteVector = new ByteVector();
        byte[] byteArray = {0x01, 0x02, 0x03};
        byteVector.putByteArray(byteArray, 0, byteArray.length);
        assertEquals(3, byteVector.length);
        assertArrayEquals(byteArray, java.util.Arrays.copyOf(byteVector.data, 3));
    }

    @Test
    void testPutByteArrayOverflow() {
        ByteVector byteVector = new ByteVector(1);
        byte[] byteArray = {0x01, 0x02, 0x03};
        byteVector.putByteArray(byteArray, 0, byteArray.length);
        assertEquals(3, byteVector.length);
        assertTrue(byteVector.data.length > 3);
    }

    @Test
    void testEnlarge() {
        ByteVector byteVector = new ByteVector(2);
        byteVector.putInt(0x12345678);
        assertEquals(4, byteVector.length);
        assertTrue(byteVector.data.length > 4);
    }
}
```