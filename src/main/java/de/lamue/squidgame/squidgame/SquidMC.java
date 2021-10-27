package de.lamue.squidgame.squidgame;

import de.lamue.squidgame.squidgame.commands.CommandSetBedSpawn;
import de.lamue.squidgame.squidgame.commands.CommandSetGameSpawn;
import de.lamue.squidgame.squidgame.commands.CommandSetLobbySpawn;
import de.lamue.squidgame.squidgame.commands.CommandStart;
import de.lamue.squidgame.squidgame.listeners.*;
import de.lamue.squidgame.squidgame.utils.*;
import de.lamue.squidgame.squidgame.utils.database.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SquidMC extends JavaPlugin {

    public static String MOTD;
    public static Integer MAXPLAYERS;

    public static SquidMC pluginInstance;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(Text.getLogo());
        Bukkit.getConsoleSender().sendMessage(Text.PREFIX + "Laden...");
        pluginInstance = this;
        try {
            ConfigurationManager.checkConfiguration();
            if(ConfigurationManager.setupCompleted()){
                GameManager.setGameStatus(GAMESTATUS.LOBBY);
                LocationManager.loadLocations();
            }else{
                GameManager.setGameStatus(GAMESTATUS.SETUP);
            }
        } catch (Exception exception){
            Bukkit.getConsoleSender().sendMessage(Text.PREFIX + "§cFehler beim Laden der Konfiguration");
        }
        setServerSlots();
        setMOTD();
        getCommand("setbedspawn").setExecutor(new CommandSetBedSpawn());
        getCommand("setlobbyspawn").setExecutor(new CommandSetLobbySpawn());
        getCommand("setgamespawn").setExecutor(new CommandSetGameSpawn());
        getCommand("start").setExecutor(new CommandStart());
        Bukkit.getPluginManager().registerEvents(new EventPlayerJoin(), this);
        Bukkit.getPluginManager().registerEvents(new EventPlayerQuit(), this);
        Bukkit.getPluginManager().registerEvents(new EventServerListPing(), this);
        Bukkit.getPluginManager().registerEvents(new EventAsyncPlayerChat(), this);
        Bukkit.getPluginManager().registerEvents(new EventPlayerDeath(), this);
        Bukkit.getPluginManager().registerEvents(new EventEntityDamage(), this);
        Bukkit.getPluginManager().registerEvents(new EventFoodLevelChange(), this);
        Bukkit.getPluginManager().registerEvents(new EventItemDrop(), this);
        Bukkit.getPluginManager().registerEvents(new EventEnvironmentChange(), this);
        Bukkit.getPluginManager().registerEvents(new EventBuild(), this);
        Bukkit.getPluginManager().registerEvents(new EventPlayerMove(), this);
        StartManager.loadMinPlayers();
        GameManager.setPVP(false);
        GameManager.setAllowedDropping(false);
        //MySQL.connect();
        //MySQL.update("CREATE TABLE IF NOT EXISTS Coins (UUID VARCHAR(100),Spielername VARCHAR(100), Anzahl LONG)");
        //MySQL.keepOnline();
        new Scoreboard().update();
        EventEnvironmentChange.keepDay();
        Bukkit.getConsoleSender().sendMessage(Text.PREFIX + "§aPlugin gestartet!");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Text.getLogo());
        //MySQL.close();
        PlayerManager.kickAllPlayers(Text.PREFIX+"§cDer Server startet neu!");
        Bukkit.getConsoleSender().sendMessage(Text.PREFIX + "§aPlugin gestoppt!");
    }

    private void setServerSlots(){
        MAXPLAYERS = ConfigurationManager.getInteger("Server.slots");
    }

    private void setMOTD(){
        MOTD = ConfigurationManager.getString("Server.motd");
    }
}
