package net.maploop.leaguesaddon.listeners;

import net.maploop.leaguesaddon.LeaguesAddon;
import net.maploop.leaguesaddon.handlers.DataHandler;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        DataHandler handler = new DataHandler(player, LeaguesAddon.getInstance());
        handler.setData();
    }
}
