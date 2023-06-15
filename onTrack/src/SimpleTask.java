public class SimpleTask implements Task {
    private String title;
    private String description;
    private boolean completed;
    public SimpleTask(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    @Override
    public void markAsCompleted() {
        if (!completed) {
            completed = true;
            System.out.println("Task marked as completed: " + title);
        } else {
            throw new IllegalStateException("Task is already completed.");
        }
    }
}
