package com.epam.action;

import com.epam.dao.factory.DAOFactory;
import com.epam.dao.order.H2OrderDAO;
import com.epam.dao.order.OrderDAO;
import com.epam.db.ConnectionPool;
import com.epam.model.Client;
import com.epam.model.Order;
import com.epam.service.OrderService;
import com.epam.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SaveBlankOrder extends AbstractAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(SaveBlankOrder.class);
    private com.epam.config.Action action;

    public SaveBlankOrder() {
    }

    public SaveBlankOrder(com.epam.config.Action action) {
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
        String number = req.getParameter("NUMBER");
        Long clientId = Long.parseLong(req.getSession().getAttribute("clientId").toString());
        Long numberId = OrderService.getMaxIdOrderByClient(clientId);
        numberId++;
        if (number.isEmpty()) {
            number = numberId.toString();
        }
        //String price=req.getParameter("price");
        View view = new View(this.getAction().getView());
        if (!Validator.isLoginCorrect(number)) {
            view.setName("errors/client");
            req.getSession().setAttribute("numbernotcorrect", " NUMBER not correct!");
            return view;
        }

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        OrderDAO orderDAO = DAOFactory.getDAOFactory(DAOFactory.DAOType.H2).getOrderDAO();
        H2OrderDAO h2OrderDAO = (H2OrderDAO) orderDAO;
        h2OrderDAO.setConnection(connectionPool);
        Order order = new Order();
        //order.setSumPaid(BigDecimal.valueOf(0));
        //order.setSum(BigDecimal.valueOf(0));
        order.setNumber(number);
        Client client = new Client();
        client.setId(clientId);
        order.setClient(client);

        long id = h2OrderDAO.insertBlankOrder(order);
        if (id == -1) {
            //req.setAttribute();
            view.setName("errors/client");
            return view;
        }
        view.setRedirect(true);
        log.debug("saveorder view.getName()=" + view.getName());
        return view;
    }


}
