package me.towercraft.rolles.minigame.deadrun.util;

import org.bukkit.entity.Player;

import java.util.List;

public class StorageSpectatorsImpl {

    public void addValue(Player player) {
        Storage.spectators.add(player);
    }

    public List<Player> getValue() {
        return Storage.spectators;
    }
}
