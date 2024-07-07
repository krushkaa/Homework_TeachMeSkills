package com.task.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ArithmeticException.class)
    public String handleArithmeticException(ArithmeticException e) {
        System.out.println("Arithmetic Exception");
        return "error";
    }
}
