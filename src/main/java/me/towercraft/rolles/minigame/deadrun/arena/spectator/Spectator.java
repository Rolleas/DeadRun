package me.towercraft.rolles.minigame.deadrun.arena.spectator;

import me.towercraft.rolles.minigame.deadrun.util.teleport.Teleport;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;

public abstract class Spectator {

    public static void set(Player player, String location) {
        Teleport.go(player, location);
        player.setFlying(true);
        player.addPotionEffect(new PotionEffect(PotionType.INVISIBILITY.getEffectType(), -1, 1, true ));
    }
}
