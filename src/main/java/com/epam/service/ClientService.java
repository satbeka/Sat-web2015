package com.epam.service;

import com.epam.db.ConnectionPool;
import com.epam.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    public static ArrayList<Client> findClientsByAdministrator() {
        ArrayList<Client> clients = new ArrayList<Client>();

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection cn = connectionPool.takeConnection();

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select * from user where role=0 and nvl(deleted,0)!=1 order by id;");
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
            log.debug("ClientService=" + e.getMessage());
        }

        return clients;
    }

    public static boolean unMarkAllClientsbyAdministrator() {

        //String SqlSeqID="select seq_id.nextval from dual;";
        String SqlUpd = "update USER set blacklist=0 where nvl(deleted,0)!=1 and role=0;";

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection cn = connectionPool.takeConnection();

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement(SqlUpd);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            assert st != null;
            int countRows = st.executeUpdate();
        } catch (SQLException e) {
            log.debug("ClientService=" + e.getMessage());
        }

        try {
            cn.commit();
            return true;
        } catch (SQLException e) {
            log.debug(e.toString());
        }

        return false;
    }


}
