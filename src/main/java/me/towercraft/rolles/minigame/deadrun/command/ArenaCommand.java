package me.towercraft.rolles.minigame.deadrun.command;

import com.avaje.ebean.validation.NotNull;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.logging.Logger;

public class ArenaCommand extends AbstractCommand {
    public ArenaCommand() {
        super("dr");
    }

    @Override
    public void execute(CommandSender commandSender, String label, String[] args) {
        if (args.length == 0) {
            commandSender.sendMessage(ChatColor.GOLD + "====================" + ChatColor.GREEN + "DeadRun" + ChatColor.GOLD + "==================== ");
            commandSender.sendMessage(ChatColor.GOLD + "/dr reload " + ChatColor.GREEN + "Перезагрузка конфига");
            commandSender.sendMessage(ChatColor.GOLD + "/dr create arena <name> " + ChatColor.GREEN + "Создание арены");
            commandSender.sendMessage(ChatColor.GOLD + "/dr set <arena name> pos1 " + ChatColor.GREEN + "Установка первой координаты арены");
            commandSender.sendMessage(ChatColor.GOLD + "/dr set <arena name> pos2 " + ChatColor.GREEN + "Установка второй координаты арены");
            commandSender.sendMessage(ChatColor.GOLD + "/dr setlobby " + ChatColor.GREEN + "Установка лобби");
            return;
        }
        if (args[0].equalsIgnoreCase("reload")) {
            DeadRun.getInstance().reloadConfig();
            commandSender.sendMessage(ChatColor.GREEN + "Конфиг перезагружен.");
            Logger log = Logger.getLogger("Config reloaded");
        }
        if (args[0].equalsIgnoreCase("create") && args[1].equalsIgnoreCase("arena")) {
            if (args.length == 3) {
                commandSender.sendMessage(ChatColor.GREEN + "Арена " + args[2] + " создана.");
            } else {
                commandSender.sendMessage(ChatColor.RED + "Укажите название арены");
            }
        }
        if (args[0].equalsIgnoreCase("setpost")) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                Location playerLocation = player.getLocation();
                commandSender.sendMessage(String.format("%1$s %2$s %3$s", playerLocation.getX(), playerLocation.getY(), playerLocation.getZ()));
            }
        }
    }
}
