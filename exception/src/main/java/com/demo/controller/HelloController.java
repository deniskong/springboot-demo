package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    String helloWithRequestParam(@RequestParam(name = "name", defaultValue = "denis") String name) {
        return "Hello " + name + " !";
    }


    @GetMapping("/hello-exception")
    String helloWithException() {
        String name = null;
        return "Hello" + name.toString() + " !";
    }


   /* @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationError(MethodArgumentNotValidException exception) {
        System.out.println("handleException");

        String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());

        return ErrorResponse.builder().message(errorMsg).hello("hello world").build();
    }*/

}