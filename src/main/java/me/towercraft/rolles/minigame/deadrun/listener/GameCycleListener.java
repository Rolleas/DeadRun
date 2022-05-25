package me.towercraft.rolles.minigame.deadrun.listener;

import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.util.YMLConfig;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

@AllArgsConstructor
public class GameCycleListener implements Listener {
    private final YMLConfig config;
    private final DeadRun instance;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent playerMoveEvent) {
        if (playerMoveEvent.getPlayer().getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.matchMaterial(config.getMaterialBlockRemove())) {
            System.out.println("Block Remove");
            Bukkit.getScheduler().scheduleSyncDelayedTask(instance, () -> {
                playerMoveEvent.getPlayer().getLocation().getBlock().setType(Material.AIR);
            }, 1);
        }
    }
}
