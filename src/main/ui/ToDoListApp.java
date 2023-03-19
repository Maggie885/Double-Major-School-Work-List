package ui;

import model.Task;
import model.ToDoList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;


// ToDoList application
public class ToDoListApp extends JPanel implements ListSelectionListener {


    private static final Color lightBlue = new Color(244, 221, 200);
    private static final Color medBlue = new Color(184, 57, 53);
    private static final String JSON_STORE = "./data/todolist.json";

    private ToDoList toDoList;
    private DefaultListModel<String> tasks;
    private JList<String> displayToDoList;

    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    private JTextField taskNameTextField;
    private JButton addTaskButton;
    private JButton removeTaskButton;
    private JButton checkOffTaskButton;
    private JButton loadTasksButton;
    private JButton saveTasksButton;


    // EFFECTS: constructs todolist and runs the toDoApplication
    public ToDoListApp() throws IOException {

        super(new BorderLayout());

        toDoTasksSetUp();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        displayToDoList();
        displaySetUp();

        setVisible(true);

    }


    // MODIFIES: this
    // EFFECTS: GUI Setup
    private void displaySetUp() {
        addSlogan();
        addButtons();

        //create a display todolist
        JScrollPane panelList = new JScrollPane(displayToDoList);
        displayToDoList.setSelectionBackground(medBlue);
        displayToDoList.setSelectionForeground(Color.white);
        panelList.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(displayToDoList, BorderLayout.CENTER);


    }


    // MODIFIES: this
    // EFFECTS: Slogan Setup
    private void addSlogan() {
        //create a slogan
        JLabel slogan = new JLabel("Enjoy Every Task");
        Font titleFont = new Font("Georgia", Font.BOLD, 14);
        slogan.setFont(titleFont);
        slogan.setForeground(medBlue);
        slogan.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        add(slogan, BorderLayout.PAGE_START);
    }


    // MODIFIES: this
    // EFFECTS: Buttons add on the panel
    private void addButtons() {

        addButtonsHelper();
        //create a display field and button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBackground(medBlue);
        buttonPanel.add(taskNameTextField);
        buttonPanel.add(addTaskButton);
        buttonPanel.add(Box.createHorizontalStrut(5));
        buttonPanel.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPanel.add(Box.createHorizontalStrut(5));
        buttonPanel.add(checkOffTaskButton);
        buttonPanel.add(removeTaskButton);
        buttonPanel.add(loadTasksButton);
        buttonPanel.add(saveTasksButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(buttonPanel, BorderLayout.PAGE_END);
    }


    // MODIFIES: this
    // EFFECTS: Help adding Buttons on the panel
    private void addButtonsHelper() {
        //create some buttons
        JButton addTaskButton = setUpAddTaskButton();
        addTaskButton.setBackground(lightBlue);
        JButton checkOffTaskButton = setUpCheckOffTaskButton();
        checkOffTaskButton.setBackground(Color.white);
        JButton removeTaskButton = setUpRemoveTaskButton();
        removeTaskButton.setBackground(Color.white);
        JButton loadTasksButton = setUpLoadTasksButton();
        loadTasksButton.setBackground(Color.white);
        JButton saveTasksButton = setUpSaveTasksButton();
        saveTasksButton.setBackground(Color.white);
        JTextField taskNameTextField = setUpTaskNameTextField();
        taskNameTextField.setBackground(Color.white);
        addListeners();
    }


    // MODIFIES: this
    // EFFECTS: Help adding Listeners on the panel
    private void addListeners() {
        AddTaskListener addTaskListener = new AddTaskListener(addTaskButton);
        addTaskButton.addActionListener(addTaskListener);
        removeTaskButton.addActionListener(new RemoveTaskListener());
        checkOffTaskButton.addActionListener(new CheckOffTaskListener());
        taskNameTextField.addActionListener(addTaskListener);
        loadTasksButton.addActionListener(new LoadTasksListener());
        saveTasksButton.addActionListener(new SaveTasksListener());
    }

    // MODIFIES: this
    // EFFECTS: SetUp the todoTasks list
    private void toDoTasksSetUp() {
        toDoList = new ToDoList("To do List");
        tasks = new DefaultListModel<>();
    }


    // MODIFIES: this
    // EFFECTS: Display the todolist using gui
    private void displayToDoList() {
        displayToDoList = new JList<>(tasks);
        displayToDoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        displayToDoList.setSelectedIndex(0);
        displayToDoList.addListSelectionListener(this);
        displayToDoList.setVisibleRowCount(10);
    }

    // MODIFIES: this
    // EFFECTS: Display the todolist using gui
    private JButton setUpAddTaskButton() {
        addTaskButton = new JButton("Add Task");
        addTaskButton.setActionCommand("Add Task");
        //addTaskButton.setEnabled(false);
        return addTaskButton;
    }

    // MODIFIES: this
    // EFFECTS: setUp the checkOffButton
    private JButton setUpCheckOffTaskButton() {
        checkOffTaskButton = new JButton("Check Off Task");
        checkOffTaskButton.setActionCommand("Check Off Task");
        return checkOffTaskButton;
    }

    // MODIFIES: this
    // EFFECTS: setUp the RemoveTaskButton
    private JButton setUpRemoveTaskButton() {
        removeTaskButton = new JButton("Remove Task");
        removeTaskButton.setActionCommand("Remove Task");
        return removeTaskButton;
    }

    // MODIFIES: this
    // EFFECTS: setUp the TaskNameTextField
    private JTextField setUpTaskNameTextField() {
        taskNameTextField = new JTextField(15);
        return taskNameTextField;
    }

    // MODIFIES: this
    // EFFECTS: setUp the LoadTasksButton
    private JButton setUpLoadTasksButton() {
        loadTasksButton = new JButton("Load Tasks");
        loadTasksButton.setActionCommand("Load Tasks");
        return loadTasksButton;
    }

    // MODIFIES: this
    // EFFECTS: setUp the SaveTasksButton
    private JButton setUpSaveTasksButton() {
        saveTasksButton = new JButton("Save Tasks");
        saveTasksButton.setActionCommand("Save Tasks");
        return saveTasksButton;
    }

    // MODIFIES: this
    // EFFECTS:create and display GUI
    public static void displayGUI() throws IOException {

        //window setup
        JFrame todoWindow = new JFrame("To Do List");
        todoWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //content setup
        JComponent contentPanel = new ToDoListApp();
        contentPanel.setOpaque(true);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setBackground(lightBlue);
        UIManager.put("List.focusCellHighlighterBorder", BorderFactory.createEmptyBorder());

        //display todoWindow
        todoWindow.setContentPane(contentPanel);
        todoWindow.pack();
        todoWindow.setVisible(true);

    }

    // MODIFIES: this
    // EFFECTS: load todolist
    private void loadSaveList() {
        ArrayList<String> taskNames = new ArrayList<>();
        try {
            toDoList = jsonReader.read();

            for (Task task : toDoList.getToDoList(toDoList.getName())) {
                if (task.isCompleted()) {
                    taskNames.add("✔" + task.getTaskName());
                } else {
                    taskNames.add(task.getTaskName());
                }
            }

            for (String n : taskNames) {
                tasks.addElement(n);
            }
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

    }

    // MODIFIES: this
    // EFFECTS: save todolist
    private void saveTodoList() {
        try {
            jsonWriter.open();
            jsonWriter.write(toDoList);
            jsonWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES:this
    // EFFECTS: load and play sound
    public void playSound(String soundName) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(soundName)));
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }



    // MODIFIES:this
    // EFFECTS: Change button status if needed
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (displayToDoList.getSelectedIndex() == -1) {
                removeTaskButton.setEnabled(false);
                checkOffTaskButton.setEnabled(false);
            } else {
                removeTaskButton.setEnabled(true);
                checkOffTaskButton.setEnabled(true);
                saveTasksButton.setEnabled(true);
                loadTasksButton.setEnabled(true);
            }
        }

    }


    // Set CheckOffTaskListener
    class CheckOffTaskListener implements ActionListener {

        // MODIFIES:this
        //  EFFECTS: Set addTaskButton Listener
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = displayToDoList.getSelectedIndex();

            String task = tasks.elementAt(index);
            if (task.contains("✔")) {
                Toolkit.getDefaultToolkit().beep();
            } else {
                String checkOffTask = "✔" + task;
                tasks.insertElementAt(checkOffTask, index);
                displayToDoList.ensureIndexIsVisible(index);
                int newIndex = displayToDoList.getSelectedIndex();
                tasks.remove(newIndex);
                displayToDoList.setSelectedIndex(index);
                Task completeTask = toDoList.getTask(toDoList.getName(), index);
                completeTask.completed();
            }
        }
    }

    // Set RemoveTaskListener
    class RemoveTaskListener implements ActionListener {

        // MODIFIES:this
        // EFFECTS: Set removeTaskButton Listener
        public void actionPerformed(ActionEvent e) {
            int index = displayToDoList.getSelectedIndex();
            int size = tasks.size();

            tasks.remove(index);

            if (size == 0) {
                setUpRemoveTaskButton().setEnabled(false);
                setUpCheckOffTaskButton().setEnabled(false);
            } else {
                if (index == size) {
                    index--;
                }

                displayToDoList.setSelectedIndex(index);
                displayToDoList.ensureIndexIsVisible(index);
                Task deleteTask = toDoList.getTask(toDoList.getName(), index);
                deleteTask.checkOff(deleteTask);
                toDoList.deleteTask(toDoList.getName(), deleteTask);


            }
        }
    }


    // Set AddTaskListener
    class AddTaskListener implements ActionListener, DocumentListener {
        private boolean isEnabled = false;
        private final JButton button;

        AddTaskListener(JButton button) {
            this.button = button;
        }

        // MODIFIES:this
        // EFFECTS: Set addTaskButton Listener
        public void actionPerformed(ActionEvent e) {
            playSound("./src/main/ui/music/Click2-Button.wav");
            String taskName = taskNameTextField.getText();
            int index = displayToDoList.getSelectedIndex();

            if (taskName.equals("") || existInList("✔" + taskName)) {
                Toolkit.getDefaultToolkit().beep();
                taskNameTextField.requestFocusInWindow();
                taskNameTextField.selectAll();
                return;
            }

            if (index == -1) {
                index = 0;
            } else {
                index++;
            }

            tasks.insertElementAt(taskName, index);
            taskNameTextField.requestFocusInWindow();
            taskNameTextField.setText("");
            displayToDoList.setSelectedIndex(index);
            displayToDoList.ensureIndexIsVisible(index);

            Task newTask = new Task(taskName, false);
            toDoList.addTask(toDoList.getName(),newTask);

        }


        // EFFECTS: Test if List contains name
        boolean existInList(String name) {
            return tasks.contains(name);
        }

        // MODIFIES:this
        // EFFECTS: Set Button enable

        @Override
        public void insertUpdate(DocumentEvent e) {
            buttonEnable();
        }

        // MODIFIES:this
        // EFFECTS: Set Button unable
        @Override
        public void removeUpdate(DocumentEvent e) {
            nothingTextField(e);
        }

        // MODIFIES:this
        // EFFECTS: Set Button unable when nothing in the text field
        @Override
        public void changedUpdate(DocumentEvent e) {
            if (!nothingTextField(e)) {
                buttonEnable();
            }
        }


        // MODIFIES:this
        // EFFECTS: Set Button enable when the buttons are not
        public void buttonEnable() {
            if (!isEnabled) {
                button.setEnabled(true);
            }
        }

        // MODIFIES:this
        // EFFECTS: Set Button enable when when nothing in the text field
        public boolean nothingTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                isEnabled = false;
                return true;
            }
            return false;
        }
    }

    // Set LoadTasksListener
    class LoadTasksListener implements ActionListener {

        // MODIFIES:this
        // EFFECTS: Set loadTasks Listener
        public void actionPerformed(ActionEvent e) {
            loadSaveList();
        }
    }

    // Set SaveTasksListener
    class SaveTasksListener implements ActionListener {

        // MODIFIES:this
        // EFFECTS: Set saveTasks Listener
        @Override
        public void actionPerformed(ActionEvent e) {
            saveTodoList();
        }
    }


}

