package de.lamue.squidgame.squidgame.utils;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigurationManager {

    public static File dir = new File("plugins//SquidMC");
    public static File file = new File("plugins//SquidMC//config.yml");
    public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
    public static String NAME;

    public static void checkConfiguration() {
        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            try
            {
                file.createNewFile();
                cfg.set("MySQL.host", "localhost");
                cfg.set("MySQL.user", "squidmc");
                cfg.set("MySQL.database", "squidmc");
                cfg.set("MySQL.password", "password");
                cfg.set("MySQL.port", 3306);
                cfg.set("Server.motd", "Zeile1\nZeile2");
                cfg.set("Server.slots", 200);
                cfg.set("Game.minplayers", 8);
                cfg.save(file);
            }
            catch (IOException e)
            {
                checkConfiguration();
            }
        }
    }

    public static void set(String ident, Object input){
        cfg.set(ident, input);
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String ident){
        return cfg.getString(ident);
    }

    public static Integer getInteger(String ident){
        return Integer.valueOf(cfg.getInt(ident));
    }

    public static Double getDouble(String ident){
        return Double.valueOf(cfg.getDouble(ident));
    }

    public static Float getFloat(String ident){
        return getInteger(ident).floatValue();
    }

    public static Location getLocation(LOCATIONTYPE locationtype){
        return LocationManager.LOBBY;
    }

    public static boolean setupCompleted(){
        if(LocationManager.spawnLocationExists()){
            return true;
        }
        return true;
    }

}
