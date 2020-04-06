package me.pepsipu.pepper;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class PepperPlayer {
    private String playerName;
    public PepperPlayer(String player) {
        this.playerName = player;
    }

    public void FrontBlast() {
        Player player = Bukkit.getPlayer(this.playerName);
        assert player != null;
        Location pos = player.getEyeLocation();
        for (int i = 0; i < 5; i++) {
            double yaw = Math.toRadians(pos.getYaw() + 90);
            double pitch = Math.toRadians(pos.getPitch());
            pos.setX(Math.cos(yaw) * 8 + pos.getX());
            pos.setZ(Math.sin(yaw) * 8 + pos.getZ());
            pos.setY(-Math.sin(pitch) * 8 + pos.getY());
            player.getWorld().createExplosion(pos, 5);
        }
    }
}
