package me.towercraft.rolles.minigame.deadrun.listener;

import me.towercraft.rolles.minigame.deadrun.handler.ArenaHandler;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.ArrayList;

public class GameStart implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

    }

    public ArrayList<Block> getBlocksBelow(Player player) {
        ArrayList<Block> blocksBelow = new ArrayList<>();
        Location location = player.getLocation();
        double x = location.getX();
        double z = location.getZ();
        World world = player.getWorld();
        double yBelow = player.getLocation().getBlock().getRelative(BlockFace.DOWN).getY();
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
        if (block.getType() != Material.AIR) {
            block.getRelative(BlockFace.DOWN).setType(Material.AIR);
            block.setType(Material.AIR);
        }
    }


    @EventHandler
    public void onRun(PlayerMoveEvent event) {
        ArenaHandler.removeBlocks();
    }
}
