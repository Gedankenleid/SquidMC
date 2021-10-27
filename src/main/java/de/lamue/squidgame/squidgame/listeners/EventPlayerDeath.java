package de.lamue.squidgame.squidgame.listeners;

import de.lamue.squidgame.squidgame.SquidMC;
import de.lamue.squidgame.squidgame.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class EventPlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent playerDeathEvent){
        Player player = playerDeathEvent.getEntity();
        playerDeathEvent.setDeathMessage("");
        playerDeathEvent.setDroppedExp(0);
        playerDeathEvent.getDrops().clear();
        if(GameManager.getCurrentGameStatus().equals(GAMESTATUS.INGAME)){
            if(PlayerManager.isAlive(player)){
                PlayerManager.removeIsAlive(player);
                for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                    Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"§7§l✞ §e"+player.getDisplayName()+" §7ist gestorben!", Sound.ENTITY_BAT_HURT);
                }
            }
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(Text.PREFIX+"§7Du bist ein Spectator.");
            player.spigot().respawn();
            player.sendTitle("§8§lSpectator", "", 0, 20*2, 20);
            Bukkit.getScheduler().scheduleSyncDelayedTask(SquidMC.pluginInstance, new Runnable() {
                @Override
                public void run() {
                    player.teleport(PlayerManager.isAliveList.get(0));
                }
            }, 1);
            GameManager.registerDeath();
        }else{
            player.spigot().respawn();
        }
    }

}
