package persistence;

import model.Task;
import model.ToDoList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            ToDoList td = new ToDoList("My todo list");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            ToDoList td = new ToDoList("My todo list");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(td);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            td = reader.read();
            assertEquals("My todo list", td.getName());
            assertEquals(0, td.size(td.getName()));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            ToDoList td = new ToDoList("My todo list");
            td.addTask(td.getName(), new Task("WakeUp", false));
            td.addTask(td.getName(), new Task("CPSC210", false));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(td);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            td = reader.read();
            assertEquals("My todo list", td.getName());
            List<Task> tasks = td.getToDoList(td.getName());
            assertEquals(2, tasks.size());
            checkTask("WakeUp", false,tasks.get(0));
            checkTask("CPSC210", false,tasks.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
