package com.epam.action;

import com.epam.config.Action;
import com.epam.dao.user.H2UserDAO;
import com.epam.dao.user.UserDAO;
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
        View view = null;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        //AdministratorDAO administratorDAO = DAOFactory.getDAOFactory(DAOFactory.DAOType.H2).getAdministratorDAO();
        //H2AdministratorDAO h2AdministratorDAO=(H2AdministratorDAO)administratorDAO;
        //ConnectionPool connectionPool=(ConnectionPool)req.getServletContext().getAttribute("poolInstance");
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        //h2AdministratorDAO.setConnection(connectionPool);
        UserDAO userDAO = DAOFactory.getDAOFactory(DAOFactory.DAOType.H2).getUserDAO();
        H2UserDAO h2UserDAO = (H2UserDAO) userDAO;
        h2UserDAO.setConnection(connectionPool);
        User user = ((H2UserDAO) userDAO).findUserByLoginByPassword(login, password);
        if (user == null) {
            //req.setAttribute();
            view.setName("errors/error_login");
            return view;
        }
        ;

        if (user.getClass().toString() == "Administrator") {
            req.getSession().setAttribute("ROLE", "ADMINISTRATOR");
            view.setName("adminmenu");
            return view;
        }

        if (user.getClass().toString() == "Client") {
            req.getSession().setAttribute("ROLE", "CLIENT");
            view.setName("clientmenu");
            return view;
        }
        return view;
    };


    public LoginAction() {};
    public LoginAction(Action action){
        this.action=action;
    };


}
