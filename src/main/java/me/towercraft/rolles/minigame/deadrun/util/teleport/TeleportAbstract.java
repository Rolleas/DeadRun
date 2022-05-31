package me.towercraft.rolles.minigame.deadrun.util.teleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public abstract class TeleportAbstract {

    public static void go(Player player, String location) {
        String[] configLocation = location.split(",");
        double x = Double.parseDouble(configLocation[0]);
        double y = Double.parseDouble(configLocation[1]);
        double z = Double.parseDouble(configLocation[2]);
        float pitch = Float.parseFloat(configLocation[3]);
        float yaw = Float.parseFloat(configLocation[4]);
        World world = Bukkit.getWorld("world");
        Location newLocation = new Location(world, x, y, z, pitch, yaw);
        player.teleport(newLocation);
    }
}
