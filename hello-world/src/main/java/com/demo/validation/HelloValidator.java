package com.demo.validation;

import com.demo.bean.Hello;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class HelloValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Hello.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Hello hello = (Hello) obj;
        if (checkInputString(hello.getName())) {
            errors.rejectValue("name", "name.empty");
        }

        if (checkInputString(hello.getEmail())) {
            errors.rejectValue("email", "email.empty");
        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}