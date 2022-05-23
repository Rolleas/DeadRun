package me.towercraft.rolles.minigame.deadrun.listener;

import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

@AllArgsConstructor
public class ArenaListener implements Listener {
    private DeadRun deadRun;

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == Material.DIAMOND_BLOCK) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(deadRun, () -> {
                player.getLocation().getBlock().getRelative(BlockFace.DOWN).setType(Material.AIR);
            }, 3);
        }
    }
}
