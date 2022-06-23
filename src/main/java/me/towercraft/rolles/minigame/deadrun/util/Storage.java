package me.towercraft.rolles.minigame.deadrun.util;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Storage {
    public static HashMap<Player, Location> playersLocation = new HashMap<>();
    public static List<Player> spectators = new ArrayList<>();
}
