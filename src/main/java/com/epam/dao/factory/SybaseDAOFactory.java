package com.epam.dao.factory;

import com.epam.dao.ConnectionException;
import com.epam.dao.address.AddressDAO;
import com.epam.dao.administrator.AdministratorDAO;
import com.epam.dao.order.OrderDAO;
import com.epam.dao.product.ProductDAO;
import com.epam.dao.user.UserDAO;


public class SybaseDAOFactory extends DAOFactory {
    @Override
    public AdministratorDAO getAdministratorDAO() throws ConnectionException {
        return null;
    }

    @Override
    public OrderDAO getOrderDAO() throws ConnectionException {
        return null;
    }

    @Override
    public ProductDAO getProductDAO() throws ConnectionException {
        return null;
    }

    @Override
    public UserDAO getUserDAO() {
        return null;
    }

    @Override
    public AddressDAO getAddressDAO() throws ConnectionException {
        return null;
    }
}
