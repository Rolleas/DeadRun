package me.towercraft.rolles.minigame.deadrun.util.timer;

import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import me.towercraft.rolles.minigame.deadrun.util.notification.SenderAbstract;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.util.List;

@AllArgsConstructor
public class Timer {
    private DeadRun plugin;
    private YMLConfig config;

    private void sendMessage(int counter, String massageStart) {
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', massageStart.replace("<time>", String.valueOf(counter))));
    }

    public void start(List<Player> players) {
        String massageStart = config.getSTART_MESSAGE();
        Bukkit.getScheduler().scheduleAsyncDelayedTask(plugin, () -> {
            for (int counter = 10; counter > 0; counter--) {
                if (counter == 10) {
                    sendMessage(counter, massageStart);
                }
                if (counter <= 5) {
                    sendMessage(counter, massageStart);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
