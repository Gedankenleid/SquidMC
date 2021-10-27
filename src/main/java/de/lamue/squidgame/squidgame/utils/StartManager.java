package de.lamue.squidgame.squidgame.utils;

import de.lamue.squidgame.squidgame.SquidMC;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class StartManager {

    public static Integer MINPLAYERS = 2;

    public static boolean ableToStart = false;

    public static boolean isAbleToStart() {
        return ableToStart;
    }

    public static Integer COUNTDOWN;

    public static Integer WAITINGTIME = 30;

    public static int counter;

    public static void triggerPlayerJoinQuit(){
        if(PlayerManager.getPlayersInHashMap().size() >= MINPLAYERS){
            ableToStart = true;
            startCountdown();
        }else{
            ableToStart = false;
            Integer morePlayersNeeded = MINPLAYERS - PlayerManager.getPlayersInHashMap().size();
            if(morePlayersNeeded == 1){
                for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                    Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Es wird noch §e1 Spieler §7benötigt.", Sound.BLOCK_NOTE_BLOCK_BASS);
                }
            }else{
                for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                    Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Es werden noch §e"+morePlayersNeeded+" Spieler §7benötigt.", Sound.BLOCK_NOTE_BLOCK_BASS);
                }
            }
        }
    }

    public static void loadMinPlayers(){
        MINPLAYERS = ConfigurationManager.getInteger("Game.minplayers");
    }

    public static void startCountdown(){
        if(isAbleToStart()){
            counter = WAITINGTIME+1;
            COUNTDOWN = Bukkit.getScheduler().scheduleSyncRepeatingTask(SquidMC.pluginInstance, new Runnable() {
                @Override
                public void run() {
                    if(isAbleToStart()){
                        counter--;
                        if(counter == 30){
                            for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                                Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Das Spiel startet in §e"+counter+" Sekunden§7!", Sound.BLOCK_ANVIL_PLACE);
                            }
                        }else if(counter == 20){
                            for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                                Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Das Spiel startet in §e"+counter+" Sekunden§7!", Sound.BLOCK_NOTE_BLOCK_PLING);
                            }
                        }else if(counter == 15){
                            for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                                Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Das Spiel startet in §e"+counter+" Sekunden§7!", Sound.BLOCK_NOTE_BLOCK_PLING);
                            }
                        }else if(counter == 10){
                            for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                                Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Das Spiel startet in §e"+counter+" Sekunden§7!", Sound.BLOCK_NOTE_BLOCK_PLING);
                            }
                        }else if(counter == 5){
                            for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                                Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Das Spiel startet in §e"+counter+" Sekunden§7!", Sound.BLOCK_NOTE_BLOCK_PLING);
                            }
                        }else if(counter == 4){
                            for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                                Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Das Spiel startet in §e"+counter+" Sekunden§7!", Sound.BLOCK_NOTE_BLOCK_PLING);
                            }
                        }else if(counter == 3){
                            for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                                Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Das Spiel startet in §e"+counter+" Sekunden§7!", Sound.BLOCK_NOTE_BLOCK_PLING);
                            }
                        }else if(counter == 2){
                            for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                                Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Das Spiel startet in §e"+counter+" Sekunden§7!", Sound.BLOCK_NOTE_BLOCK_PLING);
                            }
                        }else if(counter == 1){
                            for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                                Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Das Spiel startet in §e"+counter+" Sekunde§7!", Sound.BLOCK_NOTE_BLOCK_PLING);
                            }
                        }else if(counter == 0){
                            start();
                        }
                    }else{
                        for(Player currentPlayer : Bukkit.getOnlinePlayers()){
                            Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Der Countdown wurde abgebrochen!", Sound.AMBIENT_CAVE);
                        }
                        counter = WAITINGTIME+1;
                        Bukkit.getScheduler().cancelTask(COUNTDOWN);
                    }
                }
            },0 , 20);
        }
    }

    public static void start(){
        for(Player currentPlayer : Bukkit.getOnlinePlayers()){
            Text.sendTextToPlayerWithSound(currentPlayer, Text.PREFIX+"Das Spiel startet in §ejetzt§7!", Sound.ENTITY_PLAYER_LEVELUP);
            PlayerManager.setPlayerStatus(currentPlayer, PLAYERSTATUS.INGAME);
            PlayerManager.setIsAlive(currentPlayer);
        }
        Bukkit.getScheduler().cancelTask(COUNTDOWN);
        GameManager.setGameStatus(GAMESTATUS.INGAME);
        GameManager.addRoundNumber();
        GameManager.startGame(GameManager.getNextGame());
    }
}
