package com.epam.dao.client;

import com.epam.model.Client;

import javax.sql.RowSet;
import java.sql.Connection;
import java.util.List;


public class H2ClientDAO implements ClientDAO {

    private Connection connection = null;
    // initialization
    public H2ClientDAO(){}

    @Override
    public int insertClient(Client client) {
        return 0;
    }

    @Override
    public boolean deleteClient(Client client) {
        return false;
    }

    @Override
    public Client findClientByName(String name) {
        return null;
    }

    @Override
    public Client findClientById(long id) {
        return null;
    }

    @Override
    public boolean updateClient(Client client) {
        return false;
    }

    @Override
    public RowSet selectClientRS(Client client) {
        return null;
    }

    @Override
    public List<Client> selectClientTO() {
        return null;
    }
}
