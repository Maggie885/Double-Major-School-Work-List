package persistence;

import model.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTask(String name, Boolean completed, Task task) {
        assertEquals(name, task.getTaskName());
    }
}
