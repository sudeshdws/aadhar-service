package com.demo.aadharservice.api;

import com.demo.aadharservice.model.UserAadhar;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RequestMapping("/v1/aadhar-service")
@Api(value = "aadhar-services", tags = "aadhar-services", description = "This service maintain's the aadhar record ")
public interface UserAadharControllerAPI {

    @ApiOperation(value = "User aadhar enrollment ", nickname = "Enroll aadhar information",
            notes = "Generate the aadhar ID", response = UserAadhar.class,tags = {"Initiate",})
    @ApiResponses(value = {
    @ApiResponse(code = 201, message = "Successful Enrollment of Aadhar", response = UserAadhar.class)})
    @RequestMapping(value = "/aadhar-enrollment/initiation",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<UserAadhar> enrollUserInfoToAdhar(
            @ApiParam(value = "User Aaadhar Information Request Payload", required = true)
            @Valid @RequestBody UserAadhar body);



    @ApiOperation(value = "User update aadhar information", nickname = "Update aadhar information",
            notes = " Update Aadhar information", response = UserAadhar.class,tags = {"Update",})
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "User aadhar information is successfully updated", response = UserAadhar.class)})
    @RequestMapping(value = "/aadhar-update",
                produces = {"application/json"},
                method = RequestMethod.PUT)
    ResponseEntity<UserAadhar> updateUserInfoToAdhar(
                @ApiParam(value = "User Aaadhar Information Request Payload", required = true)
                @Valid @RequestBody UserAadhar body);


    @ApiOperation(value = "Retrieve  user aadhar information", nickname = "Get aadhar information",
            notes = " Retrieve aadhar information", response = UserAadhar.class,tags = {"Search",})
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "User aadhar information is successfully retrieved", response = UserAadhar.class)})
    @RequestMapping(value = "/aadhar-retrieve/{aadhar-id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<Optional<UserAadhar>> getUserAadharInfo(
            @ApiParam(value = "user aadhar-id", required = true)
            @Valid @PathVariable("aadhar-id") Long id);


    @ApiOperation(value = "Delete  user aadhar information", nickname = "Delete aadhar information",
            notes = " Delete aadhar information of user", response = UserAadhar.class,tags = {"Delete",})
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "User aadhar information is successfully deleted", response = UserAadhar.class)})
    @RequestMapping(value = "/aadhar-delete/{aadhar-id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    ResponseEntity<?>  deleteUserAadharInfo(
            @ApiParam(value = " user aadhar-id", required = true)
            @Valid @PathVariable("aadhar-id") Long id);



    @ApiOperation(value = "Search user aadhar information", nickname = "Search aadhar information",
            notes = " Search Aadhar information", response = UserAadhar.class,tags = {"Search",})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retrieve user information", response = UserAadhar.class)})
    @RequestMapping(value = "/aadhar-filter",
            produces = {"application/json"},
            method = RequestMethod.GET)
    ResponseEntity<List<UserAadhar>>  searchUserAadharInfo(
            @ApiParam(value = "Query for search user aadhar records")
            @RequestParam( required = false) Long id,  //.value = "aadhar-id",
            @ApiParam(value = "Query for search user aadhar records")
            @RequestParam(value = "first-name", required = false) String firstName,
            @ApiParam(value = "Query for search user aadhar records")
            @RequestParam(value = "last-name", required = false) String lastName,
            @ApiParam(value = "Query for search user aadhar records")
            @RequestParam(value = "date-of-birth", required = false) String dateOfBirth);

}