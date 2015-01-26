package com.epam.action;

import com.epam.dao.factory.DAOFactory;
import com.epam.dao.order.H2OrderDAO;
import com.epam.dao.order.OrderDAO;
import com.epam.db.ConnectionPool;
import com.epam.model.Client;
import com.epam.model.Order;
import com.epam.model.Product;
import com.epam.model.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SaveProductListForOrder extends AbstractAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(SaveProductListForOrder.class);
    private com.epam.config.Action action;

    public SaveProductListForOrder() {
    }

    public SaveProductListForOrder(com.epam.config.Action action) {
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
        Long orderId = Long.parseLong(req.getSession().getAttribute("orderId").toString());
        Long clientId = Long.parseLong(req.getSession().getAttribute("clientId").toString());
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        OrderDAO orderDAO = DAOFactory.getDAOFactory(DAOFactory.DAOType.H2).getOrderDAO();
        H2OrderDAO h2OrderDAO = (H2OrderDAO) orderDAO;
        h2OrderDAO.setConnection(connectionPool);
        String orderNumber = h2OrderDAO.getOrderNumberById(orderId);
        h2OrderDAO.closeConnection(connectionPool);
        String[] quantityList = req.getParameterValues("quantity");
        String[] productIdList = req.getParameterValues("productId");
        int k = 0;
        int productId;
        int quantity;
        Order order = new Order();
        order.setId(orderId);
        order.setNumber(orderNumber);
        Client client = new Client();
        client.setId(clientId);
        order.setClient(client);
        order.initProducts();
        for (String quantityStr : quantityList) {
            quantity = Integer.parseInt(quantityStr);
            log.debug("k=" + k);
            if (quantity > 0) {
                Warehouse warehouse = new Warehouse();
                Product product = new Product();
                warehouse.setQuantity(quantity);
                log.debug(productIdList[k].toString());
                productId = Integer.parseInt(productIdList[k].toString());
                product.setId(productId);
                warehouse.setProduct(product);
                log.debug("productId=" + productId);
                log.debug("quantity=" + quantity);
                order.add(warehouse);
            }
            k++;
        }
        View view = new View(this.getAction().getView());
        h2OrderDAO.setConnection(connectionPool);
        if (!h2OrderDAO.updateOrder(order)) {
            h2OrderDAO.closeConnection(connectionPool);
            view.setName("errors/client");
            return view;
        }
        h2OrderDAO.closeConnection(connectionPool);

        view.setRedirect(true);
        log.debug(" SaveProductListForOrder view.getName()=" + view.getName());
        return view;
    }


}
