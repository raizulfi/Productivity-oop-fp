import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class TaskFileManager {
    private String filename;

    public TaskFileManager(String filename) {
        this.filename = filename;
    }

    public void loadTasks(TodoList todoList) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String title = parts[0];
                    String description = parts[1];
                    boolean completed = Boolean.parseBoolean(parts[2]);

                    Task task = new SimpleTask(title, description);
                    task.setCompleted(completed);
                    todoList.addTask(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }

    }

    public void saveTasks(TodoList todoList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task task : todoList.getTasks()) {
                String line = task.getTitle() + ";" + task.getDescription() + ";" + task.isCompleted();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public void addTaskToFile(Task task) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            String line = task.getTitle() + ";" + task.getDescription() + ";" + task.isCompleted();
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error adding task to file: " + e.getMessage());
        }
    }

    public void updateTaskInFile(Task task) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String title = parts[0];
                    String description = parts[1];
                    boolean completed = Boolean.parseBoolean(parts[2]);

                    Task existingTask = new SimpleTask(title, description);
                    existingTask.setCompleted(completed);

                    if (existingTask.equals(task)) {
                        tasks.add(task);
                    } else {
                        tasks.add(existingTask);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating task in file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task t : tasks) {
                String line = t.getTitle() + ";" + t.getDescription() + ";" + t.isCompleted();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating task in file: " + e.getMessage());
        }
    }

    public void deleteTaskFromFile(Task task) {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String title = parts[0];
                    String description = parts[1];
                    boolean completed = Boolean.parseBoolean(parts[2]);

                    Task existingTask = new SimpleTask(title, description);
                    existingTask.setCompleted(completed);

                    if (!existingTask.equals(task)) {
                        tasks.add(existingTask);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting task from file: " + e.getMessage());
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Task t : tasks) {
                String line = t.getTitle() + ";" + t.getDescription() + ";" + t.isCompleted();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error deleting task from file: " + e.getMessage());
        }
    }
}


