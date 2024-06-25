```java
package org.example.run1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ByteVectorTest {

    private ByteVector byteVector;

    @BeforeEach
    void setUp() {
        byteVector = new ByteVector();
    }

    @Test
    void testDefaultConstructor() {
        assertEquals(0, byteVector.length);
        assertEquals(64, byteVector.data.length);
    }

    @Test
    void testConstructorWithInitialSize() {
        ByteVector bv = new ByteVector(10);
        assertEquals(0, bv.length);
        assertEquals(10, bv.data.length);
    }

    @Test
    void testPutByte() {
        byteVector.putByte(0x1A);
        assertEquals(1, byteVector.length);
        assertEquals(0x1A, byteVector.data[0]);
    }

    @Test
    void testPut11() {
        byteVector.put11(0x1A, 0x2B);
        assertEquals(2, byteVector.length);
        assertEquals(0x1A, byteVector.data[0]);
        assertEquals(0x2B, byteVector.data[1]);
    }

    @Test
    void testPutShort() {
        byteVector.putShort(0x1234);
        assertEquals(2, byteVector.length);
        assertEquals(0x12, byteVector.data[0]);
        assertEquals(0x34, byteVector.data[1]);
    }

    @Test
    void testPut12() {
        byteVector.put12(0x1A, 0x1234);
        assertEquals(3, byteVector.length);
        assertEquals(0x1A, byteVector.data[0]);
        assertEquals(0x12, byteVector.data[1]);
        assertEquals(0x34, byteVector.data[2]);
    }

    @Test
    void testPutInt() {
        byteVector.putInt(0x12345678);
        assertEquals(4, byteVector.length);
        assertEquals(0x12, byteVector.data[0]);
        assertEquals(0x34, byteVector.data[1]);
        assertEquals(0x56, byteVector.data[2]);
        assertEquals(0x78, byteVector.data[3]);
    }

    @Test
    void testPutLong() {
        byteVector.putLong(0x123456789ABCDEF0L);
        assertEquals(8, byteVector.length);
        assertEquals(0x12, byteVector.data[0]);
        assertEquals(0x34, byteVector.data[1]);
        assertEquals(0x56, byteVector.data[2]);
        assertEquals(0x78, byteVector.data[3]);
        assertEquals(0x9A, byteVector.data[4]);
        assertEquals(0xBC, byteVector.data[5]);
        assertEquals(0xDE, byteVector.data[6]);
        assertEquals(0xF0, byteVector.data[7]);
    }

    @Test
    void testPutUTF8() {
        byteVector.putUTF8("hello");
        assertEquals(7, byteVector.length); // 2 bytes for the length + 5 bytes for the string
        assertEquals(0x00, byteVector.data[0]);
        assertEquals(0x05, byteVector.data[1]);
        assertEquals((byte) 'h', byteVector.data[2]);
        assertEquals((byte) 'e', byteVector.data[3]);
        assertEquals((byte) 'l', byteVector.data[4]);
        assertEquals((byte) 'l', byteVector.data[5]);
        assertEquals((byte) 'o', byteVector.data[6]);
    }

    @Test
    void testPutByteArray() {
        byte[] bytes = {0x01, 0x02, 0x03, 0x04};
        byteVector.putByteArray(bytes, 1, 2);
        assertEquals(2, byteVector.length);
        assertEquals(0x02, byteVector.data[0]);
        assertEquals(0x03, byteVector.data[1]);
    }

    @Test
    void testEnlarge() {
        ByteVector bv = new ByteVector(2);
        bv.putByte(0x1A).putByte(0x2B).putByte(0x3C);
        assertEquals(3, bv.length);
        assertTrue(bv.data.length > 2);
    }
}
```