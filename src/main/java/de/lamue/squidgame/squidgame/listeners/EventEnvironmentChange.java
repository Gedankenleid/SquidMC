package de.lamue.squidgame.squidgame.listeners;

import de.lamue.squidgame.squidgame.SquidMC;
import de.lamue.squidgame.squidgame.utils.LOCATIONTYPE;
import de.lamue.squidgame.squidgame.utils.LocationManager;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EventEnvironmentChange implements Listener {

    @EventHandler
    public void onWeatherChange(org.bukkit.event.weather.WeatherChangeEvent e){
        e.setCancelled(true);
    }

    public static void keepDay(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SquidMC.pluginInstance, new Runnable() {
            @Override
            public void run() {
                LocationManager.getLocation(LOCATIONTYPE.LOBBY).getWorld().setTime(6000);
                LocationManager.getLocation(LOCATIONTYPE.REDLIGHTGREENLIGHT).getWorld().setTime(6000);
                LocationManager.getLocation(LOCATIONTYPE.ZUCKERFIGUR).getWorld().setTime(6000);
                LocationManager.getLocation(LOCATIONTYPE.TAUZIEHEN).getWorld().setTime(6000);
                LocationManager.getLocation(LOCATIONTYPE.MURMELN).getWorld().setTime(6000);
                LocationManager.getLocation(LOCATIONTYPE.TRITTSTEIN).getWorld().setTime(6000);
                LocationManager.getLocation(LOCATIONTYPE.TINTENFISCH).getWorld().setTime(6000);
            }
        }, 0, 20);
    }

}
