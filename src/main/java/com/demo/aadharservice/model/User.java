package com.demo.aadharservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Setter
@Getter
@Entity
public class User {

    public User() {
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
    @NotEmpty(message = "Date of birth must not be empty")
    private String dateOfBirth;

    @Size(min=1,max=11)
    @NotEmpty(message = "Contact number must not be empty")
    @JsonProperty("ContactNumber")
    private String contactNumber;

    @Size(min=1,max=200)
    @NotEmpty(message = "Address must not be empty")
    @JsonProperty("Address")
    private String address;

    @Size(min=1,max=50)
    @NotEmpty(message = "City must not be empty")
    @JsonProperty("City")
    private String city;




}
