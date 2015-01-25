package com.epam.action;


import com.epam.config.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetLogin extends AbstractCommand implements ActionCommand {
    private static final Logger log = LoggerFactory.getLogger(GetLogin.class);
    private Action action;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public GetLogin(Action action) {
        this.action = action;

    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        View view = new View(this.getAction().getView());
        log.debug("GetLoginAction=" + this.getAction().getView());
        return view;
    }
}
