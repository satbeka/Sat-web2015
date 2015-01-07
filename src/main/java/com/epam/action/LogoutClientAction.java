package com.epam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 1 on 25.12.2014.
 */
public class LogoutClientAction implements ActionCommand {
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
}
