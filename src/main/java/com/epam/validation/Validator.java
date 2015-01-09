package com.epam.validation;


//import java.sql.Date;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public boolean isDate(Date dt) {
        return true;
    };

    public boolean isChar(Character cr) {
        return true;
    };
    public boolean isNumber(Number number) {
        return true;
    };
    public boolean isLoginCorrect(String login){
        String regex="[a-zA-Z_0-9]+";
        Pattern p=Pattern.compile(regex);
        System.out.println("login="+login);
        Matcher matcher = p.matcher(login);
        if (matcher.matches()){
            return true;
        }
        else
            return false;

    }


}
