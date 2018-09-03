package com.demo.controller;

import com.demo.bean.Hello;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
@Api(description = "here description")
public class HelloController {

    @GetMapping("/helloWorld")
    @ApiOperation("hello World api operation")
    String hellos() {
        return "Hello World!";
    }

    @GetMapping("/hello")
    String helloWithRequestParam(@RequestParam(name = "name", defaultValue = "denis") String name) {
        return "Hello " + name + " !";
    }

    @GetMapping("/hello/{name}")
    String helloWithPathVariable(@PathVariable(name = "name") String name) {
        return "Hello " + name + " !";
    }

    @GetMapping("/hello-exception")
    String helloWithException() {
        String name = null;
        return "Hello" + name.toString() + " !";
    }


    @GetMapping("/hello-query")
    private ResponseEntity<?> query(@RequestParam(value = "query", required = true) String query) {
        if ("denis".equalsIgnoreCase(query)) {
            return ResponseEntity.ok("Found");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/hello")
    ResponseEntity<?> createHello(@RequestBody Hello hello) {
        int random = 5 + (int) (Math.random() * ((10 - 5) + 1));
        hello.setId(Long.valueOf(random));
        return ResponseEntity.created(URI.create("/hello/1")).body(hello);
    }

    @PostMapping("/hello-valid")
    ResponseEntity<?> createHelloValid(@Valid @RequestBody Hello hello) {
        int random = 5 + (int)(Math.random() * ((10 - 5) + 1));
        hello.setId(Long.valueOf(random));
        return ResponseEntity.created(URI.create("/hello/1")).body(hello);
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