package de.lamue.squidgame.squidgame.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerManager {

    public static HashMap<Player, PLAYERSTATUS> playerstatusHashMap = new HashMap<>();

    public static HashMap<Player, PLAYERSTATUS> getPlayersInHashMap() {
        return playerstatusHashMap;
    }

    public static ArrayList<Player> isAliveMap = new ArrayList<>();

    public static boolean isInHashMap(Player player){
        return playerstatusHashMap.containsKey(player);
    }

    public static void setIsAlive(Player player){
        if(!isAlive(player)){
            isAliveMap.add(player);
        }
    }

    public static void removeIsAlive(Player player){
        if(isAlive(player)){
            isAliveMap.remove(player);
        }
    }

    public static boolean isAlive(Player player){
        if(isAliveMap.contains(player)){
            return true;
        }else{
            return false;
        }
    }

    public static ArrayList<Player> getPlayersAlive(){
        return isAliveMap;
    }

    public static void setPlayerStatus(Player player, PLAYERSTATUS playerstatus){
        if(isInHashMap(player)){
            playerstatusHashMap.remove(player);
        }
        playerstatusHashMap.put(player, playerstatus);
    }

    public static void removePlayerInHashMap(Player player){
        if(isInHashMap(player)){
            playerstatusHashMap.remove(player);
        }
    }

    public static void kickAllPlayers(String reason){
        for(Player currentPlayer : Bukkit.getOnlinePlayers()){
            currentPlayer.kickPlayer(reason);
        }
    }

    public static PLAYERSTATUS getPlayerStatus(Player player){
        if(isInHashMap(player)){
            return playerstatusHashMap.get(player);
        }else{
            return null;
        }
    }
}
