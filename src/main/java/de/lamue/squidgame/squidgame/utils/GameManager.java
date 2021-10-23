package de.lamue.squidgame.squidgame.utils;

import de.lamue.squidgame.squidgame.SquidMC;
import de.lamue.squidgame.squidgame.utils.database.CoinsManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GameManager {

    public static GAMESTATUS STATUS = GAMESTATUS.SETUP;

    public static GAMESTATUS getCurrentGameStatus(){
        return STATUS;
    }

    public static boolean PVP = false;

    public static long currentPrize = 100;

    public static Integer count = 30;

    public static Integer ROUNDNUMBER = 0;

    public static boolean allowDrop = false;

    public static Boolean isAllowedToDropItems(){
        return allowDrop;
    }

    public static void setAllowedDropping(Boolean allowed){
        allowDrop = allowed;
    }

    public static void setGameStatus(GAMESTATUS gamestatus){
        STATUS = gamestatus;
        DebugManager.sendDebugMessage("GameStatus set to "+gamestatus);
    }

    public static void setPVP(Boolean status){
        PVP = status;
    }

    public static Boolean isPvP(){
        return PVP;
    }

    public static boolean isAbleToPlay(){
        if(PlayerManager.isAliveMap.size() > 1){
            return true;
        }
        return false;
    }

    public static void registerDeath(){
        currentPrize = currentPrize + 100 + getMultiplicator();
        if(!isAbleToPlay()){
            Player winner = PlayerManager.isAliveMap.get(0);
            end(winner);
        }else{
            for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Das Preisgeld beträgt nun §e"+currentPlayer+" Coins§7!", Sound.ENTITY_EXPERIENCE_ORB_PICKUP);
            }
        }
    }

    public static Integer getMultiplicator(){
        if(ROUNDNUMBER == 1){
            return 0;
        }else if(ROUNDNUMBER == 2){
            return 20;
        }else if(ROUNDNUMBER == 3){
            return 50;
        }else if(ROUNDNUMBER == 4){
            return 100;
        }else if(ROUNDNUMBER == 5){
            return 125;
        }else if(ROUNDNUMBER == 6){
            return 150;
        }else if(ROUNDNUMBER == 7){
            return 200;
        }
        return 0;
    }

    public static void addRoundNumber(){
        ROUNDNUMBER++;
    }

    public static void end(Player winner){
        GameManager.setGameStatus(GAMESTATUS.END);
        GameManager.ROUNDNUMBER = 8;
        Text.sendTextToPlayerWithSound(winner, Text.PREFIX+"Du hast §e"+currentPrize+" Coins §7gewonnen!", Sound.ENTITY_FIREWORK_ROCKET_BLAST);
        //CoinsManager.addCoins(winner, (int) currentPrize);
        for(Player currentPlayer : Bukkit.getOnlinePlayers()){
            currentPlayer.teleport(LocationManager.getLocation(LOCATIONTYPE.LOBBY));
            currentPlayer.setGameMode(GameMode.SURVIVAL);
            Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"§e"+winner.getDisplayName()+" §7hat das Spiel §agewonnen§7!", Sound.ENTITY_ENDER_DRAGON_DEATH);
            currentPlayer.setAllowFlight(false);
            currentPlayer.setHealth(20);
            currentPlayer.setFoodLevel(20);
            currentPlayer.sendTitle("§e§l"+winner.getDisplayName(), "§7hat gewonnen!", 5, 20*6, 20);
            Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Der Server startet in §e"+count+" Sekunden §7neu!", Sound.BLOCK_NOTE_BLOCK_BELL);
        }
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SquidMC.pluginInstance, new Runnable() {
            @Override
            public void run() {
                count--;
                if(count != 0){
                    if(count == 20){
                        for (Player currentPlayer : Bukkit.getOnlinePlayers()){
                            Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Der Server startet in §e"+count+" Sekunden §7neu!", Sound.BLOCK_NOTE_BLOCK_BELL);
                        }
                    }else if(count == 15){
                        for (Player currentPlayer : Bukkit.getOnlinePlayers()){
                            Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Der Server startet in §e"+count+" Sekunden §7neu!", Sound.BLOCK_NOTE_BLOCK_BELL);
                        }
                    }else if(count == 10){
                        for (Player currentPlayer : Bukkit.getOnlinePlayers()){
                            Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Der Server startet in §e"+count+" Sekunden §7neu!", Sound.BLOCK_NOTE_BLOCK_BELL);
                        }
                    }else if(count == 5){
                        for (Player currentPlayer : Bukkit.getOnlinePlayers()){
                            Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Der Server startet in §e"+count+" Sekunden §7neu!", Sound.BLOCK_NOTE_BLOCK_BELL);
                        }
                    }else if(count == 4){
                        for (Player currentPlayer : Bukkit.getOnlinePlayers()){
                            Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Der Server startet in §e"+count+" Sekunden §7neu!", Sound.BLOCK_NOTE_BLOCK_BELL);
                        }
                    }else if(count == 3){
                        for (Player currentPlayer : Bukkit.getOnlinePlayers()){
                            Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Der Server startet in §e"+count+" Sekunden §7neu!", Sound.BLOCK_NOTE_BLOCK_BELL);
                        }
                    }else if(count == 2){
                        for (Player currentPlayer : Bukkit.getOnlinePlayers()){
                            Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Der Server startet in §e"+count+" Sekunden §7neu!", Sound.BLOCK_NOTE_BLOCK_BELL);
                        }
                    }else if (count == 1){
                        for (Player currentPlayer : Bukkit.getOnlinePlayers()){
                            Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Der Server startet in §e"+count+" Sekunde §7neu!", Sound.BLOCK_NOTE_BLOCK_BELL);
                        }
                    }
                }else{
                    for (Player currentPlayer : Bukkit.getOnlinePlayers()){
                        Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Der Server startet in §ejetzt §7neu!", Sound.BLOCK_NOTE_BLOCK_BELL);
                    }
                    PlayerManager.kickAllPlayers(Text.PREFIX+"§cDer Server startet neu!");
                    Bukkit.shutdown();
                }
            }
        }, 20, 20);
    }

    public static GAME getNextGame(){
        if(ROUNDNUMBER == 0){
            return GAME.LOBBY;
        }else if(ROUNDNUMBER == 1){
            return GAME.REDLIGHTGREENLIGHT;
        }else if(ROUNDNUMBER == 2){
            return GAME.ZUCKERFIGUR;
        }else if(ROUNDNUMBER == 3){
            return GAME.TAUZIEHEN;
        }else if(ROUNDNUMBER == 4){
            return GAME.MURMELN;
        }else if(ROUNDNUMBER == 5){
            return GAME.TRITTSTEIN;
        }else if(ROUNDNUMBER == 6){
            return GAME.TINTENFISCH;
        }else if(ROUNDNUMBER == 7){
            return GAME.PVP;
        }else if(ROUNDNUMBER == 8){
            return GAME.ENDE;
        }
        return null;
    }

    public static void startGame(GAME game){
        //TODO: Start. game wird als erstes nach dem Countdown mit RLGL getriggert
    }
}
