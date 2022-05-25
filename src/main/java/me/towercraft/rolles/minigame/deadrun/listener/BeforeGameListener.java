package me.towercraft.rolles.minigame.deadrun.listener;

import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.util.GameState;
import me.towercraft.rolles.minigame.deadrun.module.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

@AllArgsConstructor
public class BeforeGameListener implements Listener {
    private final GameState state = DeadRun.state;
    private final DeadRun instance;
    private final Timer timer;

    @EventHandler
    public void onJoin(PlayerJoinEvent playerJoinEvent) {
        if (!state.getGameState()) {
            state.setPlayers(state.getPlayers() + 1);
            Bukkit.broadcastMessage(String.format(ChatColor.AQUA + "(%1s /%2s)" + ChatColor.GOLD + "Игрок %3s присоединился",
                    state.getPlayers(), instance.getConfig().get("deadrun.max-players"), playerJoinEvent.getPlayer().getName()));
        } else { playerJoinEvent.getPlayer().kickPlayer("");}
        if (state.getPlayers() == instance.getConfig().getInt("deadrun.max-players") && !state.getGameState()) timer.startTimer();
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent playerQuitEvent) {
        if (!state.getGameState()) {
            state.setPlayers(state.getPlayers() - 1);
            Bukkit.broadcastMessage(String.format(ChatColor.AQUA + "(%1s /%2s)" + ChatColor.GOLD + "Игрок %3s покину игру",
                    state.getPlayers(), instance.getConfig().get("deadrun.max-players"), playerQuitEvent.getPlayer().getName()));
            if (!state.getGameState()) timer.stopTimer();
        }
    }
}
