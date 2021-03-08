package net.maploop.leaguesaddon.listeners;

import net.maploop.leaguesaddon.LeaguesAddon;
import net.maploop.leaguesaddon.handlers.DataHandler;
import net.maploop.leaguesaddon.menus.Menu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeath implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onDeath(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        DataHandler handler = new DataHandler(killer, LeaguesAddon.getInstance());

        if (handler.getPerks().contains("GOLDEN_HEADS")) {
            ItemStack golden_head = Menu.makeCustomSkullItem("http://textures.minecraft.net/texture/3bb612eb495ede2c5ca5178d2d1ecf1ca5a255d25dfc3c254bc47f6848791d8", "§6Golden Head", 1);
            killer.getInventory().addItem(golden_head);
        }
    }
}
