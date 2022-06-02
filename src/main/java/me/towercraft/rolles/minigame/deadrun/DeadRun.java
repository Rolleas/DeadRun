package me.towercraft.rolles.minigame.deadrun;

import me.towercraft.rolles.minigame.deadrun.arena.lobby.Lobby;
import me.towercraft.rolles.minigame.deadrun.command.DeadRunCommand;
import me.towercraft.rolles.minigame.deadrun.listener.LobbyListener;
import me.towercraft.rolles.minigame.deadrun.arena.StateArena;
import me.towercraft.rolles.minigame.deadrun.listener.Game;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;


public final class DeadRun extends JavaPlugin {

    public static StateArena arena;
    public static YMLConfig config;
    public static Lobby lobby;

    @Override
    public void onEnable() {

        config = new YMLConfig(this);
        arena = new StateArena();
        lobby = new Lobby(arena, config, this);

        new DeadRunCommand(this, config);
        Bukkit.getPluginManager().registerEvents(new Game(), this);
        if (config.getARENA_COMPLETE()) {
            Bukkit.getPluginManager().registerEvents(new LobbyListener(config, arena, this, lobby), this);
        }
    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}
