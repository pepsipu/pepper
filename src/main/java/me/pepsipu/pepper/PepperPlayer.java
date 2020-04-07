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
            MakeExplosion(player, pos, 8, 2);
        }
    }

    public void Explosion() {
        Player player = Bukkit.getPlayer(this.playerName);
        Location pos = player.getEyeLocation();
        MakeExplosion(player, pos, .5, 4);
    }

    private void MakeExplosion(Player player, Location pos, double offset, float power) {
        double yaw = Math.toRadians(pos.getYaw() + 90);
        double pitch = Math.toRadians(pos.getPitch());
        pos.setX(Math.cos(yaw) * offset + pos.getX());
        pos.setZ(Math.sin(yaw) * offset + pos.getZ());
        pos.setY(-Math.sin(pitch) * offset + pos.getY());
        player.getWorld().createExplosion(pos, power);
    }
}
