package com.claudiobo.template.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.claudiobo.prisonexec.Main;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MySQLConnection {

    private Main main;

    private String host;
    private String port;
    private String database;
    private String username;
    private String password;
    private boolean useSSL;

    private Connection connection;

    public MySQLConnection(Main main, String host, String port, String database, String username, String password,
            boolean useSSL) {
        this.main = main;
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
        this.useSSL = useSSL;
    }

    public void loadDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Properties properties = new Properties();
            properties.setProperty("user", getUsername());
            properties.setProperty("password", getPassword());
            properties.setProperty("autoReconnect", "true");
            properties.setProperty("useUnicode", "yes");
            properties.setProperty("verifyServerCertificate", "false");
            properties.setProperty("useSSL", String.valueOf(useSSL));
            properties.setProperty("requireSSL", "false");
            setConnection(DriverManager
                    .getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, properties));
            main.getLogger().info("Connected to database");
        } catch (Exception e) {
            main.getLogger().severe("Couldn't connect to database:");
            e.printStackTrace();
        }
    }

    public void update(String qry) {
        try {
            connection.createStatement().executeUpdate(qry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        try {
            stmt.executeQuery(query);
            return stmt.getResultSet();
        } catch (Exception e) {
            main.getLogger().severe("Database query error:");
            e.printStackTrace();
            return null;
        }
    }

}
