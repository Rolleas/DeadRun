package me.towercraft.rolles.minigame.deadrun.arena.spectator;

import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.arena.StateArena;
import me.towercraft.rolles.minigame.deadrun.util.teleport.Teleport;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class Spectator {

    private static void hidePlayer(Player player, StateArena arena) {
        List<Player> players = arena.getNotSpectatorList();
        players.forEach(element -> element.hidePlayer(player));
    }

    private static void showForSpector(Player player, StateArena arena) {
        List<Player> players = arena.getSpectorList();
        players.forEach(player::showPlayer);
    }

    public static void set(DeadRun plugin, StateArena arena, Player player, String location) {
        arena.setSpectator(player, true);
        hidePlayer(player, arena);
        showForSpector(player, arena);
        Teleport.go(player, location);
        player.setAllowFlight(true);
        player.setFlying(true);
        plugin.getGhost().setGhost(player, true);
    }
}
