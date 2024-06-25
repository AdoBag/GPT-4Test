```java
package org.example.run1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ByteVectorTest {

    @Test
    void testDefaultConstructor() {
        ByteVector bv = new ByteVector();
        assertNotNull(bv.data);
        assertEquals(64, bv.data.length);
        assertEquals(0, bv.length);
    }

    @Test
    void testConstructorWithInitialSize() {
        ByteVector bv = new ByteVector(128);
        assertNotNull(bv.data);
        assertEquals(128, bv.data.length);
        assertEquals(0, bv.length);
    }

    @Test
    void testPutByte() {
        ByteVector bv = new ByteVector();
        bv.putByte(0xAB);
        assertEquals(1, bv.length);
        assertEquals((byte)0xAB, bv.data[0]);
    }

    @Test
    void testPut11() {
        ByteVector bv = new ByteVector();
        bv.put11(0xAB, 0xCD);
        assertEquals(2, bv.length);
        assertEquals((byte)0xAB, bv.data[0]);
        assertEquals((byte)0xCD, bv.data[1]);
    }

    @Test
    void testPutShort() {
        ByteVector bv = new ByteVector();
        bv.putShort(0x1234);
        assertEquals(2, bv.length);
        assertEquals((byte)0x12, bv.data[0]);
        assertEquals((byte)0x34, bv.data[1]);
    }

    @Test
    void testPut12() {
        ByteVector bv = new ByteVector();
        bv.put12(0xAB, 0x1234);
        assertEquals(3, bv.length);
        assertEquals((byte)0xAB, bv.data[0]);
        assertEquals((byte)0x12, bv.data[1]);
        assertEquals((byte)0x34, bv.data[2]);
    }

    @Test
    void testPutInt() {
        ByteVector bv = new ByteVector();
        bv.putInt(0x12345678);
        assertEquals(4, bv.length);
        assertEquals((byte)0x12, bv.data[0]);
        assertEquals((byte)0x34, bv.data[1]);
        assertEquals((byte)0x56, bv.data[2]);
        assertEquals((byte)0x78, bv.data[3]);
    }

    @Test
    void testPutLong() {
        ByteVector bv = new ByteVector();
        bv.putLong(0x123456789ABCDEF0L);
        assertEquals(8, bv.length);
        assertEquals((byte)0x12, bv.data[0]);
        assertEquals((byte)0x34, bv.data[1]);
        assertEquals((byte)0x56, bv.data[2]);
        assertEquals((byte)0x78, bv.data[3]);
        assertEquals((byte)0x9A, bv.data[4]);
        assertEquals((byte)0xBC, bv.data[5]);
        assertEquals((byte)0xDE, bv.data[6]);
        assertEquals((byte)0xF0, bv.data[7]);
    }

    @Test
    void testPutUTF8() {
        ByteVector bv = new ByteVector();
        bv.putUTF8("hello");
        assertEquals(7, bv.length); // 2 bytes for length + 5 bytes for "hello"
        assertEquals((byte)0x00, bv.data[0]);
        assertEquals((byte)0x05, bv.data[1]);
        assertEquals((byte)'h', bv.data[2]);
        assertEquals((byte)'e', bv.data[3]);
        assertEquals((byte)'l', bv.data[4]);
        assertEquals((byte)'l', bv.data[5]);
        assertEquals((byte)'o', bv.data[6]);
    }

    @Test
    void testPutByteArray() {
        ByteVector bv = new ByteVector();
        byte[] array = {0x01, 0x02, 0x03, 0x04};
        bv.putByteArray(array, 1, 2);
        assertEquals(2, bv.length);
        assertEquals((byte)0x02, bv.data[0]);
        assertEquals((byte)0x03, bv.data[1]);
    }

    @Test
    void testEnlarge() {
        ByteVector bv = new ByteVector(2);
        byte[] array = {0x01, 0x02, 0x03, 0x04};
        bv.putByteArray(array, 0, 4);
        assertEquals(4, bv.length);
        assertTrue(bv.data.length > 2); // data array should have enlarged
    }
}
```