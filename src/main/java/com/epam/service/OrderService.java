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

    //private ConnectionPool connectionPool;


    private Client client;
    private ArrayList<Order> orderArrayList;
    private ArrayList<Product> productArrayList;

    public OrderService(){};
    /*
    public OrderService(ConnectionPool connectionPool, Client client){
        this.connectionPool=connectionPool;
        this.client=client;
    }
    */

    public ArrayList<Order> findOrdersByClient() {
        ArrayList<Order> orders=new ArrayList<Order>();

        ConnectionPool connectionPool=ConnectionPool.getInstance();
        Connection cn=connectionPool.takeConnection();

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select * from client_order where user =? and nvl(deleted,0)!=1;");
            st.setString(1,client.getName());
        } catch (SQLException e) {
            //TODO log;
        }

        ResultSet rs = null;
        try {
            rs=st.executeQuery();
            connectionPool.releaseConnection(cn);
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getLong(1));
                order.setClient(client);
                order.setDate(rs.getDate(2));

                ProductDAO productDAO = DAOFactory.getDAOFactory(DAOFactory.DAOType.H2).getProductDAO();
                H2ProductDAO h2ProductDAO=(H2ProductDAO)productDAO;
                h2ProductDAO.setConnection(connectionPool);
                Product product = productDAO.findProductById(rs.getLong(3));
                h2ProductDAO.closeConnection(connectionPool);

                order.setProduct(product);
                orders.add(order);
            }
            //return administrator;

        } catch (SQLException e) {
            //TODO log
        }



        return orders;
    }




    public OrderService takeAllProduct(Client client){
        this.client=client;
        return this;
    };


    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }

    public void setProductArrayList(ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
    }
}
