package com.epam.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction extends AbstractCommand implements ActionCommand{

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {


        req.getSession().invalidate();
        return null;
    }
}
