package com.epam.dao.factory;

import com.epam.dao.user.H2UserDAO;
import com.epam.dao.user.UserDAO;
import com.epam.db.ConnectionPool;
import com.epam.dao.address.AddressDAO;
import com.epam.dao.administrator.AdministratorDAO;
import com.epam.dao.administrator.H2AdministratorDAO;
import com.epam.dao.client.ClientDAO;
import com.epam.dao.client.H2ClientDAO;
import com.epam.dao.order.H2OrderDAO;
import com.epam.dao.order.OrderDAO;
import com.epam.dao.product.H2ProductDAO;
import com.epam.dao.product.ProductDAO;

public class H2DAOFactory extends DAOFactory {
    public ConnectionPool getPool() {
        return pool;
    }

    public void setPool(ConnectionPool pool) {
        this.pool = pool;
    }

    private ConnectionPool pool = null;
    //private static Connection connection;

    /*
        // method to create connections
    public static Connection createConnection() throws SQLException {
        // Recommend connection pool implementation/usage
        Connection connection=ConnectorDb.getConnection();
        return connection;
    }
    */


    @Override
    public AdministratorDAO getAdministratorDAO() {

        return new H2AdministratorDAO();
    }

    @Override
    public ClientDAO getClientDAO() {
        return new H2ClientDAO();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new H2OrderDAO();
    }

    @Override
    public ProductDAO getProductDAO() {
        return new H2ProductDAO();
    }

    @Override
    public UserDAO getUserDAO() {
        return new H2UserDAO();
    }

    @Override
    public AddressDAO getAddressDAO() {
        return null;
    }

}
