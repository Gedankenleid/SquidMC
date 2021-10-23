package de.lamue.squidgame.squidgame.utils.database;

import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CoinsManager {

    public static boolean isUserExists(UUID uuid)
    {
        try
        {
            ResultSet rs = MySQL.query("SELECT * FROM Coins WHERE UUID= '" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
            return false;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static void updateUsername(Player p){
        if(isUserExists(p.getUniqueId())){
            MySQL.update("UPDATE Coins SET SPIELERNAME = '"+p.getName()+"' WHERE UUID='"+p.getUniqueId()+"';");
        }
    }

    public static void addCoins(Player p, int coins)
    {
        if (isUserExists(p.getUniqueId()))
        {
            int anzahl = getMoney(p) + coins;
            MySQL.update("UPDATE Coins SET SPIELERNAME = '" + p.getName() + "' WHERE UUID = '" + p.getUniqueId() + "';");
            MySQL.update("UPDATE Coins SET ANZAHL= '" + anzahl + "' WHERE UUID = '" + p.getUniqueId() + "';");
        }
        else
        {
            createPlayer(p.getUniqueId(), p.getName());
            addCoins(p, coins);
        }
    }

    public static void removeCoins(Player p, int coins)
    {
        if (isUserExists(p.getUniqueId()))
        {
            int anzahl = getMoney(p) - coins;
            MySQL.update("UPDATE Coins SET SPIELERNAME = '" + p.getName() + "' WHERE UUID = '" + p.getUniqueId() + "';");
            MySQL.update("UPDATE Coins SET ANZAHL= '" + anzahl + "' WHERE UUID = '" + p.getUniqueId() + "';");
        }
        else
        {
            createPlayer(p.getUniqueId(), p.getName());
            removeCoins(p, coins);
        }
    }

    private static void setCoins(UUID uuid, String playername, int anzahl)
    {
        if (isUserExists(uuid))
        {
            MySQL.update("UPDATE Coins SET SPIELERNAME = '" + playername + "' WHERE UUID = '" + uuid + "';");
            MySQL.update("UPDATE Coins SET ANZAHL= '" + anzahl + "' WHERE UUID = '" + uuid + "';");
        }
        else
        {
            createPlayer(uuid, playername);
            setCoins(uuid, playername, anzahl);
        }
    }

    public static void createPlayer(UUID uuid, String playername)
    {
        if (!isUserExists(uuid)) {
            MySQL.update("INSERT INTO Coins(UUID, SPIELERNAME, ANZAHL) VALUES ('" + uuid + "', '" + playername + "', '0');");
        }
    }

    public static int getMoney(Player p)
    {
        try
        {
            //ResultSet rs = MySQL.query("SELECT * FROM Coins WHERE UUID = '" + p.getUniqueId() + "';");
            //if (rs.next()) {
            //    return rs.getInt("ANZAHL");
            //}
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

}
