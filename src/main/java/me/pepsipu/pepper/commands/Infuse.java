package me.pepsipu.pepper.commands;

import me.pepsipu.pepper.PepperPlayer;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;


public class Infuse implements CommandExecutor {
    private HashMap<String, PepperPlayer> pepperBoys;

    public Infuse(HashMap<String, PepperPlayer> pepperBoys, Logger logger) {
        this.pepperBoys = pepperBoys;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            World world = player.getWorld();
            Location particle_pos = player.getLocation();
            world.spawnParticle(Particle.FLAME, particle_pos, 200);
            PepperPlayer pepperPlayer = new PepperPlayer(player.getName());
            this.pepperBoys.put(player.getName(), pepperPlayer);
            ItemStack pepper = new ItemStack(Material.SUNFLOWER, 1);
            ItemMeta pepperMeta = (ItemMeta) pepper.getItemMeta();
            List<String> lore = new ArrayList<String>();
            lore.add(ChatColor.GOLD + "A really spicy pepper.");
            pepperMeta.setDisplayName(ChatColor.RED + "pepper");
            pepperMeta.setLore(lore);
            pepper.setItemMeta(pepperMeta);
            player.getInventory().addItem(pepper);
        } else {
            sender.sendMessage(ChatColor.RED + "[pepper] Only players may be infused!");
        }
        return false;
    }
}
