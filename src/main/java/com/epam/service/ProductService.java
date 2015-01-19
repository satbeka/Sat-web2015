package com.epam.service;

import com.epam.dao.factory.DAOFactory;
import com.epam.dao.product.H2ProductDAO;
import com.epam.dao.product.ProductDAO;
import com.epam.db.ConnectionPool;
//import com.epam.model.Order;
import com.epam.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductService {

    public static ArrayList<Product> findProductsByAdministrator() {
        ArrayList<Product> products=new ArrayList<Product>();

        ConnectionPool connectionPool=ConnectionPool.getInstance();
        Connection cn=connectionPool.takeConnection();

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select * from product where nvl(deleted,0)!=1;");
            //st.setString(1,client.getName());
        } catch (SQLException e) {
            //TODO log;
        }

        ResultSet rs = null;
        try {
            rs=st.executeQuery();
            connectionPool.releaseConnection(cn);
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("ID"));
                product.setName(rs.getString("NAME"));
                product.setInsertDate(rs.getDate("INSERT_DATE"));
                product.setPrice(rs.getBigDecimal("PRICE"));
                products.add(product);
            }
            //return administrator;

        } catch (SQLException e) {
            //TODO log
        }

        return products;
    }
    public static ArrayList<Product> findProductsForOrder(Long orderId) {
        ArrayList<Product> products=new ArrayList<Product>();

        ConnectionPool connectionPool=ConnectionPool.getInstance();
        Connection cn=connectionPool.takeConnection();

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select * from product where nvl(deleted,0)!=1;");
            //st.setString(1,client.getName());
        } catch (SQLException e) {
            //TODO log;
        }

        ResultSet rs = null;
        try {
            rs=st.executeQuery();
            connectionPool.releaseConnection(cn);
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getLong("ID"));
                product.setName(rs.getString("NAME"));
                product.setInsertDate(rs.getDate("INSERT_DATE"));
                product.setPrice(rs.getBigDecimal("PRICE"));
                products.add(product);
            }
            //return administrator;

        } catch (SQLException e) {
            //TODO log
        }

        return products;
    }

}
