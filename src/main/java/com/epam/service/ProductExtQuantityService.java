package com.epam.service;


import com.epam.db.ConnectionPool;
import com.epam.model.Product;
import com.epam.model.ProductExtQuantity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import com.epam.model.Order;

public class ProductExtQuantityService {

    public static ArrayList<ProductExtQuantity> findProductsForOrder(Long orderId) {
        ArrayList<ProductExtQuantity> products=new ArrayList<ProductExtQuantity>();

        ConnectionPool connectionPool=ConnectionPool.getInstance();
        Connection cn=connectionPool.takeConnection();

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement(
            " select p.*,o.QUANTITY from product p left OUTER join " +
            " order_detail o on p.ID=o.PRODUCT and o.client_order=" +orderId.toString()+
            " where nvl(p.deleted,0)!=1;"
            );
            //st.setString(1,client.getName());
        } catch (SQLException e) {
            //TODO log;
        }

        ResultSet rs = null;
        try {
            rs=st.executeQuery();
            connectionPool.releaseConnection(cn);
            while (rs.next()) {
                ProductExtQuantity productExtQuantity = new ProductExtQuantity();
                productExtQuantity.setId(rs.getLong("ID"));
                productExtQuantity.setName(rs.getString("NAME"));
                productExtQuantity.setInsertDate(rs.getDate("INSERT_DATE"));
                productExtQuantity.setPrice(rs.getBigDecimal("PRICE"));
                productExtQuantity.setQuantity(rs.getInt("QUANTITY"));
                products.add(productExtQuantity);
            }
            //return administrator;

        } catch (SQLException e) {
            //TODO log
        }

        return products;
    }

}
