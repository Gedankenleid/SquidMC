package de.lamue.squidgame.squidgame.listeners;

import de.lamue.squidgame.squidgame.utils.GAMESTATUS;
import de.lamue.squidgame.squidgame.utils.GameManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EventEntityDamage implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent entityDamageByEntityEvent){
        if(entityDamageByEntityEvent.getDamager() instanceof Player){
            if(GameManager.isPvP()){
                entityDamageByEntityEvent.setCancelled(false);
            }else{
                entityDamageByEntityEvent.setCancelled(true);
            }
        }else{
            entityDamageByEntityEvent.setCancelled(true);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent entityDamageEvent){
        if(!GameManager.getCurrentGameStatus().equals(GAMESTATUS.INGAME)){
            entityDamageEvent.setCancelled(true);
        }
    }

}
