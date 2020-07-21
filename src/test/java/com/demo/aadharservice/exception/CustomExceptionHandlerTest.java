package com.demo.aadharservice.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

@RunWith(MockitoJUnitRunner.class)
class CustomExceptionHandlerTest {


    @Test
    void handleException() throws Exception {
        Exception exception = null;
        WebRequest request = null;
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        ResponseEntity<Object> object = customExceptionHandler.handleException(exception,request);
        Assertions.assertEquals(400,object.getStatusCodeValue());
    }
    @Test
    void dateParseException() {
        String message = "Not a valid date";
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        DateParseException dateParseException = new DateParseException(message);
        ResponseEntity<Object> object = customExceptionHandler.dateParseException(dateParseException);
        Assertions.assertEquals(400,object.getStatusCodeValue());
    }

    @Test
    void userNotFoundException()  {

        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        String message = "User ID not found";
        UserNotFoundException dateParseException = new UserNotFoundException(message);
        ResponseEntity<Object> object = customExceptionHandler.userNotFoundException(dateParseException);
        Assertions.assertEquals(400,object.getStatusCodeValue());

    }

    @Test
    void customValidationException() {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        String message = "First Nme not found";
        CustomValidationException validationException = new CustomValidationException(message);
        ResponseEntity<Object> object = customExceptionHandler.
                                              customValidationException(validationException);
        Assertions.assertEquals(400,object.getStatusCodeValue());

    }

    @Test
    void contactValidationException() {
        CustomExceptionHandler customExceptionHandler = new CustomExceptionHandler();
        String message = "First Nme not found";
        ContactValidationException contactValidationException = new ContactValidationException(message);
        ResponseEntity<Object> object = customExceptionHandler.
                contactValidationException(contactValidationException);
        Assertions.assertEquals(400,object.getStatusCodeValue());
    }
}