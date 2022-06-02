package me.towercraft.rolles.minigame.deadrun.arena;

import lombok.Data;
import me.towercraft.rolles.minigame.deadrun.enumerate.GameState;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

@Data
public class StateArena {
    private GameState state = GameState.WAITING;
    private Integer counter = 0;
}
