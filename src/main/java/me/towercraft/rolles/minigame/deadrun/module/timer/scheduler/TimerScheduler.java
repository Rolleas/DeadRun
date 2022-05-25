package me.towercraft.rolles.minigame.deadrun.module.timer.scheduler;

import me.towercraft.rolles.minigame.deadrun.util.GameState;
import me.towercraft.rolles.minigame.deadrun.module.timer.Timer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerScheduler extends BukkitRunnable {
    private final GameState state;
    private final Timer timer;

    public TimerScheduler(GameState state, Timer timer) {
        this.state = state;
        this.timer = timer;
    }

    @Override
    public void run() {
        try {
            Bukkit.broadcastMessage(ChatColor.RED + "Timer Started");
            for (int counter = 10; counter > 0; counter--) {
                Bukkit.broadcastMessage(ChatColor.GREEN + "До игры осталось " + counter + " секунд");
                Thread.sleep(1000);
                if(!timer.getTimer()) {
                    Bukkit.broadcastMessage(ChatColor.RED + "Timer Stopped");
                    return;
                }
            }
            state.setGameState(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
