package com.demo.aadharservice.exception;

import lombok.Generated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Generated
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        if (ex instanceof MethodArgumentNotValidException) {
            BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();
            List<FieldErrorMessage> errors = result.getFieldErrors().stream()
                    .map(x -> new FieldErrorMessage(x.getField(), x.getDefaultMessage()))
                    .collect(Collectors.toList());
            body.put("errors", errors);
        }
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateParseException.class)
    public final ResponseEntity<Object> dateParseException(DateParseException ex) {
        FieldErrorMessage errorDetails = new FieldErrorMessage("DateOfBirth"
                , ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> userNotFoundException(UserNotFoundException ex) {
        FieldErrorMessage errorDetails = new FieldErrorMessage("ID"
                , ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateUserException.class)
    public final ResponseEntity<Object> duplicateUserException(DuplicateUserException ex) {
        FieldErrorMessage errorDetails = new FieldErrorMessage("FirstName"
                , ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);
    }

}


