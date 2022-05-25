package me.towercraft.rolles.minigame.deadrun;

import me.towercraft.rolles.minigame.deadrun.listener.BeforeGameListener;
import me.towercraft.rolles.minigame.deadrun.listener.GameCycleListener;
import me.towercraft.rolles.minigame.deadrun.util.GameState;
import me.towercraft.rolles.minigame.deadrun.module.timer.Timer;
import me.towercraft.rolles.minigame.deadrun.util.YMLConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;



public final class DeadRun extends JavaPlugin {
    public static DeadRun instance;
    public static GameState state;
    public static Timer timer;
    public static YMLConfig config;

    @Override
    public void onEnable() {
        //Load modules...
        config = new YMLConfig(this);
        this.getLogger().info("Config loaded");

        timer = new Timer(this);
        this.getLogger().info("Module Timer loaded");

        state = new GameState();
        this.getLogger().info("States loaded");


        Bukkit.getPluginManager().registerEvents(new BeforeGameListener(timer, config), this);
        Bukkit.getPluginManager().registerEvents(new GameCycleListener(config, this), this);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public static DeadRun getInstance() {
        return instance;
    }
}
