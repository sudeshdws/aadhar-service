package com.demo.aadharservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data

@Entity
public class UserAadhar {

    public UserAadhar() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min=1,max=50)
    @NotEmpty(message = "First name must not be empty")
    @JsonProperty("FirstName")
    private String firstName;

    @Size(min=1,max=50)
    @NotEmpty(message = "Last name must not be empty")
    @JsonProperty("LastName")
    private String lastName;


    @JsonProperty("DateOfBirth")
    private String dateOfBirth;

    @Size(min=1,max=10)
    @NotEmpty(message = "Contact number must not be empty")
    @JsonProperty("ContactNumber")
    private String contactNumber;






}
