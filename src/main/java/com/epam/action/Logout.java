package com.epam.action;


import com.epam.config.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends AbstractCommand implements ActionCommand {
    private static final Logger log = LoggerFactory.getLogger(Logout.class);

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        View view = new View(this.getAction().getView());
        log.debug("LogoutAction=" + this.getAction().getView());
        req.getSession().invalidate();
        return view;

    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private Action action;

    public Logout() {
    }

    ;

    public Logout(Action action) {
        this.action = action;
    }

    ;
}
