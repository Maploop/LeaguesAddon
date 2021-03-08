package net.maploop.leaguesaddon.menus;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class BreakConfirmMenu extends Menu{
    private final Block block;
    public BreakConfirmMenu(PlayerMenuUtility playerMenuUtility, Block block) {
        super(playerMenuUtility);
        this.block = block;
    }

    @Override
    public String getTitle() {
        return "Are you sure?";
    }

    @Override
    public int getSize() {
        return 27;
    }

    @Override
    public void hadleMenu(InventoryClickEvent event) {
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();

        switch (event.getCurrentItem().getItemMeta().getDisplayName()) {
            case "§aConfirm":
                block.setType(Material.AIR);
                for (Entity e : player.getWorld().getNearbyEntities(block.getLocation(), 0.5, 0.5, 0.5)) {
                    if (e.getType().equals(EntityType.ARMOR_STAND)) {
                        e.remove();
                    }
                }

                player.sendMessage(ChatColor.GREEN + "Successfully removed this well!");
                player.playSound(player.getLocation(), Sound.GLASS, 1f, 0);
                player.closeInventory();
                break;
            case "§cCancel":
                player.sendMessage(ChatColor.RED + "Action cancelled.");
                player.closeInventory();
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 0);
                break;
            default:
                event.setCancelled(true);
                break;
        }
    }

    @Override
    public void setItems() {
        ItemStack confirm = makeItem(Material.STAINED_CLAY, ChatColor.GREEN + "Confirm", 1, 5,
                ChatColor.GRAY + "Confirm breaking the",
                ChatColor.GRAY + "this well?",
                "",
                ChatColor.RED + "Warning: This action is permenant.",
                "",
                ChatColor.YELLOW + "Click to remove!");

        ItemStack cancel = makeItem(Material.STAINED_CLAY, ChatColor.RED + "Cancel", 1, 14, ChatColor.YELLOW + "Click to cancel.");

        inventory.setItem(11, confirm);
        inventory.setItem(15, cancel);
    }
}
