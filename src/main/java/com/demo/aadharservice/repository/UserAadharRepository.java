package com.demo.aadharservice.repository;

import com.demo.aadharservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;



public interface UserAadharRepository extends JpaRepository<User, Long> {

    List<User> findAllByIdOrFirstNameIgnoreCaseOrLastNameIgnoreCaseOrDateOfBirth(Long id, String firstName , String lastName , String DateOfBirth);

    boolean existsByFirstNameIgnoreCaseAndLastNameIgnoreCaseAndDateOfBirthAndContactNumber(String firstName , String lastName , String DateOfBirth,String contactNumber);

    @Override
    List<User> findAllById(Iterable<Long> iterable);

    boolean existsById(Long id);

}
