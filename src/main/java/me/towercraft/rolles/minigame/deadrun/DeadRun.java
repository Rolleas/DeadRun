package me.towercraft.rolles.minigame.deadrun;

import me.towercraft.rolles.minigame.deadrun.listener.BeforeGameListener;
import me.towercraft.rolles.minigame.deadrun.util.GameState;
import me.towercraft.rolles.minigame.deadrun.module.timer.Timer;
import me.towercraft.rolles.minigame.deadrun.util.YMLConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;



public final class DeadRun extends JavaPlugin {
    public static DeadRun instance;
    public static final GameState state = new GameState();
    public static Timer timer;

    @Override
    public void onEnable() {
        new YMLConfig(this);
        timer = new Timer(this);
        Bukkit.getPluginManager().registerEvents(new BeforeGameListener(this, timer), this);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public static DeadRun getInstance() {
        return instance;
    }
}
