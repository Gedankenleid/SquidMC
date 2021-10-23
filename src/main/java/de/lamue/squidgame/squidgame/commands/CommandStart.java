package de.lamue.squidgame.squidgame.commands;

import de.lamue.squidgame.squidgame.utils.StartManager;
import de.lamue.squidgame.squidgame.utils.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandStart implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender.hasPermission("squidgame.start")){
            if(StartManager.isAbleToStart()){
                sender.sendMessage(Text.PREFIX+"Du hast das Spiel gestartet!");
                StartManager.start();
            }else{
                sender.sendMessage(Text.PREFIX+"§cEs sind nicht genügend Spieler online um das Spiel zu starten!");
            }
        }else{
            Text.sendNoPermMessage((Player) sender);
        }
        return true;
    }
}
