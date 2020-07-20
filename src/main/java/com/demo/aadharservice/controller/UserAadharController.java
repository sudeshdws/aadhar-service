package com.demo.aadharservice.controller;

import com.demo.aadharservice.api.UserAadharControllerAPI;
import com.demo.aadharservice.model.UserAadhar;
import com.demo.aadharservice.service.UserAadharService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
public class UserAadharController implements UserAadharControllerAPI {

    @Autowired
    UserAadharService userAadharService;

    public ResponseEntity<UserAadhar> enrollUserInfoToAdhar(@Valid UserAadhar userAadhar) {

        return new ResponseEntity<>(userAadharService.enrollUserInfoToAdhar(userAadhar),
                HttpStatus.CREATED);
    }

    public ResponseEntity<UserAadhar> updateUserInfoToAdhar(@Valid UserAadhar userAadhar) {
        return new ResponseEntity<>(userAadharService.updateUserInfoToAdhar(userAadhar),
                HttpStatus.OK);
    }


    public ResponseEntity<Optional<UserAadhar>> getUserAadharInfo(@Valid Long id) {
        return new ResponseEntity<>(userAadharService.getUserAadharInfo(id),
                HttpStatus.OK);
    }


    public ResponseEntity<?>   deleteUserAadharInfo(@Valid Long id) {
        userAadharService.deleteUserAadharInfo(id);
        return new ResponseEntity<UserAadhar>(HttpStatus.NO_CONTENT);
    }


    public ResponseEntity<List<UserAadhar>> searchUserAadharInfo(Long id, String firstName, String lastName,
                                                                     String dateOfBirth) {
        List<UserAadhar>  userDataList = userAadharService.searchUserAadharInfo(id,firstName,lastName,dateOfBirth);
        return ResponseEntity.ok(userDataList);

       }
}
