package com.claudiobo.template;

import org.bukkit.plugin.java.JavaPlugin;

import com.claudiobo.template.commands.TemplateCommand;
import com.claudiobo.template.events.TemplateEvents;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        getServer().getPluginManager().registerEvents(new TemplateEvents(this), this);
        getCommand("template").setExecutor(new TemplateCommand(this));
    }

    @Override
    public void onDisable() {
        return;
    }
}