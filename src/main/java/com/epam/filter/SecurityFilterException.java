package com.epam.filter;


public class SecurityFilterException extends RuntimeException {
    public SecurityFilterException(String message) {
        super(message);
    }

    public SecurityFilterException(Throwable cause) {
        super(cause);
    }


}
