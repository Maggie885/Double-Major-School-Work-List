package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ToDoList td = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyToDoList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyToDoList.json");
        try {
            ToDoList td = reader.read();
            assertEquals("My todo list", td.getName());
            assertEquals(0, td.size(td.getName()));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralToDoList.json");
        try {
            ToDoList td = reader.read();
            assertEquals("My todo list", td.getName());
            List<Task> tasks = td.getToDoList(td.getName());
            assertEquals(2, tasks.size());
            checkTask("WakeUp", false, tasks.get(0));
            checkTask("CPSC210", false, tasks.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
