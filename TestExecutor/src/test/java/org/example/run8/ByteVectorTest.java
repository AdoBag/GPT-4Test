
package org.example.run8;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ByteVectorTest {

    private ByteVector byteVector;

    @BeforeEach
    void setUp() {
        byteVector = new ByteVector();
    }

    @Test
    void testDefaultConstructor() {
        ByteVector vector = new ByteVector();
        assertEquals(0, vector.length);
        assertNotNull(vector.data);
        assertEquals(64, vector.data.length);
    }

    @Test
    void testConstructorWithInitialSize() {
        ByteVector vector = new ByteVector(128);
        assertEquals(0, vector.length);
        assertNotNull(vector.data);
        assertEquals(128, vector.data.length);
    }

    @Test
    void testPutByte() {
        byteVector.putByte(255);
        assertEquals(1, byteVector.length);
        assertEquals((byte) 255, byteVector.data[0]);
    }

    @Test
    void testPut11() {
        byteVector.put11(10, 20);
        assertEquals(2, byteVector.length);
        assertEquals((byte) 10, byteVector.data[0]);
        assertEquals((byte) 20, byteVector.data[1]);
    }

    @Test
    void testPutShort() {
        byteVector.putShort(1025);
        assertEquals(2, byteVector.length);
        assertEquals((byte) 4, byteVector.data[0]);
        assertEquals((byte) 1, byteVector.data[1]);
    }

    @Test
    void testPut12() {
        byteVector.put12(255, 1025);
        assertEquals(3, byteVector.length);
        assertEquals((byte) 255, byteVector.data[0]);
        assertEquals((byte) 4, byteVector.data[1]);
        assertEquals((byte) 1, byteVector.data[2]);
    }

    @Test
    void testPutInt() {
        byteVector.putInt(16777217);
        assertEquals(4, byteVector.length);
        assertEquals((byte) 1, byteVector.data[0]);
        assertEquals((byte) 0, byteVector.data[1]);
        assertEquals((byte) 0, byteVector.data[2]);
        assertEquals((byte) 1, byteVector.data[3]);
    }

    @Test
    void testPutLong() {
        byteVector.putLong(4294967296L);
        assertEquals(8, byteVector.length);
        assertEquals((byte) 0, byteVector.data[0]);
        assertEquals((byte) 0, byteVector.data[1]);
        assertEquals((byte) 0, byteVector.data[2]);
        assertEquals((byte) 1, byteVector.data[3]);
        assertEquals((byte) 0, byteVector.data[4]);
        assertEquals((byte) 0, byteVector.data[5]);
        assertEquals((byte) 0, byteVector.data[6]);
        assertEquals((byte) 0, byteVector.data[7]);
    }

    @Test
    void testPutUTF8() {
        String testString = "Hello";
        byteVector.putUTF8(testString);
        assertEquals(7, byteVector.length);
        assertEquals((byte) 0, byteVector.data[0]);
        assertEquals((byte) 5, byteVector.data[1]);
        assertEquals((byte) 'H', byteVector.data[2]);
        assertEquals((byte) 'e', byteVector.data[3]);
        assertEquals((byte) 'l', byteVector.data[4]);
        assertEquals((byte) 'l', byteVector.data[5]);
        assertEquals((byte) 'o', byteVector.data[6]);
    }

//    @Test
//    void testPutByteArray() {
//        byte[] testArray = {1, 2, 3, 4, 5};
//        byteVector.putByteArray(testArray, 0, testArray.length);
//        assertEquals(5, byteVector.length);
//        assertArrayEquals(testArray, byteVector.data);
//    }
    /**
     * Manual fix of the above test
     * Problem: ByteVector is initialised with the size of 64
     */
    @Test
    void testPutByteArray__fixed() {
        byte[] testArray = {1, 2, 3, 4, 5};
        byteVector.putByteArray(testArray, 0, testArray.length);
        assertEquals(5, byteVector.length);
        byte[] result_array = new byte[64];
        for (int i = 0; i < 5; i++)
            result_array[i] = (byte) (i + 1);
        assertArrayEquals(result_array, byteVector.data);
    }

//    @Test
//    void testEnlarge() {
//        ByteVector smallVector = new ByteVector(2);
//        smallVector.putInt(123456789);
//        assertTrue(smallVector.data.length >= 6);
//    }
    /**
     * Manual fix of the above test
     * Problem: The number can be stored in 4 bytes
     */
    @Test
    void testEnlarge__fixed() {
        ByteVector smallVector = new ByteVector(2);
        smallVector.putInt(123456789);
        System.out.println(smallVector.data.length);
        assertTrue(smallVector.data.length >= 4);
    }
}
