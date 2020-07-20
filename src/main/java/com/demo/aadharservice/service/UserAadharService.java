package com.demo.aadharservice.service;

import com.demo.aadharservice.exception.UserNotFoundException;
import com.demo.aadharservice.model.UserAadhar;
import com.demo.aadharservice.repository.UserAadharRepository;
import com.demo.aadharservice.util.Utiilty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserAadharService {

    @Autowired
    UserAadharRepository userAadharRepository;

    @Autowired
    public UserAadharService(UserAadharRepository userAadharRepository) {
        this.userAadharRepository = userAadharRepository;
    }

    public UserAadhar enrollUserInfoToAdhar(UserAadhar userAadhar) {
        Utiilty.validateRequest(userAadhar);
        return userAadharRepository.save(userAadhar);

    }

    public UserAadhar updateUserInfoToAdhar(UserAadhar userAadhar) {
       Utiilty.validateRequest(userAadhar);
       if (userAadharRepository.existsById(userAadhar.getId()))
            userAadharRepository.save(userAadhar);
       else
            throw new UserNotFoundException(Utiilty.USER_ID_NOT_FOUND);
        return userAadhar;
    }

    public Optional<UserAadhar> getUserAadharInfo(Long id) {
        return Optional.ofNullable(userAadharRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException(Utiilty.USER_ID_NOT_FOUND)));
    }

    public void deleteUserAadharInfo(Long id) {
        if (userAadharRepository.existsById(id))
            userAadharRepository.deleteById(id);
        else
            throw new UserNotFoundException(Utiilty.USER_ID_NOT_FOUND);
    }


    public List<UserAadhar> searchUserAadharInfo(Long id, String firstName, String lastName, String dateOfBirth) {

        return userAadharRepository.findAllByIdOrFirstNameIgnoreCaseOrLastNameIgnoreCaseOrDateOfBirth(id, firstName, lastName, dateOfBirth);
    }
}

