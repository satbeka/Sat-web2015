package com.epam.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface ActionCommand {
    public View execute(HttpServletRequest req, HttpServletResponse resp);
}
