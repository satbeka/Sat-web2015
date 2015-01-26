package com.epam.action;

import com.epam.dao.client.ClientDAO;
import com.epam.dao.client.H2ClientDAO;
import com.epam.dao.factory.DAOFactory;
import com.epam.db.ConnectionPool;
import com.epam.model.Client;
import com.epam.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SaveClientList extends AbstractAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(SaveClientList.class);
    private com.epam.config.Action action;

    public SaveClientList() {
    }

    public SaveClientList(com.epam.config.Action action) {
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
        String[] listMarkId = req.getParameterValues("MarkId");
        View view = new View(this.getAction().getView());
        if (!ClientService.unMarkAllClientsbyAdministrator()) {
            view.setName("errors/administrator");
            req.getSession().setAttribute("markidnotcorrect", " UnMark All Clients is not possible!=");
            return view;
        }
        for (String clientId : listMarkId) {
            System.out.println(" mark ID= " + clientId);
            Long id = Long.parseLong(clientId);
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            ClientDAO clientDAO = DAOFactory.getDAOFactory(DAOFactory.DAOType.H2).getClientDAO();
            H2ClientDAO h2ClientDAO = (H2ClientDAO) clientDAO;
            h2ClientDAO.setConnection(connectionPool);
            Client client = new Client();
            client.setId(id);
            if (!h2ClientDAO.markClientbyAdministrator(client)) {
                h2ClientDAO.closeConnection(connectionPool);
                view.setName("errors/administrator");
                req.getSession().setAttribute("markidnotcorrect", " MarkId not correct!=" + id);
                return view;
            }
            h2ClientDAO.closeConnection(connectionPool);
        }
        view.setRedirect(true);
        log.debug(" markClientbyAdministrator view.getName()=" + view.getName());
        return view;
    }


}
