package me.towercraft.rolles.minigame.deadrun.notification;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.Collection;

public abstract class Sender {

    public static String customiseMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void broadcastMessage(String message) {
        Bukkit.broadcastMessage(customiseMessage(message));
    }

    public static void sendMessagePlayer(Player player, String message) {
        player.sendMessage(customiseMessage(message));
    }

    public static void sendTitlePlayer(Player player, String message) {
        player.sendTitle(ChatColor.GREEN + message, "");
    }

    public static void sendNoteStickPlayer(Player player) {
        player.playSound(player.getLocation(), Sound.NOTE_STICKS, 1, 1);
    }

    public static void messageForListPlayers(String message) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        for (Player player : players) {
            sendMessagePlayer(player, message);
        }

    }

    public static void titleForListPlayers(String message) {
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        for (Player player : players) {
            try {
                sendTitlePlayer(player, message);
                sendNoteStickPlayer(player);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
