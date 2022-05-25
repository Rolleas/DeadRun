package me.towercraft.rolles.minigame.deadrun.util;

import lombok.Data;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

@Data
public class YMLConfig {
    //Config
    private int maxPlayers;
    private Location lobbySpawn;
    private String materialBlockRemove;

    //Massage
    private String playerJoinMassage;

    //Local
    private FileConfiguration config;

    public YMLConfig(DeadRun instance) {
        if (!instance.getDataFolder().exists()) {
            instance.getDataFolder().mkdirs();
            instance.saveDefaultConfig();
        }
        config = instance.getConfig();
        load();
    }

    public void load() {
        //Config
        this.maxPlayers = config.getInt("deadrun.max-players");
        this.lobbySpawn = (Location) config.get("deadrun.lobby-spawn");
        this.materialBlockRemove = config.getString("deadrun.material-block-remove");

        //Massage
        this.playerJoinMassage = config.getString("massage.join-player");
    }

}
