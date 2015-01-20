package com.epam.action;

import com.epam.config.Action;
import com.epam.dao.client.ClientDAO;
import com.epam.dao.client.H2ClientDAO;
import com.epam.dao.factory.DAOFactory;
import com.epam.dao.order.H2OrderDAO;
import com.epam.dao.order.OrderDAO;
import com.epam.db.ConnectionPool;
import com.epam.model.Client;
import com.epam.model.Order;
import com.epam.model.ProductExtQuantity;
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
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        OrderDAO orderDAO = DAOFactory.getDAOFactory(DAOFactory.DAOType.H2).getOrderDAO();
        H2OrderDAO h2OrderDAO = (H2OrderDAO) orderDAO;
        h2OrderDAO.setConnection(connectionPool);
        String orderNumber=h2OrderDAO.getOrderNumberById(orderId);
        h2OrderDAO.closeConnection(connectionPool);
        String[] quantityList = req.getParameterValues("quantity");
        String[] productIdList =req.getParameterValues("productId");
        int k=0;
        int productId;
        int quantity;
        Order order=new Order();
        order.setId(orderId);
        order.setNumber(orderNumber);
        Client client=new Client();
        client.setId(clientId);
        order.setClient(client);
        order.initProducts();
        for (String quantityStr : quantityList) {
            quantity=Integer.parseInt(quantityStr);
            System.out.println("k="+k);
            if (quantity>0) {
                ProductExtQuantity productExtQuantity=new ProductExtQuantity();
                productExtQuantity.setQuantity(quantity);
                    System.out.println(productIdList[k].toString());
                productId=Integer.parseInt(productIdList[k].toString());
                productExtQuantity.setId(productId);
                   System.out.println("productId="+productId);
                   System.out.println("quantity="+quantity);
                order.add(productExtQuantity);
            }
            k++;
        }


        h2OrderDAO.setConnection(connectionPool);
        h2OrderDAO.updateOrder(order);
        h2OrderDAO.closeConnection(connectionPool);


                //Long.parseLong(req.getSession().getAttribute("productId").toString());
        View view = new View("/do/clientlistorder");
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
