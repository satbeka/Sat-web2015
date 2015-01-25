package com.epam.controller;

import com.epam.action.ActionCommand;
import com.epam.action.CommandFactory;
import com.epam.action.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class FrontServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(FrontServlet.class);
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
        log.debug("Requested action: " + actionName);
        log.debug("current URI: " + req.getRequestURL());
        log.debug("Referrer: " + req.getHeader("Referrer"));
        //ConnectionPool connectionPool = ConnectionPool.getInstance();
        //System.out.println("FrontServlet  connectionPool = [" + connectionPool + "]");

        //User user=new Administrator();
        if (session.getAttribute("ROLE") != "ADMINISTRATOR") {
            log.debug("front role= " + session.getAttribute("ROLE"));
            //session.setAttribute("ROLE", "ADMINISTRATOR");
        }
        ;
        String role = (String) session.getAttribute("ROLE");
        System.out.println("front role=" + role);
        String method = req.getMethod();
        String path = req.getPathInfo();

        //System.out.println("doGet 5577778887775999111");
        Object object = req.getServletContext().getAttribute("CommandFabric");
        //System.out.println("doGet CommandFabric="+object);

        CommandFactory commandFactory = (CommandFactory) req.getServletContext().getAttribute("CommandFabric");
        ActionCommand actionCommand = commandFactory.defineCommand(method, path);
        if (actionCommand == null) {
            log.debug("Action not found");
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Not found");
            return;
        }

        View view = actionCommand.execute(req, resp);
        log.debug("   /WEB-INF/view/" + view.getName().toString() + ".jsp");
        doForwardOrRedirect(view, req, resp);
        //return;

    }

    private void doForwardOrRedirect(View view, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (view.isRedirect()) {
            //resp.encodeRedirectURL("");
            //req.s
            String location = req.getContextPath() + view.getName().toString();
            //System.out.println("post send redirect location="+location);
            resp.sendRedirect(location);
            log.debug("return post send redirect location=" + location);
            return;
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/view/" + view.getName().toString() + ".jsp");
            log.debug("forward=" + view.getName().toString());
            if (dispatcher != null) {
                dispatcher.forward(req, resp);
            }
        }


    }
}

