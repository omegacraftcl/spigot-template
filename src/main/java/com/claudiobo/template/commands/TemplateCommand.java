package com.claudiobo.template.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.claudiobo.template.Main;

public class TemplateCommand implements CommandExecutor {

    Main main;

    public TemplateCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§e/template");
            sender.sendMessage("§e/template test <name>");
        } else {
            if (args[0].equalsIgnoreCase("test")) {
                if (args.length != 3) {
                    sender.sendMessage("§c/template test <name>");
                    return false;
                }
                String name = args[3];
                sender.sendMessage("§eHola " + name);
            }
        }
        return false;
    }

}