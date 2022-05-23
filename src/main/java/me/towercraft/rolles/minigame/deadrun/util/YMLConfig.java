package me.towercraft.rolles.minigame.deadrun.util;

import lombok.RequiredArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class YMLConfig {
    private final DeadRun instance = DeadRun.getInstance();

    public YMLConfig() {
        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdirs();
            instance.saveConfig();
            this.setDefaultConfig();
        }
    }

    public void setDefaultConfig() {
       FileConfiguration config = instance.getConfig();
       config.set("name", "DeadRun");
       instance.saveConfig();
    }
}
