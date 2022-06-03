package me.towercraft.rolles.minigame.deadrun;

import me.towercraft.rolles.minigame.deadrun.arena.lobby.Lobby;
import me.towercraft.rolles.minigame.deadrun.arena.spectator.Ghost;
import me.towercraft.rolles.minigame.deadrun.command.DeadRunCommand;
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
    public Ghost ghostFactory;

    @Override
    public void onEnable() {

        config = new YMLConfig(this);
        arena = new StateArena();
        lobby = new Lobby(arena, config, this);
        this.ghostFactory = new Ghost(this);

        new DeadRunCommand(this, config);
        Bukkit.getPluginManager().registerEvents(new GameListener(), this);
        if (config.getARENA_COMPLETE()) {
            Bukkit.getPluginManager().registerEvents(new LobbyListener(config, arena, this, lobby), this);
        }
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public Ghost getGhost() {
        return this.ghostFactory;
    }
}
