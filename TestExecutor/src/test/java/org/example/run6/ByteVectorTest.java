
package org.example.run6;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ByteVectorTest {

    @Test
    public void testDefaultConstructor() {
        ByteVector byteVector = new ByteVector();
        assertNotNull(byteVector.data);
        assertEquals(64, byteVector.data.length);
        assertEquals(0, byteVector.length);
    }

    @Test
    public void testConstructorWithInitialSize() {
        ByteVector byteVector = new ByteVector(128);
        assertNotNull(byteVector.data);
        assertEquals(128, byteVector.data.length);
        assertEquals(0, byteVector.length);
    }

    @Test
    public void testPutByte() {
        ByteVector byteVector = new ByteVector();
        byteVector.putByte(0x12);
        assertEquals(1, byteVector.length);
        assertEquals((byte) 0x12, byteVector.data[0]);
    }

    @Test
    public void testPut11() {
        ByteVector byteVector = new ByteVector();
        byteVector.put11(0x12, 0x34);
        assertEquals(2, byteVector.length);
        assertArrayEquals(new byte[] {(byte) 0x12, (byte) 0x34}, 
                          new byte[] {byteVector.data[0], byteVector.data[1]});
    }

    @Test
    public void testPutShort() {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(0x1234);
        assertEquals(2, byteVector.length);
        assertArrayEquals(new byte[] {(byte) 0x12, (byte) 0x34}, 
                          new byte[] {byteVector.data[0], byteVector.data[1]});
    }

    @Test
    public void testPut12() {
        ByteVector byteVector = new ByteVector();
        byteVector.put12(0x12, 0x3456);
        assertEquals(3, byteVector.length);
        assertArrayEquals(new byte[] {(byte) 0x12, (byte) 0x34, (byte) 0x56}, 
                          new byte[] {byteVector.data[0], byteVector.data[1], byteVector.data[2]});
    }

    @Test
    public void testPutInt() {
        ByteVector byteVector = new ByteVector();
        byteVector.putInt(0x12345678);
        assertEquals(4, byteVector.length);
        assertArrayEquals(new byte[] {(byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78}, 
                          new byte[] {byteVector.data[0], byteVector.data[1], byteVector.data[2], byteVector.data[3]});
    }

    @Test
    public void testPutLong() {
        ByteVector byteVector = new ByteVector();
        byteVector.putLong(0x123456789ABCDEF0L);
        assertEquals(8, byteVector.length);
        assertArrayEquals(new byte[] {(byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78, 
                                      (byte) 0x9A, (byte) 0xBC, (byte) 0xDE, (byte) 0xF0}, 
                          new byte[] {byteVector.data[0], byteVector.data[1], byteVector.data[2], byteVector.data[3],
                                      byteVector.data[4], byteVector.data[5], byteVector.data[6], byteVector.data[7]});
    }

    @Test
    public void testPutUTF8() {
        ByteVector byteVector = new ByteVector();
        byteVector.putUTF8("Hello");
        assertEquals(7, byteVector.length);
        assertArrayEquals(new byte[] {(byte) 0x0, (byte) 0x5, (byte) 0x48, (byte) 0x65, 
                                      (byte) 0x6C, (byte) 0x6C, (byte) 0x6F}, 
                          new byte[] {byteVector.data[0], byteVector.data[1], byteVector.data[2], byteVector.data[3],
                                      byteVector.data[4], byteVector.data[5], byteVector.data[6]});
    }

    @Test
    public void testPutByteArray() {
        ByteVector byteVector = new ByteVector();
        byte[] byteArray = new byte[] {0x01, 0x02, 0x03};
        byteVector.putByteArray(byteArray, 0, byteArray.length);
        assertEquals(3, byteVector.length);
        assertArrayEquals(byteArray, new byte[] {byteVector.data[0], byteVector.data[1], byteVector.data[2]});
    }

    @Test
    public void testEnlarge() {
        ByteVector byteVector = new ByteVector(2);
        byteVector.putInt(0x12345678);
        assertTrue(byteVector.data.length > 2);
        assertEquals(4, byteVector.length);
    }
}
