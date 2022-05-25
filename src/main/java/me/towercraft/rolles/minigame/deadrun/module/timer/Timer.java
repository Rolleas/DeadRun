package me.towercraft.rolles.minigame.deadrun.module.timer;

import lombok.AllArgsConstructor;
import lombok.Data;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.module.timer.scheduler.TimerScheduler;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

@Data
@AllArgsConstructor
public class Timer {
    private final DeadRun instance;
    private Boolean timer = false;

    public Timer(DeadRun instance) {
        this.instance = instance;
    }
    public void startTimer() {
        timer = true;
        System.out.println(Bukkit.getScheduler().getActiveWorkers());
        BukkitTask task = new TimerScheduler(DeadRun.state, this).runTaskAsynchronously(instance);
        System.out.println(Bukkit.getScheduler().getActiveWorkers());
    }

    public void stopTimer() {
        timer = false;
    }
}
