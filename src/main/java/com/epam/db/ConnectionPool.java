package com.epam.db;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConnectionPool {
    public static final int POOL_SIZE = 10;
    private static final Logger log = LoggerFactory.getLogger(ConnectionPool.class);
    private BlockingQueue<Connection> connectionQueue;

    private ConnectionPool() {

        /*
//------------
        Locale locale=Locale.ENGLISH;
        Locale current = Locale.getDefault();
        Locale.setDefault(locale);
        ResourceBundle resource = ResourceBundle.getBundle("dbh2");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String pass = resource.getString("password");
        String driverName=resource.getString("driver");
        Locale.setDefault(current);
*/
//----------
        connectionQueue
                = new ArrayBlockingQueue<Connection>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {

            Connection connection = null;
            try {
                //connection = DriverManager.getConnection(url, user, pass);//ConnectorDb.getConnection();
                connection = ConnectorDb.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            assert connection != null;
            connectionQueue.offer(connection);

        }

    }

    public static ConnectionPool getInstance() {
        //ConnectionPoolHolder connectionPoolHolder = new ConnectionPoolHolder();
        log.debug("ConnectionPoolHolder.instance=" + ConnectionPoolHolder.instance);


        return ConnectionPoolHolder.instance;
    }

    public Connection takeConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            log.debug("e=" + e.toString());
        }
        return connection;
    }

    private void clearConnectionQueue() throws SQLException {
        Connection connection;
        while ((connection = connectionQueue.poll()) != null) {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
            connection.close();
        }
    }

    public void releaseConnection(Connection connection) {
        try {
            if (!connection.isClosed()) {
                if (!connectionQueue.offer(connection)) {
                    log.debug("releaseConnection!");
                }
            }
        } catch (SQLException e) {
            log.debug(e.toString());
        }
    }

    public void dispose() throws SQLException {
        this.clearConnectionQueue();
    }

    private static class ConnectionPoolHolder {
        private static ConnectionPool instance = new ConnectionPool();
    }


}
