package me.pepsipu.pepper.commands;

import me.pepsipu.pepper.PepperPlayer;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
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
            pepperPlayer.FrontBlast();
        } else {
            sender.sendMessage(ChatColor.RED + "[pepper] Only players may be infused!");
        }
        return false;
    }
}
