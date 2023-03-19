package persistence;

import model.Task;
import model.ToDoList;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.processing.Completion;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads todolist from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads todolist from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ToDoList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseToDoList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ToDoList parseToDoList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ToDoList td = new ToDoList(name);
        addTasks(td, jsonObject);
        return td;
    }

    // MODIFIES: td
    // EFFECTS: parses thingies from JSON object and adds them to todolist
    private void addTasks(ToDoList td, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("tasks");
        for (Object json : jsonArray) {
            JSONObject nextTask = (JSONObject) json;
            addTask(td, nextTask);
        }
    }

    // MODIFIES: wr
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addTask(ToDoList td, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Boolean completed = jsonObject.getBoolean("completed");
        Task task = new Task(name, completed);
        td.addTask(td.getName(),task);
    }
}
