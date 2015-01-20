package com.epam.action;

import com.epam.config.Action;
import com.epam.dao.factory.DAOFactory;
import com.epam.dao.order.H2OrderDAO;
import com.epam.dao.order.OrderDAO;
import com.epam.dao.product.H2ProductDAO;
import com.epam.dao.product.ProductDAO;
import com.epam.db.ConnectionPool;
import com.epam.model.Client;
import com.epam.model.Order;
import com.epam.model.Product;
import com.epam.service.OrderService;
import com.epam.validation.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;


public class SaveBlankOrder extends AbstractCommand implements ActionCommand{
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    private Action action;
    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) {
        String number=req.getParameter("NUMBER");
        Long clientId= Long.parseLong(req.getSession().getAttribute("clientId").toString());
        Long numberId=OrderService.getMaxIdOrderByClient(clientId);
        numberId++;
        if (number.isEmpty()){number= numberId.toString();};
        //String price=req.getParameter("price");
        View view = new View(this.getAction().getView());
        if (!Validator.isLoginCorrect(number)){
            view.setName("errors/client");
            req.getSession().setAttribute("numbernotcorrect", " NUMBER not correct!");
            return view;
        };

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        OrderDAO orderDAO=DAOFactory.getDAOFactory(DAOFactory.DAOType.H2).getOrderDAO();
        H2OrderDAO h2OrderDAO=(H2OrderDAO)orderDAO;
        h2OrderDAO.setConnection(connectionPool);
        Order order=new Order();
        //order.setSumPaid(BigDecimal.valueOf(0));
        //order.setSum(BigDecimal.valueOf(0));
        order.setNumber(number);
        Client client=new Client();
        client.setId(clientId);
        order.setClient(client);

        long id=h2OrderDAO.insertBlankOrder(order);
        if (id == -1) {
            //req.setAttribute();
            view.setName("errors/client");
            return view;
        };
        view.setRedirect(true);
        System.out.println("saveorder view.getName()=" + view.getName());
        return view;
    };


    public SaveBlankOrder() {};
    public SaveBlankOrder(Action action){
        this.action=action;
    };


}
