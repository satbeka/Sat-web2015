package com.epam.controller;

import com.epam.action.ActionCommand;
import com.epam.action.CommandFactory;
import com.epam.action.View;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FrontServlet extends HttpServlet {

    private ServletContext login;

    //@Override
    public void init(ServletContext servletContext) throws ServletException {

    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);

        //System.out.println("front sess=" + session);
        //System.out.println("service req.getContextPath()="+req.getContextPath());
        String meth = req.getMethod();
        String actionName = req.getMethod() + req.getPathInfo();
        System.out.println("actionName=" + actionName);

        //ConnectionPool connectionPool = ConnectionPool.getInstance();
        //System.out.println("FrontServlet  connectionPool = [" + connectionPool + "]");

        //User user=new Administrator();
        if (session.getAttribute("ROLE") != "ADMINISTRATOR") {
            System.out.println("front role= "+session.getAttribute("ROLE"));
            //session.setAttribute("ROLE", "ADMINISTRATOR");
        }
        ;
        String role = (String) session.getAttribute("ROLE");
        System.out.println("front role=" + role);
        String method = req.getMethod();
        String path = req.getPathInfo();

        //System.out.println("doGet 5577778887775999111");
        Object object=req.getServletContext().getAttribute("CommandFabric");
        //System.out.println("doGet CommandFabric="+object);

        CommandFactory commandFactory = (CommandFactory) req.getServletContext().getAttribute("CommandFabric");
        ActionCommand actionCommand = commandFactory.defineCommand(method, path);
        if (actionCommand == null) {
            System.out.println("Action not found");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found");
            return;
        }

        View view = actionCommand.execute(req, resp);
        System.out.println("   /WEB-INF/view/"+view.getName().toString() + ".jsp");
        doForwardOrRedirect(view, req, resp);
        //return;

    }

    private void doForwardOrRedirect(View view, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (view.isRedirect()){
            //resp.encodeRedirectURL("");
            //req.s
            String location = req.getContextPath() +  view.getName().toString();
            //System.out.println("post send redirect location="+location);
            resp.sendRedirect(location);
            System.out.println("return post send redirect location="+location);
            return;
        }
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/" + view.getName().toString() + ".jsp");
            System.out.println("forward="+view.getName().toString());
            if (dispatcher != null) {
                dispatcher.forward(req, resp);
            }
    }


    }
}

