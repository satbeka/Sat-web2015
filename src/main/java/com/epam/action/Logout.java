package com.epam.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends AbstractAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(Logout.class);
    private com.epam.config.Action action;

    public Logout() {
    }

    public Logout(com.epam.config.Action action) {
        this.action = action;
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        View view = new View(this.getAction().getView());
        log.debug("LogoutAction=" + this.getAction().getView());
        req.getSession().invalidate();
        return view;

    }

    public com.epam.config.Action getAction() {
        return action;
    }

    public void setAction(com.epam.config.Action action) {
        this.action = action;
    }

}
