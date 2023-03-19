package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;


// Tasks that have been added to the todolist (Make a todolist)
public class ToDoList implements Writable {
    String name;
    Map<String, ArrayList<Task>> toDoList;



    // EFFECTS: Make a todolist
    public ToDoList(String name) {
        this.name = name;
        toDoList = new HashMap<>();
        toDoList.put(name,new ArrayList<>());
    }


    // EFFECTS: Get the name of the todolist
    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS: Task is added to the ToDoList if task is not
    // already in the ToDoList
    public void addTask(String name, Task t) {
        ArrayList<Task> tasks = toDoList.get(name);
        tasks.add(t);
    }

    // REQUIRES: Task t is an element of the ToDoList
    // MODIFIES: this
    // EFFECTS: Task t is deleted from the toDoList
    public void deleteTask(String name, Task t) {
        ArrayList<Task> tasks = toDoList.get(name);
        tasks.remove(t);
    }

    // EFFECTS: Return the size of the todolist
    public int size(String name) {
        ArrayList<Task> tasks = toDoList.get(name);
        return tasks.size();
    }

    // EFFECTS: Get the todolist
    public List<Task> getToDoList(String name) {
        return toDoList.get(name);
    }

    //EFFECTS: get the task in the list at the given index
    public Task getTask(String name, int index) {
        ArrayList<Task> tasks = toDoList.get(name);
        return tasks.get(index);
    }

    // EFFECTS: If todolist contains task t, return true
    public boolean contains(String name, Task t) {
        ArrayList<Task> tasks = toDoList.get(name);
        return tasks.contains(t);
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("tasks", tasksToJson(name));
        return json;
    }

    // EFFECTS: returns things in this todolist as a JSON array
    private JSONArray tasksToJson(String name) {
        JSONArray jsonArray = new JSONArray();

        for (Task t : toDoList.get(name)) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }
}
