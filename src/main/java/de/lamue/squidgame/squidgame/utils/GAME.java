package de.lamue.squidgame.squidgame.utils;

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
}
