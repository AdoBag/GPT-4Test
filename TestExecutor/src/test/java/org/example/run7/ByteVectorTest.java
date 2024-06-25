
package org.example.run7;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ByteVectorTest {

    @Test
    public void testDefaultConstructor() {
        ByteVector byteVector = new ByteVector();
        assertNotNull(byteVector.data);
        assertEquals(64, byteVector.data.length);
        assertEquals(0, byteVector.length);
    }

    @Test
    public void testCustomConstructor() {
        ByteVector byteVector = new ByteVector(128);
        assertNotNull(byteVector.data);
        assertEquals(128, byteVector.data.length);
        assertEquals(0, byteVector.length);
    }

    @Test
    public void testPutByte() {
        ByteVector byteVector = new ByteVector();
        byteVector.putByte(0xAB);
        assertEquals(1, byteVector.length);
        assertEquals((byte) 0xAB, byteVector.data[0]);
    }

    @Test
    public void testPut11() {
        ByteVector byteVector = new ByteVector();
        byteVector.put11(0xAB, 0xCD);
        assertEquals(2, byteVector.length);
        assertEquals((byte) 0xAB, byteVector.data[0]);
        assertEquals((byte) 0xCD, byteVector.data[1]);
    }

    @Test
    public void testPutShort() {
        ByteVector byteVector = new ByteVector();
        byteVector.putShort(0xABCD);
        assertEquals(2, byteVector.length);
        assertEquals((byte) 0xAB, byteVector.data[0]);
        assertEquals((byte) 0xCD, byteVector.data[1]);
    }

    @Test
    public void testPut12() {
        ByteVector byteVector = new ByteVector();
        byteVector.put12(0xAB, 0xCDEF);
        assertEquals(3, byteVector.length);
        assertEquals((byte) 0xAB, byteVector.data[0]);
        assertEquals((byte) 0xCD, byteVector.data[1]);
        assertEquals((byte) 0xEF, byteVector.data[2]);
    }

    @Test
    public void testPutInt() {
        ByteVector byteVector = new ByteVector();
        byteVector.putInt(0xABCDEF12);
        assertEquals(4, byteVector.length);
        assertEquals((byte) 0xAB, byteVector.data[0]);
        assertEquals((byte) 0xCD, byteVector.data[1]);
        assertEquals((byte) 0xEF, byteVector.data[2]);
        assertEquals((byte) 0x12, byteVector.data[3]);
    }

    @Test
    public void testPutLong() {
        ByteVector byteVector = new ByteVector();
        byteVector.putLong(0xABCDEF1234567890L);
        assertEquals(8, byteVector.length);
        assertEquals((byte) 0xAB, byteVector.data[0]);
        assertEquals((byte) 0xCD, byteVector.data[1]);
        assertEquals((byte) 0xEF, byteVector.data[2]);
        assertEquals((byte) 0x12, byteVector.data[3]);
        assertEquals((byte) 0x34, byteVector.data[4]);
        assertEquals((byte) 0x56, byteVector.data[5]);
        assertEquals((byte) 0x78, byteVector.data[6]);
        assertEquals((byte) 0x90, byteVector.data[7]);
    }

    @Test
    public void testPutUTF8() {
        ByteVector byteVector = new ByteVector();
        byteVector.putUTF8("ABC");
        assertEquals(5, byteVector.length);
        assertEquals((byte) 0x00, byteVector.data[0]); // UTF8 length high byte
        assertEquals((byte) 0x03, byteVector.data[1]); // UTF8 length low byte
        assertEquals((byte) 'A', byteVector.data[2]);
        assertEquals((byte) 'B', byteVector.data[3]);
        assertEquals((byte) 'C', byteVector.data[4]);
    }

    @Test
    public void testPutByteArray() {
        ByteVector byteVector = new ByteVector();
        byte[] byteArray = {0x01, 0x02, 0x03, 0x04};
        byteVector.putByteArray(byteArray, 1, 2);
        assertEquals(2, byteVector.length);
        assertEquals((byte) 0x02, byteVector.data[0]);
        assertEquals((byte) 0x03, byteVector.data[1]);
    }

    @Test
    public void testAutomaticEnlargement() {
        ByteVector byteVector = new ByteVector(1);
        byteVector.putByte(0);
        assertEquals(1, byteVector.data.length);
        byteVector.putByte(1);
        assertTrue(byteVector.data.length > 1);
        assertEquals((byte) 0, byteVector.data[0]);
        assertEquals((byte) 1, byteVector.data[1]);
    }

    @Test
    public void testEnlarge() {
        ByteVector byteVector = new ByteVector(2);
        byteVector.putByte(1).putByte(2);  // Should occupy initial size
        byteVector.putByte(3);             // Should trigger enlarge
        assertEquals(3, byteVector.length);
        assertTrue(byteVector.data.length >= 3);
    }
}
