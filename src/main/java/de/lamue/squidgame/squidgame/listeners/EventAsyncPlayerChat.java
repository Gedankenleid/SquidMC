package de.lamue.squidgame.squidgame.listeners;

import de.lamue.squidgame.squidgame.utils.PLAYERSTATUS;
import de.lamue.squidgame.squidgame.utils.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EventAsyncPlayerChat implements Listener {

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent asyncPlayerChatEvent){
        Player player = asyncPlayerChatEvent.getPlayer();
        if(PlayerManager.getPlayerStatus(player).equals(PLAYERSTATUS.SPECTATOR)){
            asyncPlayerChatEvent.setCancelled(true);
        }else{
            String message = asyncPlayerChatEvent.getMessage().replaceAll("%", "%%");
            asyncPlayerChatEvent.setFormat("§e"+player.getDisplayName()+" §8» §7"+message);
        }
    }
}
