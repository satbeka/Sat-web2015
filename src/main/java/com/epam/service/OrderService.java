package com.epam.service;


import com.epam.db.ConnectionPool;
import com.epam.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    public static ArrayList<Order> findOrdersByClient(Long clientId) {
        ArrayList<Order> orders = new ArrayList<Order>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection cn = connectionPool.takeConnection();

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select * from client_order where user =? and nvl(deleted,0)!=1 order by id;");
            st.setLong(1, clientId);
        } catch (SQLException e) {
            log.debug(e.toString());
        }

        ResultSet rs = null;
        try {
            assert st != null;
            rs = st.executeQuery();
            connectionPool.releaseConnection(cn);
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong(1));
                order.setNumber(rs.getString("NUMBER"));
                //order.setSum(rs.getBigDecimal("SUM"));
                BigDecimal sumPaid = BigDecimal.valueOf(0.00);
                if ((rs.getDouble("SUM_PAID") > 0)) {
                    sumPaid = rs.getBigDecimal("SUM_PAID");
                }
                order.setSumPaid(sumPaid);
                //order.setClient(clientId);
                order.setInsertDate(rs.getDate("INSERT_DATE"));
                log.debug("sumPaid=" + sumPaid);
/*
                ProductDAO productDAO = DAOFactory.getDAOFactory(DAOFactory.DAOType.H2).getProductDAO();
                H2ProductDAO h2ProductDAO = (H2ProductDAO) productDAO;
                h2ProductDAO.setConnection(connectionPool);
                Product product = productDAO.findProductById(rs.getLong(3));
                h2ProductDAO.closeConnection(connectionPool);
                order.setProduct(product);
                */
                orders.add(order);
            }
            //return administrator;

        } catch (SQLException e) {
            log.debug("OrderService=" + e.getMessage());
        }


        return orders;
    }

    public static Long getMaxIdOrderByClient(Long clientId) {
        Long id = (long) 0;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection cn = connectionPool.takeConnection();

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select max (id) from client_order where user =? and nvl(deleted,0)!=1;");
            st.setLong(1, clientId);
        } catch (SQLException e) {
            log.debug(e.toString());
        }

        ResultSet rs = null;
        try {
            assert st != null;
            rs = st.executeQuery();
            connectionPool.releaseConnection(cn);
            if (rs.next()) {
                do {
                    id = rs.getLong(1);
                    return id;
                } while (rs.next());
            }
            //return administrator;

        } catch (SQLException e) {
            log.debug("OrderService=" + e.getMessage());
        }
        return id;
    }

}
