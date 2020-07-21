package com.demo.aadharservice.controller;

import com.demo.aadharservice.api.UserAadharControllerAPI;
import com.demo.aadharservice.model.User;
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

    @Override
    public ResponseEntity<User> enrollUserInfoToAdhar(@Valid User userAadhar) {

        return new ResponseEntity<>(userAadharService.enrollUserInfoToAdhar(userAadhar),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> updateUserInfoToAdhar(@Valid User userAadhar) {
        return new ResponseEntity<>(userAadharService.updateUserInfoToAdhar(userAadhar),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Optional<User>> getUserAadharInfo(@Valid Long id) {
        return new ResponseEntity<>(userAadharService.getUserAadharInfo(id),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?>   deleteUserAadharInfo(@Valid Long id) {
        userAadharService.deleteUserAadharInfo(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<User>> searchUserAadharInfo(Long id, String firstName, String lastName,
                                                           String dateOfBirth) {
        List<User>  userDataList = userAadharService.searchUserAadharInfo(id,firstName,lastName,dateOfBirth);
        return ResponseEntity.ok(userDataList);

       }
}
