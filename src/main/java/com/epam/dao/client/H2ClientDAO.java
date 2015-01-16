package com.epam.dao.client;

import com.epam.db.ConnectionPool;
import com.epam.model.Client;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;


public class H2ClientDAO implements ClientDAO {

    private Connection connection = null;
    // initialization
    public H2ClientDAO(){}

    public Connection setConnection(ConnectionPool connectionPool) {
        if (this.connection==null){
            this.connection=connectionPool.takeConnection();};
        return this.connection;
    }
    public void closeConnection(ConnectionPool connectionPool) {
        if (this.connection!=null){
            connectionPool.releaseConnection(this.connection);};
        return;
    }
    public boolean markClientbyAdministrator(Client client) {

        //String SqlSeqID="select seq_id.nextval from dual;";
        String SqlUpd="update USER set blacklist=1 where nvl(deleted,0)!=1 and id="+client.getId();

        Connection cn=this.connection;

        PreparedStatement st=null;
        try {
            st=cn.prepareStatement(SqlUpd);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
        try {
            st.setLong(1, client.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
        try {
            int countRows = st.executeUpdate();
            //if (countRows==0){return false;}
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            cn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

    public boolean unMarkClientbyAdministrator(Client client) {

        //String SqlSeqID="select seq_id.nextval from dual;";
        String SqlUpd="update USER set blacklist=0 where nvl(deleted,0)!=1 and id="+client.getId();

        Connection cn=this.connection;

        PreparedStatement st=null;
        try {
            st=cn.prepareStatement(SqlUpd);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*
        try {
            st.setLong(1, client.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
        try {
            int countRows = st.executeUpdate();
            //if (countRows==0){return false;}
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            cn.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }

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
