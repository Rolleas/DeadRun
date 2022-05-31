package me.towercraft.rolles.minigame.deadrun;

import me.towercraft.rolles.minigame.deadrun.command.DeadRunCommand;
import me.towercraft.rolles.minigame.deadrun.listener.Lobby;
import me.towercraft.rolles.minigame.deadrun.arena.StateArena;
import me.towercraft.rolles.minigame.deadrun.listener.Game;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;



public final class DeadRun extends JavaPlugin {

    public static StateArena arena;
    public static YMLConfig config;

    @Override
    public void onEnable() {

        config = new YMLConfig(this);
        arena = new StateArena();
        new DeadRunCommand(this, config);
        Bukkit.getPluginManager().registerEvents(new Game(), this);
        if (config.getARENA_COMPLETE()) {
            Bukkit.getPluginManager().registerEvents(new Lobby(config, arena, this), this);
        }
    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}
