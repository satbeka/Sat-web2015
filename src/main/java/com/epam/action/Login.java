package com.epam.action;

import com.epam.dao.factory.DAOFactory;
import com.epam.dao.user.H2UserDAO;
import com.epam.dao.user.UserDAO;
import com.epam.db.ConnectionPool;
import com.epam.model.User;
import com.epam.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login extends AbstractAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(Login.class);
    private com.epam.config.Action action;

    public Login() {
    }

    public Login(com.epam.config.Action action) {
        this.action = action;
    }

    public com.epam.config.Action getAction() {
        return action;
    }

    public void setAction(com.epam.config.Action action) {
        this.action = action;
    }

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user33", "");
        View view = new View(this.getAction().getView());
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        req.getSession().setAttribute("loginnotcorrect", null);
        req.getSession().setAttribute("passwordnotcorrect", null);
        if (!Validator.isLoginCorrect(login)) {
            view.setName("errors/login");
            req.getSession().setAttribute("loginnotcorrect", " LOGIN not correct!");
            return view;
        }
        if (!Validator.isLoginCorrect(password)) {
            view.setName("errors/login");
            req.getSession().setAttribute("passwordnotcorrect", " PASSWORD not correct!");
            return view;
        }

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

        log.debug("user.getClass().getSimpleName().toString()=" + user.getClass().getSimpleName().toString());
        if (user.getClass().getSimpleName().equals("Administrator")) {
            String com = req.getParameter("command1");
            req.getSession().setAttribute("ROLE", "ADMINISTRATOR");
            req.getSession().setAttribute("user33", "Hello! ADMINISTRATOR");
            view.setRedirect(true);
            view.setName("administratorwhatdo");
            //view.setName("redirect");
            log.debug("administratorwhatdo view.getName()=" + view.getName());
            return view;
        }

        if (user.getClass().getSimpleName().equals("Client")) {
            req.getSession().setAttribute("ROLE", "CLIENT");
            req.getSession().setAttribute("user33", "Hello! CLIENT");
            req.getSession().setAttribute("clientId", user.getId());
            view.setRedirect(true);
            view.setName("clientwhatdo");
            log.debug("client view.getName()=" + view.getName());
            log.debug("client clientId=" + user.getId());
            req.getSession().setAttribute("clientId", user.getId());
            return view;
        }
        return view;
    }


}
