
package org.example.run1;

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
    public void testConstructorWithDefaultSize() {
        assertNotNull(byteVector.data);
        assertEquals(64, byteVector.data.length);
    }

    @Test
    public void testConstructorWithInitialSize() {
        int initialSize = 128;
        byteVector = new ByteVector(initialSize);
        assertNotNull(byteVector.data);
        assertEquals(initialSize, byteVector.data.length);
    }

    @Test
    public void testPutByte() {
        byteVector.putByte(0x12);
        assertEquals(1, byteVector.length);
        assertEquals((byte) 0x12, byteVector.data[0]);
    }

    @Test
    public void testPut11() {
        byteVector.put11(0x12, 0x34);
        assertEquals(2, byteVector.length);
        assertEquals((byte) 0x12, byteVector.data[0]);
        assertEquals((byte) 0x34, byteVector.data[1]);
    }

    @Test
    public void testPutShort() {
        byteVector.putShort(0x1234);
        assertEquals(2, byteVector.length);
        assertEquals((byte) 0x12, byteVector.data[0]);
        assertEquals((byte) 0x34, byteVector.data[1]);
    }

    @Test
    public void testPut12() {
        byteVector.put12(0x12, 0x3456);
        assertEquals(3, byteVector.length);
        assertEquals((byte) 0x12, byteVector.data[0]);
        assertEquals((byte) 0x34, byteVector.data[1]);
        assertEquals((byte) 0x56, byteVector.data[2]);
    }

    @Test
    public void testPutInt() {
        byteVector.putInt(0x12345678);
        assertEquals(4, byteVector.length);
        assertEquals((byte) 0x12, byteVector.data[0]);
        assertEquals((byte) 0x34, byteVector.data[1]);
        assertEquals((byte) 0x56, byteVector.data[2]);
        assertEquals((byte) 0x78, byteVector.data[3]);
    }

    @Test
    public void testPutLong() {
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
    public void testPutUTF8() {
        String testString = "Hello";
        byteVector.putUTF8(testString);
        assertEquals(7, byteVector.length);
        assertEquals((byte) 0x00, byteVector.data[0]); // length byte 1
        assertEquals((byte) 0x05, byteVector.data[1]); // length byte 2
        assertEquals((byte) 'H', byteVector.data[2]);
        assertEquals((byte) 'e', byteVector.data[3]);
        assertEquals((byte) 'l', byteVector.data[4]);
        assertEquals((byte) 'l', byteVector.data[5]);
        assertEquals((byte) 'o', byteVector.data[6]);
    }

    @Test
    public void testPutByteArray() {
        byte[] byteArray = { 0x12, 0x34, 0x56 };
        byteVector.putByteArray(byteArray, 0, byteArray.length);
        assertEquals(3, byteVector.length);
        assertEquals((byte) 0x12, byteVector.data[0]);
        assertEquals((byte) 0x34, byteVector.data[1]);
        assertEquals((byte) 0x56, byteVector.data[2]);
    }

    @Test
    public void testPutNullByteArray() {
        byteVector.putByteArray(null, 0, 3);
        assertEquals(3, byteVector.length);
        assertEquals((byte) 0x00, byteVector.data[0]);
        assertEquals((byte) 0x00, byteVector.data[1]);
        assertEquals((byte) 0x00, byteVector.data[2]);
    }
}
