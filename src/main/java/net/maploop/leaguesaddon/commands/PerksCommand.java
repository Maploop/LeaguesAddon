package net.maploop.leaguesaddon.commands;

import net.maploop.leaguesaddon.menus.PerksMenu;
import net.maploop.leaguesaddon.menus.PlayerMenuUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PerksCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!(commandSender instanceof Player)) return true;

        Player player = (Player) commandSender;
        if (s.equalsIgnoreCase("perks")) {
            new PerksMenu(new PlayerMenuUtility(player)).open();
        }
        return false;
    }
}
