package com.epam.controller;

import com.epam.action.ActionCommand;
import com.epam.action.CommandFabric;
import com.epam.action.View;
import com.epam.db.ConnectionPool;

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

        System.out.println("init");
        //servletContext.setAttribute("poolInstance",connectionPool);
        //this.login = servletContext;
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession(true);
        System.out.println("front sess=" + session);
        System.out.println("com.epam.service ");
        String meth = req.getMethod();
        String actionName = req.getMethod() + req.getPathInfo();
        System.out.println("actionName=" + actionName);

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        System.out.println("FrontServlet  connectionPool = [" + connectionPool + "]");

        //User user=new Administrator();
        if (session.getAttribute("ROLE") != "ADMINISTRATOR") {
            System.out.println("front role= "+session.getAttribute("ROLE"));
            //session.setAttribute("ROLE", "ADMINISTRATOR");
        }
        ;
        String role = (String) session.getAttribute("ROLE");
        System.out.println("front role=" + role);
        //System.out.println("front Attr role="+req.getAttributeNames().hashCode());

        if (req.getMethod() == "GET") {
            doGet(req, resp);
            return;
        }
        ;

        doPost(req, resp);
        return;

        /*
        String actionName = "99999 "+req.getContextPath()+" "+req.getMethod() +" "+ req.getPathInfo();
        System.out.println(actionName);
        */
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        //int i;
        String method = req.getMethod();
        String path = req.getPathInfo();
        //System.out.println("doGet 5577778887775999111");
        Object object=req.getServletContext().getAttribute("CommandFabric");
        System.out.println("doGet CommandFabric="+object);

        CommandFabric commandFabric = (CommandFabric) req.getServletContext().getAttribute("CommandFabric");
        //System.out.println("doGet CommandFabric="+commandFabric);
        ActionCommand actionCommand = commandFabric.defineCommand(method, path);
        View view = actionCommand.execute(req, resp);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/"+view.getName().toString() + ".jsp");
        if (dispatcher != null){
            dispatcher.forward(req, resp);}


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

        String location = req.getContextPath();
        //System.out.println("context path " + location);

        String method = req.getMethod();
        String path = req.getPathInfo();
        System.out.println("doPost "+req.getContextPath());
        Object object=req.getServletContext().getAttribute("CommandFabric");
        System.out.println("doPost CommandFabric="+object);

        CommandFabric commandFabric = (CommandFabric) req.getServletContext().getAttribute("CommandFabric");
        //System.out.println("doGet CommandFabric="+commandFabric);
        ActionCommand actionCommand = commandFabric.defineCommand(method, path);
        View view = actionCommand.execute(req, resp);
        if (view.getName().equals("redirect")){
            resp.sendRedirect(location);
        }
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/"+view.getName().toString() + ".jsp");
            if (dispatcher != null) {
                dispatcher.forward(req, resp);
            }
        }
        /*
        RequestDispatcher dispatcher =req.getRequestDispatcher("/myweb/index.jsp");
        if (dispatcher!=null){
        System.out.println("post com.epam.service ");
        dispatcher.forward(req, resp);}
        */

    }
}

