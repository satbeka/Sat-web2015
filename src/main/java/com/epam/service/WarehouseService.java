package com.epam.service;

import com.epam.db.ConnectionPool;
import com.epam.model.Product;
import com.epam.model.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WarehouseService {

    private static final Logger log = LoggerFactory.getLogger(WarehouseService.class);

    public static ArrayList<Warehouse> findProductsForOrder(Long orderId) {
        ArrayList<Warehouse> products = new ArrayList<Warehouse>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection cn = connectionPool.takeConnection();

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement(
                    " select p.*,o.QUANTITY from product p left OUTER join " +
                            " order_detail o on p.ID=o.PRODUCT and nvl(o.deleted,0)!=1 and o.client_order=" + orderId.toString() +
                            " where nvl(p.deleted,0)!=1;"
            );
            //st.setString(1,client.getName());
        } catch (SQLException e) {
            log.debug(e.toString());
        }

        ResultSet rs = null;
        try {
            assert st != null;
            rs = st.executeQuery();
            connectionPool.releaseConnection(cn);
            while (rs.next()) {
                Product product = new Product();
                Warehouse warehouse = new Warehouse();
                product.setId(rs.getLong("ID"));
                warehouse.setId(rs.getLong("ID"));
                product.setName(rs.getString("NAME"));
                product.setInsertDate(rs.getDate("INSERT_DATE"));
                product.setPrice(rs.getBigDecimal("PRICE"));
                warehouse.setProduct(product);
                warehouse.setQuantity(rs.getInt("QUANTITY"));
                products.add(warehouse);
            }
            //return administrator;

        } catch (SQLException e) {
            log.debug("WarehouseService=" + e.getMessage());
        }

        return products;
    }

}
