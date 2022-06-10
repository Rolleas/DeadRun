package me.towercraft.rolles.minigame.deadrun.arena;

import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.entity.PlayerLocation;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.stream.Collectors;

public class StandsCheckArena {

    private final HashMap<Player, PlayerLocation> playerLocationHashMap = new HashMap<>();

    public PlayerLocation convert(Location location) {
        PlayerLocation playerLocation = new PlayerLocation();
        playerLocation.setX(location.getX());
        playerLocation.setY(location.getY());
        playerLocation.setZ(location.getY());
        return playerLocation;
    }

    public void fillPlayers() {
        List<Player> players = DeadRun.spectator.getNotSpectatorList();
        players.forEach(player -> this.playerLocationHashMap.put(player, convert(player.getLocation())));
    }

    public List<Player> compare() {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        List<PlayerLocation> localLocation = new ArrayList<>();
        players.forEach(player -> localLocation.add(convert(player.getLocation())));
        return playerLocationHashMap.entrySet().stream().filter(player -> localLocation.contains(player.getValue())).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    public ArrayList<Block> getBlocksBelow(Player player) {
        ArrayList<Block> blocksBelow = new ArrayList<Block>();
        Location location = player.getLocation();
        double x = location.getX();
        double z = location.getZ();
        World world = player.getWorld();
        double yBelow = player.getLocation().getY() - 0.0001;
        Block northEast = new Location(world, x + 0.3, yBelow, z - 0.3).getBlock();
        Block northWest = new Location(world, x - 0.3, yBelow, z - 0.3).getBlock();
        Block southEast = new Location(world, x + 0.3, yBelow, z + 0.3).getBlock();
        Block southWest = new Location(world, x - 0.3, yBelow, z + 0.3).getBlock();
        Block[] blocks = {northEast, northWest, southEast, southWest};
        for (Block block : blocks) {
            if (!blocksBelow.isEmpty()) {
                boolean duplicateExists = false;
                for (int i = 0; i < blocksBelow.size(); i++) {
                    if (blocksBelow.get(i).equals(block)) {
                        duplicateExists = true;
                    }
                }
                if (!duplicateExists) {
                    blocksBelow.add(block);
                }
            } else {
                blocksBelow.add(block);
            }
        }
        return blocksBelow;
    }

    public void removeBlock(Block block) {
        block.setType(Material.AIR);
        block.getRelative(BlockFace.DOWN).setType(Material.AIR);
    }

    public void removeBlockAFK(Player player) {
        Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        if (DeadRun.config.getMaterials().contains(block.getType())) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(DeadRun.plugin, () -> {
                getBlocksBelow(player).forEach(this::removeBlock);
            }, 8);
        }
    }

    public void check() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(DeadRun.plugin, this::fillPlayers, 0, 8);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(DeadRun.plugin, () -> {
            List<Player> players = compare();
            System.out.println(players);
            if (players.size() > 0) {
                players.forEach(this::removeBlockAFK);
            }
        }, 0, 8);
    }
}
