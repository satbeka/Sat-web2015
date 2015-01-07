package com.epam.db;


import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
//import java.util.concurrent.PriorityBlockingQueue;


public class ConnectionPool {

    public static final int POOL_SIZE = 10;
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
            connectionQueue.offer(connection);

        }

    }


    private static class ConnectionPoolHolder {
        private static ConnectionPool instance = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        //ConnectionPoolHolder connectionPoolHolder = new ConnectionPoolHolder();
        System.out.println("ConnectionPoolHolder.instance=" + ConnectionPoolHolder.instance);


        return ConnectionPoolHolder.instance;
    }


    public Connection takeConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {/*TODO log*/
            System.out.println("e=" + e.toString());
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

                }
            }
        } catch (SQLException e) {

        }
    }


    public void dispose() throws SQLException {
        if (this != null) {
            this.clearConnectionQueue();
            //this.finalize();

        }
    }


}
