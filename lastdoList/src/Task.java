import java.util.Date;

abstract class Task {
    private String title;
    private boolean completed;

    public Task(String title) {
        this.title = title;
        this.completed = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsCompleted() {
        completed = true;
    }

    public void markAsIncomplete() {
        completed = false;
    }

    public abstract String getDetails();

    public void setTitle(String title) {
        this.title = title;
    }

    public abstract <T> Comparable<T> getDeadline();
}