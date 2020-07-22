package com.demo.aadharservice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CityValidationException extends RuntimeException {
    public CityValidationException(String message) {
        super(message);
    }
}

