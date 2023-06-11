private static class TaskComparator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        // Compare tasks based on their deadline
        int deadlineComparison = task1.getDeadline().compareTo(task2.getDeadline());

        // If the deadlines are equal, compare based on priority
        if (deadlineComparison == 0) {
            return task1.getPriority().compareTo(task2.getPriority());
        }

        return deadlineComparison;
    }
}