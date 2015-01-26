package com.epam.listener;


import com.epam.action.ActionFactory;
import com.epam.config.Eshop;
import com.epam.config.parser.Parser;
import com.epam.db.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

public class ContextListener implements ServletContextListener {
    private static final Logger log = LoggerFactory.getLogger(ContextListener.class);
    //private ServletContext poolInstance;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        log.debug("ContextListener connectionPool = [" + connectionPool + "]");
        //context.setAttribute("poolInstance",instance);
        //context.setAttribute("ROLE","null");

        log.debug("ServletContext init888=");
        Parser parser = new Parser();
        Eshop eshop = parser.parser();
        context.setAttribute("ESHOP", eshop);

        ActionFactory actionFactory = new ActionFactory(eshop);
        //actionFactory.LoadEshopConfig(eshop);
        context.setAttribute("CommandFabric", actionFactory);
        log.debug("context commandFabric=" + actionFactory);


    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext contextD = sce.getServletContext();
        ConnectionPool connectionPool = ConnectionPool.getInstance();//(ConnectionPool) contextD.getAttribute("poolInstance");
        try {
            connectionPool.dispose();
            log.debug("Cont List  end connectionPool = [" + connectionPool + "]");
        } catch (SQLException e) {
            log.debug(e.getMessage());

        }
    }
}
