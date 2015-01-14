package com.epam.action;


import com.epam.config.Action;

public class AbstractCommand {

    public void setSuccessView(String successView) {
        this.successView = successView;
    }

    public String getErrorView() {
        return errorView;
    }

    public void setErrorView(String errorView) {
        this.errorView = errorView;
    }

    private String successView;
    private String errorView;

}
