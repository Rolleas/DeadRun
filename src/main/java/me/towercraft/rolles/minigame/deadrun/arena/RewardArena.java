package me.towercraft.rolles.minigame.deadrun.arena;

import me.towercraft.rolles.minigame.deadrun.DeadRun;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RewardArena {

    public void giveRewardLoserMoney() {
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        if (DeadRun.spectator.getPlayers().size() > 3) {
            DeadRun.spectator.getPlayers().entrySet().stream().filter(player -> !DeadRun.arena.getPlaces().containsValue(player.getKey())).collect(Collectors.toList())
                    .stream().map(Map.Entry::getKey).collect(Collectors.toList()).forEach(player -> Bukkit.dispatchCommand(console, "tclevel addrandommoney " + player.getName() +
                            " " + DeadRun.config.getREWARD_LOSER_MIN() + " " + DeadRun.config.getREWARD_LOSER_MAX() + " no no"));
        }
    }

    public void giveRewardLoserExp() {
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        if (DeadRun.spectator.getPlayers().size() > 3) {
            DeadRun.spectator.getPlayers().entrySet().stream().filter(player -> !DeadRun.arena.getPlaces().containsValue(player.getKey())).collect(Collectors.toList())
                    .stream().map(Map.Entry::getKey).collect(Collectors.toList()).forEach(player -> Bukkit.dispatchCommand(console, "tclevel addrandomexp " + player.getName() +
                            " " + DeadRun.config.getREWARD_EXP_LOSER_MIN() + " " + DeadRun.config.getREWARD_EXP_LOSER_MAX() + " no no"));
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

    public void giveRewardWinnerExp() {
        ConsoleCommandSender console = Bukkit.getConsoleSender();
        try {
            Bukkit.dispatchCommand(console, "tclevel addrandomexp " + DeadRun.arena.getPlaces().get(1).getName() +
                    " " + DeadRun.config.getREWARD_EXP_FIRST_MIN() + " " + DeadRun.config.getREWARD_EXP_FIRST_MAX() + " no no");
            Bukkit.dispatchCommand(console, "tclevel addrandomexp " + DeadRun.arena.getPlaces().get(2).getName() +
                    " " + DeadRun.config.getREWARD_EXP_SECOND_MIN() + " " + DeadRun.config.getREWARD_EXP_SECOND_MAX() + " no no");
            Bukkit.dispatchCommand(console, "tclevel addrandomexp " + DeadRun.arena.getPlaces().get(3).getName() +
                    " " + DeadRun.config.getREWARD_EXP_THIRD_MIN() + " " + DeadRun.config.getREWARD_EXP_THIRD_MAX() + " no no");
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
