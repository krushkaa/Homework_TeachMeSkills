package lesson;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class ServiceAspect {
    @Before("execution(public void input(*))")
    public void processInput(JoinPoint joinPoint) {
        System.out.println("Process input method");
        String arg = (String) joinPoint.getArgs()[0];
        if (arg.equals("input")) {
            System.out.println("Coincidence " + "'" + arg + "'");
        } else {
            System.out.println("No matches " + "'" + arg + "'");
        }
    }

    @Before("execution(public void input(*))")
    public void processSomeMethod(JoinPoint joinPoint) {
        System.out.println("Process output method");
        String arg = (String) joinPoint.getArgs()[0];
        if (arg.equals("output")) {
            System.out.println("Coincidence " + "'" + arg + "'");
        } else {
            System.out.println("No matches " + "'" + arg + "'");
        }
    }
}
