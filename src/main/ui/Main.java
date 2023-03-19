package ui;

import java.awt.*;
import java.io.IOException;



// Run main class
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                try {
                    ToDoListApp window = new ToDoListApp();
                    ToDoListApp.displayGUI();
                    window.setVisible(true);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });




    }
}
