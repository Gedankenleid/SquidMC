package de.lamue.squidgame.squidgame.commands;

import de.lamue.squidgame.squidgame.utils.LOCATIONTYPE;
import de.lamue.squidgame.squidgame.utils.LocationManager;
import de.lamue.squidgame.squidgame.utils.Text;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

public class CommandSetGameSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("squidmc.setlocation")){
                if(args.length == 1){
                    Location location = player.getLocation();
                    String input = args[0];
                    LOCATIONTYPE locationtype = null;
                    if(input.equalsIgnoreCase("REDLIGHTGREENLIGHT")){
                        locationtype = LOCATIONTYPE.REDLIGHTGREENLIGHT;
                    }else if(input.equalsIgnoreCase("ZUCKERFIGUREN")){
                        locationtype = LOCATIONTYPE.ZUCKERFIGUR;
                    }else if(input.equalsIgnoreCase("TAUZIEHEN")){
                        locationtype = LOCATIONTYPE.TAUZIEHEN;
                    }else if(input.equalsIgnoreCase("MURMELN")){
                        locationtype = LOCATIONTYPE.MURMELN;
                    }else if(input.equalsIgnoreCase("TRITTSTEIN")){
                        locationtype = LOCATIONTYPE.TRITTSTEIN;
                    }else if(input.equalsIgnoreCase("TINTENFISCH")){
                        locationtype = LOCATIONTYPE.TINTENFISCH;
                    }else{
                        Text.sendTextToPlayerWithSound(player, Text.PREFIX+"§cDu hast folgende Möglichkeiten:", Sound.BLOCK_NOTE_BLOCK_PLING);
                        player.sendMessage(Text.PREFIX+"§cREDLIGHTGREENLIGHT");
                        player.sendMessage(Text.PREFIX+"§cZUCKERFIGUREN");
                        player.sendMessage(Text.PREFIX+"§cTAUZIEHEN");
                        player.sendMessage(Text.PREFIX+"§cMURMELN");
                        player.sendMessage(Text.PREFIX+"§cTRITTSTEIN");
                        player.sendMessage(Text.PREFIX+"§cTINTENFISCH");
                        return false;
                    }
                    if(locationtype != null){
                        LocationManager.setLocation(locationtype, location);
                        Text.sendTextToPlayerWithSound(player, Text.PREFIX+"Location §e"+locationtype.name()+" §7gesetzt!", Sound.BLOCK_NOTE_BLOCK_PLING);
                    }
                }else{
                    player.sendMessage(Text.PREFIX+"§cBitte nutze /setgamespawn [Game]");
                }
            }else{
                Text.sendNoPermMessage(player);
            }
        }else{
            sender.sendMessage("Du musst ein Spieler sein!");
            return true;
        }
        return false;
    }

}
