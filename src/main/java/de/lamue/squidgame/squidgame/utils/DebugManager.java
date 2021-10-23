package de.lamue.squidgame.squidgame.utils;

import org.bukkit.Bukkit;

public class DebugManager {

    public static boolean DEBUG = true;

    public static void sendDebugMessage(String debugmessage){
        if(DEBUG){
            Bukkit.getConsoleSender().sendMessage(Text.PREFIX + "§8[DEBUG] §7"+debugmessage);
        }
    }

    public static void setDebugStatus(Boolean status){
        DEBUG = status;
    }

}
