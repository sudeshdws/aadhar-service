package com.demo.aadharservice.service;

import com.demo.aadharservice.exception.DateParseException;
import com.demo.aadharservice.exception.UserNotFoundException;
import com.demo.aadharservice.model.UserAadhar;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RunWith(MockitoJUnitRunner.class)
class UserAadharServiceTest {

    @Mock
    UserAadharRepository userAadharRepository;

    @InjectMocks
    UserAadharService userAadharService;


    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void enrollUserInfoToAdharTest() {
        UserAadhar request = userAadharRequest();
        UserAadhar responce = userAadharResponse();
        Mockito.when(userAadharRepository.save(request)).thenReturn(responce);
        UserAadhar userAadharResp = userAadharService.enrollUserInfoToAdhar(request);

        Assert.assertEquals(request.getFirstName(), userAadharResp.getFirstName());
        Assert.assertEquals(request.getLastName(), userAadharResp.getLastName());
   }

    @Test
    void updateUserInfoToAdharTest() {
        UserAadhar request = userAadharRequest();
        Mockito.when(userAadharRepository.existsById(request.getId())).thenReturn(true);
        UserAadhar finalResp = userAadharService.updateUserInfoToAdhar(request);
        Assert.assertEquals(request.getFirstName(), finalResp.getFirstName());
        Assert.assertEquals(request.getLastName(), finalResp.getLastName());
        Assert.assertNotEquals("232323", finalResp.getContactNumber());
    }

    @Test
    void getUserAadharInfo() {
        UserAadhar request = userAadharRequest();
        UserAadhar response = userAadharResponse();
        Mockito.when(userAadharRepository.findById(request.getId())).thenReturn(Optional.of(response));
        Optional<UserAadhar> finalResp =   userAadharService.getUserAadharInfo(request.getId());

        Assert.assertEquals( response.getFirstName(),finalResp.get().getFirstName());
        Assert.assertEquals( response.getLastName() ,finalResp.get().getLastName());
    }

    @Test
    void deleteUserAadharInfoTest() {

        UserAadhar request = userAadharRequest();
        Mockito.when(userAadharRepository.existsById(request.getId())).thenReturn(true);
        userAadharService.deleteUserAadharInfo(request.getId());
        Mockito.verify(userAadharRepository).deleteById(request.getId());
    }


    @Test
    void updateUserInfoToAdharExceptionTest() {
        UserAadhar request = userAadharRequest();
        Mockito.when(userAadharRepository.existsById(request.getId())).thenReturn(false);
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userAadharService.updateUserInfoToAdhar(request);
        });
    }


    @Test
    void getUserAadharInfoIfUserAadharIdNotFound() {
        Long id = 233232222L;
        UserAadhar request = userAadharRequest();
        Mockito.when(userAadharRepository.findById(request.getId())).thenReturn(Optional.empty());
        Mockito.when(userAadharRepository.existsById(request.getId())).thenReturn(true);
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userAadharService.getUserAadharInfo(id);
        });
    }

    @Test
    void deleteUserAadharInfoIfUserNotFoundTest() {

        UserAadhar request = userAadharRequest();
        Mockito.when(userAadharRepository.existsById(request.getId())).thenReturn(false);
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userAadharService.deleteUserAadharInfo(request.getId());
        });
    }

    @Test
    void dateParseFunctionTest(){
        UserAadhar request = userAadharRequest();
        request.setDateOfBirth("4242423333");
        Assertions.assertThrows(DateParseException.class, () -> {
            userAadharService.enrollUserInfoToAdhar(request);;
        });
    }


    @Test
    void searchUserAadharInfo() {
        List<UserAadhar>  responseList = userAadharResponseList();
        Long id = 123232222L ;
        String firstName = "John";
        String lastName = "Doe" ;
        String dateOfBirth = "2020-07-14";

        Mockito.when(userAadharRepository.findAllByIdOrFirstNameIgnoreCaseOrLastNameIgnoreCaseOrDateOfBirth(
                 id,firstName,lastName,dateOfBirth)).thenReturn(responseList);
        List<UserAadhar>  responseLists = userAadharService.searchUserAadharInfo(id,firstName,lastName,dateOfBirth);
        Assert.assertEquals(responseList.size() , responseLists.size());
        Assert.assertEquals(responseList.get(1).getFirstName() , responseLists.get(1).getFirstName());
    }


    private UserAadhar userAadharRequest() {
        UserAadhar userAadhar = new UserAadhar();
        userAadhar.setId(1);
        userAadhar.setFirstName("John");
        userAadhar.setLastName("Doe");
        userAadhar.setContactNumber("4343434343");
        userAadhar.setDateOfBirth("2020-07-14");
        userAadhar.setCity("Washington");
        return userAadhar;
    }

    private UserAadhar userAadharResponse() {
        UserAadhar userAadhar = new UserAadhar();
        userAadhar.setId(1);
        userAadhar.setFirstName("John");
        userAadhar.setLastName("Doe");
        userAadhar.setContactNumber("4343434343");
        userAadhar.setDateOfBirth("2020-07-14");
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

