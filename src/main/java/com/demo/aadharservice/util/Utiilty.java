package com.demo.aadharservice.util;

import com.demo.aadharservice.exception.*;
import com.demo.aadharservice.model.User;
import lombok.experimental.UtilityClass;
import org.apache.commons.validator.GenericValidator;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Utiilty {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_IS_NOT_VALID = "Please provide valid date : yyyy-MM-dd";
    public static final String USER_ID_NOT_FOUND = "User ID not found";
    private static final String SPECIAL_CHAR_NOT_ALLOWED = "Special character is not allowed";
    private static final String CONTACT_NUMBER_IS_NOT_VALID = "Contact number is not valid";

    private Pattern pattern = Pattern.compile("[^A-Za-z]");

    public void validateRequest(User userAadhar) {

        Matcher matcher;
        matcher = pattern.matcher(userAadhar.getFirstName());
        if (matcher.find())
            throw new CustomValidationException(SPECIAL_CHAR_NOT_ALLOWED);

        matcher = pattern.matcher(userAadhar.getLastName());
        if (matcher.find())
            throw new CustomValidationException(SPECIAL_CHAR_NOT_ALLOWED);

        matcher = pattern.matcher(userAadhar.getCity());
        if (matcher.find())
            throw new CityValidationException(SPECIAL_CHAR_NOT_ALLOWED);

        isValidDate(userAadhar.getDateOfBirth());
        isValidContactNumber(userAadhar.getContactNumber());
    }

    private static void isValidContactNumber(String contactNumber) {
        if (!GenericValidator.isInt(contactNumber)) {
            throw new ContactValidationException(CONTACT_NUMBER_IS_NOT_VALID);
        }
    }

    private static void isValidDate(String date) {
        Predicate<String> check = i ->
                (GenericValidator.isDate(i, DATE_FORMAT, true));
        if (!check.test(date))
            throw new DateParseException(DATE_IS_NOT_VALID);
    }

}
