
package org.example.run8;

import org.junit.jupiter.api.Test;
import java.util.Vector;
import static org.junit.jupiter.api.Assertions.*;

class QueueTest {

    @Test
    void testIsEmptyInitial() {
        Queue queue = new Queue();
        assertTrue(queue.isEmpty(), "Queue should be empty initially.");
    }

    @Test
    void testEnqueueSingleElement() {
        Queue queue = new Queue();
        queue.enqueue("Test");
        assertFalse(queue.isEmpty(), "Queue should not be empty after enqueue.");
        assertEquals(1, queue.getNumberItems(), "Queue should have one item after enqueue.");
    }

    @Test
    void testEnqueueMultipleElements() {
        Queue queue = new Queue();
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        assertFalse(queue.isEmpty(), "Queue should not be empty after enqueue.");
        assertEquals(2, queue.getNumberItems(), "Queue should have two items after enqueue.");
    }

    @Test
    void testDequeueSingleElement() {
        Queue queue = new Queue();
        queue.enqueue("Test");
        Object result = queue.dequeue();
        assertEquals("Test", result, "Dequeue should return the enqueued element.");
        assertTrue(queue.isEmpty(), "Queue should be empty after dequeue.");
    }

    @Test
    void testDequeueMultipleElements() {
        Queue queue = new Queue();
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        Object result1 = queue.dequeue();
        Object result2 = queue.dequeue();
        assertEquals("Test1", result1, "Dequeue should return the first enqueued element.");
        assertEquals("Test2", result2, "Dequeue should return the second enqueued element.");
        assertTrue(queue.isEmpty(), "Queue should be empty after dequeueing all elements.");
    }

    @Test
    void testGetNumberItems() {
        Queue queue = new Queue();
        assertEquals(0, queue.getNumberItems(), "Initial number of items should be zero.");
        queue.enqueue("Test1");
        assertEquals(1, queue.getNumberItems(), "Number of items should be one after enqueue.");
        queue.enqueue("Test2");
        assertEquals(2, queue.getNumberItems(), "Number of items should be two after enqueue.");
        queue.dequeue();
        assertEquals(1, queue.getNumberItems(), "Number of items should be one after dequeue.");
    }

    @Test
    void testGetPeakNumberItems() {
        Queue queue = new Queue();
        assertEquals(0, queue.getPeakNumberItems(), "Initial peak number of items should be zero.");
        queue.enqueue("Test1");
        assertEquals(1, queue.getPeakNumberItems(), "Peak number of items should be one after enqueue.");
        queue.enqueue("Test2");
        assertEquals(2, queue.getPeakNumberItems(), "Peak number of items should be two after enqueue.");
        queue.dequeue();
        assertEquals(2, queue.getPeakNumberItems(), "Peak number of items should remain two after dequeue.");
    }

//    @Test
//    void testRemove() {
//        Queue queue = new Queue();
//        queue.enqueue("Test");
//        queue.enqueue("RemoveMe");
//        queue.enqueue("RemoveMe");
//        queue.enqueue("Test");
//        int removedCount = queue.remove("RemoveMe");
//        assertEquals(2, removedCount, "Two elements should be removed.");
//        assertEquals(2, queue.getNumberItems(), "Queue should have two items left.");
//        assertNull(queue.first.value.equals("RemoveMe"), "Removed elements should not be in the queue.");
//    }
    /**
     * Manual fix of the above test
     * Problem: The last assertion tried to access a private variable. Also, equals returns a boolean, so it would have
     * failed regardless
     */
    @Test
    void testRemove__fixed() {
        Queue queue = new Queue();
        queue.enqueue("Test");
        queue.enqueue("RemoveMe");
        queue.enqueue("RemoveMe");
        queue.enqueue("Test");
        int removedCount = queue.remove("RemoveMe");
        assertEquals(2, removedCount, "Two elements should be removed.");
        assertEquals(2, queue.getNumberItems(), "Queue should have two items left.");
        assertEquals(0, queue.remove("RemoveMe"), "Removed elements should not be in the queue.");
    }

    @Test
    void testMaxCapacityExceeded() {
        Queue queue = new Queue(2);
        assertFalse(queue.maxCapacityExceeded(), "Max capacity should not be exceeded initially.");
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        assertTrue(queue.maxCapacityExceeded(), "Max capacity should be exceeded when at capacity.");
    }

    @Test
    void testToString() {
        Queue queue = new Queue();
        queue.enqueue("Test");
        String expected = "org.example.run8.Queue:[numItems=1, maxNumItems=1, maxCapacity=-1, getObjects()=[Test]\r\n]";
        assertEquals(expected, queue.toString(), "toString method should return appropriate representation.");
    }

    @Test
    void testRefreshElement() {
        Queue queue = new Queue();
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        queue.refreshElement("Test1");
        Object dequeuedFirst = queue.dequeue();
        Object dequeuedSecond = queue.dequeue();
        assertEquals("Test2", dequeuedFirst, "After refresh, Test1 should be moved to the back, so Test2 should be dequeued first.");
        assertEquals("Test1", dequeuedSecond, "After refresh, Test1 should be moved to the back, so Test1 should be dequeued second.");
    }

    @Test
    void testGetObjects() {
        Queue queue = new Queue();
        queue.enqueue("Test1");
        queue.enqueue("Test2");
        Vector<Object> objects = queue.getObjects();
        assertEquals(2, objects.size(), "getObjects should return a vector of size equal to number of items in queue.");
        assertEquals("Test1", objects.get(0), "First element in vector should match first enqueued item.");
        assertEquals("Test2", objects.get(1), "Second element in vector should match second enqueued item.");
    }
}
