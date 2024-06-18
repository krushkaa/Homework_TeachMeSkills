package hello;

import java.util.List;

public class Task {

    private String name;
    private List<Subtask> subtasks;

    public Task(String name, List<Subtask> subtasks) {
        this.name = name;
        this.subtasks = subtasks;
    }

    public void executeTask() {
        System.out.println("Executing task: " + name);
        for (Subtask subtask : subtasks) {
            subtask.executeSubtask();
        }
    }

    public static class Subtask {
        private String subtaskName;

        public Subtask(String subtaskName) {
            this.subtaskName = subtaskName;
        }

        public void executeSubtask() {
            System.out.println("Executing subtask: " + subtaskName);
        }
    }
}
