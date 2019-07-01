public class Task {
    // Used to point to the next task in linked list
    private Task nextTask;
    private Task previouseTask;
    // Using the static field means same value across for every object of class task
    private static int nextid = 0;
    private int id;
    private String typeOfTask;
    private String creator;
    private String owner;

    public Task(String creator, String owner, String typeOfTask) {
        // Setters for ID Owner and Creator
        // Set id to -1 else will be set to default 0
        // (creates error when searching for id 0 in an arraylist with no elements)
        id = -1;
        this.creator = creator;
        this.owner = owner;
        this.typeOfTask = typeOfTask;
        nextid++;
    }

    public Task(String creator, String owner, Task previouseTask, Task nextTask, String typeOfTask) {
        // Setters for ID Owner and Creator
        id = nextid;
        this.creator = creator;
        this.owner = owner;
        this.typeOfTask = typeOfTask;
        this.nextTask = nextTask;
        this.previouseTask = previouseTask;
        nextid++;
    }

    // Same as constructor above expect here we can set our own id
    public Task(int ID, String creator, String owner, Task previouseTask, Task nextTask, String typeOfTask) {
        // Setters for ID Owner and Creator
        id = ID;
        this.creator = creator;
        this.owner = owner;
        this.typeOfTask = typeOfTask;
        this.nextTask = nextTask;
        this.previouseTask = previouseTask;
    }

    // Can create empty tasks (used as null)
    public Task() {

    }

    // Getter's for all data attributes from Object
    public String getOwner() {
        return owner;
    }

    public String getCreator() {
        return creator;
    }

    public String getTypeOfTask() {
        return typeOfTask;
    }

    public int getID() {
        return id;
    }

    public Task getNextTask() {
        return nextTask;
    }

    public Task getPreviouseTask() {
        return previouseTask;
    }

    // Setters for next and previous task
    public void setNextTask(Task task) {
        nextTask = task;
    }

    public void setPreviouseTask(Task task) {
        previouseTask = task;
    }
}