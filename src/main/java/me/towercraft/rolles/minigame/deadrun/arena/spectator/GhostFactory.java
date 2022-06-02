package me.towercraft.rolles.minigame.deadrun.arena.spectator;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class GhostFactory {
    private static final String GHOST_TEAM_NAME = "Ghosts";
    private static final long UPDATE_DELAY = 20L;


    private static final OfflinePlayer[] EMPTY_PLAYERS = new OfflinePlayer[0];
    private Team ghostTeam;

    private BukkitTask task;
    private boolean closed;

    private Set<String> ghosts = new HashSet<>();

    public GhostFactory(Plugin plugin) {
        createTask(plugin);
        createGetTeam();
    }

    private void createGetTeam() {
        Scoreboard board = Bukkit.getServer().getScoreboardManager().getMainScoreboard();
        ghostTeam = board.getTeam(GHOST_TEAM_NAME);

        if (ghostTeam == null) {
            ghostTeam = board.registerNewTeam(GHOST_TEAM_NAME);
        }
        ghostTeam.setCanSeeFriendlyInvisibles(true);
    }

    private void createTask(Plugin plugin) {
        task = Bukkit.getScheduler().runTaskTimer(plugin, new Runnable() {
            @Override
            public void run() {
                for (OfflinePlayer member : getMembers()) {
                    Player player = member.getPlayer();

                    if (player != null) {
                        // Update invisibility effect
                        setGhost(player, isGhost(player));
                    } else {
                        ghosts.remove(member.getName());
                        ghostTeam.removePlayer(member);
                    }
                }
            }
        }, UPDATE_DELAY, UPDATE_DELAY);
    }

    public void clearMembers() {
        if (ghostTeam != null) {
            for (OfflinePlayer player : getMembers()) {
                ghostTeam.removePlayer(player);
            }
        }
    }

    public void addPlayer(Player player) {
        validateState();
        if (!ghostTeam.hasPlayer(player)) {
            ghostTeam.addPlayer(player);
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 15));
        }
    }

    public boolean isGhost(Player player) {
        return player != null && hasPlayer(player) && ghosts.contains(player.getName());
    }

    public boolean hasPlayer(Player player) {
        validateState();
        return ghostTeam.hasPlayer(player);
    }

    public void setGhost(Player player, boolean isGhost) {
        if (!hasPlayer(player))
            addPlayer(player);

        if (isGhost) {
            ghosts.add(player.getName());
            player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 15));
        } else {
            ghosts.remove(player.getName());
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
    }

    public void removePlayer(Player player) {
        validateState();
        if (ghostTeam.removePlayer(player)) {
            player.removePotionEffect(PotionEffectType.INVISIBILITY);
        }
    }

    public OfflinePlayer[] getGhosts() {
        validateState();
        Set<OfflinePlayer> players = new HashSet<>(ghostTeam.getPlayers());
        players.removeIf(offlinePlayer -> !ghosts.contains(offlinePlayer.getName()));
        return toArray(players);
    }

    public OfflinePlayer[] getMembers() {
        validateState();
        return toArray(ghostTeam.getPlayers());
    }

    private OfflinePlayer[] toArray(Set<OfflinePlayer> players) {
        if (players != null) {
            return players.toArray(new OfflinePlayer[players.size()]);
        } else {
            return EMPTY_PLAYERS;
        }
    }

    public void close() {
        if (!closed) {
            task.cancel();
            ghostTeam.unregister();
            closed = true;
        }
    }

    public boolean isClosed() {
        return closed;
    }

    private void validateState() {
        if (closed) {
            throw new IllegalStateException("Ghost factory has closed. Cannot reuse instances.");
        }
    }
}
