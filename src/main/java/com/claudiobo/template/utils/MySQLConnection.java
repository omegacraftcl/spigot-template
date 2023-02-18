package com.claudiobo.template.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.bukkit.plugin.java.JavaPlugin;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MySQLConnection {

    private JavaPlugin main;

    private String host;
    private int port;
    private String database;
    private String username;
    private String password;

    private Connection instance = null;

    public MySQLConnection(JavaPlugin main, String host, int port, String database, String username, String password) {
        this.main = main;
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public Connection connectDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.setProperty("user", getUsername());
            properties.setProperty("password", getPassword());
            properties.setProperty("autoReconnect", "true");
            properties.setProperty("useUnicode", "yes");
            instance = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, properties);
            main.getLogger().info("Connected to MySQL database");
        } catch (Exception e) {
            main.getLogger().severe("Couldn't connect to MySQL database:");
            e.printStackTrace();
        }
        return instance;
    }

    public Connection getConnection() {
        if (instance == null) {
            return connectDatabase();
        } else {
            try {
                if (instance.isClosed()) {
                    return connectDatabase();
                }
            } catch (SQLException e) {
                return connectDatabase();
            }
        }
        return instance;
    }

    public void closeConnection() {
        if (instance != null) {
            try {
                instance.close();
            } catch (SQLException e) {
            }
            instance = null;
        }
    }

    public boolean ping() {
        try (Statement st = getConnection().createStatement()) {
            st.execute("SELECT 1;");
            return true;
        } catch (Exception e) {
            main.getLogger().severe("Couldn't ping to the database: " + e.getMessage());
            return false;
        }
    }

}
