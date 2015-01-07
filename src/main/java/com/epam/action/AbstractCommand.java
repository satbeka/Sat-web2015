package com.epam.action;


import com.epam.config.Action;

public class AbstractCommand {
    private Action action;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
