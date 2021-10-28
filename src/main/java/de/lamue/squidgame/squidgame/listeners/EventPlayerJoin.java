package de.lamue.squidgame.squidgame.listeners;

import de.lamue.squidgame.squidgame.SquidMC;
import de.lamue.squidgame.squidgame.utils.*;
import de.lamue.squidgame.squidgame.utils.database.CoinsManager;
import de.lamue.squidgame.squidgame.utils.tablist.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventPlayerJoin implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent){
        Player player = playerJoinEvent.getPlayer();
        TitleAPI.setActionbarText(player, "§3§lSquid§c§lMC");
        player.setPlayerListHeaderFooter("§3§lSquid§c§lMC", "§8SquidMC.net");
        player.setGameMode(GameMode.SURVIVAL);
        player.setAllowFlight(false);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        playerJoinEvent.setJoinMessage(null);
        Scoreboard scoreboard = new Scoreboard();
        scoreboard.setScoreboard(player);
        for(Player currentPlayer : Bukkit.getOnlinePlayers()){
            scoreboard.setScoreboard(currentPlayer);
        }
        try {
            if(!CoinsManager.isUserExists(player.getUniqueId())){
                CoinsManager.createPlayer(player.getUniqueId(), player.getName());
            }else{
                CoinsManager.updateUsername(player);
            }
        }catch (Exception ignored){}
        if(GameManager.getCurrentGameStatus().equals(GAMESTATUS.SETUP)){
            DebugManager.sendDebugMessage("Player "+player.getName()+" is now a configurator.");
            Text.sendTextToPlayerWithSound(player, Text.PREFIX + "Danke das du SquidMC nutzt!", Sound.ENTITY_PLAYER_LEVELUP);
            player.sendMessage(Text.PREFIX + "Um den Lobbyspawn zu setzen, nutze /setlobbyspawn");
            player.sendMessage(Text.PREFIX + "Um den Bettenraumspawn zu setzen, nutze /setbedspawn");
            player.sendMessage(Text.PREFIX + "Um einen Spielspawn zu setzen, nutze /setgamespawn");
        }else{
            if(GameManager.getCurrentGameStatus().equals(GAMESTATUS.LOBBY)){
                playerJoinEvent.setJoinMessage("§7§l→ §e"+player.getDisplayName()+" §7hat das Spiel betreten!");
                PlayerManager.setPlayerStatus(player, PLAYERSTATUS.WAITING);
                DebugManager.sendDebugMessage("Player "+player.getName()+" is now waiting");
                player.teleport(LocationManager.getLocation(LOCATIONTYPE.LOBBY));
                Bukkit.getScheduler().scheduleSyncDelayedTask(SquidMC.pluginInstance, new Runnable() {
                    @Override
                    public void run() {
                        player.teleport(LocationManager.getLocation(LOCATIONTYPE.LOBBY));
                    }
                }, 1);
                Text.sendTextToPlayerWithSound(player, Text.PREFIX + "§7Willkommen bei §cSquidMC§7!", Sound.BLOCK_PORTAL_TRAVEL);
                StartManager.triggerPlayerJoinQuit();
            }else{
                PlayerManager.setPlayerStatus(player, PLAYERSTATUS.SPECTATOR);
                player.setGameMode(GameMode.SPECTATOR);
                DebugManager.sendDebugMessage("Player "+player.getName()+" is now a spectator.");
                Bukkit.getScheduler().scheduleSyncDelayedTask(SquidMC.pluginInstance, new Runnable() {
                    @Override
                    public void run() {
                        player.teleport(PlayerManager.isAliveList.get(0));
                    }
                }, 1);
                player.sendMessage(Text.PREFIX+"§7Das Spiel läuft bereits.");
                player.sendMessage(Text.PREFIX+"§7Du bist ein Spectator.");
                player.sendTitle("§8§lSpectator", "", 0, 20*2, 20);
            }
        }
    }

}
