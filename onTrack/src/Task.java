public interface Task {
    String getTitle();
    String getDescription();
    boolean isCompleted();
    void markAsCompleted();
    void setCompleted(boolean completed);
}
