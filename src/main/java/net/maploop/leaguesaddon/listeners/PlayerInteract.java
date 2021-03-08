package net.maploop.leaguesaddon.listeners;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerInteract implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (item == null) return;
            if (!(item.hasItemMeta())) return;

            if (item.getItemMeta().getDisplayName().contains("§6Golden Head")) {
                event.setCancelled(true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 0));
                player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 600, 1));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 1));
                player.getInventory().removeItem(player.getItemInHand());
                player.playSound(player.getLocation(), Sound.EAT, 1f, 1f);
            }
        }
    }
}
