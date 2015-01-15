package com.epam.service;

import com.epam.db.ConnectionPool;
import com.epam.model.Client;
//import com.epam.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

//import com.epam.model.Order;

public class ClientService {

    public static ArrayList<Client> findClientsByAdministrator() {
        ArrayList<Client> clients=new ArrayList<Client>();

        ConnectionPool connectionPool=ConnectionPool.getInstance();
        Connection cn=connectionPool.takeConnection();

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select * from user where role=0 and nvl(deleted,0)!=1;");
            //st.setString(1,client.getName());
        } catch (SQLException e) {
            //TODO log;
        }

        ResultSet rs = null;
        try {
            rs=st.executeQuery();
            connectionPool.releaseConnection(cn);
            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getLong("ID"));
                client.setName(rs.getString("NAME"));
                client.setInsertDate(rs.getDate("INSERT_DATE"));
                client.setBlackList(rs.getInt("BLACKLIST"));
                client.setInn(rs.getString("INN"));
                clients.add(client);
            }
            //return administrator;

        } catch (SQLException e) {
            //TODO log
        }

        return clients;
    }

}
