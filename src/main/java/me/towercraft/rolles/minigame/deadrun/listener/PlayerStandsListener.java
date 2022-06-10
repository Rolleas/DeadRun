package me.towercraft.rolles.minigame.deadrun.listener;

import me.towercraft.rolles.minigame.deadrun.event.BlockRemoveEvent;
import me.towercraft.rolles.minigame.deadrun.event.PlayerStandsEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerStandsListener implements Listener {

    @EventHandler
    public void onStands(PlayerStandsEvent event) {
        Bukkit.getPluginManager().callEvent(new BlockRemoveEvent(event.getPlayer()));
    }
}
