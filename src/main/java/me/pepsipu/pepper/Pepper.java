package me.pepsipu.pepper;

import me.pepsipu.pepper.commands.Infuse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;
import java.util.logging.Logger;

public final class Pepper extends JavaPlugin {

    private Logger logger = getLogger();
    private HashMap<String, PepperPlayer> pepperBoys = new HashMap<String, PepperPlayer>();

    @Override
    public void onEnable() {
        this.logger.info(ChatColor.GOLD + "pepper has started!");
        Objects.requireNonNull(getCommand("infuse")).setExecutor(new Infuse(pepperBoys, logger));
        getServer().getPluginManager().registerEvents(new Events(this.pepperBoys), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            private double i = 0;
            @Override
            public void run() {
                for (HashMap.Entry<String, PepperPlayer> username: pepperBoys.entrySet()) {
                    Player player = Bukkit.getPlayer(username.getKey());
                    if (player == null) {
                        pepperBoys.remove(username.getKey());
                    }
                    Location loc = player.getLocation();
                    Location anti_loc = loc.clone();
                    double cos_i = Math.cos(i);
                    double sin_i = Math.sin(i);
                    loc.setX(cos_i + loc.getX());
                    loc.setZ(sin_i + loc.getZ());
                    anti_loc.setX(-cos_i + anti_loc.getX());
                    anti_loc.setZ(-sin_i + anti_loc.getZ());
                    i = i + Math.PI/16;
                    if (i == 2 * Math.PI) {
                        i = 0;
                    }
                    player.getWorld().spawnParticle(Particle.FLAME, loc, 0, 0, 0, 0);
                    player.getWorld().spawnParticle(Particle.FLAME, anti_loc, 0, 0, 0, 0);
                }
            }
        }, 0, 1);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        this.logger.info(ChatColor.GOLD + "pepper is shutting down. Cya!");
    }
}
