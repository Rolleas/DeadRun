package me.towercraft.rolles.minigame.deadrun.arena.spectator;

import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.arena.StateArena;
import me.towercraft.rolles.minigame.deadrun.util.teleport.Teleport;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

import java.util.List;

public abstract class Spectator {

    private static void hidePlayer(Player player, StateArena arena) {
        List<Player> players = arena.getNotSpectatorList();
        players.forEach(element -> element.hidePlayer(player));
    }


    public static void set(DeadRun plugin, StateArena arena, Player player, String location) {
        hidePlayer(player, arena);
        Teleport.go(player, location);
        player.setAllowFlight(true);
        plugin.getGhost().setGhost(player, true);
    }
}
