package me.towercraft.rolles.minigame.deadrun.util;

import lombok.RequiredArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class YMLConfig {
    private DeadRun instance;

    public YMLConfig(DeadRun instance) {
        this.instance = instance;
        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdirs();
            instance.saveDefaultConfig();
        }
    }
}
