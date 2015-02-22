package com.grim.spnhelp;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class CommandHelp implements CommandExecutor {

    Main plugin;
    
    public CommandHelp(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(command.getName().equalsIgnoreCase("help")) {
            if (strings.length == 1) {
                if (strings[0].equalsIgnoreCase("reload")) {
                    if (commandSender.hasPermission("spnhelp.reload"))
                        plugin.reloadConfig();
                        plugin.initHelp();
                        return true;
                }
            }
            List<String> helpList = plugin.getHelpList();
            for (String help : helpList) {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', help));
            }
        }
        return false;
    }
        
}
