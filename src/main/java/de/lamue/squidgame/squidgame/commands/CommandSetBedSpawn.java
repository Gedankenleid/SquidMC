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

public class CommandSetBedSpawn implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(player.hasPermission("squidmc.setlocation")){
                Location playerLocation = player.getLocation();
                LocationManager.setLocation(LOCATIONTYPE.BEDROOM, playerLocation);
                Text.sendTextToPlayerWithSound(player, Text.PREFIX+"Bettenspawn gesetzt!", Sound.BLOCK_NOTE_BLOCK_PLING);
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
