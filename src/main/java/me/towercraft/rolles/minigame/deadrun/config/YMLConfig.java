package me.towercraft.rolles.minigame.deadrun.config;

import lombok.Data;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

@Data
public class YMLConfig {

    private DeadRun plugin;
    private String PLAYER_JOIN_MESSAGE;
    private Integer MAX_PLAYERS;
    private String ARENA_NAME;
    private Boolean ARENA_COMPLETE;
    private String ARENA_SPAWN;
    private String START_MESSAGE;
    private String PLAYER_LEAVE_MESSAGE;
    private int LOOSE_ZONE;
    private String PLAYER_LOOSE_MESSAGE;
    private String PLAYER_WIN_MESSAGE;
    private String PLAYER_WIN_TITLE;
    private List<Material> materials = new ArrayList<>();
    private Integer REWARD_FIST_MIN;
    private Integer REWARD_FIST_MAX;
    private Integer REWARD_SECOND_MIN;
    private Integer REWARD_SECOND_MAX;
    private Integer REWARD_THIRD_MIN;
    private Integer REWARD_THIRD_MAX;
    private String WIN_PLACES;
    private FileConfiguration config;

    public YMLConfig(DeadRun plugin) {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdirs();
            plugin.saveDefaultConfig();
        }
        config = plugin.getConfig();
        this.plugin = plugin;
        load();
    }

    public void set(String path, String value) {
        config.set(path, value);
        plugin.saveConfig();
    }

    public void set(String path, Boolean value) {
        config.set(path, value);
        plugin.saveConfig();
    }

    public void set(String path, Integer value) {
        config.set(path, value);
        plugin.saveConfig();
    }

    public void transformationMaterial() {
        String[] configMaterial = config.getString("arena.list-remove-block").split(",");
        for (String material : configMaterial) {
            materials.add(Material.getMaterial(material));
        }
    }

    public void load() {
        this.PLAYER_JOIN_MESSAGE = config.getString("messages.join-player");
        this.MAX_PLAYERS = config.getInt("arena.max-players");
        this.ARENA_NAME = config.getString("arena.name");
        this.ARENA_COMPLETE = config.getBoolean("arena.complete");
        this.ARENA_SPAWN = config.getString("arena.arena-spawn");
        this.START_MESSAGE = config.getString("messages.start");
        this.PLAYER_LEAVE_MESSAGE = config.getString("messages.leave-player");
        this.transformationMaterial();
        this.LOOSE_ZONE = config.getInt("arena.loose-zone");
        this.PLAYER_LOOSE_MESSAGE = config.getString("messages.loose");
        this.PLAYER_WIN_MESSAGE = config.getString("messages.win");
        this.PLAYER_WIN_TITLE = config.getString("messages.win-for-player");
        this.REWARD_FIST_MIN = config.getInt("arena.reward.money.first-place.min");
        this.REWARD_FIST_MAX = config.getInt("arena.reward.money.first-place.max");
        this.REWARD_SECOND_MIN = config.getInt("arena.reward.money.second-place.min");
        this.REWARD_SECOND_MAX = config.getInt("arena.reward.money.second-place.max");
        this.REWARD_THIRD_MIN = config.getInt("arena.reward.money.third-place.min");
        this.REWARD_THIRD_MAX = config.getInt("arena.reward.money.third-place.max");
        this.WIN_PLACES = config.getString("messages.win-places");
    }
}
