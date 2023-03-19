package model;

import org.json.JSONObject;
import persistence.Writable;

// A single task
public class Task implements Writable {

    private String taskName;
    private Boolean completed;

    // EFFECTS: constructs a task with a name and completeStatus
    public Task(String taskName, Boolean completed) {
        this.taskName = taskName;
        this.completed = completed;
    }

    // EFFECTS: Get the taskName
    public String getTaskName() {
        return taskName;
    }

    //  EFFECTS: Get the task complete status
    public boolean isCompleted() {
        return completed;
    }

    // MODIFIES: this
    // EFFECTS: complete the task
    public void completed() {
        completed = true;
    }

    // MODIFIES: this
    // EFFECTS: Task t is checked off in the ToDoList if it is completed
    //          just return it's name if it is not completed
    public String checkOff(Task t) {
        if (t.isCompleted()) {
            return ("✔" + " " + t.getTaskName());
        }
        return (t.getTaskName());
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", taskName);
        json.put("completed", completed);
        return json;
    }
}

