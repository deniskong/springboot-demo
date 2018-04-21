package com.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/helloWorld")
    String hello() {
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


}