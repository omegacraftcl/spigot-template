package com.claudiobo.template.utils;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.claudiobo.template.Main;

public class ConfigUtil {

    private Main main;
    private String configFile;

    public File fileConfig;
    public FileConfiguration config;

    public ConfigUtil(Main main, String configFile) {
        this.main = main;
        this.configFile = configFile;
        saveDefaultConfig();
        loadConfig();
    }

    private boolean saveDefaultConfig() {
        File fileConfig = new File(main.getDataFolder(), configFile + ".yml");
        if (!fileConfig.exists()) {
            try {
                main.saveResource(configFile, false);
            } catch (Exception e) {
                try {
                    fileConfig.createNewFile();
                } catch (Exception e1) {
                    e.printStackTrace();
                    e1.printStackTrace();
                }
            }
            return false;
        }
        return true;
    }

    public void loadConfig() {
        fileConfig = new File(main.getDataFolder(), configFile + ".yml");
        config = YamlConfiguration.loadConfiguration(fileConfig);
    }

    public void saveConfig() {
        try {
            config.save(fileConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void unloadConfig(){
        this.main = null;
        this.configFile = null;
        this.fileConfig = null;
        this.config = null;
    }

}
