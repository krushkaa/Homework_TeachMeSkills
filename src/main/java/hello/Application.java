package hello;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        TaskExecutor taskExecutor = context.getBean(TaskExecutor.class);
        taskExecutor.executeAllTasks();
    }
}
