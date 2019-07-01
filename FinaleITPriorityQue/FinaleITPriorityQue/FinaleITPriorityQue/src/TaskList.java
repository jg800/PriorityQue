
public class TaskList {
    private int priority;
    private Task frontRoot;
    private Task backRoot;
    // Length of linked list
    private int length;

    public TaskList(int priority) {
        this.priority = priority;
        this.frontRoot = new Task();
        this.backRoot = new Task();
        this.length = 0;
    }

    public int getPriority() {
        return priority;
    }

    public int getLength() {
        return length;
    }

    public String seeTasks() {
        Task start = frontRoot;
        Task end = backRoot.getNextTask();
        String answer = "";
        while (start != end) {
            answer += start.getID() + " , ";
            start = start.getNextTask();
        }
        if (answer.length() > 0) {
            // -3 Removes the last " , " from word
            answer = answer.substring(0, answer.length()-3);
        }
        return answer;
    }

    public void removeFirstTask() {
        // Front of list is to be removed
        frontRoot = frontRoot.getNextTask();
        frontRoot.setPreviouseTask(new Task());
        // At this point the front Task has no way of being referenced and ...
        // ... the java garbage collector will destroy any object without a reference.
        length--;
        if (length == 0) {
            this.backRoot = new Task();
        }
    }
    
    public String logFirstTask() {
        // Get the info of front of list
        String answer = "ID: " + frontRoot.getID() + " Priority: " + this.priority + " TypeOfTask: " + frontRoot.getTypeOfTask() + " Creator: " + frontRoot.getCreator() + " Owner: " + frontRoot.getOwner();
        return answer;
    }

    public void addTask(String creator, String owner, String typeOfTask) {
        Task newTask = new Task(creator, owner, backRoot, new Task(), typeOfTask);
        backRoot.setNextTask(newTask);
        // Add to the back of list thus change backRoot to the new task
        this.backRoot = newTask;
        // If adding to new list need to set front root
        if (length == 0) {
            frontRoot = newTask;
        }
        length++;
    }

    // Same as above expect allows us to add with a specific id (used in changing a ...
    // ... task priority)
    public void addOldTask(int ID, String creator, String owner, String typeOfTask) {
        Task newTask = new Task(ID, creator, owner, backRoot, new Task(), typeOfTask);
        backRoot.setNextTask(newTask);
        this.backRoot = newTask;
        if (length == 0) {
            frontRoot = newTask;
        }
        length++;
    }

    public Task getTaskId(int ID) {
        // Start from the front of the linked list
        Task task = frontRoot;
        boolean end = false;
        while (end == false) {
            // If task is null means come to the end of list without finding ID...
            // else weve found the specific task
            if (task == null || task.getID() == ID) {
                end = true;
            } else {
                // Iterates to next task in list
                task = task.getNextTask();
            }
        }
        // Either returns the task with the same ID or null (as task not found)
        return task;
    }

    public void removeID(int ID) {
        Task task = getTaskId(ID);
        if (task != null) {
            length--;
            if (length == 0) {
                this.frontRoot = new Task();
                this.backRoot = new Task();
            } else {
                Task prevtask = task.getPreviouseTask();
                Task nexttask = task.getNextTask();
                // If removing front task, need to set the front root (as no task is before this)
                if (task != frontRoot) {
                    prevtask.setNextTask(nexttask);
                } else {
                    this.frontRoot = nexttask;
                }
                // If removing back task, need to set the back root (as no task is after this)
                if (task != backRoot) {
                    nexttask.setPreviouseTask(prevtask);
                } else {
                    this.backRoot = prevtask;
                }
            }
        }
    }
}