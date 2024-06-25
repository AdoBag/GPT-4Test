```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ByteVectorTest {

    @Test
    void testDefaultConstructor() {
        ByteVector bv = new ByteVector();
        assertEquals(64, bv.data.length);
        assertEquals(0, bv.length);
    }

    @Test
    void testConstructorWithInitialSize() {
        ByteVector bv = new ByteVector(100);
        assertEquals(100, bv.data.length);
        assertEquals(0, bv.length);
    }

    @Test
    void testPutByte() {
        ByteVector bv = new ByteVector(1);
        bv.putByte(1);
        assertEquals(1, bv.length);
        assertEquals(1, bv.data[0]);
    }

    @Test
    void testPut11() {
        ByteVector bv = new ByteVector(2);
        bv.put11(1, 2);
        assertEquals(2, bv.length);
        assertEquals(1, bv.data[0]);
        assertEquals(2, bv.data[1]);
    }

    @Test
    void testPutShort() {
        ByteVector bv = new ByteVector(2);
        bv.putShort(258); // 0x0102 in hexadecimal
        assertEquals(2, bv.length);
        assertEquals(1, bv.data[0]);
        assertEquals(2, bv.data[1]);
    }

    @Test
    void testPut12() {
        ByteVector bv = new ByteVector(3);
        bv.put12(1, 258);
        assertEquals(3, bv.length);
        assertEquals(1, bv.data[0]);
        assertEquals(1, bv.data[1]);
        assertEquals(2, bv.data[2]);
    }

    @Test
    void testPutInt() {
        ByteVector bv = new ByteVector(4);
        bv.putInt(16909060); // 0x01020304 in hexadecimal
        assertEquals(4, bv.length);
        assertEquals(1, bv.data[0]);
        assertEquals(2, bv.data[1]);
        assertEquals(3, bv.data[2]);
        assertEquals(4, bv.data[3]);
    }

    @Test
    void testPutLong() {
        ByteVector bv = new ByteVector(8);
        bv.putLong(72623859790382856L); // 0x0102030405060708 in hexadecimal
        assertEquals(8, bv.length);
        assertEquals(1, bv.data[0]);
        assertEquals(2, bv.data[1]);
        assertEquals(3, bv.data[2]);
        assertEquals(4, bv.data[3]);
        assertEquals(5, bv.data[4]);
        assertEquals(6, bv.data[5]);
        assertEquals(7, bv.data[6]);
        assertEquals(8, bv.data[7]);
    }

    @Test
    void testPutUTF8() {
        ByteVector bv = new ByteVector(10);
        bv.putUTF8("hello");
        assertEquals(7, bv.length); // 5 chars + 2 bytes for length
        assertEquals(0, bv.data[0]);
        assertEquals(5, bv.data[1]);
        assertEquals((byte) 'h', bv.data[2]);
        assertEquals((byte) 'e', bv.data[3]);
        assertEquals((byte) 'l', bv.data[4]);
        assertEquals((byte) 'l', bv.data[5]);
        assertEquals((byte) 'o', bv.data[6]);
    }

    @Test
    void testPutByteArray() {
        ByteVector bv = new ByteVector(5);
        byte[] arr = {1, 2, 3, 4, 5};
        bv.putByteArray(arr, 0, arr.length);
        assertEquals(5, bv.length);
        assertArrayEquals(arr, bv.data);
    }

    @Test
    void testEnlarge() {
        ByteVector bv = new ByteVector(1);
        bv.putByte(1);
        bv.putByte(2);
        assertEquals(2, bv.length);
        assertEquals(1, bv.data[0]);
        assertEquals(2, bv.data[1]);
    }
}
```