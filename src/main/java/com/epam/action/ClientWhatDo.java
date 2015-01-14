package com.epam.action;

import com.epam.config.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientWhatDo extends AbstractCommand implements ActionCommand {
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        View view = new View(this.getAction().getView());
        System.out.println("ClientWhatDo="+this.getAction().getView());
        return view;
    }
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private Action action;

    public ClientWhatDo(){};
    public ClientWhatDo(Action action){this.action=action;};
}
