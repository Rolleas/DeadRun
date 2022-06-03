package me.towercraft.rolles.minigame.deadrun.notification;

import lombok.AllArgsConstructor;
import me.towercraft.rolles.minigame.deadrun.arena.StateArena;
import me.towercraft.rolles.minigame.deadrun.config.YMLConfig;
import org.bukkit.entity.Player;

public class Message {
    public void playerJoin(YMLConfig config, StateArena arena, Player player) {
        Sender.broadcastMessage(config.getPLAYER_JOIN_MESSAGE()
                .replace("<current>", String.valueOf(arena.getPlayers().size()))
                .replace("<max>", String.valueOf(config.getMAX_PLAYERS()))
                .replace("<name>", String.valueOf(player.getName())));
    }
}
