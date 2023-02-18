package com.claudiobo.template.utils;

import org.bukkit.plugin.java.JavaPlugin;
import lombok.Getter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisConnection {

    private JavaPlugin main;

    private String hostname;
    private String port;
    private String password;
    private int database_number;

    @Getter
    private JedisPool instance = null;

    public RedisConnection(JavaPlugin main, String hostname, String port, String password, int database_number) {
        this.main = main;
        this.hostname = hostname;
        this.port = port;
        this.password = password;
        this.database_number = database_number;
    }

    public JedisPool connectDatabase() {
        try {
            instance = new JedisPool(String.format("redis://:%s@%s:%s/%s?keepAlive=true&autoReconnect=true", password, hostname, port, database_number));
            main.getLogger().info("Connected to Redis database");
        } catch (Exception e) {
            main.getLogger().severe("Couldn't connect to Redis database:");
            e.printStackTrace();
        }
        return instance;
    }

    public boolean isConnected() {
        return instance != null && !instance.isClosed();
    }

    public Jedis getResource() {
        if (!isConnected()) {
            connectDatabase();
        }
        return instance.getResource();
    }

    public void closeConnection() {
        if (instance != null) {
            instance.close();
            instance = null;
        }
    }

}
