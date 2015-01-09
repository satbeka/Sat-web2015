package com.epam.listener;


import com.epam.action.CommandFabric;
import com.epam.config.Eshop;
import com.epam.config.parser.Parser;
import com.epam.db.ConnectionPool;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

public class ContextListener implements ServletContextListener{
    //private ServletContext poolInstance;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context=sce.getServletContext();
        ConnectionPool connectionPool= ConnectionPool.getInstance();
        System.out.println("ContextListener connectionPool = [" + connectionPool + "]");
        //context.setAttribute("poolInstance",instance);
        //context.setAttribute("ROLE","null");

        System.out.println("ServletContext init888=");
        Parser parser = new Parser();
        Eshop eshop = parser.parser();
        context.setAttribute("ESHOP", eshop);

        CommandFabric commandFabric = new CommandFabric();
        commandFabric.LoadEshopConfig(eshop);
        context.setAttribute("CommandFabric", commandFabric);
        System.out.println("context commandFabric="+commandFabric);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext contextD=sce.getServletContext();
        ConnectionPool connectionPool= ConnectionPool.getInstance();//(ConnectionPool) contextD.getAttribute("poolInstance");
        try {
            connectionPool.dispose();
            System.out.println("Cont List  end connectionPool = [" + connectionPool + "]");
        } catch (SQLException e) {
            /*TODO log timeout session or logout from application!*/
        }
    }
}
