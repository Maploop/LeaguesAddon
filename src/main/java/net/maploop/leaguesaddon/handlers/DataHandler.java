package net.maploop.leaguesaddon.handlers;

import net.maploop.leaguesaddon.LeaguesAddon;
import net.maploop.leaguesaddon.enums.Perk;
import org.bukkit.entity.Player;

import java.util.List;

public class DataHandler {
    private final Player player;
    private final LeaguesAddon plugin;

    public DataHandler(Player player, LeaguesAddon plugin) {
        this.player = player;
        this.plugin = plugin;
    }

    public void setData() {
        if (exists()) return;

        plugin.getData().set("data.players." + player.getUniqueId().toString() + ".username", player.getName());
        plugin.getData().set("data.players." + player.getUniqueId().toString() + ".owned-perks", new String[] {"NONE"});
        plugin.saveData();
    }

    public void addPerk(Perk perk) {
        if (!(exists())) return;

        List<String> perks = plugin.getData().getStringList("data.players." + player.getUniqueId().toString() + ".owned-perks");
        if (perks.contains(perk.toString())) return;

        perks.add(perk.toString());
        plugin.getData().set("data.players." + player.getUniqueId().toString() + ".owned-perks", perks);
        plugin.saveData();
    }

    public void resetPerks() {
        if (!(exists())) return;

        plugin.getData().set("data.players." + player.getUniqueId().toString() + ".owned-perks", new String[] {"NONE"});
        plugin.saveData();
    }

    public List<String> getPerks() {
        return plugin.getData().getStringList("data.players." + player.getUniqueId().toString() + ".owned-perks");
    }

    private boolean exists() {
        return (plugin.getData().getString("data.players." + player.getUniqueId().toString()) != null);
    }
}
