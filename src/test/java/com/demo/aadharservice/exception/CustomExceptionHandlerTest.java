package com.demo.aadharservice.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

@RunWith(MockitoJUnitRunner.class)
class CustomExceptionHandlerTest {

    private CustomExceptionHandler customExceptionHandler;
    @BeforeEach
    public void setUp(){
        customExceptionHandler = new CustomExceptionHandler();
    }

    @Test
    public void handleExceptionTest() throws Exception {
        Exception exception = null;
        WebRequest request = null;
        ResponseEntity<Object> object = customExceptionHandler.handleException(exception,request);
        Assertions.assertEquals(400,object.getStatusCodeValue());
    }
    @Test
    public void dateParseExceptionTest() {
        String message = "Not a valid date";
        DateParseException dateParseException = new DateParseException(message);
        ResponseEntity<Object> object = customExceptionHandler.dateParseException(dateParseException);
        Assertions.assertEquals(400,object.getStatusCodeValue());
    }

    @Test
    public void userNotFoundExceptionTest()  {
        String message = "User ID not found";
        UserNotFoundException dateParseException = new UserNotFoundException(message);
        ResponseEntity<Object> object = customExceptionHandler.userNotFoundException(dateParseException);
        Assertions.assertEquals(404,object.getStatusCodeValue());
    }

    @Test
    public void duplicateUserExceptionTest(){
        String message = "User is already registered";
        DuplicateUserException duplicateUserException = new DuplicateUserException(message);
        ResponseEntity<Object> object = customExceptionHandler.duplicateUserException(duplicateUserException);
        Assertions.assertEquals(409,object.getStatusCodeValue());
    }
}