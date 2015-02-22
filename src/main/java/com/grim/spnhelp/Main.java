package com.grim.spnhelp;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class Main extends JavaPlugin {

    private Main plugin = null;
    private List<String> helpList = null; 
    
    @Override
    public void onDisable() {
        this.plugin = null;
    }

    @Override
    public void onEnable() {
        this.plugin = this;
        this.saveDefaultConfig();
        initHelp();
        this.getCommand("help").setExecutor(new CommandHelp(plugin));
    }

    public void initHelp(){
        this.helpList = plugin.getConfig().getStringList("help");
    }

    public List<String> getHelpList() {
        return helpList;
    }
}
