package com.demo.controller;

import org.springframework.data.rest.core.RepositoryConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

   /* @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(MethodArgumentNotValidException exception) {

        String errorMsg = exception.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst()
                .orElse(exception.getMessage());

        return ErrorResponse.builder().message(errorMsg).hello("hello world").build();
    }*/


    @ExceptionHandler({ RepositoryConstraintViolationException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {

        RepositoryConstraintViolationException nevEx =
                (RepositoryConstraintViolationException) ex;

        String errors = nevEx.getErrors().getAllErrors().stream()
                .map(p -> p.toString()).collect(Collectors.joining("\n"));

        return new ResponseEntity<Object>(errors, new HttpHeaders(),
                HttpStatus.PARTIAL_CONTENT);
    }
}
