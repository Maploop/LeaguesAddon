package net.maploop.leaguesaddon.util;

import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Utilities {
    public static String colorize(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static ItemStack storeStringInItem(ItemStack host, String key, String value) {
        ItemMeta meta = host.getItemMeta();

        net.minecraft.server.v1_8_R3.ItemStack stack = CraftItemStack.asNMSCopy(host);
        NBTTagCompound tag = stack.getTag();
        tag.setString(key, value);
        stack.setTag(tag);

        return host;
    }
}
