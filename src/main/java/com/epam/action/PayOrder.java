package com.epam.action;

import com.epam.dao.factory.DAOFactory;
import com.epam.dao.order.H2OrderDAO;
import com.epam.dao.order.OrderDAO;
import com.epam.db.ConnectionPool;
import com.epam.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PayOrder extends AbstractAction implements Action {
    private static final Logger log = LoggerFactory.getLogger(Logout.class);
    private com.epam.config.Action action;

    public PayOrder() {
    }

    public PayOrder(com.epam.config.Action action) {
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
        log.debug("sumPaid=" + req.getQueryString().toString());
        String queryStr = req.getQueryString().toString();
        double sumPaid = 0;
        Long orderId = null;
        Pattern regexp = Pattern.compile("=\\d+[.]*\\d+");
        Matcher m2 = regexp.matcher(queryStr);
        int i = 0;
        while (m2.find()) {
            if (i == 0) {
                orderId = Long.parseLong(m2.group().replace("=", ""));
                log.debug("orderId: " + orderId.toString());
            }
            if (i == 1) {
                sumPaid = Double.valueOf(m2.group().replace("=", ""));
                log.debug("sumPaid=" + sumPaid);
            }
            i++;
        }
        if (sumPaid > 0) {
            throw new PayOrderException(" Order is paid!");
        }
        Order order = new Order();
        order.setId(orderId);
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        OrderDAO orderDAO = DAOFactory.getDAOFactory(DAOFactory.DAOType.H2).getOrderDAO();
        H2OrderDAO h2OrderDAO = (H2OrderDAO) orderDAO;
        h2OrderDAO.setConnection(connectionPool);
        View view = new View(this.getAction().getView());
        if (!h2OrderDAO.payOrder(order)) {
            view.setName("errors/client");
            //req.getSession().setAttribute("markidnotcorrect", " UnMark All Clients is not possible!=");
            h2OrderDAO.closeConnection(connectionPool);
            return view;
        }
        h2OrderDAO.closeConnection(connectionPool);

        view.setRedirect(true);
        log.debug(" PayOrder view.getName()=" + view.getName());
        return view;
    }


}
