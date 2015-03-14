package com.grim.spnhelp;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Menu {

    private Inventory inventory;
    private FileConfiguration config;
    
    public Menu(Main plugin){
        config = plugin.getConfig();
        inventory = Bukkit.getServer().createInventory(null, 9, colourCode("inventoryname"));
        for(int i = 0; i < 9; i++) {
            if(config.getString("SLOT" + i + ".name") != null) {
                int id = config.getInt("SLOT" + i + ".id");
                String name = colourCode("SLOT" + i + ".name");
                String lore = colourCode("SLOT" + i + ".lore");
                inventory.setItem(i, item(id, name, lore));
            }
        }
    }
    
    private ItemStack item(int id, String name, String lore){
        ItemStack i = new ItemStack(id, 1);
        ItemMeta im = i.getItemMeta();
        im.setDisplayName(name);
        im.setLore(Arrays.asList(lore));
        i.setItemMeta(im);
        return i;
    }

    public String colourCode(String path){
        String string = config.getString(path);
        return ChatColor.translateAlternateColorCodes('&', string);
    }
    
    public Inventory getInventory() {
        return inventory;
    }
}
