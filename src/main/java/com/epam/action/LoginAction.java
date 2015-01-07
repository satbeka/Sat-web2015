package com.epam.action;

import com.epam.config.Action;
import com.epam.db.ConnectionPool;
import com.epam.dao.administrator.AdministratorDAO;
import com.epam.dao.administrator.H2AdministratorDAO;
import com.epam.dao.factory.DAOFactory;
import com.epam.model.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginAction extends AbstractCommand implements ActionCommand{
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private Action action;
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        View view=null;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        AdministratorDAO administratorDAO = DAOFactory.getDAOFactory(DAOFactory.DAOType.H2).getAdministratorDAO();
        H2AdministratorDAO h2AdministratorDAO=(H2AdministratorDAO)administratorDAO;
        //ConnectionPool connectionPool=(ConnectionPool)req.getServletContext().getAttribute("poolInstance");
        ConnectionPool connectionPool=ConnectionPool.getInstance();
        h2AdministratorDAO.setConnection(connectionPool);
        Administrator administrator = administratorDAO.findAdministratorByLogin(login);
        h2AdministratorDAO.closeConnection(connectionPool);
        if (administrator == null) {

            return view;
        }
        ;
        if (administrator != null) {
            if (administrator.getPassword() != password) {
                return view;
            }
            ;

            //ClientDAO clientDAO=DAOFactory.getDAOFactory(DAOFactory.H2).getClientDAO();


            return view;
        }
        return view;
    }

    public LoginAction() {};
    public LoginAction(Action action){
        this.action=action;
    };


}
