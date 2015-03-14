package com.grim.spnhelp;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class InventoryListener implements Listener {
    
    private Main plugin;
    private FileConfiguration config;

    public InventoryListener(Main plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }
    
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getName().equalsIgnoreCase(plugin.getMenu().getName())){
            event.setCancelled(true);
            int i = event.getSlot();
            Player player = (Player) event.getWhoClicked();
            String server = config.getString("SLOT" + i + ".server");
            player.closeInventory();
            if(server == null) return;
            ByteArrayDataOutput out = ByteStreams.newDataOutput();

            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
        }  
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
        if(event.getMessage().equalsIgnoreCase("/server") || event.getMessage().equalsIgnoreCase("/servers")) {
            event.setCancelled(true);
            Player player = event.getPlayer();
            player.openInventory(plugin.getMenu());
        }
    }
}
