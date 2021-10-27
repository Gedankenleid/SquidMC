package de.lamue.squidgame.squidgame.utils;

import de.lamue.squidgame.squidgame.SquidMC;
import de.lamue.squidgame.squidgame.utils.database.CoinsManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

public class Scoreboard {

    public void setScoreboard(Player player) {
        org.bukkit.scoreboard.Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective objective = scoreboard.registerNewObjective("aaa", "bbb");

        Team money = scoreboard.registerNewTeam("money");
        money.setPrefix(" §7");
        money.setSuffix("§7" + CoinsManager.getMoney(player));
        money.addEntry(ChatColor.BLUE.toString());

        Team currentPlayersAlive = scoreboard.registerNewTeam("playersonline");
        currentPlayersAlive.setPrefix(" §7");
        String nextnav = "";
        if(!GameManager.getCurrentGameStatus().equals(GAMESTATUS.INGAME)){
            currentPlayersAlive.setSuffix("§7"+Bukkit.getOnlinePlayers().size());
        }else{
            currentPlayersAlive.setSuffix("§7"+PlayerManager.isAliveList.size());
        }
        currentPlayersAlive.addEntry(ChatColor.RED.toString());

        Team game = scoreboard.registerNewTeam("game");
        game.setPrefix(" §7");
        game.setSuffix("§7" + GAME.getName(GameManager.getNextGame()));
        game.addEntry(ChatColor.GREEN.toString());

        Team admin = scoreboard.registerNewTeam("00001admin");
        Team dev = scoreboard.registerNewTeam("00002dev");
        Team mod = scoreboard.registerNewTeam("00003mod");
        Team sup = scoreboard.registerNewTeam("00004sup");
        Team builder = scoreboard.registerNewTeam("00005builder");
        Team social = scoreboard.registerNewTeam("00006social");
        Team premium = scoreboard.registerNewTeam("00007premium");
        Team user = scoreboard.registerNewTeam("00008user");

        admin.setPrefix(ChatColor.DARK_RED+"Admin "+ChatColor.DARK_GRAY+"| "+ChatColor.DARK_RED);
        dev.setPrefix(ChatColor.AQUA+"Dev "+ChatColor.DARK_GRAY+"| "+ChatColor.AQUA);
        mod.setPrefix(ChatColor.RED+"Mod "+ChatColor.DARK_GRAY+"| "+ChatColor.RED);
        sup.setPrefix(ChatColor.GREEN+"Sup "+ChatColor.DARK_GRAY+"| "+ChatColor.GREEN);
        builder.setPrefix(ChatColor.DARK_PURPLE+"Builder "+ChatColor.DARK_GRAY+"| "+ChatColor.DARK_PURPLE);
        social.setPrefix(ChatColor.BLUE+"Social "+ChatColor.DARK_GRAY+"| "+ChatColor.BLUE);
        premium.setPrefix(ChatColor.YELLOW+"");
        user.setPrefix(ChatColor.GRAY+"");


        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§3§lSquid§c§lMC§7§l.net");
        objective.getScore("§c§l§b§e").setScore(9);
        objective.getScore("§3§lCoins").setScore(8);
        objective.getScore(ChatColor.BLUE.toString()).setScore(7);
        objective.getScore("§c§e§b").setScore(6);
        objective.getScore("§3§lSpieler").setScore(5);
        objective.getScore(ChatColor.RED.toString()).setScore(4);
        objective.getScore("§a§b§e§a").setScore(3);
        objective.getScore("§3§lSpiel").setScore(2);
        objective.getScore(ChatColor.GREEN.toString()).setScore(1);
        objective.getScore("§c§b§f§a").setScore(0);

        for(Player current : Bukkit.getOnlinePlayers()){
            if(current.hasPermission("squidmc.admin")){
                scoreboard.getTeam("00001admin").addEntry(current.getName());
            }else if(current.hasPermission("squidmc.developer")){
                scoreboard.getTeam("00002dev").addEntry(current.getName());
            }else if(current.hasPermission("squidmc.moderator")){
                scoreboard.getTeam("00003mod").addEntry(current.getName());
            }else if(current.hasPermission("squidmc.supporter")){
                scoreboard.getTeam("00004sup").addEntry(current.getName());
            }else if(current.hasPermission("squidmc.builder")){
                scoreboard.getTeam("00005builder").addEntry(current.getName());
            }else if(current.hasPermission("squidmc.socialmedia")){
                scoreboard.getTeam("00006social").addEntry(current.getName());
            }else if(current.hasPermission("squidmc.premium")){
                scoreboard.getTeam("00007premium").addEntry(current.getName());
            }else{
                scoreboard.getTeam("00008user").addEntry(current.getName());
            }
        }

        player.setScoreboard(scoreboard);
    }

    public void update() {
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(SquidMC.pluginInstance, new Runnable() {
            @Override
            public void run() {
                try {
                    for(Player all : Bukkit.getOnlinePlayers()) {
                        Player player = all;
                        org.bukkit.scoreboard.Scoreboard scoreboard = all.getScoreboard();
                        if(scoreboard == null) {
                            setScoreboard(all);
                        }

                        Player p = all;

                        if(p.hasPermission("squidmc.admin")){
                            p.setDisplayName("§4§lAdmin §8| §4"+p.getName()+"§7");
                        }else if(p.hasPermission("squidmc.developer")){
                            p.setDisplayName("§b§lDev §8| §b"+p.getName()+"§7");
                        }else if(p.hasPermission("squidmc.moderator")){
                            p.setDisplayName("§c§lMod §8| §c"+p.getName()+"§7");
                        }else if(p.hasPermission("squidmc.supporter")){
                            p.setDisplayName("§a§lSup §8| §a"+p.getName()+"§7");
                        }else if(p.hasPermission("squidmc.builder")){
                            p.setDisplayName("§5§lBuilder §8| §5"+p.getName()+"§7");
                        }else if(p.hasPermission("squidmc.socialmedia")){
                            p.setDisplayName("§9§lSocial §8| §9"+p.getName()+"§7");
                        }else if(p.hasPermission("squidmc.premium")){
                            p.setDisplayName("§6Premium §8| §6"+p.getName()+"§7");
                        }else{
                            p.setDisplayName("§7"+p.getName()+"§7");
                        }

                        if(player.hasPermission("squidmc.admin")){
                            scoreboard.getTeam("00001admin").addEntry(player.getName());
                        }else if(player.hasPermission("squidmc.developer")){
                            scoreboard.getTeam("00002dev").addEntry(player.getName());
                        }else if(player.hasPermission("squidmc.moderator")){
                            scoreboard.getTeam("00003mod").addEntry(player.getName());
                        }else if(player.hasPermission("squidmc.supporter")){
                            scoreboard.getTeam("00004sup").addEntry(player.getName());
                        }else if(player.hasPermission("squidmc.builder")){
                            scoreboard.getTeam("00005builder").addEntry(player.getName());
                        }else if(player.hasPermission("squidmc.socialmedia")){
                            scoreboard.getTeam("00006social").addEntry(player.getName());
                        }else if(player.hasPermission("squidmc.premium")){
                            scoreboard.getTeam("00007premium").addEntry(player.getName());
                        }else{
                            scoreboard.getTeam("00008user").addEntry(player.getName());
                        }

                        Team money = scoreboard.getTeam("money");
                        money.setSuffix("§7" + CoinsManager.getMoney(all)+" Coins");

                        Team nav = scoreboard.getTeam("playersonline");
                        if(!GameManager.getCurrentGameStatus().equals(GAMESTATUS.INGAME)){
                            nav.setSuffix("§7" + Bukkit.getOnlinePlayers().size());
                        }else{
                            nav.setSuffix("§7" + PlayerManager.isAliveList.size());
                        }
                        Team map = scoreboard.getTeam("game");
                        map.setSuffix("§7" + GAME.getName(GameManager.getNextGame()));
                    }
                }catch (NullPointerException exc){}
            }
        }, 0, 1);
    }

}
