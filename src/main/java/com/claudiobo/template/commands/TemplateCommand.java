package com.claudiobo.template.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.claudiobo.template.Main;
import com.claudiobo.template.utils.Utils;

public class TemplateCommand implements CommandExecutor {

    Main main;

    public TemplateCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§e/template");
            sender.sendMessage("§e/template author");
            sender.sendMessage("§e/template test <name>");
        } else {
            if (args[0].equalsIgnoreCase("author")) {
                sender.sendMessage(Utils.color("&6&l&m----&r &e[&r&lTemplate&e] &6&l&m----"));
                sender.sendMessage("");
                sender.sendMessage(Utils.color("&bPlugin hecho por Kledioz"));
                sender.sendMessage(Utils.color("&e&nwww.claudiobo.com"));
                sender.sendMessage("");
                sender.sendMessage(Utils.color("&6&l&m--------------------------"));
            } else if (args[0].equalsIgnoreCase("test")) {
                if (args.length != 2) {
                    sender.sendMessage("§c/template test <name>");
                    return false;
                }
                String name = args[1];
                sender.sendMessage("§eHola " + name);
            }
        }
        return false;
    }

}
