package com.demo.aadharservice.api;

import com.demo.aadharservice.model.User;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RequestMapping("api/v1/aadhar-service/aadhar-cards")
@Api(value = "aadhar-services", tags = "aadhar-services", description = "This service maintain's the aadhar record ")
public interface UserAadharControllerAPI {

    @ApiOperation(value = "User aadhar enrollment ", nickname = "Enroll aadhar information",
            notes = "Generate the aadhar ID", response = User.class,tags = {"Initiate",})
    @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Successful Enrollment of Aadhar", response = User.class)})
    @PostMapping()
    ResponseEntity<User> enrollUserInfoToAdhar(
            @ApiParam(value = "User Aaadhar Information Request Payload", required = true)
            @Valid @RequestBody User user);



    @ApiOperation(value = "Update user aadhar information", nickname = "Update aadhar information",
            notes = " Update Aadhar information", response = User.class,tags = {"Update",})
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "User aadhar information is successfully updated", response = User.class)})
    @PutMapping("/{aadhar-id}")
    ResponseEntity<User> updateUserInfoToAdhar(
                @ApiParam(value = "User Aaadhar Information Request Payload", required = true)
                @Valid @RequestBody User user,
                @Valid @PathVariable("aadhar-id") Long id);


    @ApiOperation(value = "Retrieve  user aadhar information", nickname = "Get aadhar information",
            notes = " Retrieve aadhar information", response = User.class,tags = {"Search",})
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "User aadhar information is successfully retrieved", response = User.class)})
    @GetMapping("/{aadhar-id}")
    ResponseEntity<Optional<User>> getUserAadharInfo(
            @ApiParam(value = "user aadhar-id", required = true)
            @Valid @PathVariable("aadhar-id") Long id);


    @ApiOperation(value = "Delete  user aadhar information", nickname = "Delete aadhar information",
            notes = " Delete aadhar information of user", response = User.class,tags = {"Delete",})
    @ApiResponses(value = {
    @ApiResponse(code = 204, message = "User aadhar information is successfully deleted", response = User.class)})
    @DeleteMapping("/{aadhar-id}")
    ResponseEntity  deleteUserAadharInfo(
            @ApiParam(value = " user aadhar-id", required = true)
            @Valid @PathVariable("aadhar-id") Long id);



    @ApiOperation(value = "Search user aadhar information", nickname = "Search aadhar information",
            notes = " Search Aadhar information", response = User.class,tags = {"Search",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve user aadhar information", response = User.class)})
    @GetMapping("/aadhar-filters")
    ResponseEntity<List<User>>  searchUserAadharInfo(
            @ApiParam(value = "Query for search user aadhar records")
            @RequestParam( required = false) Long id,
            @ApiParam(value = "Query for search user aadhar records")
            @RequestParam(value = "first-name", required = false) String firstName,
            @ApiParam(value = "Query for search user aadhar records")
            @RequestParam(value = "last-name", required = false) String lastName,
            @ApiParam(value = "Query for search user aadhar records")
            @RequestParam(value = "date-of-birth", required = false) String dateOfBirth);

}