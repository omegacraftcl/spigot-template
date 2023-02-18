package com.claudiobo.template;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.claudiobo.template.commands.TemplateCommand;
import com.claudiobo.template.events.TemplateEvents;
import com.claudiobo.template.hooks.PlaceholderHook;
import com.claudiobo.template.tasks.AutoUpdaterTask;
import com.claudiobo.template.utils.Lang;
import com.claudiobo.template.utils.MySQLConnection;
import com.claudiobo.template.utils.RedisConnection;
import lombok.Getter;

@Getter
public class Main extends JavaPlugin {

    public static Main instance;
    public MySQLConnection sqlConnection;
    public RedisConnection redisConnection;
    public PlaceholderHook placeholderHook;
    public AutoUpdaterTask autoUpdaterTask;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        reloadConfig();
        Lang.loadLang();
        getServer().getPluginManager().registerEvents(new TemplateEvents(this), this);
        getCommand("template").setExecutor(new TemplateCommand(this));
        registerAutoUpdater();
        if (!loadSQLDatabase()) {
            return;
        }
        if (!loadRedisDatabase()) {
            return;
        }
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            placeholderHook = new PlaceholderHook(this);
            placeholderHook.register();
        }
    }

    public void registerAutoUpdater() {
        autoUpdaterTask = new AutoUpdaterTask(this);
        autoUpdaterTask.runTaskTimerAsynchronously(this, 0L, 20L * 60L * 5L);
    }

    public boolean loadSQLDatabase() {
        String mysql_host = getConfig().getString("mysql.host");
        int mysql_port = getConfig().getInt("mysql.port");
        String mysql_user = getConfig().getString("mysql.user");
        String mysql_pass = getConfig().getString("mysql.pass");
        String mysql_database = getConfig().getString("mysql.database");

        sqlConnection = new MySQLConnection(this, mysql_host, mysql_port, mysql_database, mysql_user, mysql_pass);
        if (sqlConnection.connectDatabase() == null) {
            getLogger().severe("Disabling plugin because we couldn't connect to the SQL database");
            getServer().getPluginManager().disablePlugin(this);
            return false;
        }
        return true;
    }

    public boolean loadRedisDatabase() {
        String redis_host = getConfig().getString("redis.host");
        String redis_port = getConfig().getString("redis.port");
        String redis_pass = getConfig().getString("redis.pass");
        int redis_database_number = getConfig().getInt("redis.database_number");
        redisConnection = new RedisConnection(this, redis_host, redis_port, redis_pass, redis_database_number);
        if (redisConnection.connectDatabase() == null) {
            getLogger().severe("Disabling plugin because we couldn't connect to the Redis database");
            getServer().getPluginManager().disablePlugin(this);
            return false;
        }
        return true;
    }


    @Override
    public void onDisable() {
        return;
    }

    public static Main getInstance() {
        return instance;
    }
}
