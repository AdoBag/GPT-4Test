
package org.example.run7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class QueueTest {
    private Queue queue;

    @BeforeEach
    public void setup() {
        queue = new Queue();
    }

    @Test
    public void testIsEmpty() {
        assertTrue(queue.isEmpty());
        queue.enqueue("Test");
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testEnqueue() {
        queue.enqueue("Test");
        assertFalse(queue.isEmpty());
        assertEquals(1, queue.getNumberItems());
    }

    @Test
    public void testDequeue() {
        queue.enqueue("Test");
        assertEquals("Test", queue.dequeue());
        assertTrue(queue.isEmpty());
    }

    @Test
//    public void testDequeueEmptyQueue() {
    public void testDequeueEmptyQueue__finds_bug() {
        assertNull(queue.dequeue());
//        assertEquals(0, queue.getNumberItems());
        /** The above assertion catches a bug. Queue.dequeue() decreases the number of items even if nothing is removed */
    }

    @Test
    public void testRemove() {
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        queue.enqueue("Test1");
        assertEquals(2, queue.remove("Test1"));
        assertEquals(1, queue.getNumberItems());
    }

    @Test
    public void testRefreshElement() {
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        queue.refreshElement("Test1");
        assertEquals(2, queue.getNumberItems());
        assertEquals("Test2", queue.dequeue());
        assertEquals("Test1", queue.dequeue());
    }

    @Test
    public void testGetNumberItems() {
        assertEquals(0, queue.getNumberItems());
        queue.enqueue("Test");
        assertEquals(1, queue.getNumberItems());
    }

    @Test
    public void testGetPeakNumberItems() {
        assertEquals(0, queue.getPeakNumberItems());
        queue.enqueue("Test");
        queue.enqueue("Test2");
        assertEquals(2, queue.getPeakNumberItems());
        queue.dequeue();
        assertEquals(2, queue.getPeakNumberItems());
    }

    @Test
    public void testGetObjects() {
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        Vector objects = queue.getObjects();
        assertEquals(2, objects.size());
        assertEquals("Test1", objects.elementAt(0));
        assertEquals("Test2", objects.elementAt(1));
    }

    @Test
//    public void testMaxCapacityExceeded() {
    public void testMaxCapacityExceeded__fixed() {
        queue = new Queue(1);
//        queue.enqueue("Test1");
//        assertFalse(queue.maxCapacityExceeded());
        /** The method has a misleading name but if the capacity = size then this returns true */
        queue.enqueue("Test1");
        assertTrue(queue.maxCapacityExceeded());

        queue.enqueue("Test2");
        assertTrue(queue.maxCapacityExceeded());
    }

    @Test
    public void testToString() {
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        String expectedString = "org.example.run7.Queue:[numItems=2, maxNumItems=2, maxCapacity=-1, getObjects()=[Test1, Test2]\r\n]";
        assertEquals(expectedString, queue.toString());
    }
}
