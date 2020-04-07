package me.pepsipu.pepper;

import me.pepsipu.pepper.PepperPlayer;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class Events implements Listener {
    private HashMap<String, PepperPlayer> pepperBoys;
    public Events(HashMap<String, PepperPlayer> pepperBoys) {
        this.pepperBoys = pepperBoys;
    }
    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Action action = e.getAction();
        if (action == Action.LEFT_CLICK_BLOCK || action == Action.LEFT_CLICK_AIR) {
            if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "pepper")) {
                Player player = e.getPlayer();
                e.setCancelled(true);
                if (this.pepperBoys.containsKey(player.getDisplayName())) {
                    PepperPlayer pepperPlayer = this.pepperBoys.get(player.getDisplayName());
                    pepperPlayer.Explosion();
                }
            }
        } else if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
            if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.RED + "pepper")) {
                Player player = e.getPlayer();
                e.setCancelled(true);
                if (this.pepperBoys.containsKey(player.getDisplayName())) {
                    PepperPlayer pepperPlayer = this.pepperBoys.get(player.getDisplayName());
                    pepperPlayer.FrontBlast();
                }
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByBlockEvent e) {
        if (e.getEntity() instanceof Player) {
            Player player = (Player) e.getEntity();
            if (this.pepperBoys.containsKey(player.getDisplayName()) && e.getCause() == EntityDamageEvent.DamageCause.BLOCK_EXPLOSION) {
                e.setDamage(0);
            }
        }
    }
}
