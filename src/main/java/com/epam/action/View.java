package com.epam.action;


public class View {
    private String name;
    private boolean isRedirect;

    public View(String nameJsp) {
        this.name = nameJsp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean isRedirect) {
        this.isRedirect = isRedirect;
    }
}
