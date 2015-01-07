package com.epam.db;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class ConnectorDb {
    public static Connection getConnection() throws SQLException {
        Locale locale=Locale.ENGLISH;
        Locale current = Locale.getDefault();
        Locale.setDefault(locale);
        ResourceBundle resource = ResourceBundle.getBundle("h2config");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String pass = resource.getString("password");

        String driverName=resource.getString("driver");
        try {
            Class.forName (driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Locale.setDefault(current);
        return DriverManager.getConnection(url, user, pass);

    }
}
