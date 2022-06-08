package me.towercraft.rolles.minigame.deadrun.arena.spectator;

import lombok.Data;
import me.towercraft.rolles.minigame.deadrun.DeadRun;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class Spectator {
    private HashMap<Player, Boolean> players = new HashMap<>();

    public void addPlayer(Player player, Boolean value) {
        players.put(player, value);
    }

    public Boolean getSpector(Player player) {
        return players.get(player);
    }

    public void setSpectator(Player player, Boolean value) {
        players.put(player, value);
    }

    public List<Player> getNotSpectatorList() {
        Set<Player> keys = players.keySet();
        return keys.stream().filter(key -> players.get(key).equals(false)).collect(Collectors.toList());
    }

    public List<Player> getSpectorList() {
        Set<Player> keys = players.keySet();
        return keys.stream().filter(key -> players.get(key).equals(true)).collect(Collectors.toList());
    }

    private void hidePlayer(Player player) {
        List<Player> players = getNotSpectatorList();
        players.forEach(element -> element.hidePlayer(player));
    }

    private void showForSpector(Player player) {
        List<Player> players = getSpectorList();
        players.forEach(player::showPlayer);
    }

    public void set(Player player) {
        setSpectator(player, true);
        hidePlayer(player);
        showForSpector(player);
        player.setAllowFlight(true);
        player.setFlying(true);
        DeadRun.ghostFactory.setGhost(player, true);
    }


}
