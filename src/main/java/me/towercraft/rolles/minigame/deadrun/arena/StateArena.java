package me.towercraft.rolles.minigame.deadrun.arena;

import lombok.Data;
import me.towercraft.rolles.minigame.deadrun.enumerate.GameState;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class StateArena {
    private GameState state = GameState.WAITING;
    private Integer counter = 0;
    private HashMap<Player, Boolean> players = new HashMap<>();

    public void addPlayer(Player player, Boolean value) {
        players.put(player, value);
    }

    public Boolean getSpector(Player player) {
        return players.get(player);
    }

    public void setSpectator(Player player, Boolean value) {
        players.put(player, value);
    }

    public List<Player> getNotSpectatorList() {
        Set<Player> keys = players.keySet();
        return keys.stream().filter(key -> players.get(key).equals(false)).collect(Collectors.toList());
    }

}
