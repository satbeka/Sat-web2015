package com.epam.validation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    private static final Logger log = LoggerFactory.getLogger(Validator.class);

    public static boolean isChar(Character cr) {
        return true;
    }

    public static boolean isNumber(Number number) {
        return true;
    }

    public static boolean isLoginCorrect(String login) {
        String regex = "[a-zA-Z_0-9]+";
        Pattern p = Pattern.compile(regex);
        log.debug("login=" + login);
        Matcher matcher = p.matcher(login);
        return matcher.matches();

    }

    public static boolean isPriceCorrect(String price) {
        String regex = "[0-9.]+";
        Pattern p = Pattern.compile(regex);
        log.debug("price=" + price);
        Matcher matcher = p.matcher(price);
        return matcher.matches();

    }

    public static boolean isDate(Date dt) {
        return true;
    }
}
