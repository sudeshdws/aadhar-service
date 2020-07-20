package com.demo.aadharservice.exception;

import lombok.Data;

@Data
class ErrorMessage {
private String status;
private  String message;

  public ErrorMessage(String status, String message) {
    this.status = status;
    this.message = message;
  }
}