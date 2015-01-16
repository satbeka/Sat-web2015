package com.epam.action;

import com.epam.config.Action;
import com.epam.dao.client.ClientDAO;
import com.epam.dao.client.H2ClientDAO;
import com.epam.dao.factory.DAOFactory;
import com.epam.dao.product.H2ProductDAO;
import com.epam.dao.product.ProductDAO;
import com.epam.db.ConnectionPool;
import com.epam.model.Client;
import com.epam.model.Product;
import com.epam.service.ClientService;
import com.epam.validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;


public class SaveClientList extends AbstractCommand implements ActionCommand {
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private Action action;

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String[] listMarkId = req.getParameterValues("MarkId");
        View view = new View(this.getAction().getView());
        if (!ClientService.unMarkAllClientsbyAdministrator()){
            view.setName("errors/administrator");
            req.getSession().setAttribute("markidnotcorrect", " UnMark All Clients is not possible!=");
            return view;
        };
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
                req.getSession().setAttribute("markidnotcorrect", " MarkId not correct!="+id);
                return view;
            }
            ;
            h2ClientDAO.closeConnection(connectionPool);
        }
        view.setRedirect(true);
        System.out.println("markClientbyAdministrator view.getName()=" + view.getName());
        return view;
    }

    ;


    public SaveClientList() {
    }

    ;

    public SaveClientList(Action action) {
        this.action = action;
    }

    ;


}
