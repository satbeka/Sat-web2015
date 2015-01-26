package com.epam.action;

public class AbstractAction {

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
