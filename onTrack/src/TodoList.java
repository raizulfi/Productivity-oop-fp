import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class TodoList {
    private List<Task> taskList;

    public TodoList() {
        this.taskList = taskList = new ArrayList<>();
        ;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public void displayTasks() {
        System.out.println("Tasks:");
        if (taskList.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.print((i + 1) + ". " + task.getTitle() + " - Completed: " + task.isCompleted());

                if (task instanceof RecurringTask) {
                    RecurringTask recurringTask = (RecurringTask) task;
                    System.out.print(" (Tally: " + recurringTask.getCompletionTally() +
                            " / Target: " + recurringTask.getCompletionTarget() + ")");
                } else if (task instanceof DeadlineTask) {
                    DeadlineTask deadlineTask = (DeadlineTask) task;
                    LocalDateTime deadline = deadlineTask.getDeadline();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    String deadlineStr = deadline.format(formatter);

                    System.out.print(" (Deadline: " + deadlineStr + ")");
                    if (LocalDateTime.now().isAfter(deadline)) {
                        System.out.print(" - Overdue");
                    }
                }

                System.out.println();
            }
        }
        System.out.println();
    }
}
