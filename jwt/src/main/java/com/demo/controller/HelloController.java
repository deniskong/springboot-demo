package com.demo.controller;

import com.demo.bean.Hello;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/helloWorld")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    String hellos() {
        return "Hello World!";
    }

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    String helloWithRequestParam(@RequestParam(name = "name", defaultValue = "denis") String name) {
        return "Hello " + name + " !";
    }

    @GetMapping("/hello/{name}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
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
    ResponseEntity<?> createHello(@Valid @RequestBody Hello hello) {
        return ResponseEntity.created(URI.create("/hello/1")).body("1");
    }


}