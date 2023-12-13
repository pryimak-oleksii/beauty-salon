package com.example.beautysaloneeservlets.security;

//import org.apache.commons.codec.binary.Hex;
//import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Helper class for hashing password.
 * Also provides basic validation.
 */
public class Security {

    /**
     * @param email
     * @return true if email is valid
     */
    public static boolean isEmailValid(String email) {
        final String regex = "^[a-zA-Z\\d_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z\\d.-]+$";
        Pattern pattern = java.util.regex.Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    /**
     * @param password
     * @return true if password is valid
     */
    public static boolean isPasswordValid(final String password) {
        final String regex = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

}
