package de.lamue.squidgame.squidgame.listeners;

import de.lamue.squidgame.squidgame.utils.GameManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class EventItemDrop implements Listener {

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent playerDropItemEvent){
        if(!GameManager.isAllowedToDropItems()){
            playerDropItemEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickupItem(EntityPickupItemEvent entityPickupItemEvent){
        entityPickupItemEvent.setCancelled(true);
    }

}
