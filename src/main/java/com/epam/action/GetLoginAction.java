package com.epam.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetLoginAction extends AbstractCommand implements ActionCommand{

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        View view = new View("login");
        return view;
    }
}
