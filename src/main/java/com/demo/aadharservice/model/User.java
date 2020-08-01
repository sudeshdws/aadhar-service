package com.demo.aadharservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Setter
@Getter
@Entity
@NoArgsConstructor
public class User {

    @Id
    private Long id;

    @Size(min=1,max=50)
    @NotEmpty(message = "{validate.first.name}")
    @Pattern(regexp = "[A-Za-z]*", message = "{validate.special.character}")
    @JsonProperty("FirstName")
    private String firstName;

    @Size(min=1,max=50)
    @NotEmpty(message = "{validate.last.name}")
    @Pattern(regexp = "[A-Za-z]*", message = "{validate.special.character}")
    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("DateOfBirth")
    @NotEmpty(message = "{validate.dob}")
    private String dateOfBirth;


    @Size(min=0,max=10)
    @NotEmpty(message = "{validate.contact.number}")
    @JsonProperty("ContactNumber")
    @Pattern(regexp = "[0-9]*", message = "{validate.number}")
    private String contactNumber;

    @Size(min=1,max=200)
    @NotEmpty(message = "{validate.address}")
    @JsonProperty("Address")
    private String address;

    @Size(min=1,max=50)
    @NotEmpty(message = "{validate.city}")
    @Pattern(regexp = "[A-Za-z]*", message = "{validate.special.character}")
    @JsonProperty("City")
    private String city;

}
