package me.towercraft.rolles.minigame.deadrun.arena;

import lombok.Data;
import me.towercraft.rolles.minigame.deadrun.enumerate.GameState;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
public class StateArena {
    private GameState state = GameState.WAITING;
    private HashMap<Integer, Player> places = new HashMap<>();

    public void addPlayer(Player player, Integer place) {
        places.put(place, player);
    }
}





















