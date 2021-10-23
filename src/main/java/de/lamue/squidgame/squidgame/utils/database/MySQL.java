package de.lamue.squidgame.squidgame.utils.database;

import de.lamue.squidgame.squidgame.SquidMC;
import de.lamue.squidgame.squidgame.utils.ConfigurationManager;
import de.lamue.squidgame.squidgame.utils.Text;
import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL {

    public static Connection con;


    public static void connect()
    {
        String HOST = ConfigurationManager.getString("MySQL.host");
        String DATABASE = ConfigurationManager.getString("MySQL.database");
        String USER = ConfigurationManager.getString("MySQL.user");
        String PASSWORD = ConfigurationManager.getString("MySQL.password");
        int port = ConfigurationManager.getInteger("MySQL.port");
        try
        {
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + port + "/" + DATABASE + "?autoReconnect=true", USER, PASSWORD);
        }
        catch (SQLException e)
        {
            Bukkit.getConsoleSender().sendMessage(Text.PREFIX+"§cDie Verbindung zur MySQL ist fehlgeschlagen! Fehler: " + e.getMessage());
        }
    }

    public static void close()
    {
        try
        {
            if (con != null)
            {
                con.close();
            }
        }
        catch (SQLException e)
        {
            Bukkit.getConsoleSender().sendMessage(Text.PREFIX+"§cFehler beim beenden der Verbindung zur MySQL! Fehler: " + e.getMessage());
        }
    }

    public static void update(String qry)
    {
        try
        {
            Statement st = con.createStatement();
            st.executeUpdate(qry);
            st.close();
        }
        catch (SQLException e)
        {
            connect();
            System.err.println(e);
        }
    }

    public static ResultSet query(String qry)
    {
        ResultSet rs = null;
        try
        {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        }
        catch (SQLException e)
        {
            connect();
            System.err.println(e);
        }
        return rs;
    }

    public static void keepOnline(){
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(SquidMC.pluginInstance, new Runnable() {
            @Override
            public void run() {
                query("SELECT * FROM Coins");
            }
        }, 20*60, 20*60);
    }

}
