package de.lamue.squidgame.squidgame.utils;

import org.bukkit.Location;

public enum GAME {

    LOBBY,
    REDLIGHTGREENLIGHT,
    ZUCKERFIGUR,
    TAUZIEHEN,
    MURMELN,
    TRITTSTEIN,
    TINTENFISCH,
    PVP,
    ENDE;

    public static String getName(GAME game){
        if (game.equals(LOBBY)) {
            return "Lobby";
        }
        if (game.equals(REDLIGHTGREENLIGHT)) {
            return "Ampelspiel";
        }
        if (game.equals(ZUCKERFIGUR)) {
            return "Zuckerfiguren";
        }
        if (game.equals(TAUZIEHEN)) {
            return "Tauziehen";
        }
        if (game.equals(MURMELN)) {
            return "Murmelspiel";
        }
        if (game.equals(TRITTSTEIN)) {
            return "Glasplatten";
        }
        if (game.equals(TINTENFISCH)) {
            return "Tintenfisch";
        }
        if (game.equals(PVP)) {
            return "Bonusrunde";
        }
        if (game.equals(ENDE)) {
            return "Ende";
        }
        return "Keins";
    }

    public static Location getSpawnLocation(GAME game){
        if (game.equals(LOBBY)) {
            return LocationManager.getLocation(LOCATIONTYPE.LOBBY);
        }
        if (game.equals(REDLIGHTGREENLIGHT)) {
            return LocationManager.getLocation(LOCATIONTYPE.REDLIGHTGREENLIGHT);
        }
        if (game.equals(ZUCKERFIGUR)) {
            return LocationManager.getLocation(LOCATIONTYPE.ZUCKERFIGUR);
        }
        if (game.equals(TAUZIEHEN)) {
            return LocationManager.getLocation(LOCATIONTYPE.TAUZIEHEN);
        }
        if (game.equals(MURMELN)) {
            return LocationManager.getLocation(LOCATIONTYPE.MURMELN);
        }
        if (game.equals(TRITTSTEIN)) {
            return LocationManager.getLocation(LOCATIONTYPE.TRITTSTEIN);
        }
        if (game.equals(TINTENFISCH)) {
            return LocationManager.getLocation(LOCATIONTYPE.TINTENFISCH);
        }
        if (game.equals(PVP)) {
            return LocationManager.getLocation(LOCATIONTYPE.BEDROOM);
        }
        if (game.equals(ENDE)) {
            return LocationManager.getLocation(LOCATIONTYPE.LOBBY);
        }
        return LocationManager.getLocation(LOCATIONTYPE.LOBBY);
    }
}
