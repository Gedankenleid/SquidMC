package de.lamue.squidgame.squidgame.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class EventBuild implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent blockBreakEvent){
        blockBreakEvent.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent blockPlaceEvent){
        blockPlaceEvent.setCancelled(true);
    }
}
