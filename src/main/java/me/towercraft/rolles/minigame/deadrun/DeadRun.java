package me.towercraft.rolles.minigame.deadrun;

import me.towercraft.rolles.minigame.deadrun.arena.lobby.Lobby;
import me.towercraft.rolles.minigame.deadrun.arena.spectator.Ghost;
import me.towercraft.rolles.minigame.deadrun.arena.spectator.Spectator;
import me.towercraft.rolles.minigame.deadrun.command.DeadRunCommand;
import me.towercraft.rolles.minigame.deadrun.listener.ArenaListener;
import me.towercraft.rolles.minigame.deadrun.listener.LobbyListener;
import me.towercraft.rolles.minigame.deadrun.arena.StateArena;
import me.towercraft.rolles.minigame.deadrun.listener.GameListener;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public final class DeadRun extends JavaPlugin {

    public static StateArena arena;
    public static YMLConfig config;
    public static Lobby lobby;
    public static Ghost ghostFactory;
    public static Spectator spectator;
    public static DeadRun plugin;

    @Override
    public void onEnable() {
        plugin = this;
        config = new YMLConfig(this);
        arena = new StateArena();
        lobby = new Lobby(arena, config, this);
        spectator = new Spectator();
        ghostFactory = new Ghost(this);
        new DeadRunCommand(this, config);
        Bukkit.getPluginManager().registerEvents(new GameListener(), this);
        if (config.getARENA_COMPLETE()) {
            Bukkit.getPluginManager().registerEvents(new LobbyListener(config, arena, this, lobby), this);
            Bukkit.getPluginManager().registerEvents(new ArenaListener(config, this), this);
        }
    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}
