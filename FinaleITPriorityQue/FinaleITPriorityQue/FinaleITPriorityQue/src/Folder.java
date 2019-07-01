public class Folder {
    // Construct 2D array of four job requests
    private static TaskList[] ary = { new TaskList(1), new TaskList(2), new TaskList(3), new TaskList(4) };

    public static void main(String[] args) {
        // Add Tasks
        System.out.println("Test 1: Add Tasks");
        System.out.println("This test shows three Tasks have been added to the que.");
        System.out.println("");
        System.out.println("Before");
        printList();
        addTask(1, "John", "Philip", "Security issues"); // 0
        addTask(3, "Micheal", "Felps", "Software/app installation");
        addTask(1, "Beth", "William", "Security issues"); // 2
        System.out.println("After");
        printList();
        
        // Log task
        System.out.println("Test 2: Log Task");
        System.out.println("Shows all information about the first task");
        System.out.println("");
        logTask();

        // Remove Tasks
        System.out.println("Test 3: Remove Tasks");
        System.out.println("This test shows the first task will be reomved from the list.");
        System.out.println("");
        System.out.println("Before");
        printList();
        removeTask();
        System.out.println("After");
        printList();
        removeTask();
        removeTask();

        // Change Priority of a specific task
        System.out.println("Test 4: Change Priority ");
        System.out.println("In this example ID 3 will change from priority 1 to priority 3");
        System.out.println("");
        addTask(1, "John", "Philip", "Security issues"); // 3
        addTask(3, "Micheal", "Felps", "Software/app installation");
        addTask(1, "Beth", "William", "Security issues"); // 5
        System.out.println("Before");
        printList();
        changeIDPriority(1, 3, 3);
        System.out.println("After");
        printList();
        
        // Delete specific task
        System.out.println("Test 5: Delete Specific Task");
        System.out.println("In this example ID: 3 will be deleted");
        System.out.println("");
        System.out.println("Before");
        printList();
        deleteID(3, 3);
        System.out.println("After");
        printList();
        System.out.println("");
    }

    private static void addTask(int priority, String creator, String owner, String typeOfTask) {
        // Returns the position of where the priority "should" be within the array
        int pos = search(priority);
        // Check if pos position actually contains the same priority value
        if (ary[pos].getPriority() == priority) {
            // The addTask is contained within TaskList
            ary[pos].addTask(owner, creator, typeOfTask);
        } else {
            System.out.println("Priority not found");
            System.out.println();
        }
    }

    private static void removeTask() {
        for (int i = 0; i < ary.length; i++) {
            // Check if list contains any tasks
            if (ary[i].getLength() != 0) {
                // If it does remove task and exit loop
                ary[i].removeFirstTask();
                i = ary.length;
            }
        }
    }
    
    private static void logTask() {
        String task = "There are no more tasks left...";
        for (int i = 0; i < ary.length; i++) {
            // Check if list contains any tasks
            if (ary[i].getLength() != 0) {
                // If it does remove task and exit loop
                task = ary[i].logFirstTask();
                i = ary.length;
            }
        }
        System.out.println(task);
        System.out.println("");
    }

    // Binary search used for locating priority position in array
    private static int search(int priority) {
        int left = 0;
        int right = ary.length - 1;
        // Remember integers round down
        int middle = ary.length / 2;
        while (left < right) {
            if (priority < ary[middle].getPriority()) {
                right = middle - 1;
            } else {
                left = middle;
            }
            middle = (((right + 1) - left) / 2) + left;
        }
        return middle;
    }

    private static void changeIDPriority(int oldpriority, int ID, int newpriority) {
        // Supposed positions of priority in array
        int pos = search(oldpriority);
        int npos = search(newpriority);
        // Check if priority's are actually in array
        if (ary[pos].getPriority() == oldpriority && ary[npos].getPriority() == newpriority) {
            // Get the task to change
            Task task = ary[pos].getTaskId(ID);
            // If task is within list
            if (task != null) {
                // Add task with specific/old ID
                ary[npos].addOldTask(task.getID(), task.getCreator(), task.getOwner(), task.getTypeOfTask());
                deleteID(oldpriority, ID);
            } else {
                System.out.println("This ID is not contained within the list");
                System.out.println("");
            }
        } else {
            System.out.println("No such priority exists");
            System.out.println("");
        }
    }

    private static void deleteID(int priority, int ID) {
        int pos = search(priority);
        // Check if priority has location in array and linked list contains at least one
        if (ary[pos].getPriority() == priority) {
            if (ary[pos].getLength() > 0) {
                ary[pos].removeID(ID);
            } else {
                System.out.println("No tasks exists at entered priority");
                System.out.println("");
            }
        } else {
            System.out.println("No such priority or exists");
            System.out.println("");
        }
    }

    private static void printList() {
        for (int i = 0; i < ary.length; i++) {
            if (ary[i].getLength() != 0) {
                System.out.println("Linked List"+ (i + 1) +" Priority " + (i + 1) + " ID: " + ary[i].seeTasks());
            } else {
                System.out.println("Linked List"+ (i + 1) +" Priority " + (i + 1) + " ID: (is empty)");
            }
        }
        System.out.println("");
    }
}