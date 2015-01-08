package com.epam.action;


public class View {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public View(String nameJsp) {
        this.name = nameJsp;
    }
}
