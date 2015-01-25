package com.epam.service;


import com.epam.db.ConnectionPool;
import com.epam.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductService {
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    public static ArrayList<Product> findProductsByAdministrator() {
        ArrayList<Product> products = new ArrayList<Product>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection cn = connectionPool.takeConnection();

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select * from product where nvl(deleted,0)!=1;");
            //st.setString(1,client.getName());
        } catch (SQLException e) {

        }

        ResultSet rs = null;
        try {
            rs = st.executeQuery();
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
            log.debug("ProductService=" + e.getMessage());
        }

        return products;
    }

}
