import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TodoListApp todoList = new TodoListApp();
        Scanner scanner = new Scanner(System.in);

        todoList.readTasksFromFile();

        boolean exit = false;
        while (!exit) {
            System.out.println("----- Todo List Menu -----");
            System.out.println("1. Add an untimed task");
            System.out.println("2. Add a timed task");
            System.out.println("3. Mark a task as completed");
            System.out.println("4. Remove a task");
            System.out.println("5. Edit a task");
            System.out.println("6. Display all tasks");
            System.out.println("7. Sort tasks by date");
            System.out.println("8. Sort tasks by category");
            System.out.println("9. Save tasks to file");
            System.out.println("10. Read tasks from file");
            System.out.println("11. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task category: ");
                    String category = scanner.nextLine();
                    todoList.addTask(new UntimedTask(title, category));
                    System.out.println("Untimed task added successfully");
                    break;
                case 2:
                    System.out.print("Enter task title: ");
                    String timedTitle = scanner.nextLine();
                    System.out.print("Enter task category: ");
                    String timedCategory = scanner.nextLine();
                    System.out.print("Enter task date (YYYY-MM-DD): ");
                    String dateString = scanner.nextLine();
                    todoList.addTask(new TimedTask(timedTitle, timedCategory, dateString));
                    System.out.println("Timed task added successfully");
                    break;
                case 3:
                    System.out.print("Enter the index of the task to mark as completed: ");
                    int indexCompleted = scanner.nextInt();
                    scanner.nextLine();
                    todoList.markTaskAsCompleted(indexCompleted);
                    break;
                case 4:
                    System.out.print("Enter the index of the task to remove: ");
                    int indexRemove = scanner.nextInt();
                    scanner.nextLine();
                    todoList.removeTask(indexRemove);
                    break;
                case 5:
                    System.out.print("Enter the index of the task to edit: ");
                    int indexEdit = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter the new title for the task: ");
                    String newTitle = scanner.nextLine();
                    todoList.editTask(indexEdit, newTitle);
                    break;
                case 6:
                    todoList.displayTasks();
                    break;
                case 7:
                    todoList.sortTasksByDate();
                    break;
                case 8:
                    todoList.sortTasksByCategory();
                    break;
                case 9:
                    todoList.saveTasksToFile();
                    System.out.println("Tasks saved to file");
                    break;
                case 10:
                    todoList.readTasksFromFile();
                    System.out.println("Tasks read from file");
                    break;
                case 11:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }

        todoList.saveTasksToFile();

        scanner.close();
    }
}
