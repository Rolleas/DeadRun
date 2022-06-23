package me.towercraft.rolles.minigame.deadrun.handler;

import me.towercraft.rolles.minigame.deadrun.Deadrun;
import org.bukkit.Bukkit;

public final class ArenaHandler {

    public static void removeBlocks() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(Deadrun.plugin, () -> {
        }, 8);
    }

    public static void afkCheck() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(Deadrun.plugin, () -> {
        },0, 10);
    }
}
