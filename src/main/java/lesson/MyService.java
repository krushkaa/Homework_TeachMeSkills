package lesson;

import org.aspectj.lang.annotation.Before;

public class MyService {
    public void input(String input) {
        System.out.println("Processing input: " + input);
    }

    public void someMethod(String input) {
        System.out.println("Also processing input: " + input);
    }
}
