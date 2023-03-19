package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for SingleTask
class TaskTest {
    private Task testTask;

    @BeforeEach
    public void runBefore() {
        testTask = new Task("Review 210!",false);
    }

    @Test
    public void testConstructor() {
        assertEquals("Review 210!", testTask.getTaskName());
        assertFalse(testTask.isCompleted());
    }

    @Test
    public void testTaskCompleted() {
        assertFalse(testTask.isCompleted());
        testTask.completed();
        assertTrue(testTask.isCompleted());
    }

    @Test
    public void testCheckOffYes() {
        testTask.completed();
        assertTrue(testTask.isCompleted());
        assertEquals("✔ Review 210!", testTask.checkOff(testTask));
    }

    @Test
    public void testCheckOffNo() {
        assertFalse(testTask.isCompleted());
        assertEquals("Review 210!", testTask.checkOff(testTask));
    }

}