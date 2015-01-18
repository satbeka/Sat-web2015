package com.epam.dao.order;

import com.epam.db.ConnectionPool;
import com.epam.model.Order;

import javax.sql.RowSet;
import java.sql.*;
import java.util.List;


public class H2OrderDAO implements OrderDAO {
    private Connection connection = null;

    // initialization
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

    public H2OrderDAO() {
    }

    @Override
    public long insertOrder(Order order) {
        String SqlSeqID = "select seq_id.nextval from dual;";
        String SqlInsert2 = "insert into CLIENT_ORDER(id,number,quantity,product,user,sum,sum_paid,insert_date," +
                "deleted) values (?,?,?,?,?,?,?,?,0)";

        Connection cn = this.connection;
        //Connection cn=
        try {
            cn.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement st = null;
        try {
            st = cn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet rs = null;
        long id = -1;
        try {
            rs = st.executeQuery(SqlSeqID);
            if (rs.next()) {
                id = rs.getLong(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        PreparedStatement st2 = null;
        try {
            st2 = cn.prepareStatement(SqlInsert2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            st2.setLong(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            st2.setString(2, order.getNumber());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st2.setInt(3, order.getQuantity());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //long productId;// = Long.parseLong(null);//Long.parseLong(null);
        if (order.getProduct() != null) {
            try {
                st2.setLong(4, order.getProduct().getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                st2.setNull(4, Types.BIGINT);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ;

        if (order.getClient() != null) {
            try {
                st2.setLong(5, order.getClient().getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                st2.setNull(5, Types.BIGINT);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ;


/*
        if (order.getClient() != null) {
            try {
                st2.setLong(5, order.getClient().getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
*/

        try {
            st2.setBigDecimal(6, order.getSum());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            st2.setBigDecimal(7, order.getSumPaid());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (order.getInsertDate() == null) {
            order.setInsertDate(new Date((new java.util.Date()).getTime()));
        }
        try {
            st2.setDate(8, order.getInsertDate());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        rs = null;
        try {
            int countRows = st2.executeUpdate();
            if (countRows == 0) {
                id = -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            cn.commit();
            return id;
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

        return id;
    }

    @Override
    public boolean deleteOrder(Order order) {
        return false;
    }

    @Override
    public Order findOrderByName(String name) {
        return null;
    }

    @Override
    public Order findOrderById(long id) {
        return null;
    }

    @Override
    public boolean updateOrder(Order order) {
        return false;
    }

    @Override
    public RowSet selectOrderRS(Order order) {
        return null;
    }

    @Override
    public List<Order> selectOrderTO() {
        return null;
    }
}
