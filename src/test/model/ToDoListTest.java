package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {
    private ToDoList myToDoList;


    @BeforeEach
    public void runBefore() {
        myToDoList = new ToDoList("testToDoList");
    }

    @Test
    public void testGetName() {
        assertEquals("testToDoList", myToDoList.getName());
    }

    @Test
    public void testSize() {
        assertEquals(0, myToDoList.size(myToDoList.getName()));
        Task task0 = new Task("Review CPSC 210!", false);
        myToDoList.addTask(myToDoList.getName(),task0);
        assertEquals(1, myToDoList.size(myToDoList.getName()));
    }

    @Test
    public void testAddOneTask() {
        Task task0 = new Task("Review CPSC 210!", false);
        myToDoList.addTask(myToDoList.getName(),task0);
        assertEquals(1, myToDoList.size(myToDoList.getName()));
        assertTrue(myToDoList.contains(myToDoList.getName(),task0));
    }

    @Test
    public void testAddLOTSTasks() {
        Task task0 = new Task("Review CPSC 210!", false);
        Task task1 = new Task("Do CPSC 210 Exercise Problems!", false);
        myToDoList.addTask(myToDoList.getName(),task0);
        myToDoList.addTask(myToDoList.getName(),task1);
        assertEquals(2, myToDoList.size(myToDoList.getName()));
        assertTrue(myToDoList.contains(myToDoList.getName(),task0));
        assertTrue(myToDoList.contains(myToDoList.getName(),task1));

    }

    @Test
    public void testDeleteOneTask() {
        Task task0 = new Task("Review CPSC 210!", false);
        myToDoList.addTask(myToDoList.getName(),task0);
        myToDoList.deleteTask(myToDoList.getName(),task0);
        assertEquals(0, myToDoList.size(myToDoList.getName()));
        assertFalse(myToDoList.contains(myToDoList.getName(),task0));
    }

    @Test
    public void testDeleteLOTSTask() {
        Task task0 = new Task("Review CPSC 210!", false);
        Task task1 = new Task("Do CPSC 210 Exercise Problems!", false);
        myToDoList.addTask(myToDoList.getName(),task0);
        myToDoList.addTask(myToDoList.getName(),task1);
        myToDoList.deleteTask(myToDoList.getName(),task0);
        myToDoList.deleteTask(myToDoList.getName(),task1);
        assertEquals(0, myToDoList.size(myToDoList.getName()));
        assertFalse(myToDoList.contains(myToDoList.getName(),task0));
        assertFalse(myToDoList.contains(myToDoList.getName(),task1));
    }

    @Test
    public void testGetTasks() {
        Task task0 = new Task("Review CPSC 210!", false);
        myToDoList.addTask(myToDoList.getName(),task0);
        assertEquals(Collections.singletonList(task0),myToDoList.getToDoList(myToDoList.getName()));
    }

    @Test
    public void testGetTask() {
        Task task0 = new Task("Review CPSC 210!", false);
        Task task1 = new Task("Do CPSC 210 Exercise Problems!", false);
        myToDoList.addTask(myToDoList.getName(),task0);
        myToDoList.addTask(myToDoList.getName(),task1);
        assertEquals(task0,myToDoList.getTask(myToDoList.getName(),0));
        assertEquals(task1,myToDoList.getTask(myToDoList.getName(),1));
    }

}