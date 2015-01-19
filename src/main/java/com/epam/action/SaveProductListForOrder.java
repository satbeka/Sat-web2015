package com.epam.action;

import com.epam.config.Action;
import com.epam.dao.client.ClientDAO;
import com.epam.dao.client.H2ClientDAO;
import com.epam.dao.factory.DAOFactory;
import com.epam.db.ConnectionPool;
import com.epam.model.Client;
import com.epam.service.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SaveProductListForOrder extends AbstractCommand implements ActionCommand {
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private Action action;

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        Long orderId= Long.parseLong(req.getSession().getAttribute("orderId").toString());
        Long clientId= Long.parseLong(req.getSession().getAttribute("clientId").toString());

        //req.getSession().setAttribute("orderId",orderId);
        String[] productList = req.getParameterValues("quantity");
        String[] productList2 =req.getParameterValues("productId");
                //Long.parseLong(req.getSession().getAttribute("productId").toString());
        View view = new View(this.getAction().getView());
        if (!ClientService.unMarkAllClientsbyAdministrator()){
            view.setName("errors/client");
            req.getSession().setAttribute("markidnotcorrect", " UnMark All Clients is not possible!=");
            return view;
        };
        for (String productId : productList) {
            System.out.println(" productId ID= " + productId);
            Long id = Long.parseLong(productId);
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
        System.out.println(" SaveProductListForOrder view.getName()=" + view.getName());
        return view;
    }

    ;


    public SaveProductListForOrder() {
    }

    ;

    public SaveProductListForOrder(Action action) {
        this.action = action;
    }

    ;


}
