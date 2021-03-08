package net.maploop.leaguesaddon.menus;

import net.maploop.leaguesaddon.LeaguesAddon;
import net.maploop.leaguesaddon.enums.Perk;
import net.maploop.leaguesaddon.handlers.DataHandler;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class PerksMenu extends Menu{
    public PerksMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getTitle() {
        return "Leagues Perks";
    }

    @Override
    public int getSize() {
        return 36;
    }

    @Override
    public void hadleMenu(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Economy eco = LeaguesAddon.getEconomy();
        DataHandler handler = new DataHandler(player, LeaguesAddon.getInstance());
        event.setCancelled(true);

        switch (event.getCurrentItem().getType()) {
            case BARRIER:
                player.closeInventory();
                break;
            case SKULL_ITEM:
                if (ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName().toLowerCase()).contains("golden heads")) {
                    if (event.getCurrentItem().getItemMeta().getLore().contains("§cYou cannot afford this!")) {
                        player.sendMessage(ChatColor.RED + "You cannot afford this perk!");
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 0);
                        setItems();
                        player.openInventory(inventory);
                        break;
                    }
                    if (event.getCurrentItem().getItemMeta().getLore().contains("§aOwned!")) {
                        player.sendMessage(ChatColor.RED + "You already own that perk!");
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1f, 0);
                        break;
                    }
                    handler.addPerk(Perk.GOLDEN_HEADS);
                    eco.withdrawPlayer(player, 500);
                    player.sendMessage(ChatColor.GREEN + "You purchased the §6Golden Heads §aperk!");
                    player.playSound(player.getLocation(), Sound.NOTE_PLING, 1f, 2f);
                    setItems();
                    player.openInventory(inventory);
                    break;
                }
            default:
                break;
        }
    }

    @Override
    public void setItems() {
        Economy eco = LeaguesAddon.getEconomy();
        DataHandler handler = new DataHandler(playerMenuUtility.getOwner(), LeaguesAddon.getInstance());
        Player player = playerMenuUtility.getOwner();
        setFilter();

        ItemStack close = makeItem(Material.BARRIER, "§cClose", 1, 0);
        inventory.setItem(31, close);

        ItemStack golden_heads;
        if (!(handler.getPerks().contains("GOLDEN_HEADS"))) {
            if (eco.getBalance(player) < 500) {
                golden_heads = makeCustomSkullItem("http://textures.minecraft.net/texture/3bb612eb495ede2c5ca5178d2d1ecf1ca5a255d25dfc3c254bc47f6848791d8", "§cGolden Heads", 1,
                        "§6Golden Apples §7you earn",
                        "§7turn into §6Golden Heads§7.",
                        "",
                        "§7Cost: §6500t",
                        "",
                        "§cYou cannot afford this!");
            } else {
                golden_heads = makeCustomSkullItem("http://textures.minecraft.net/texture/3bb612eb495ede2c5ca5178d2d1ecf1ca5a255d25dfc3c254bc47f6848791d8", "§eGolden Heads", 1,
                        "§6Golden Apples §7you earn",
                        "§7turn into §6Golden Heads§7.",
                        "",
                        "§7Cost: §6500t",
                        "",
                        "§aClick to purchase!");
            }
        } else {
            golden_heads = makeCustomSkullItem("http://textures.minecraft.net/texture/3bb612eb495ede2c5ca5178d2d1ecf1ca5a255d25dfc3c254bc47f6848791d8", "§aGolden Heads", 1,
                    "§6Golden Apples §7you earn",
                    "§7turn into §6Golden Heads§7.",
                    "",
                    "§aOwned!");
        }

        inventory.setItem(10, golden_heads);

        ItemStack commingsoon = makeItem(Material.BEDROCK, "§cUnavailable Perk!", 1, 0, "§7This perk is either ", "§7disabled or not released yet!");
        inventory.setItem(12, commingsoon);
        inventory.setItem(14, commingsoon);
        inventory.setItem(16, commingsoon);
    }
}
