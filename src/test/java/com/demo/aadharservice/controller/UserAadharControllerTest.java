package com.demo.aadharservice.controller;

import com.demo.aadharservice.model.User;
import com.demo.aadharservice.service.UserAadharService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import java.util.*;


@RunWith(MockitoJUnitRunner.class)
public class UserAadharControllerTest {

    @InjectMocks
    UserAadharController userAadharController;

    @Mock
    UserAadharService userAadharService;


    @Test
    public void enrollUserInfoToAdharTest() {
        User response = userAadharResponse();
        User request = userAadharRequest();
        Mockito.when(userAadharService.enrollUserInfoToAdhar(Mockito.any(User.class)))
                .thenReturn(response);
        ResponseEntity<User> actual = userAadharController.
                enrollUserInfoToAdhar(request);
        Assert.assertEquals(201, actual.getStatusCodeValue());
    }

    @Test
    public void updateUserInfoToAdharTest() {
        User response = userAadharResponse();
        User request = userAadharRequest();
        Long id = 123234343432L;
        Mockito.when(userAadharService.updateUserInfoToAdhar(request, id))
                .thenReturn(response);
        ResponseEntity<User> actual = userAadharController.
                updateUserInfoToAdhar(request, id);
        Assert.assertEquals(200, actual.getStatusCodeValue());
    }

    @Test
    public void getUserAadharInfoTest() {
        User response = userAadharResponse();
        Long id = 123234343432L;
        Mockito.when(userAadharService.getUserAadharInfo(id))
                .thenReturn(Optional.of(response));
        ResponseEntity<Optional<User>> actual = userAadharController.
                getUserAadharInfo(id);
        Assert.assertEquals(200, actual.getStatusCodeValue());
        Assert.assertEquals("John", actual.getBody().get().getFirstName());
    }

    @Test
    public void deleteUserAadharInfoTest() {
        Long id = 123234343432L;
        Mockito.doNothing().when(userAadharService).deleteUserAadharInfo(id);
        ResponseEntity<?> actual = userAadharController.deleteUserAadharInfo(id);
        Assert.assertEquals(204, actual.getStatusCodeValue());
    }

    @Test
    public void searchUserAadharInfoTest() {
        Long id = null;
        String firstName = "John";
        String lastName = "";
        String dateOfBirth = "";
        List<User> response = userAadharResponseList();
        Mockito.when(userAadharService.searchUserAadharInfo(id, firstName, lastName, dateOfBirth))
                .thenReturn(response);
        ResponseEntity<List<User>> actual = userAadharController.
                searchUserAadharInfo(id, firstName, lastName, dateOfBirth);
        Assert.assertEquals(200, actual.getStatusCodeValue());
        Assert.assertEquals(2, actual.getBody().size());
    }

    private User userAadharResponse() {

        User userAadhar = new User();
        userAadhar.setId(123234343432L);
        userAadhar.setFirstName("John");
        userAadhar.setLastName("Doe");
        userAadhar.setContactNumber("4343434343");
        userAadhar.setDateOfBirth("1987-11-11");
        userAadhar.setCity("Washington");
        return userAadhar;
    }

    private User userAadharRequest() {
        User userAadhar = new User();
        userAadhar.setId(123234343432L);
        userAadhar.setFirstName("John");
        userAadhar.setLastName("Doe");
        userAadhar.setContactNumber("4343434343");
        userAadhar.setDateOfBirth("1987-11-11");
        userAadhar.setCity("Washington");
        return userAadhar;
    }

    private List<User> userAadharResponseList() {
        List<User> list = new ArrayList<>();
        User user1 = new User();
        user1.setId(123234343432L);
        user1.setFirstName("John");
        user1.setLastName("Doe");
        user1.setContactNumber("4343434343");
        user1.setDateOfBirth("1987-11-11");
        user1.setCity("Washington");

        User user2 = new User();
        user2.setId(123234343432L);
        user2.setFirstName("John");
        user2.setLastName("Doe");
        user2.setContactNumber("4343434343");
        user2.setDateOfBirth("1987-11-11");
        user2.setCity("Washington");
        list.add(user1);
        list.add(user2);
        return list;
    }

}
