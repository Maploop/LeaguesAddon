package net.maploop.leaguesaddon.menus;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MysterywellMenu extends Menu{
    public MysterywellMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getTitle() {
        return "Mystery Well";
    }

    @Override
    public int getSize() {
        return 54;
    }

    @Override
    public void hadleMenu(InventoryClickEvent event) {
        event.setCancelled(true);
        Player player = (Player) event.getWhoClicked();
        
        switch (event.getSlot()) {
            case 49:
                player.closeInventory();
                break;
            default:
                event.setCancelled(true);
                break;
        }
    }

    @Override
    public void setItems() {
        setFilter();

        ItemStack wooden = makeCustomSkullItem("http://textures.minecraft.net/texture/b10cf36282b4527ec684c7102646da2c86375fe2aa096d5ed13e433ae5850f70",
                "§aWooden Mystery Chest", 1,
                "§7Open this chest to",
                "§7get wacky tools!",
                "",
                "§eClick to open!");

        inventory.setItem(20, wooden);
        
        ItemStack close = makeItem(Material.BARRIER, "§cClose", 1, 0);
        inventory.setItem(49, close);
    }
}
