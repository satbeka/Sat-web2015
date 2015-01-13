package com.epam.dao.user;


import com.epam.db.ConnectionPool;
import com.epam.model.Administrator;
import com.epam.model.Client;
import com.epam.model.User;

import javax.sql.RowSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class H2UserDAO implements UserDAO{


    private Connection connection = null;

    // initialization
    public H2UserDAO() {
    }

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

    @Override
    public long insertUser(User user) {
        return 0;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public User findUserByName(String name) {
        return null;
    }

    @Override
    public User findUserByLogin(String login) {
        //User user=new User();
        Connection cn=this.connection;

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select * from user where login =? and nvl(deleted,0)!=1;");
            st.setString(1,login);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet rs = null;
        Long role=null;
        try {
            rs=st.executeQuery();
            rs.next();
            role=rs.getLong(3);
            if (role==1) {
                Administrator administrator=null;
                administrator.setId(rs.getLong(1));
                administrator.setName(rs.getString(2));
                administrator.setBirthDay(rs.getDate(8));
                administrator.setInn(rs.getString(7));
                administrator.setPassword(rs.getString(6));
                return administrator;}

            Client client=null;
            client.setId(rs.getLong(1));
            client.setName(rs.getString(2));
            client.setBirthDay(rs.getDate(8));
            client.setInn(rs.getString(7));
            client.setPassword(rs.getString(6));
            return client;

        } catch (SQLException e) {
            //TODO log e.printStackTrace();
        }

        return null;
    }

    public User findUserByLoginByPassword(String login,String password) {
        //User user=new User();
        Connection cn=this.connection;
        System.out.println("login="+login);
        System.out.println("password="+password);
        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select * from user where login =? and password =? and nvl(deleted,0)!=1;");
            st.setString(1,login);
            st.setString(2,password);
        } catch (SQLException e) {
            //TODO log e.printStackTrace();
        }

        ResultSet rs = null;
        Long role=null;
        try {
            rs=st.executeQuery();
            rs.next();
            role=rs.getLong("ROLE");
            if (role==1) {
                Administrator administrator=new Administrator();
                System.out.println("administrato="+administrator);
                System.out.println("rs.getLong(1)="+rs.getLong(1));
                administrator.setId(rs.getLong(1));
                administrator.setName(rs.getString(2));
                administrator.setBirthDay(rs.getDate(8));
                administrator.setInn(rs.getString(7));
                administrator.setPassword(rs.getString(6));
                return administrator;}

            Client client=new Client();
            client.setId(rs.getLong(1));
            client.setName(rs.getString(2));
            client.setBirthDay(rs.getDate(8));
            client.setInn(rs.getString(7));
            client.setPassword(rs.getString(6));
            return client;

        } catch (SQLException e) {
            System.out.println("sql="+e.getMessage());
            //TODO log e.printStackTrace();
        }

        return null;
    }

    @Override
    public User findUserById(long id) {
        return null;
    }

    @Override
    public User findFirstUserByName(String name) {
        return null;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public RowSet selectUserRS(User user) {
        return null;
    }

    @Override
    public List<User> selectUserTO() {
        return null;
    }
}
