package me.towercraft.rolles.minigame.deadrun.afk;

import me.towercraft.rolles.minigame.deadrun.util.StoragePlayersImpl;
import org.bukkit.Bukkit;

public final class AFKCheck {

    public AFKCheck() {
        StoragePlayersImpl storagePlayers = new StoragePlayersImpl();
        Bukkit.getOnlinePlayers().forEach(storagePlayers::addValue);
    }

}
