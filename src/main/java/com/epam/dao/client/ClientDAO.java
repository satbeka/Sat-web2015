package com.epam.dao.client;

import com.epam.model.Client;

import javax.sql.RowSet;
import java.util.List;


public interface ClientDAO {

    public int insertClient(Client client);
    public boolean deleteClient(Client client);
    public Client findClientByName(String name);
    public Client findClientById(long id);
    public boolean updateClient(Client client);
    public RowSet selectClientRS(Client client);
    public List<Client> selectClientTO();

}