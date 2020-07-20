package com.demo.aadharservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ContactValidationException extends RuntimeException {
    public ContactValidationException(String message) {
        super(message);
    }
}
