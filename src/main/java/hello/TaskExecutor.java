package hello;

import java.util.List;

public class TaskExecutor {

    private final List<Task> tasks;

    public TaskExecutor(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void executeAllTasks() {
        for (Task task : tasks) {
            task.executeTask();
        }
    }
}
