import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class TodoListApp implements Sortable {
    private List<Task> tasks;
    private File tasksFile;

    public TodoListApp() {
        tasks = new ArrayList<>();
        tasksFile = new File("tasks.txt");
    }

    @Override
    public void sort(List<Task> tasks) {
        Collections.sort(tasks, new TaskComparator());
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveTasksToFile();
    }
    public void markTaskAsCompleted(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.markAsCompleted();
            saveTasksToFile();
            System.out.println("Task marked as completed: " + task.getTitle());
        } else {
            System.out.println("Invalid task index");
        }
    }


    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.remove(index);
            saveTasksToFile();
            System.out.println("Task removed: " + task.getTitle());
        } else {
            System.out.println("Invalid task index");
        }
    }

    public void editTask(int index, String newTitle) {
        if (index >= 0 && index < tasks.size()) {
            Task task = tasks.get(index);
            task.setTitle(newTitle);
            saveTasksToFile();
            System.out.println("Task edited: " + task.getTitle());
        } else {
            System.out.println("Invalid task index");
        }
    }



    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("[" + i + "] " + tasks.get(i).getDetails());
            }
        }
    }


    public void sortTasksByDate() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                if (task1 instanceof TimedTask && task2 instanceof TimedTask) {
                    TimedTask timedTask1 = (TimedTask) task1;
                    TimedTask timedTask2 = (TimedTask) task2;
                    return timedTask1.getDate().compareTo(timedTask2.getDate());
                }
                return 0;
            }
        });
        System.out.println("Tasks sorted by date");
    }

    public void sortTasksByCategory() {
        Collections.sort(tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                if (task1 instanceof TimedTask && task2 instanceof TimedTask) {
                    TimedTask timedTask1 = (TimedTask) task1;
                    TimedTask timedTask2 = (TimedTask) task2;
                    return timedTask1.getCategory().compareToIgnoreCase(timedTask2.getCategory());
                }
                return 0;
            }
        });
        System.out.println("Tasks sorted by category");
    }


    public void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter(tasksFile)) {
            for (Task task : tasks) {
                writer.println(taskToString(task));
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }


    public void readTasksFromFile() {
        try (Scanner scanner = new Scanner(tasksFile)) {
            tasks.clear();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = stringToTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Error reading tasks from file: " + e.getMessage());
        }
    }


    private String taskToString(Task task) {
        if (task instanceof TimedTask) {
            TimedTask timedTask = (TimedTask) task;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return "TimedTask|" + task.getTitle() + "|" + dateFormat.format(timedTask.getDate()) + "|" + timedTask.getCategory();
        } else if (task instanceof UntimedTask) {
            UntimedTask untimedTask = (UntimedTask) task;
            return "UntimedTask|" + task.getTitle() + "|" + untimedTask.getCategory();
        }
        return "";
    }


    private Task stringToTask(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 2) {
            return null;
        }

        String taskType = parts[0];
        String title = parts[1];
        String category = parts.length > 2 ? parts[2] : "";

        if (taskType.equals("TimedTask") && parts.length >= 4) {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(parts[3]);
                return new TimedTask(title, date, category);
            } catch (Exception e) {
                System.err.println("Error parsing timed task: " + e.getMessage());
            }
        } else if (taskType.equals("UntimedTask")) {
            return new UntimedTask(title, category);
        }

        return null;
    }

    private static class TaskComparator implements Comparator<Task> {
        @Override
        public int compare(Task task1, Task task2) {

            return task1.getDeadline().compareTo(task2.getDeadline());


            public void exitProgram() {
                System.exit(0);
            }
        }
    }
}
