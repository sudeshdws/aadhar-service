package com.demo.aadharservice.repository;

import com.demo.aadharservice.model.UserAadhar;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface UserAadharRepository extends JpaRepository<UserAadhar, Long> {

   List<UserAadhar>  findAllByIdOrFirstNameIgnoreCaseOrLastNameIgnoreCaseOrDateOfBirth(Long id, String firstName ,String lastName ,String DateOfBirth);

    @Override
    List<UserAadhar> findAllById(Iterable<Long> iterable);

}
