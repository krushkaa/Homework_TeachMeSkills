package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class Config {

    @Bean
    public Task task1() {
        List<Task.Subtask> subtasks = Arrays.asList(
                new Task.Subtask("Subtask 1.1"),
                new Task.Subtask("Subtask 1.2")
        );
        return new Task("Task 1", subtasks);
    }

    @Bean
    public Task task2() {
        List<Task.Subtask> subtasks = Arrays.asList(
                new Task.Subtask("Subtask 2.1"),
                new Task.Subtask("Subtask 2.2")
        );
        return new Task("Task 2", subtasks);
    }

    @Bean
    public TaskExecutor taskExecutor(List<Task> tasks) {
        return new TaskExecutor(tasks);
    }
}
