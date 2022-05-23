package me.towercraft.rolles.minigame.deadrun;

import me.towercraft.rolles.minigame.deadrun.command.ArenaCommand;
import me.towercraft.rolles.minigame.deadrun.listener.ArenaListener;
import me.towercraft.rolles.minigame.deadrun.util.YMLConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeadRun extends JavaPlugin {
    public static DeadRun instance;

    @Override
    public void onEnable() {
        instance = this;
        new ArenaCommand();
        new YMLConfig();
        Bukkit.getPluginManager().registerEvents(new ArenaListener(this), this);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }

    public static DeadRun getInstance() {
        return instance;
    }
}
