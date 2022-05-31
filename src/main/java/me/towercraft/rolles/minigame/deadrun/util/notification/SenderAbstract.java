package me.towercraft.rolles.minigame.deadrun.util.notification;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public abstract class SenderAbstract {

    public String customiseMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public void broadcastMessage(String message) {
        Bukkit.broadcastMessage(customiseMessage(message));
    }

    public void sendMessagePlayer(Player player, String message) {
        player.sendMessage(customiseMessage(message));
    }

    public void sendTitlePlayer(Player player, String message) {
        player.sendTitle(ChatColor.GREEN + message, "");
    }

    public void sendNoteStickPlayer(Player player) {
        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1, 1);
    }

    public void messageForListPlayers(HashMap<Player, Location> players, String message) {
        for (Map.Entry<Player, Location> pair : players.entrySet()) {
            Player player = (Player) pair.getKey();
            sendMessagePlayer(player, message);
        }
    }

    public void titleForListPlayers(HashMap<Player, Location> players, String message) {
        for (Map.Entry<Player, Location> pair : players.entrySet()) {
            try {
                Player player = pair.getKey();
                sendTitlePlayer(player, message);
                sendNoteStickPlayer(player);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
