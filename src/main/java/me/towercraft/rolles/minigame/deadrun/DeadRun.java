package me.towercraft.rolles.minigame.deadrun;

import me.towercraft.rolles.minigame.deadrun.command.ArenaCommand;
import me.towercraft.rolles.minigame.deadrun.util.ArenaYMLConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class DeadRun extends JavaPlugin {
    public static DeadRun instance;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        instance = this;
        new ArenaCommand();
        getConfig().set("arena.arena1", 123);
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
    }

    public static DeadRun getInstance() {
        return instance;
    }
}
