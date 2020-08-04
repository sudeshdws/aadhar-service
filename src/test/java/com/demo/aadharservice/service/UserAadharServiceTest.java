package com.demo.aadharservice.service;

import com.demo.aadharservice.exception.*;
import com.demo.aadharservice.model.User;
import com.demo.aadharservice.repository.UserAadharRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.*;


@RunWith(MockitoJUnitRunner.class)
class UserAadharServiceTest {

    @Mock
    private UserAadharRepository userAadharRepository;

    @InjectMocks
    private UserAadharService userAadharService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void enrollUserInfoToAdharTest() {
        User request = userAadharRequest();
        User response = userAadharResponse();
        Mockito.when(userAadharRepository.save(request)).thenReturn(response);
        User userAadharResp = userAadharService.enrollUserInfoToAdhar(request);
        Assert.assertEquals(request.getFirstName(), userAadharResp.getFirstName());
        Assert.assertEquals(request.getLastName(), userAadharResp.getLastName());
    }


    @Test
    void updateUserInfoToAdharTest() {
        Long id = 123234343432L;
        User request = userAadharRequest();
        Mockito.when(userAadharRepository.existsById(id)).thenReturn(true);
        User finalResp = userAadharService.updateUserInfoToAdhar(request,id);
        Assert.assertEquals(request.getFirstName(), finalResp.getFirstName());
        Assert.assertEquals(request.getLastName(), finalResp.getLastName());
        Assert.assertNotEquals("232323", finalResp.getContactNumber());
    }

    @Test
    void getUserAadharInfo()  {
        User request = userAadharRequest();
        User response = userAadharResponse();
        Mockito.when(userAadharRepository.findById(request.getId())).thenReturn(Optional.of(response));
        Optional<User> finalResp = userAadharService.getUserAadharInfo(request.getId());
        Assert.assertEquals(response.getFirstName(), finalResp.get().getFirstName());
        Assert.assertEquals(response.getLastName(), finalResp.get().getLastName());
    }

    @Test
    void deleteUserAadharInfoTest() {
        User request = userAadharRequest();
        Mockito.when(userAadharRepository.existsById(request.getId())).thenReturn(true);
        userAadharService.deleteUserAadharInfo(request.getId());
        Mockito.verify(userAadharRepository).deleteById(request.getId());
    }

    @Test
    void updateUserInfoToAdharExceptionTest(){

        User request = userAadharRequest();
        Long id = 123234343432L;
        Mockito.when(userAadharRepository.existsById(request.getId())).thenReturn(false);
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userAadharService.updateUserInfoToAdhar(request,id);
        });
    }




    @Test
    void getUserAadharInfoIfUserAadharIdNotFound(){
        User request = userAadharRequest();
        Long id = 123234343432L;
        Mockito.when(userAadharRepository.findById(request.getId())).thenReturn(Optional.empty());
        Mockito.when(userAadharRepository.existsById(request.getId())).thenReturn(true);
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userAadharService.getUserAadharInfo(id);
        });
    }

    @Test
    void deleteUserAadharInfoIfUserNotFoundTest() {
        User request = userAadharRequest();
        Mockito.when(userAadharRepository.existsById(request.getId())).thenReturn(false);
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userAadharService.deleteUserAadharInfo(request.getId());
        });
    }

    @Test
    void userAllReadyRegisterTest(){
        User request = userAadharRequest();
        Mockito.when(userAadharRepository.
                existsByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDateOfBirthAndContactNumber(request.getFirstName(),
                        request.getLastName(),
                        request.getDateOfBirth(),
                        request.getContactNumber())
              ).thenReturn(true);
        Assertions.assertThrows(DuplicateUserException.class, () -> {
            userAadharService.enrollUserInfoToAdhar(request);
        });
    }


    @Test
    void dateParseFunctionTest() {
        User request = userAadharRequest();
        request.setDateOfBirth("4242423333");
        Assertions.assertThrows(DateParseException.class, () -> {
            userAadharService.enrollUserInfoToAdhar(request);
            ;
        });
    }

    @Test
    void searchUserAadharInfoTest() {
        List<User> responseList = userAadharResponseList();
        Long id = 123234343432L;
        String firstName = "John";
        String lastName = "Doe";
        String dateOfBirth = "2020-07-14";
       Mockito.when(userAadharRepository.findAllByIdOrFirstNameIgnoreCaseOrLastNameIgnoreCaseOrDateOfBirth(
                id, firstName, lastName, dateOfBirth)).thenReturn(responseList);
        List<User> responseLists = userAadharService.searchUserAadharInfo(id, firstName, lastName, dateOfBirth);
        Assert.assertEquals(responseList.size(), responseLists.size());
        Assert.assertEquals(responseList.get(1).getFirstName(), responseLists.get(1).getFirstName());
    }

    private User userAadharRequest() {
        User userAadhar = new User();
        userAadhar.setId(123234345456L);
        userAadhar.setFirstName("John");
        userAadhar.setLastName("Doe");
        userAadhar.setContactNumber("434343434");
        userAadhar.setDateOfBirth("1989-11-11");
        userAadhar.setCity("Washington");
        return userAadhar;
    }

    private User userAadharResponse() {
        User userAadhar = new User();
        userAadhar.setId(123234345456L);
        userAadhar.setFirstName("John");
        userAadhar.setLastName("Doe");
        userAadhar.setContactNumber("4343434343");
        userAadhar.setDateOfBirth("1989-11-11");
        return userAadhar;
    }

    private List<User> userAadharResponseList() {
        List<User> list = new ArrayList<>();
        User userAadhar1 = new User();
        userAadhar1.setId(123234345456L);
        userAadhar1.setFirstName("John");
        userAadhar1.setLastName("Doe");
        userAadhar1.setContactNumber("4343434343");
        userAadhar1.setDateOfBirth("1989-11-11");
        userAadhar1.setCity("Washington");

        User userAadhar2 = new User();
        userAadhar2.setId(123234345456L);
        userAadhar2.setFirstName("John");
        userAadhar2.setLastName("Doe");
        userAadhar2.setContactNumber("4343434343");
        userAadhar2.setDateOfBirth("1989-11-11");
        userAadhar2.setCity("Washington");

        list.add(userAadhar1);
        list.add(userAadhar2);
        return list;
    }


    @Test
    public void testUserClass() {
        User user = new User();
        user.setId(123234345456L);
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setContactNumber("4343434343");
        user.setDateOfBirth("1989-11-11");
        user.setCity("Washington");
        Assert.assertEquals("John", user.getFirstName());
        Assert.assertEquals("Doe", user.getLastName());
        Assert.assertEquals("4343434343", user.getContactNumber());
        Assert.assertEquals("1989-11-11", user.getDateOfBirth());
        Assert.assertEquals("Washington", user.getCity());

        User user1 = new User();
        user1.setId(123234345456L);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setContactNumber("4343434343");
        user1.setDateOfBirth("1989-11-11");
        user1.setCity("Washington");

        Assert.assertEquals(user.getId(), user1.getId());
        Assert.assertEquals(user.getFirstName(), user1.getFirstName());
        Assert.assertEquals(user.getLastName(), user1.getLastName());
        Assert.assertEquals(user.getContactNumber(), user1.getContactNumber());
        Assert.assertEquals(user.getDateOfBirth(), user1.getDateOfBirth());
        Assert.assertEquals(user.getCity(), user1.getCity());

    }

    @Test
    public void testErrorMessageClass() {
        String field = "LastName";
        String message = "Last Name Cant be Empty";
        FieldErrorMessage fieldErrorMessage = new FieldErrorMessage(field, message);
        Assert.assertEquals("LastName", fieldErrorMessage.getField());
        Assert.assertEquals("Last Name Cant be Empty", fieldErrorMessage.getMessage());
    }

    @Test
    public void testFieldErrorMessage() {
        String field = "Test";
        String message = "Should be filled";
        FieldErrorMessage fieldErrorMessage = new FieldErrorMessage(field, message);
        fieldErrorMessage.setField(field);
        fieldErrorMessage.setMessage(message);
        Assert.assertEquals("Test", fieldErrorMessage.getField());
        Assert.assertEquals("Should be filled", fieldErrorMessage.getMessage());

    }

    @Test
    public void testErrorMessage() {
        String status = "Test";
        String message = "Should be filled";
        ErrorMessage errorMessages = new ErrorMessage(status, message);
        errorMessages.setMessage(message);
        errorMessages.setStatus(status);
        Assert.assertEquals("Test", errorMessages.getStatus());
        Assert.assertEquals("Should be filled", errorMessages.getMessage());
    }


}

