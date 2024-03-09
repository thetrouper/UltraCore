package me.trouper.ultracore.server.util;

import me.trouper.ultracore.UltraCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.metadata.MetadataValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ServerUtils {
    public static void sendCommand(String command) {
        ServerUtils.verbose("Getting scheduler");
        Bukkit.getScheduler().scheduleSyncDelayedTask(UltraCore.getInstance(), () -> {
            try {
                ServerUtils.verbose("Attempting to run command...");
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        },1);
    }
    public static void verbose(String message) {
        if (UltraCore.config.debugMode) {
            String log = "[UltraCore] [DEBUG]: " + message;
            UltraCore.log.info(log);
            for (Player trustedPlayer : Bukkit.getOnlinePlayers()) {
                if (trustedPlayer.isOp()) {
                    trustedPlayer.sendMessage("§d§lUltraCore §7[§bDEBUG§7] §8» §7" + message);
                }
            }
        }
    }

    public static List<Player> getPlayers() {
        return new ArrayList<>(Bukkit.getOnlinePlayers());
    }

    public static List<Player> getStaff() {
        return getPlayers().stream().filter(Player -> Player.hasPermission("ultracore.staff")).toList();
    }

    public static void forEachPlayer(Consumer<Player> consumer) {
        getPlayers().forEach(consumer);
    }

    public static void forEachStaff(Consumer<Player> consumer) {
        getStaff().forEach(consumer);
    }

    public static void dmEachPlayer(Predicate<Player> condition, String dm) {
        forEachPlayer(p -> {
            if (condition.test(p)) p.sendMessage(dm);
        });
    }

    public static void dmEachPlayer(String dm) {
        forEachPlayer(p -> p.sendMessage(dm));
    }

    public static void forEachSpecified(Iterable<Player> players, Consumer<Player> consumer) {
        players.forEach(consumer);
    }

    public static void forEachSpecified(Consumer<Player> consumer, Player... players) {
        Arrays.stream(players).forEach(consumer);
    }
    public static void forEachPlayerRun(Predicate<Player> condition, Consumer<Player> task) {
        forEachPlayer(p -> {
            if (condition.test(p)) {
                task.accept(p);
            }
        });
    }

    public static boolean isVanished(Player player) {
        for (MetadataValue meta : player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true;
        }
        return false;
    }

    public static String[] unVanishedPlayers() {
        return io.github.itzispyder.pdk.utils.ServerUtils.players(ServerUtils::isVanished).stream().map(Player::getName).toArray(String[]::new);
    }
}
