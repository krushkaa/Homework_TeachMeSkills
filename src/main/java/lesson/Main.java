package lesson;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MyService myService = (MyService) context.getBean("MyService", MyService.class);

        myService.input("Hello World");
        myService.input("input");
        myService.input("output");
    }
}
