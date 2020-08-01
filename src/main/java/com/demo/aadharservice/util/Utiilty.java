package com.demo.aadharservice.util;

import com.demo.aadharservice.exception.*;
import lombok.experimental.UtilityClass;
import org.apache.commons.validator.GenericValidator;
import java.util.function.Predicate;


@UtilityClass
public class Utiilty {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATE_IS_NOT_VALID = "Please provide valid date : yyyy-MM-dd";
    public static final String USER_ID_NOT_FOUND = "User ID not found";
    public static final String USER_IS_ALREADY_REGISTERED = "User is already registered";


    public static void isValidDate(String date) {
        Predicate<String> check = i ->
                (GenericValidator.isDate(i, DATE_FORMAT, true));
        if (!check.test(date))
            throw new DateParseException(DATE_IS_NOT_VALID);
    }

}
