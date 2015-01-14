package com.epam.action;

import com.epam.config.Action;
import com.epam.dao.user.H2UserDAO;
import com.epam.dao.user.UserDAO;
import com.epam.db.ConnectionPool;
import com.epam.dao.administrator.AdministratorDAO;
import com.epam.dao.administrator.H2AdministratorDAO;
import com.epam.dao.factory.DAOFactory;
import com.epam.model.*;
import com.epam.validation.Validator;

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
        req.getSession().setAttribute("user33", "");
        View view = new View(this.getAction().getView());
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        req.getSession().setAttribute("loginnotcorrect", null);
        req.getSession().setAttribute("passwordnotcorrect", null);
        if (!Validator.isLoginCorrect(login)){
            view.setName("errors/login");
            req.getSession().setAttribute("loginnotcorrect", " LOGIN not correct!");
            return view;
        };
        if (!Validator.isLoginCorrect(password)){
            view.setName("errors/login");
            req.getSession().setAttribute("passwordnotcorrect", " PASSWORD not correct!");
            return view;
        };

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        UserDAO userDAO = DAOFactory.getDAOFactory(DAOFactory.DAOType.H2).getUserDAO();
        H2UserDAO h2UserDAO = (H2UserDAO) userDAO;
        h2UserDAO.setConnection(connectionPool);
        User user = ((H2UserDAO) userDAO).findUserByLoginByPassword(login, password);
        if (user == null) {
            //req.setAttribute();
            view.setName("errors/login");
            return view;
        }
        ;

        System.out.println("user.getClass().getSimpleName().toString()=" + user.getClass().getSimpleName().toString());
        if (user.getClass().getSimpleName().equals("Administrator")) {
            String com = req.getParameter("command1");
            req.getSession().setAttribute("ROLE", "ADMINISTRATOR");
            req.getSession().setAttribute("user33", "Hello! ADMINISTRATOR");
            view.setRedirect(true);
            view.setName("administratorwhatdo");
            //view.setName("redirect");
            System.out.println("administratorwhatdo view.getName()=" + view.getName());
            return view;
        }

        if (user.getClass().getSimpleName().equals("Client")) {
            req.getSession().setAttribute("ROLE", "CLIENT");
            req.getSession().setAttribute("user33", "Hello! CLIENT");
            view.setRedirect(true);
            System.out.println("client view.getName()="+view.getName());
            return view;
        }
        return view;
    };


    public LoginAction() {};
    public LoginAction(Action action){
        this.action=action;
    };


}
