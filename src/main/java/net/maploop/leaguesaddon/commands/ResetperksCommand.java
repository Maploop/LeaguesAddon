package net.maploop.leaguesaddon.commands;

import net.maploop.leaguesaddon.LeaguesAddon;
import net.maploop.leaguesaddon.handlers.DataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ResetperksCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (command.getLabel().equalsIgnoreCase("resetperks")) {
                if (player.hasPermission("perks.admin")) {
                    if (args.length == 0) {
                        player.sendMessage(ChatColor.RED + "Please specify a player!");
                        return true;
                    }
                    Player target = (Player) Bukkit.getPlayer(args[0]);
                    if (target.isOnline()) {
                        player.sendMessage("§aSuccessfully reset " + args[0] + "'s perks!");
                        DataHandler handler = new DataHandler(target, LeaguesAddon.getInstance());
                        handler.resetPerks();
                        return true;
                    }
                    player.sendMessage(ChatColor.RED + "That player is not online!");
                } else {
                    player.sendMessage(ChatColor.RED + "You cannot use this.");
                }
            }
        } else {
            commandSender.sendMessage("Nope!");
        }
        return false;
    }
}
