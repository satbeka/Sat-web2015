package com.epam.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ErrorHandler extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher =req.getRequestDispatcher("/WEB-INF/view/errors/runtime.jsp");
        System.out.println("Err Post  req = [" + req + "], resp = [" + resp + "]");
        if (dispatcher != null)
            dispatcher.forward(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher =req.getRequestDispatcher("/WEB-INF/view/errors/runtime.jsp");
        System.out.println("Err Get req = [" + req + "], resp = [" + resp + "]");
        if (dispatcher != null)
            dispatcher.forward(req, resp);
    }
}
