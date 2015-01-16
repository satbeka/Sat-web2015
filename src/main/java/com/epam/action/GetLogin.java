package com.epam.action;


import com.epam.config.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetLogin extends AbstractCommand implements ActionCommand{

    private Action action;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public GetLogin(Action action) {
      this.action=action;

    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        View view = new View(this.getAction().getView());
        System.out.println("GetLoginAction="+this.getAction().getView());
        return view;
    }
}
