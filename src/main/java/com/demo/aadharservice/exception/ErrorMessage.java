package com.demo.aadharservice.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorMessage {
    private String status;
    private String message;

    public ErrorMessage(String status, String message) {
        this.status = status;
        this.message = message;
    }
}