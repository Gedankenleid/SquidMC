package de.lamue.squidgame.squidgame.listeners;

import de.lamue.squidgame.squidgame.SquidMC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class EventServerListPing implements Listener {

    @EventHandler
    public void onServerListPing(ServerListPingEvent serverListPingEvent){
        serverListPingEvent.setMaxPlayers(SquidMC.MAXPLAYERS);
        serverListPingEvent.setMotd(SquidMC.MOTD);
    }

}
