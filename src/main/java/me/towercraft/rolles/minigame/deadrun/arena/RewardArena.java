package me.towercraft.rolles.minigame.deadrun.arena;

import me.towercraft.rolles.minigame.deadrun.DeadRun;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class RewardArena {

    public void giveRewardLoserMoney() {
        if (DeadRun.spectator.getPlayers().size() > 3) {

        }
    }

    public void giveRewardWinnerMoney() {
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        try {
            Bukkit.dispatchCommand(console, "tclevel addrandommoney " + DeadRun.arena.getPlaces().get(1).getName() +
                    " " + DeadRun.config.getREWARD_FIST_MIN() + " " + DeadRun.config.getREWARD_FIST_MAX() + " no no");
            Bukkit.dispatchCommand(console, "tclevel addrandommoney " + DeadRun.arena.getPlaces().get(2).getName() +
                    " " + DeadRun.config.getREWARD_SECOND_MIN() + " " + DeadRun.config.getREWARD_SECOND_MAX() + " no no");
            Bukkit.dispatchCommand(console, "tclevel addrandommoney " + DeadRun.arena.getPlaces().get(3).getName() +
                    " " + DeadRun.config.getREWARD_THIRD_MIN() + " " + DeadRun.config.getREWARD_THIRD_MAX() + " no no");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
