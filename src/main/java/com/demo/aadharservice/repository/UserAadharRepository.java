package com.demo.aadharservice.repository;

import com.demo.aadharservice.model.UserAadhar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface UserAadharRepository extends JpaRepository<UserAadhar, Long> {

   // List<UserAadhar>  findAllByIdOrFirstNameOrLastNameOrDateOfBirthInIgnoreCase(Long id, String firstName ,String lastName ,String DateOfBirth);
   List<UserAadhar>  findAllByIdOrFirstNameIgnoreCaseOrLastNameIgnoreCaseOrDateOfBirth(Long id, String firstName ,String lastName ,String DateOfBirth);

    @Override
    List<UserAadhar> findAllById(Iterable<Long> iterable);

//    @Override
//    Optional<UserAadhar> findById(Long aLong);
}
