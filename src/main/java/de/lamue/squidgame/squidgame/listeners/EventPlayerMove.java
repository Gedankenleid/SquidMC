package de.lamue.squidgame.squidgame.listeners;

import de.lamue.squidgame.squidgame.SquidMC;
import de.lamue.squidgame.squidgame.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class EventPlayerMove implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent playerMoveEvent){
        Player player = playerMoveEvent.getPlayer();
        String xString = String.valueOf(Math.round(player.getLocation().getX()));
        String yString = String.valueOf(Math.round(player.getLocation().getY()));
        String zString = String.valueOf(Math.round(player.getLocation().getZ()));
        String locationString = xString+" "+yString+" "+zString;
        String oldLocationString = "";
        if(PlayerManager.PLAYERLOCATIONS.containsKey(player)){
            oldLocationString = PlayerManager.PLAYERLOCATIONS.get(player);
            PlayerManager.PLAYERLOCATIONS.remove(player);
        }
        PlayerManager.PLAYERLOCATIONS.put(player, locationString);
        if(!GameManager.ableToMove){
            if(PlayerManager.isAliveList.contains(player)){
                playerMoveEvent.setCancelled(true);
            }
        }
        Material blockBelow = player.getLocation().subtract(0, 2, 0).getBlock().getType();
        if(!GameManager.getNextGame().equals(GAME.PVP)){
            if(player.getGameMode().equals(GameMode.SURVIVAL)){
                if(PlayerManager.isAliveList.contains(player)){
                    if(blockBelow.equals(Material.GOLD_BLOCK)){
                        player.setGameMode(GameMode.SPECTATOR);
                        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                        player.sendTitle("§a§lÜberlebt!", "");
                        GameManager.FINISHED.add(player);
                        if((GameManager.FINISHED.size() == PlayerManager.isAliveList.size()) && PlayerManager.isAliveList.size() != 0){
                            if(!GameManager.getCurrentGameStatus().equals(GAMESTATUS.END)){
                                GameManager.FINISHED.clear();
                                GameManager.addRoundNumber();
                                GAME nextGame = GameManager.getNextGame();
                                for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                                    currentPlayer.sendTitle("§d§lRunde", "§b§lbeendet!", 5, 20*5, 5);
                                    Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Die Runde ist beendet!", Sound.ENTITY_PLAYER_LEVELUP);
                                    currentPlayer.sendMessage(Text.PREFIX+"§7Das nächste Spiel §e"+GAME.getName(nextGame)+" §7startet in Kürze!");
                                }
                                Bukkit.getScheduler().cancelTask(GameManager.RLGLCounter);
                                Bukkit.getScheduler().scheduleSyncDelayedTask(SquidMC.pluginInstance, new Runnable() {
                                    @Override
                                    public void run() {
                                        GameManager.startGame(nextGame);
                                    }
                                }, 20*5);
                            }
                        }
                    }else{
                        if(!GameManager.allowedToMove){
                            if(!locationString.toString().equalsIgnoreCase(oldLocationString.toString())){
                                if(PlayerManager.isAliveList.contains(player)){
                                    PlayerManager.isAliveList.remove(player);
                                }
                                if(!GameManager.isEnd){
                                    player.setHealth(0);
                                }
                                Bukkit.getScheduler().cancelTask(GameManager.RLGLCounter);
                            }
                        }
                    }
                }
            }
        }
    }

}
