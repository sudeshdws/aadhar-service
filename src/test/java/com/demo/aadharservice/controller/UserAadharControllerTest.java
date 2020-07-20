package com.demo.aadharservice.controller;

import com.demo.aadharservice.model.UserAadhar;
import com.demo.aadharservice.service.UserAadharService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
public class UserAadharControllerTest {

    @InjectMocks
    UserAadharController userAadharController;

    @Mock
    UserAadharService userAadharService;


    @BeforeEach
    public void init() {
    }

    @Test
    public void enrollUserInfoToAdharTest() {

        UserAadhar response = userAadharResponse();
        UserAadhar request = userAadharRequest();

        Mockito.when(userAadharService.enrollUserInfoToAdhar(Mockito.any(UserAadhar.class)))
                .thenReturn(response);
        ResponseEntity<UserAadhar> actual = userAadharController.
                enrollUserInfoToAdhar(request);

        Assert.assertEquals(201, actual.getStatusCodeValue());

    }


    @Test
    public void updateUserInfoToAdharTest() {

        UserAadhar response = userAadharResponse();
        UserAadhar request = userAadharRequest();
        Mockito.when(userAadharService.updateUserInfoToAdhar(request))
                .thenReturn(response);
        ResponseEntity<UserAadhar> actual = userAadharController.
                updateUserInfoToAdhar(request);

        Assert.assertEquals(200, actual.getStatusCodeValue());
    }

    @Test
    public void getUserAadharInfoTest() {

        UserAadhar response = userAadharResponse();

        Long val = 20L;
        Mockito.when(userAadharService.getUserAadharInfo(val))
                .thenReturn(Optional.of(response));
        ResponseEntity<Optional<UserAadhar>> actual = userAadharController.
                getUserAadharInfo(val);

        Assert.assertEquals(200, actual.getStatusCodeValue());
        Assert.assertEquals("John", actual.getBody().get().getFirstName());
    }

    @Test
    public void deleteUserAadharInfoTest() {
        Long val = 20L;

        Mockito.doNothing().when(userAadharService).deleteUserAadharInfo(val);
        ResponseEntity<?> actual = userAadharController.deleteUserAadharInfo(val);

        Assert.assertEquals(204, actual.getStatusCodeValue());

    }

    @Test
    public void searchUserAadharInfoTest() {

        Long id = null;
        String firstName = "John";
        String lastName = "";
        String dateOfBirth = "";
        List<UserAadhar> response = userAadharResponseList();
        Mockito.when(userAadharService.searchUserAadharInfo(id, firstName, lastName, dateOfBirth))
                .thenReturn(response);
        ResponseEntity<List<UserAadhar>> actual = userAadharController.
                searchUserAadharInfo(id, firstName, lastName, dateOfBirth);

        Assert.assertEquals(200, actual.getStatusCodeValue());
        Assert.assertEquals(2, actual.getBody().size());
    }


    private UserAadhar userAadharResponse() {
        UserAadhar userAadhar = new UserAadhar();
        userAadhar.setId(1);
        userAadhar.setFirstName("John");
        userAadhar.setLastName("Doe");
        userAadhar.setContactNumber("4343434343");
        userAadhar.setDateOfBirth("2020-07-14");
        userAadhar.setCity("Washington");
        return userAadhar;
    }

    private UserAadhar userAadharRequest() {
        UserAadhar userAadhar = new UserAadhar();
        userAadhar.setId(2);
        userAadhar.setFirstName("John");
        userAadhar.setLastName("Doe");
        userAadhar.setContactNumber("4343434343");
        userAadhar.setDateOfBirth("2020-07-14");
        userAadhar.setCity("Washington");

        return userAadhar;
    }

    private List<UserAadhar> userAadharResponseList() {
        List<UserAadhar> list = new ArrayList<>();
        UserAadhar userAadhar1 = new UserAadhar();
        userAadhar1.setId(1);
        userAadhar1.setFirstName("John");
        userAadhar1.setLastName("Doe");
        userAadhar1.setContactNumber("4343434343");
        userAadhar1.setDateOfBirth("2020-07-14");
        userAadhar1.setCity("Washington");

        UserAadhar userAadhar2 = new UserAadhar();
        userAadhar2.setId(2);
        userAadhar2.setFirstName("John");
        userAadhar2.setLastName("Doe");
        userAadhar2.setContactNumber("4343434343");
        userAadhar2.setDateOfBirth("2020-07-14");
        userAadhar2.setCity("Washington");
        list.add(userAadhar1);
        list.add(userAadhar2);
        return list;
    }


}
