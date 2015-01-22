package com.epam.action;

public class PayOrderException extends RuntimeException {
    public PayOrderException(String message) {
        super(message);
    }

    public PayOrderException(Throwable cause) {
        super(cause);
    }

    public PayOrderException() {

    }
}
