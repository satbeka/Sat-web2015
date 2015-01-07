package com.epam.action;


public class View {
    public String getName() {
        return name;
    }

    private final String name;

    public View(String jsp) {
        this.name = jsp;
    }
}
