package net.maploop.leaguesaddon.listeners;

import net.maploop.leaguesaddon.menus.BreakConfirmMenu;
import net.maploop.leaguesaddon.menus.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (event.getBlock().getType().equals(Material.ENDER_PORTAL_FRAME)) {
            if (player.hasPermission("wells.admin")) {
                if (!(player.isSneaking())) {
                    player.sendMessage(ChatColor.RED + "You can break the well by holding sneak and breaking it!");
                    event.setCancelled(true);
                } else {
                    event.setCancelled(true);
                    Block block = event.getBlock();
                    new BreakConfirmMenu(new PlayerMenuUtility(player), block).open();
                }
            } else {
                event.setCancelled(true);
            }
        }
    }
}
