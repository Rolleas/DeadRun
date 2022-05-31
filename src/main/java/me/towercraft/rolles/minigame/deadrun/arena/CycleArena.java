package me.towercraft.rolles.minigame.deadrun.arena;

import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.util.teleport.TeleportAbstract;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

@AllArgsConstructor
public class CycleArena implements Listener {

    private final StateArena arena;
    private final YMLConfig config;
    private final DeadRun plugin;
    private Location location;

    public void start () {
        String location = config.getARENA_SPAWN();
        for (Player player : arena.getPlayers()) {
            TeleportAbstract.go(player, location);
        }
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            Bukkit.getPluginManager().registerEvents(this, plugin);
        }, 20);
    }

    public Material calculateBlock(Player player) {
        location = player.getLocation();
        location.setY(location.getY()-1);
        return location.getBlock().getType();
    }


    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (config.getMaterials().contains(calculateBlock(player))) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                location.getBlock().setType(Material.AIR);
                Location blockLocation = block.getLocation();
                blockLocation.setY(blockLocation.getY()-1);
                player.getWorld().getBlockAt(blockLocation).setType(Material.AIR);
            }, 8);
        }
    }

    @EventHandler
    public void onMoveDeadZone(PlayerMoveEvent event) {
        if (event.getPlayer().getLocation().getY() <= config.getDEAD_ZONE()) {

        }
    }

    public void checkAFK() {

    }
}
