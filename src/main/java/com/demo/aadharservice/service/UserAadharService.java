package com.demo.aadharservice.service;

import com.demo.aadharservice.exception.DuplicateUserException;
import com.demo.aadharservice.exception.UserNotFoundException;
import com.demo.aadharservice.model.User;
import com.demo.aadharservice.repository.UserAadharRepository;
import com.demo.aadharservice.util.Utiilty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UserAadharService {

    @Autowired
    UserAadharRepository userAadharRepository;

    private static final Logger log = LoggerFactory.getLogger(UserAadharService.class);

    @Autowired
    public UserAadharService(UserAadharRepository userAadharRepository) {
        this.userAadharRepository = userAadharRepository;
    }

    public User enrollUserInfoToAdhar(User userAadhar) {
        Utiilty.isValidDate(userAadhar.getDateOfBirth());
        User user = new User();
        Boolean isExist = userAadharRepository.
                existsByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDateOfBirthAndContactNumber(userAadhar.getFirstName(),
                userAadhar.getLastName(),userAadhar.getDateOfBirth(),userAadhar.getContactNumber());

        if(isExist) throw DuplicateUserException(Utiilty.USER_IS_ALREADY_REGISTERED)


        try {
            userAadhar.setId(ThreadLocalRandom.current().nextLong(910000000000L));
            user = userAadharRepository.save(userAadhar);
        } catch (Exception e) {
            log.error("Error in enrollUserInfoToAdhar {}", e.getMessage());
        }
        return user;

    }

    public User updateUserInfoToAdhar(User userAadhar, Long id) {
        log.info("updateUserInfoToAdhar() starts ");
        Utiilty.isValidDate(userAadhar.getDateOfBirth());

        if (userAadharRepository.existsById(id)) {
            userAadhar.setId(id);
            userAadharRepository.save(userAadhar);
        } else {
            throw new UserNotFoundException(Utiilty.USER_ID_NOT_FOUND);
        }
        return userAadhar;
    }

    public Optional<User> getUserAadharInfo(Long id) {
        log.info("getUserAadharInfo() starts {}", id);
        return Optional.ofNullable(userAadharRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException(Utiilty.USER_ID_NOT_FOUND)));
    }

    public void deleteUserAadharInfo(Long id) {
        log.info("deleteUserAadharInfo() starts {}", id);
        if (userAadharRepository.existsById(id))
            userAadharRepository.deleteById(id);
        else
            throw new UserNotFoundException(Utiilty.USER_ID_NOT_FOUND);
    }


    public List<User> searchUserAadharInfo(Long id, String firstName, String lastName, String dateOfBirth) {
        log.info("searchUserAadharInfo() starts");
        List<User> list = new ArrayList<>();
        try {
            list = userAadharRepository.findAllByIdOrFirstNameIgnoreCaseOrLastNameIgnoreCaseOrDateOfBirth(id, firstName, lastName, dateOfBirth);
        } catch (Exception e) {
            log.error("Error in searchUserAadharInfo {}", e.getMessage());
        }
        return list;
    }
}

