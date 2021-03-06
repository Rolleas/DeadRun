package me.towercraft.rolles.minigame.deadrun.util.teleport;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Teleport {

    public static Location locationFormation(String location) {
        World world = Bukkit.getWorlds().get(0);
        List<Double> coords = Arrays.stream(location.split(",")).map(Double::parseDouble).collect(Collectors.toList());
        return new Location(world, coords.get(0), coords.get(1), coords.get(2), 100 ,100);
    }

    public static void go(Player player, String location) {
        player.teleport(locationFormation(location));
    }

    public static void TeleportAllPlayers(String location) {
        World world = Bukkit.getWorlds().get(0);
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        players.forEach(player -> player.teleport(locationFormation(location)));
    }

}
