package me.towercraft.rolles.minigame.deadrun.arena;

import lombok.Data;
import me.towercraft.rolles.minigame.deadrun.enumerate.GameState;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

@Data
public class StateArena {
    private GameState state = GameState.WAITING;
    private HashMap<Player, Location> players = new HashMap<>();

    public void addPlayer(Player player) {
        players.put(player, player.getLocation());
    }

    public int getNumbersOfPlayers() {
        return players.size();
    }
}
