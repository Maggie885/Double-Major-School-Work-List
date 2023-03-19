# To-do List

## For students and employees

*The **To-do list** can help you:*

*- Keep track of your things to do*  
*- Cross things off*  
*- View showing all items*  

I am a college student with two majors. I designed this to-do list because my friends and I have a lot of challenging 
tasks from different courses every day. We always struggle with managing time.I hope with this app,
employees and students like us can make their life under control and dig out their potential.
 
 
## User Story

As a user, I want to be able to add a task to my to-do list  
As a user, I want to be able to view the list of tasks on my to-do list  
As a user, I want to be able to mark a task as complete on my to-do list  
As a user, I want to be able to delete a task from my to-do-list  
As a user, I want to be able to save my to-do list to file
As a user, I want to be able to be able to load my to-do list from file


**Phase 4: Task 2**
**Make appropriate use of the Map interface somewhere in your code.**

- In *ToDoList* Class

**Phase 4: Task 3**  
 UML
- *Task* and *ToDoList* implement Interface *Writable*
- Aggregation relationship between *Task* and *ToDoList*. 
Each *ToDoList* object is associated with many *Task* objects.
- We consider *ToDoList* to be thw whole and *Task* objects to be the parts.
- *ToDoList* can access behaviors provided by the *Task* class but not vice-versa.
- *ToDoListApp* is associated with *ToDoList*, *JsonReader* and *JsonWriter*.
- *ToDoListApp* can access behaviors provided by *ToDoList*, *JsonReader* and *JsonWriter class but not vice-versa.  

Refactor
- Split Class *AddTaskListener*, *CheckOffTaskListener*, *RemoveTaskListener*,
  *LoadTasksListener* and *SaveTasksListener* from *ToDoListApp*
- Rename the class *ToDoListApp* to *ToDoListManager*. That's more 
descriptive.
- Rename method *addButtonsHelper* to *addButtonsColor*. That's more 
descriptive.
- Introduce a type hierarchy (adding letters on the buttons and set actions of the buttons) 
to avoid code duplication when set up buttons.