package com.demo.aadharservice.util;

import com.demo.aadharservice.exception.CustomValidationException;
import com.demo.aadharservice.exception.DateParseException;
import com.demo.aadharservice.model.UserAadhar;
import com.sun.javafx.scene.control.behavior.OptionalBoolean;
import lombok.experimental.UtilityClass;
import org.apache.commons.validator.GenericValidator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Utiilty {
//    private static final String ISO_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String ISO_DATETIME_FORMAT = "yyyy-MM-dd";
    private static final String DATE_IS_NOT_VALID = "Please provide valid date : yyyy-MM-dd";
    public static final String USER_ALREADY_REGISTERED = "User is already registered";
    public static final String USER_ID_NOT_FOUND = "User ID not found";
    public static final String SPECIAL_CHAR_NOT_ALLOWED = "Special character is not allowed";


    private Pattern pattern = Pattern.compile("[^A-Za-z0-9]");

    public void validateRequest(UserAadhar userAadhar) {

       Matcher matcher;
        matcher = pattern.matcher(userAadhar.getFirstName());
        if (matcher.find())
            throw new CustomValidationException(SPECIAL_CHAR_NOT_ALLOWED);

        matcher = pattern.matcher(userAadhar.getLastName());
        if (matcher.find())
            throw new CustomValidationException(SPECIAL_CHAR_NOT_ALLOWED);

        isValidDate(userAadhar.getDateOfBirth());


    }

    private static void isValidDate(String date) {
        Predicate<String> check = i ->
                (GenericValidator.isDate(i, ISO_DATETIME_FORMAT, true));

        if (check.test(date))
        {} else throw new DateParseException(DATE_IS_NOT_VALID);
    }


}
