package com.epam.dao.administrator;

import com.epam.db.ConnectionPool;
import com.epam.model.Administrator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.RowSet;
import java.sql.*;
import java.util.List;


public class H2AdministratorDAO implements AdministratorDAO {

    private static final Logger log = LoggerFactory.getLogger(H2AdministratorDAO.class);
    private Connection connection = null;

    // initialization
    public H2AdministratorDAO() {
    }

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

    @Override
    public Administrator findAdministratorByName(String name) {
        return null;
    }

    @Override
    public long insertAdministrator(Administrator administrator) {

        String SqlSeqID = "select seq_id.nextval from dual;";
        String SqlInsert2 = "insert into USER(id,name,role,address,login,password,inn," +
                "birth_day,insert_date) values (?,?,?,?,?,?,?,?,?)";

        Connection cn = this.connection;
        //Connection cn=
        try {
            cn.setAutoCommit(false);
        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }

        Statement st = null;
        try {
            st = cn.createStatement();
        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }

        ResultSet rs = null;
        long id = -1;
        try {
            rs = st.executeQuery(SqlSeqID);
            if (rs.next()) {
                id = rs.getLong(1);
            }

        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }

        PreparedStatement st2 = null;
        try {
            st2 = cn.prepareStatement(SqlInsert2);
        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }

        try {
            st2.setLong(1, id);
        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }


        try {
            st2.setString(2, administrator.getName());
        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }
        try {
            st2.setInt(3, 1);  //Administrator role=1
        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }
        long addressId = 0;
        try {
            addressId = administrator.getAddress().getId();
        } catch (Exception e) {
            addressId = 0;
        }
        try {
            st2.setLong(4, addressId);
        } catch (SQLException e) {

        }
        try {
            st2.setString(5, administrator.getLogin());
        } catch (SQLException e) {

        }
        try {
            st2.setString(6, administrator.getPassword());
        } catch (SQLException e) {

        }
        try {
            st2.setString(7, administrator.getInn());
        } catch (SQLException e) {

        }
        try {
            st2.setDate(8, administrator.getBirthDay());
        } catch (SQLException e) {

        }
        try {

            st2.setDate(9, administrator.getInsertDate());//insert_date
        } catch (SQLException e) {

        }

        rs = null;
        try {
            int countRows = st2.executeUpdate();
            if (countRows == 0) {
                id = -1;
            }
        } catch (SQLException e) {

        }

        try {
            cn.commit();
            return id;
        } catch (SQLException e) {

        }


        return -1;
    }

    @Override
    public boolean deleteAdministrator(Administrator administrator) {
        Connection cn = this.connection;
        PreparedStatement st = null;
        String SqlUpd = "update USER set deleted=1 where id=?";
        try {
            st = cn.prepareStatement(SqlUpd);
            st.setLong(1, administrator.getId());
            administrator.setDeleted(1);
            return true;
        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }

        return false;
    }

    @Override
    public Administrator findFirstAdministratorByName(String name) {
        Administrator administrator = new Administrator();
        Connection cn = this.connection;

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select * from user where name =? and nvl(deleted,0)!=1;");
            st.setString(1, name);
        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }

        ResultSet rs = null;
        try {
            rs = st.executeQuery();
            rs.next();
            administrator.setId(rs.getLong(1));
            administrator.setName(rs.getString(2));
            administrator.setBirthDay(rs.getDate(8));
            administrator.setInn(rs.getString(7));
            administrator.setPassword(rs.getString(6));
            return administrator;

        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }


        return null;
    }

    @Override
    public Administrator findAdministratorByLogin(String login) {
        Administrator administrator = new Administrator();
        Connection cn = this.connection;

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select * from user where login =? and nvl(deleted,0)!=1;");
            st.setString(1, login);
        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }

        ResultSet rs = null;
        try {
            rs = st.executeQuery();
            rs.next();
            administrator.setId(rs.getLong(1));
            administrator.setName(rs.getString(2));
            administrator.setBirthDay(rs.getDate(8));
            administrator.setInn(rs.getString(7));
            administrator.setPassword(rs.getString(6));
            return administrator;

        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }


        return null;
    }

    @Override
    public Administrator findAdministratorById(long id) {

        Administrator administrator = new Administrator();
        Connection cn = this.connection;

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement("select * from user where id =? and deleted!=1;");
            st.setLong(1, id);
        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }

        ResultSet rs = null;
        try {
            rs = st.executeQuery();
            rs.next();
            administrator.setId(rs.getLong(1));
            administrator.setName(rs.getString(2));
            administrator.setLogin(rs.getString(5));
            administrator.setInn(rs.getString(7));
            administrator.setBirthDay(rs.getDate(8));

            return administrator;

        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }


        return null;


    }

    @Override
    public boolean updateAdministrator(Administrator administrator) {

        //String SqlSeqID="select seq_id.nextval from dual;";
        String SqlUpd = "update USER set name=?,address=?,login=?,password=?" +
                ",inn=?,birth_day=? where Id=? and deleted!=1";

        Connection cn = this.connection;
        try {
            cn.setAutoCommit(false);
        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }

        PreparedStatement st = null;
        try {
            st = cn.prepareStatement(SqlUpd);
        } catch (SQLException e) {
            log.debug("H2AdministratorDAO=" + e.getMessage());
        }

        try {
            st.setLong(7, administrator.getId());
        } catch (SQLException e) {

        }

        try {
            st.setString(1, administrator.getName());
        } catch (SQLException e) {

        }

        try {
            st.setLong(2, administrator.getAddress().getId());
        } catch (SQLException e) {

        } catch (NullPointerException e) {
            try {
                st.setNull(2, Types.INTEGER);
            } catch (SQLException e1) {

            }
        }
        try {
            st.setString(3, administrator.getLogin());
        } catch (SQLException e) {

        }
        try {
            st.setString(4, administrator.getPassword());
        } catch (SQLException e) {

        }
        try {
            st.setString(5, administrator.getInn());
        } catch (SQLException e) {

        }
        try {
            st.setDate(6, administrator.getBirthDay());
        } catch (SQLException e) {

        }

        try {
            int countRows = st.executeUpdate();
            if (countRows == 0) {
                return false;
            }
        } catch (SQLException e) {

        }

        try {
            cn.commit();
            return true;
        } catch (SQLException e) {

        }


        return false;
    }

    @Override
    public RowSet selectAdministratorRS(Administrator administrator) {
        return null;
    }

    @Override
    public List<Administrator> selectAdministratorTO() {
        return null;
    }
}
