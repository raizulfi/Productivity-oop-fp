public class RecurringTask implements Task {
    private String title;
    private String description;
    private int completionTally;
    private int completionTarget;

    public RecurringTask(String title, String description, int completionTarget) {
        this.title = title;
        this.description = description;
        this.completionTally = 0;
        this.completionTarget = completionTarget;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completionTally >= completionTarget;
    }

    public void markAsCompleted() {
        if (!isCompleted()) {
            completionTally++;
        }
    }

    @Override
    public void setCompleted(boolean completed) {

    }

    public int getCompletionTally() {
        return completionTally;
    }

    public int getCompletionTarget() {
        return completionTarget;
    }
}
