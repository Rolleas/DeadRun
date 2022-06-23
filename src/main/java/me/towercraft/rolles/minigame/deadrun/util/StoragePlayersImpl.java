package me.towercraft.rolles.minigame.deadrun.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class StoragePlayersImpl {

    public StoragePlayersImpl() {
        Bukkit.getOnlinePlayers().forEach(this::addValue);
    }

    public void addValue(Player player) {
        Storage.playersLocation.put(player, player.getLocation());
    }

    public List<Player> getValue() {
        return new ArrayList<>(Storage.playersLocation.keySet());
    }
}
