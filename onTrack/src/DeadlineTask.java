import java.time.LocalDateTime;

class DeadlineTask implements Task {
    private String title;
    private String description;
    private boolean completed;
    private LocalDateTime deadline;

    public DeadlineTask(String title, String description, LocalDateTime deadline) {
        this.title = title;
        this.description = description;
        this.completed = false;
        this.deadline = deadline;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        completed = true;
    }

    @Override
    public void setCompleted(boolean completed) {

    }

    public LocalDateTime getDeadline() {
        return deadline;
    }
}
