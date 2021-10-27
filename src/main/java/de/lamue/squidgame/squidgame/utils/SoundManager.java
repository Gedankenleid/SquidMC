package de.lamue.squidgame.squidgame.utils;

import de.lamue.squidgame.squidgame.SquidMC;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundManager {

    public static void sendSoundRedLightGreenLight(Player player){
        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 3, 1);
        Bukkit.getScheduler().scheduleSyncDelayedTask(SquidMC.pluginInstance, new Runnable() {
            @Override
            public void run() {
                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 3, 3);
                Bukkit.getScheduler().scheduleSyncDelayedTask(SquidMC.pluginInstance, new Runnable() {
                    @Override
                    public void run() {
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 3, 3);
                        Bukkit.getScheduler().scheduleSyncDelayedTask(SquidMC.pluginInstance, new Runnable() {
                            @Override
                            public void run() {
                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 3, 1);
                                Bukkit.getScheduler().scheduleSyncDelayedTask(SquidMC.pluginInstance, new Runnable() {
                                    @Override
                                    public void run() {
                                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 3, 3);
                                        Bukkit.getScheduler().scheduleSyncDelayedTask(SquidMC.pluginInstance, new Runnable() {
                                            @Override
                                            public void run() {
                                                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 3, 3);
                                            }
                                        }, 10);
                                    }
                                }, 10);
                            }
                        }, 20);
                    }
                }, 10);
            }
        }, 10);
    }

}
