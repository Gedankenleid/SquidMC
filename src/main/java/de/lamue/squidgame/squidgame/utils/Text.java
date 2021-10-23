package de.lamue.squidgame.squidgame.utils;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Text {

    public static String PREFIX = "§8[§3Squid§cMC§8] §7";

    public static void sendTextToPlayerWithSound(@NotNull Player player, @NotNull String message, Sound sound){
        player.sendMessage(message);
        if(!sound.equals(null)){
            player.playSound(player.getLocation(), sound, 1, 1);
        }
    }

    public static void sendNoPermMessage(Player player){
        player.sendMessage(PREFIX+"§cDu hast nicht genügend Rechte für diesen Befehl.");
    }

}
