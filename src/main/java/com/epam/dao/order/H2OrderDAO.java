package com.epam.dao.order;

import com.epam.db.ConnectionPool;
import com.epam.model.Order;
import com.epam.model.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.RowSet;
import java.math.BigDecimal;
import java.sql.*;
import java.util.List;


public class H2OrderDAO implements OrderDAO {
    private static final Logger log = LoggerFactory.getLogger(H2OrderDAO.class);
    private Connection connection = null;

    public H2OrderDAO() {
    }

    // initialization
    public Connection setConnection(ConnectionPool connectionPool) {
        if (this.connection == null) {
            this.connection = connectionPool.takeConnection();
        }
        return this.connection;
    }

    public void closeConnection(ConnectionPool connectionPool) {
        if (this.connection != null) {
            connectionPool.releaseConnection(this.connection);
        }

    }

    @Override
    public long insertBlankOrder(Order order) {
        String SqlSeqID = "select seq_id.nextval from dual;";
        String SqlInsert2 = "insert into CLIENT_ORDER(id,number,user,insert_date,deleted)" +
                " values (?,?,?,?,0)";

        Connection cn = this.connection;
        //Connection cn=
        try {
            cn.setAutoCommit(false);
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }

        Statement st = null;
        try {
            st = cn.createStatement();
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }

        ResultSet rs = null;
        long id = -1;
        try {
            assert st != null;
            rs = st.executeQuery(SqlSeqID);
            if (rs.next()) {
                id = rs.getLong(1);
            }

        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }

        PreparedStatement st2 = null;
        try {
            st2 = cn.prepareStatement(SqlInsert2);
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }

        try {
            assert st2 != null;
            st2.setLong(1, id);
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }


        try {
            st2.setString(2, order.getNumber());
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }

        if (order.getClient() != null) {
            try {
                st2.setLong(3, order.getClient().getId());
            } catch (SQLException e) {
                log.debug("H2OrderDAO=" + e.getMessage());
            }
        } else {
            try {
                st2.setNull(3, Types.BIGINT);
            } catch (SQLException e) {
                log.debug("H2OrderDAO=" + e.getMessage());
            }
        }

        if (order.getInsertDate() == null) {
            order.setInsertDate(new Date((new java.util.Date()).getTime()));
        }
        try {
            st2.setDate(4, order.getInsertDate());
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }

        rs = null;
        try {
            int countRows = st2.executeUpdate();
            if (countRows == 0) {
                id = -1;
            }
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }

        try {
            cn.commit();
            return id;
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
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

    public String getOrderNumberById(long id) {
        String orderNumber = null;
        Connection cn = this.connection;
        String SqlSelect = "select * from CLIENT_ORDER where id=" + id;
        try {
            cn.setAutoCommit(false);
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }

        Statement st = null;
        try {
            st = cn.createStatement();
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }

        ResultSet rs = null;
        try {
            assert st != null;
            rs = st.executeQuery(SqlSelect);
            rs.next();
            orderNumber = rs.getString("NUMBER");
            return orderNumber;

        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }


        return orderNumber;
    }

    @Override
    public boolean updateOrder(Order order) {
        //String SqlSeqID = "select seq_id.nextval from dual;";
        if (order == null) {
            return true;
        }
        Connection cn = this.connection;
        String SqlUpdate1 = "update ORDER_DETAIL set deleted=1,quantity=0 where client_order=" + order.getId();
        try {
            cn.setAutoCommit(false);
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }
        PreparedStatement st1 = null;
        try {
            st1 = cn.prepareStatement(SqlUpdate1);
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }

        try {
            assert st1 != null;
            int countRows = st1.executeUpdate();
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }
        try {
            cn.commit();
            //return true;
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }


        if (order.getProducts().size() > 0) {
            for (Warehouse warehouse : order.getProducts()) {
                String SqlInsert2 = "insert into ORDER_DETAIL(client_order,quantity,product,sum,sum_paid,deleted)" +
                        " values (?,?,?,0,0,0)";

                try {
                    cn.setAutoCommit(false);
                } catch (SQLException e) {
                    log.debug("H2OrderDAO=" + e.getMessage());
                }
                PreparedStatement st2 = null;
                try {
                    st2 = cn.prepareStatement(SqlInsert2);
                } catch (SQLException e) {
                    log.debug("H2OrderDAO=" + e.getMessage());
                }

                try {
                    assert st2 != null;
                    st2.setLong(1, order.getId());
                } catch (SQLException e) {
                    log.debug("H2OrderDAO=" + e.getMessage());
                }


                try {
                    st2.setInt(2, warehouse.getQuantity());
                } catch (SQLException e) {
                    log.debug("H2OrderDAO=" + e.getMessage());
                }

                try {
                    st2.setLong(3, warehouse.getProduct().getId());
                } catch (SQLException e) {
                    log.debug("H2OrderDAO=" + e.getMessage());
                }


                try {
                    int countRows = st2.executeUpdate();
                    if (countRows == 0) {
                        cn.rollback();
                        return false;
                    }
                } catch (SQLException e) {
                    log.debug("H2OrderDAO=" + e.getMessage());
                }

            }

        }

        try {
            cn.commit();
            return true;
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }

        return false;
    }

    public boolean payOrder(Order order) {
        //String SqlSeqID = "select seq_id.nextval from dual;";
        if (order == null) {
            return true;
        }
        Connection cn = this.connection;
        String SqlSelect = "select sum(o.QUANTITY*p.PRICE) from product p\n" +
                "  join order_detail o\n" +
                "    on p.ID=o.PRODUCT and o.client_order=" + order.getId() +
                "   and o.QUANTITY>0 and nvl(o.deleted,0)!=1\n" +
                " where nvl(p.deleted,0)!=1; ";
        try {
            cn.setAutoCommit(false);
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }
        Statement st = null;
        try {
            st = cn.createStatement();
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }
        ResultSet rs = null;
        BigDecimal sumPaid = null;
        try {
            rs = st.executeQuery(SqlSelect);
            if (rs.next()) {
                sumPaid = rs.getBigDecimal(1);
            }
        } catch (SQLException e) {
            log.debug("H2OrderDAO=" + e.getMessage());
        }

        if (sumPaid.doubleValue() > 0) {
            order.setSumPaid(sumPaid);
            String SqlUpdate1 = "update CLIENT_ORDER set sum_paid=" + sumPaid +
                    " where id=" + order.getId();
            try {
                cn.setAutoCommit(false);
            } catch (SQLException e) {
                log.debug("H2OrderDAO=" + e.getMessage());
            }
            PreparedStatement st1 = null;
            try {
                st1 = cn.prepareStatement(SqlUpdate1);
            } catch (SQLException e) {
                log.debug("H2OrderDAO=" + e.getMessage());
            }

            try {
                assert st1 != null;
                int countRows = st1.executeUpdate();
            } catch (SQLException e) {
                log.debug("H2OrderDAO=" + e.getMessage());
            }
            try {
                cn.commit();
                return true;
            } catch (SQLException e) {
                log.debug("H2OrderDAO=" + e.getMessage());
            }
        }
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
