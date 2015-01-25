package com.epam.dao.product;

import com.epam.db.ConnectionPool;
import com.epam.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.RowSet;
import java.sql.*;
import java.util.List;


public class H2ProductDAO implements ProductDAO {
    private static final Logger log = LoggerFactory.getLogger(ProductDAO.class);
    private Connection connection = null;

    // initialization
    public Connection setConnection(ConnectionPool connectionPool) {
        if (this.connection == null) {
            this.connection = connectionPool.takeConnection();
        }
        ;
        return this.connection;
    }

    public void closeConnection(ConnectionPool connectionPool) {
        if (this.connection != null) {
            connectionPool.releaseConnection(this.connection);
        }
        ;
        return;
    }

    public H2ProductDAO() {
    }

    @Override
    public long insertProduct(Product product) {
        String SqlSeqID = "select seq_id.nextval from dual;";
        String SqlInsert2 = "insert into PRODUCT(id,name,active,insert_date," +
                "price,deleted) values (?,?,?,?,?,0)";

        Connection cn = this.connection;
        //Connection cn=
        try {
            cn.setAutoCommit(false);
        } catch (SQLException e) {

        }

        Statement st = null;
        try {
            st = cn.createStatement();
        } catch (SQLException e) {

        }

        ResultSet rs = null;
        long id = -1;
        try {
            rs = st.executeQuery(SqlSeqID);
            if (rs.next()) {
                id = rs.getLong(1);
            }

        } catch (SQLException e) {
            log.debug("H2ProductDAO=" + e.getMessage());
        }

        PreparedStatement st2 = null;
        try {
            st2 = cn.prepareStatement(SqlInsert2);
        } catch (SQLException e) {

        }

        try {
            st2.setLong(1, id);
        } catch (SQLException e) {

        }


        try {
            st2.setString(2, product.getName());
        } catch (SQLException e) {

        }
        try {
            st2.setInt(3, 1);  //active=1
        } catch (SQLException e) {

        }
        if (product.getInsertDate() == null) {
            //java.util.Date sysDate=new java.util.Date();
            //Date sqlDate = new Date((new java.util.Date()).getTime());
            product.setInsertDate(new Date((new java.util.Date()).getTime()));
        }

        try {
            st2.setDate(4, product.getInsertDate());
        } catch (SQLException e) {

        }
        try {
            st2.setBigDecimal(5, product.getPrice());
        } catch (SQLException e) {

        }

        rs = null;
        try {
            int countRows = st2.executeUpdate();
            if (countRows == 0) {
                id = -1;
            }
        } catch (SQLException e) {
            log.debug("H2ProductDAO=" + e.getMessage());
        }

        try {
            cn.commit();
            return id;
        } catch (SQLException e) {

        }

        return id;
    }

    @Override
    public boolean deleteProduct(Product product) {
        return false;
    }

    @Override
    public Product findProductByName(String name) {
        return null;
    }

    @Override
    public Product findProductById(long id) {
        return null;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public RowSet selectProductRS(Product product) {
        return null;
    }

    @Override
    public List<Product> selectProductTO() {
        return null;
    }
}
