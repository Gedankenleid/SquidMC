package de.lamue.squidgame.squidgame.listeners;

import de.lamue.squidgame.squidgame.utils.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventPlayerQuit implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent){
        playerQuitEvent.setQuitMessage(null);
        Player player = playerQuitEvent.getPlayer();
        if(!PlayerManager.getPlayerStatus(player).equals(PLAYERSTATUS.SPECTATOR)){
            playerQuitEvent.setQuitMessage("§7§l← §e"+player.getDisplayName()+" §7hat das Spiel verlassen!");
        }
        PlayerManager.removePlayerInHashMap(player);
        if(PlayerManager.isAlive(player)){
            PlayerManager.removeIsAlive(player);
            GameManager.registerDeath();
        }
        if(GameManager.getCurrentGameStatus().equals(GAMESTATUS.LOBBY)){
            StartManager.triggerPlayerJoinQuit();
        }
    }

}
