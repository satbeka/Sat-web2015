package com.epam.service;


import com.epam.db.ConnectionPool;
//import com.epam.dao.administrator.H2AdministratorDAO;
import com.epam.dao.factory.DAOFactory;
import com.epam.dao.product.H2ProductDAO;
import com.epam.dao.product.ProductDAO;
import com.epam.model.Client;
import com.epam.model.Order;
import com.epam.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderService {

    public static ArrayList<Order> findOrdersByClient(Long clientId) {
        ArrayList<Order> orders = new ArrayList<Order>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection cn = connectionPool.takeConnection();

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select * from client_order where user =? and nvl(deleted,0)!=1;");
            st.setLong(1, clientId);
        } catch (SQLException e) {
            //TODO log;
        }

        ResultSet rs = null;
        try {
            rs = st.executeQuery();
            connectionPool.releaseConnection(cn);
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong(1));
                order.setNumber(rs.getString("NUMBER"));
                order.setSum(rs.getBigDecimal("SUM"));
                order.setSumPaid(rs.getBigDecimal("SUM_PAID"));
                //order.setClient(clientId);
                order.setInsertDate(rs.getDate("INSERT_DATE"));
                System.out.println("InsertDate="+order.getInsertDate().toString());
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
            //TODO log
        }


        return orders;
    }

    public static Long getMaxIdOrderByClient(Long clientId) {
        Long id= Long.valueOf(0);
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection cn = connectionPool.takeConnection();

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select max (id) from client_order where user =? and nvl(deleted,0)!=1;");
            st.setLong(1, clientId);
        } catch (SQLException e) {
            //TODO log;
        }

        ResultSet rs = null;
        try {
            rs = st.executeQuery();
            connectionPool.releaseConnection(cn);
            while (rs.next()) {
                id=rs.getLong(1);
                return id;
            }
            //return administrator;

        } catch (SQLException e) {
            //TODO log
        }
        return id;
    }

}
