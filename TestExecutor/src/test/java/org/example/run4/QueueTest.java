
package org.example.run4;

import org.example.run4.Queue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test suite for the Queue class.
 */
public class QueueTest {

    private Queue queue;

    @BeforeEach
    public void setUp() {
        queue = new Queue();
    }

    @Test
    public void testQueueIsEmptyOnInit() {
        assertTrue(queue.isEmpty(), "Queue should be empty on initialization");
    }

    @Test
    public void testEnqueue() {
        queue.enqueue("first");
        assertFalse(queue.isEmpty(), "Queue should not be empty after enqueue");
        assertEquals(1, queue.getNumberItems(), "Number of items should be 1 after one enqueue");
    }

    @Test
    public void testDequeue() {
        queue.enqueue("first");
        queue.enqueue("second");
        assertEquals("first", queue.dequeue(), "Dequeue should return the first enqueued item");
        assertEquals(1, queue.getNumberItems(), "Number of items should be 1 after one dequeue");
        assertEquals("second", queue.dequeue(), "Dequeue should return the remaining item");
        assertTrue(queue.isEmpty(), "Queue should be empty after dequeueing all items");
    }

    @Test
    public void testDequeueFromEmptyQueue() {
        assertNull(queue.dequeue(), "Dequeue from an empty queue should return null");
    }

    @Test
    public void testRemoveExistingElement() {
        queue.enqueue("item1");
        queue.enqueue("item2");
        queue.enqueue("item1");
        int removedCount = queue.remove("item1");
        assertEquals(2, removedCount, "Remove should return the number of removed elements");
        assertEquals(1, queue.getNumberItems(), "Number of items should be 1 after removing two elements");
    }

    @Test
    public void testRemoveNonExistingElement() {
        queue.enqueue("item1");
        queue.enqueue("item2");
        int removedCount = queue.remove("item3");
        assertEquals(0, removedCount, "Remove should return 0 if the element does not exist");
        assertEquals(2, queue.getNumberItems(), "Number of items should remain the same after attempting to remove a non-existing element");
    }

    @Test
    public void testRefreshElement() {
        queue.enqueue("item1");
        queue.enqueue("item2");
        queue.refreshElement("item1");
        assertEquals("item2", queue.dequeue(), "After refresh, 'item2' should be dequeued first");
        assertEquals("item1", queue.dequeue(), "After refresh, 'item1' should be dequeued last");
    }

    @Test
    public void testGetNumberItems() {
        assertEquals(0, queue.getNumberItems(), "Initial queue size should be 0");
        queue.enqueue("item1");
        assertEquals(1, queue.getNumberItems(), "Queue size should be 1 after enqueueing one item");
    }

    @Test
    public void testGetPeakNumberItems() {
        assertEquals(0, queue.getPeakNumberItems(), "Initial peak item count should be 0");
        queue.enqueue("item1");
        queue.enqueue("item2");
        assertEquals(2, queue.getPeakNumberItems(), "Peak items should be 2 after enqueueing two items");
        queue.dequeue();
        assertEquals(2, queue.getPeakNumberItems(), "Peak items should still be 2 after dequeueing one item");
    }

    @Test
    public void testMaxCapacityExceeded() {
        Queue limitedQueue = new Queue(2);
        limitedQueue.enqueue("item1");
        limitedQueue.enqueue("item2");
        assertTrue(limitedQueue.maxCapacityExceeded(), "Queue should be marked as exceeding capacity when max capacity is reached");
        limitedQueue.dequeue();
        assertFalse(limitedQueue.maxCapacityExceeded(), "Queue should not be marked as exceeding capacity after reducing items below max");
    }

    @Test
    public void testToString() {
        queue.enqueue("item1");
        assertTrue(queue.toString().contains("item1"), "toString should include enqueued item");
    }
}
