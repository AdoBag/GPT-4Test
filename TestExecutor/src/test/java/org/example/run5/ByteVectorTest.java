
package org.example.run5;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ByteVectorTest {
    
    private ByteVector byteVector;

    @BeforeEach
    public void setUp() {
        byteVector = new ByteVector();
    }

    @Test
    public void testDefaultConstructor() {
        byteVector = new ByteVector();
        assertNotNull(byteVector.data);
        assertEquals(0, byteVector.length);
        assertEquals(64, byteVector.data.length);
    }

    @Test
    public void testCustomConstructor() {
        byteVector = new ByteVector(128);
        assertNotNull(byteVector.data);
        assertEquals(0, byteVector.length);
        assertEquals(128, byteVector.data.length);
    }

    @Test
    public void testPutByte() {
        byteVector.putByte(0x12);
        assertEquals(1, byteVector.length);
        assertEquals(0x12, byteVector.data[0]);
    }

    @Test
    public void testPut11() {
        byteVector.put11(0x12, 0x34);
        assertEquals(2, byteVector.length);
        assertEquals(0x12, byteVector.data[0]);
        assertEquals(0x34, byteVector.data[1]);
    }

    @Test
    public void testPutShort() {
        byteVector.putShort(0x1234);
        assertEquals(2, byteVector.length);
        assertEquals((byte)(0x1234 >>> 8), byteVector.data[0]);
        assertEquals((byte)0x1234, byteVector.data[1]);
    }

    @Test
    public void testPut12() {
        byteVector.put12(0x12, 0x1234);
        assertEquals(3, byteVector.length);
        assertEquals(0x12, byteVector.data[0]);
        assertEquals((byte)(0x1234 >>> 8), byteVector.data[1]);
        assertEquals((byte)0x1234, byteVector.data[2]);
    }

    @Test
    public void testPutInt() {
        byteVector.putInt(0x12345678);
        assertEquals(4, byteVector.length);
        assertEquals((byte)(0x12345678 >>> 24), byteVector.data[0]);
        assertEquals((byte)(0x12345678 >>> 16), byteVector.data[1]);
        assertEquals((byte)(0x12345678 >>> 8), byteVector.data[2]);
        assertEquals((byte)0x12345678, byteVector.data[3]);
    }
    
    @Test
    public void testPutLong() {
        byteVector.putLong(0x123456789ABCDEF0L);
        assertEquals(8, byteVector.length);
        assertEquals((byte)(0x123456789ABCDEF0L >>> 56), byteVector.data[0]);
        assertEquals((byte)(0x123456789ABCDEF0L >>> 48), byteVector.data[1]);
        assertEquals((byte)(0x123456789ABCDEF0L >>> 40), byteVector.data[2]);
        assertEquals((byte)(0x123456789ABCDEF0L >>> 32), byteVector.data[3]);
        assertEquals((byte)(0x123456789ABCDEF0L >>> 24), byteVector.data[4]);
        assertEquals((byte)(0x123456789ABCDEF0L >>> 16), byteVector.data[5]);
        assertEquals((byte)(0x123456789ABCDEF0L >>> 8), byteVector.data[6]);
        assertEquals((byte)0x123456789ABCDEF0L, byteVector.data[7]);
    }

    @Test
    public void testPutUTF8() {
        String str = "hello";
        byteVector.putUTF8(str);
        assertEquals(7, byteVector.length); // 2 for length + 5 for "hello"
        assertEquals(str.length(), byteVector.data[1]);
        assertEquals('h', byteVector.data[2]);
        assertEquals('e', byteVector.data[3]);
        assertEquals('l', byteVector.data[4]);
        assertEquals('l', byteVector.data[5]);
        assertEquals('o', byteVector.data[6]);
    }

//    @Test
//    public void testPutByteArray() {
//        byte[] array = {0x01, 0x02, 0x03};
//        byteVector.putByteArray(array, 0, array.length);
//        assertEquals(3, byteVector.length);
//        assertArrayEquals(array, byteVector.data);
//    }
    /**
     * Manual fix of the above test
     * Problem: ByteVector is initialised with the size of 64
     */
    @Test
    public void testPutByteArray__fixed() {
        byte[] array = {0x01, 0x02, 0x03};
        byteVector.putByteArray(array, 0, array.length);
        assertEquals(3, byteVector.length);
        byte[] result_array = new byte[64];
        result_array[0] = 0x01;
        result_array[1] = 0x02;
        result_array[2] = 0x03;
        assertArrayEquals(result_array, byteVector.data);
    }

    @Test
    public void testEnlarge() {
        byteVector = new ByteVector(2);
        byteVector.putInt(0x12345678); // should trigger enlarge
        assertTrue(byteVector.data.length >= 4); // ensure the array is enlarged
        assertEquals(4, byteVector.length);
    }
}
