package com.demo.aadharservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.xml.bind.ValidationException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler {

    private static final String ISO_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    ErrorMessage exceptionHandler(ValidationException e) {
        return new ErrorMessage("400", e.getMessage());
    }


    public Date validateDate(String date) {
        Date parsedDate;
        SimpleDateFormat sdf = new SimpleDateFormat(ISO_DATETIME_FORMAT);
        try {
            parsedDate = sdf.parse(date);
        } catch (ParseException e) {
            //log.error(e.getMessage(), e);
            return null;
        }
        return parsedDate;
    }


    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public final ResponseEntity<Object> handleException(Exception ex, WebRequest request) throws Exception {

        Map<String, Object> body = new LinkedHashMap<>();

        if (ex instanceof MethodArgumentNotValidException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            BindingResult result = ((MethodArgumentNotValidException) ex).getBindingResult();

            //body.put("timestamp", new Date());
            //body.put("status", status.value());
            List<FieldErrorMessage> errors = result.getFieldErrors().stream()
                    .map(x -> new FieldErrorMessage(x.getField(), x.getDefaultMessage()))
                    .collect(Collectors.toList());
            body.put("errors", errors);

        }
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
////    @ExceptionHandler(MethodArgumentNotValidException.class)
////    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
////        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
////        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
////
////        return fieldErrorMessages;
////    }


    @ExceptionHandler(DateParseException.class)
    public final ResponseEntity<Object> dateParseException(DateParseException ex) {
        FieldErrorMessage errorDetails = new FieldErrorMessage("DateOfBirth"
                ,ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> userNotFoundException(UserNotFoundException ex) {
        FieldErrorMessage errorDetails = new FieldErrorMessage("ID"
                ,ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomValidationException.class)
    public final ResponseEntity<Object> customValidationException(CustomValidationException ex) {
        FieldErrorMessage errorDetails = new FieldErrorMessage("Name"
                ,ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ContactValidationException.class)
    public final ResponseEntity<Object> contactValidationException(ContactValidationException ex) {
        FieldErrorMessage errorDetails = new FieldErrorMessage("ContactNumber"
                ,ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}


