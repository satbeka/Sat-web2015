package com.epam.dao.factory;

import com.epam.dao.address.AddressDAO;
import com.epam.dao.administrator.AdministratorDAO;
import com.epam.dao.client.ClientDAO;
import com.epam.dao.order.OrderDAO;
import com.epam.dao.product.ProductDAO;
import com.epam.dao.user.UserDAO;

public abstract class DAOFactory {

    // List of DAO types supported by the com.epam.dao
    /*
    public static final int H2 = 1;
    public static final int ORACLE = 2;
    public static final int SYBASE = 3;
    */
    public enum DAOType {H2,ORACLE,SYBASE};

    // There will be a method for each DAO that can be
    // created. The concrete factories will have to
    // implement these methods.
    //public abstract AdministratorDAO getAdministratorDAO() throws ConnectionException;

    //public abstract AdministratorDAO getAdministratorDAO(ConnectionPool connectionPool) throws ConnectionException;

    //public abstract ClientDAO getClientDAO() throws ConnectionException;

    //public abstract OrderDAO getOrderDAO() throws ConnectionException;

    //public abstract ProductDAO getProductDAO() throws ConnectionException;

    //public abstract AddressDAO getAddressDAO() throws ConnectionException;


    public static DAOFactory getDAOFactory(
            DAOType whichFactory) {

        switch (whichFactory) {
            case H2:
                return new H2DAOFactory();
            case ORACLE:
                return new OracleDAOFactory();
            case SYBASE:
                return new SybaseDAOFactory();

            default:
                return null;
        }
    }

    public abstract AdministratorDAO getAdministratorDAO();

    public abstract OrderDAO getOrderDAO();

    public abstract ProductDAO getProductDAO();

    public abstract UserDAO getUserDAO();

    public abstract ClientDAO getClientDAO();

    public abstract AddressDAO getAddressDAO();
}
