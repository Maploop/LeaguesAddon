package net.maploop.leaguesaddon.commands;

import net.maploop.leaguesaddon.LeaguesAddon;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SetmysterywellCommand implements CommandExecutor {
    public static Map<Block, List<ArmorStand>> tag = new HashMap<>();
    public static Map<Block, Integer> particle = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;
            Location loc = player.getLocation();
            Block block = loc.getBlock();
            block.setType(Material.ENDER_PORTAL_FRAME);
            ArmorStand stand = (ArmorStand) block.getWorld().spawnEntity(block.getLocation().add(+0.5, 0.3, +0.5), EntityType.ARMOR_STAND);
            ArmorStand stand1 = (ArmorStand) block.getWorld().spawnEntity(block.getLocation().add(+0.5, -0.1, +0.5), EntityType.ARMOR_STAND);
            tag.put(block, Arrays.asList(stand, stand1));
            stand.setCustomNameVisible(true);
            stand.setCustomName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Mystery Well" + ChatColor.GRAY + "");
            stand.setSmall(true);
            stand.setVisible(false);
            stand.setGravity(false);

            stand1.setCustomNameVisible(true);
            stand1.setCustomName(ChatColor.YELLOW + "" + ChatColor.BOLD + "RIGHT CLICK" + ChatColor.GRAY + "");
            stand1.setSmall(true);
            stand1.setVisible(false);
            stand1.setGravity(false);

        } else {
            commandSender.sendMessage(ChatColor.RED + "Nope!");
        }
        return false;
    }
}
