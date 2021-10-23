package de.lamue.squidgame.squidgame.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public class LocationManager {

    public static Location LOBBY;
    public static Location BEDROOM;
    public static Location REDLIGHTGREENLIGHT;
    public static Location MARBLES;
    public static Location ZUCKERFIGUR;
    public static Location TAUZIEHEN;
    public static Location TRITTSTUFEN;
    public static Location TINTENFISCH;

    public static Location getLocation(@NotNull LOCATIONTYPE locationtype){
        if(locationtype.equals(LOCATIONTYPE.LOBBY)){
            return LOBBY;
        }
        if(locationtype.equals(LOCATIONTYPE.BEDROOM)){
            return BEDROOM;
        }
        if(locationtype.equals(LOCATIONTYPE.REDLIGHTGREENLIGHT)){
            return REDLIGHTGREENLIGHT;
        }
        if(locationtype.equals(LOCATIONTYPE.TAUZIEHEN)){
            return TAUZIEHEN;
        }
        if(locationtype.equals(LOCATIONTYPE.MURMELN)){
            return MARBLES;
        }
        if(locationtype.equals(LOCATIONTYPE.ZUCKERFIGUR)){
            return ZUCKERFIGUR;
        }
        if(locationtype.equals(LOCATIONTYPE.TRITTSTEIN)){
            return TRITTSTUFEN;
        }
        if(locationtype.equals(LOCATIONTYPE.TINTENFISCH)){
            return TINTENFISCH;
        }
        return LOBBY;
    }

    public static void setLocation(LOCATIONTYPE locationtype, Location location){
        String locationprefix = "";
        if(locationtype.equals(LOCATIONTYPE.LOBBY)){
            locationprefix = "Lobby";
        } else if(locationtype.equals(LOCATIONTYPE.BEDROOM)){
            locationprefix = "Bedroom";
        } else if(locationtype.equals(LOCATIONTYPE.REDLIGHTGREENLIGHT)){
            locationprefix = "RLGL";
        } else if(locationtype.equals(LOCATIONTYPE.MURMELN)){
            locationprefix = "Marbles";
        } else if(locationtype.equals(LOCATIONTYPE.ZUCKERFIGUR)){
            locationprefix = "Sugar";
        } else if(locationtype.equals(LOCATIONTYPE.TRITTSTEIN)){
            locationprefix = "Tritt";
        } else if(locationtype.equals(LOCATIONTYPE.TINTENFISCH)){
            locationprefix = "Squid";
        } else if(locationtype.equals(LOCATIONTYPE.TAUZIEHEN)){
            locationprefix = "Tauziehen";
        }
        locationprefix = locationprefix+".";
        ConfigurationManager.set(locationprefix+"world", location.getWorld().getName());
        ConfigurationManager.set(locationprefix+"x", location.getX());
        ConfigurationManager.set(locationprefix+"y", location.getY());
        ConfigurationManager.set(locationprefix+"z", location.getZ());
        ConfigurationManager.set(locationprefix+"pitch", location.getPitch());
        ConfigurationManager.set(locationprefix+"yaw", location.getYaw());
    }

    public static void loadLocations(){
        LocationManager.LOBBY = new Location(
                Bukkit.getWorld(ConfigurationManager.getString("Lobby.world")),
                ConfigurationManager.getDouble("Lobby.x"), ConfigurationManager.getDouble("Lobby.y"), ConfigurationManager.getDouble("Lobby.z"),
                ConfigurationManager.getFloat("Lobby.yaw"), ConfigurationManager.getFloat("Lobby.pitch"));
        LocationManager.BEDROOM = new Location(
                Bukkit.getWorld(ConfigurationManager.getString("Bedroom.world")),
                ConfigurationManager.getDouble("Bedroom.x"), ConfigurationManager.getDouble("Bedroom.y"), ConfigurationManager.getDouble("Bedroom.z"),
                ConfigurationManager.getFloat("Bedroom.yaw"), ConfigurationManager.getFloat("Bedroom.pitch"));
        LocationManager.REDLIGHTGREENLIGHT = new Location(
                Bukkit.getWorld(ConfigurationManager.getString("RLGL.world")),
                ConfigurationManager.getDouble("RLGL.x"), ConfigurationManager.getDouble("RLGL.y"), ConfigurationManager.getDouble("RLGL.z"),
                ConfigurationManager.getFloat("RLGL.yaw"), ConfigurationManager.getFloat("RLGL.pitch"));
        LocationManager.MARBLES = new Location(
                Bukkit.getWorld(ConfigurationManager.getString("Marbles.world")),
                ConfigurationManager.getDouble("Marbles.x"), ConfigurationManager.getDouble("Marbles.y"), ConfigurationManager.getDouble("Marbles.z"),
                ConfigurationManager.getFloat("Marbles.yaw"), ConfigurationManager.getFloat("Marbles.pitch"));
        LocationManager.ZUCKERFIGUR = new Location(
                Bukkit.getWorld(ConfigurationManager.getString("Sugar.world")),
                ConfigurationManager.getDouble("Sugar.x"), ConfigurationManager.getDouble("Sugar.y"), ConfigurationManager.getDouble("Sugar.z"),
                ConfigurationManager.getFloat("Sugar.yaw"), ConfigurationManager.getFloat("Sugar.pitch"));
        LocationManager.TRITTSTUFEN = new Location(
                Bukkit.getWorld(ConfigurationManager.getString("Tritt.world")),
                ConfigurationManager.getDouble("Tritt.x"), ConfigurationManager.getDouble("Tritt.y"), ConfigurationManager.getDouble("Tritt.z"),
                ConfigurationManager.getFloat("Tritt.yaw"), ConfigurationManager.getFloat("Tritt.pitch"));
        LocationManager.TINTENFISCH = new Location(
                Bukkit.getWorld(ConfigurationManager.getString("Squid.world")),
                ConfigurationManager.getDouble("Squid.x"), ConfigurationManager.getDouble("Squid.y"), ConfigurationManager.getDouble("Squid.z"),
                ConfigurationManager.getFloat("Squid.yaw"), ConfigurationManager.getFloat("Squid.pitch"));
        LocationManager.TAUZIEHEN = new Location(
                Bukkit.getWorld(ConfigurationManager.getString("Tauziehen.world")),
                ConfigurationManager.getDouble("Tauziehen.x"), ConfigurationManager.getDouble("Tauziehen.y"), ConfigurationManager.getDouble("Tauziehen.z"),
                ConfigurationManager.getFloat("Tauziehen.yaw"), ConfigurationManager.getFloat("Tauziehen.pitch"));
        DebugManager.sendDebugMessage("Loaded Locations");
    }

    public static boolean spawnLocationExists(){
        if(getLocation(LOCATIONTYPE.LOBBY) != null){
            return true;
        }
        return false;
    }

}
