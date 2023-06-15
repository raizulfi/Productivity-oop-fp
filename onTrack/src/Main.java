import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    private static final String MENU = "Menu:\n" +
            "1. Add Task\n" +
            "2. Mark Task as Complete\n" +
            "3. Delete Task\n" +
            "4. Exit\n" +
            "Enter your choice (1-4): ";

    public static void main(String[] args) {
        System.out.println("█▀█ █▄░█ ▀█▀ █▀█ ▄▀█ █▀▀ █▄▀\n" +
                "█▄█ █░▀█ ░█░ █▀▄ █▀█ █▄▄ █░█");
        TodoList todoList = new TodoList();
        TaskFileManager taskFileManager = new TaskFileManager("tasks.txt");
        taskFileManager.loadTasks(todoList);

        Scanner scanner = new Scanner(System.in);

        boolean exit = false;
        while (!exit) {
            todoList.displayTasks();

            System.out.print(MENU);
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addTask(scanner, todoList, taskFileManager);
                    break;
                case 2:
                    markTaskAsComplete(scanner, todoList, taskFileManager);
                    break;
                case 3:
                    deleteTask(scanner, todoList, taskFileManager);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        taskFileManager.saveTasks(todoList);
        System.out.println("Exiting the program (～￣▽￣)～");
        scanner.close();
    }

    private static void addTask(Scanner scanner, TodoList todoList, TaskFileManager taskFileManager) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();

        System.out.print("Enter task description: ");
        String description = scanner.nextLine();

        System.out.print("Task type: (1 - Simple Task, 2 - Recurring Task, 3 - Deadline Task): ");
        int taskType = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Task task;
        switch (taskType) {
            case 1:
                task = new SimpleTask(title, description);
                break;
            case 2:
                System.out.print("Enter completion target: ");
                int completionTarget = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                task = new RecurringTask(title, description, completionTarget);
                break;
            case 3:
                System.out.print("Enter deadline (yyyy-MM-dd HH:mm): ");
                String deadlineStr = scanner.nextLine();
                LocalDateTime deadline = LocalDateTime.parse(deadlineStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                task = new DeadlineTask(title, description, deadline);
                break;
            default:
                System.out.println("Invalid task type. Task not added.\n");
                return;
        }

        todoList.addTask(task);
        taskFileManager.addTaskToFile(task);

        System.out.println("Task added successfully.\n");
    }

    private static void markTaskAsComplete(Scanner scanner, TodoList todoList, TaskFileManager taskFileManager) {
        System.out.print("Enter task number to mark as complete: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (taskNumber >= 1 && taskNumber <= todoList.getTaskCount()) {
            Task task = todoList.getTask(taskNumber - 1);
            task.markAsCompleted();
            taskFileManager.updateTaskInFile(task);

            System.out.println("Task marked as complete.\n");
        } else {
            System.out.println("Invalid task number.\n");
        }
    }

    private static void deleteTask(Scanner scanner, TodoList todoList, TaskFileManager taskFileManager) {
        System.out.print("Enter task number to delete: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (taskNumber >= 1 && taskNumber <= todoList.getTaskCount()) {
            Task task = todoList.getTask(taskNumber - 1);
            todoList.removeTask(task);
            taskFileManager.deleteTaskFromFile(task);

            System.out.println("Task deleted.\n");
        } else {
            System.out.println("Invalid task number.\n");
        }
    }
}
