package be.jordiperreman.mobkillermessage.Listeners;

import be.jordiperreman.mobkillermessage.MobKillerMessage;
import be.jordiperreman.mobkillermessage.utils.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.logging.Logger;

public class MobDeathListener implements Listener {

    public Logger logger = MobKillerMessage.LOGGER;
    public FileConfiguration fc;

    public MobDeathListener(MobKillerMessage plugin) {
        this.fc = plugin.getConfig();
    }

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event) {
        final Entity entity = event.getEntity();
        if (entity instanceof Mob) {
            final Mob mob = (Mob) entity;
            final Player player = mob.getKiller();
            if (player != null) {
                if (!player.hasPermission("mobkillermessage.deathmessagesender.bypass")) {
                    final String entityName = mob.getName();
                    logger.warning(entityName);
                    if (fc.getStringList("general.type-mobs-to-display-kill-message").contains(entityName)) {
                        final Location loc = player.getLocation();
                        final String attackerName = player.getName();
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (!player.hasPermission("mobkillermessage.deathmessagereceiver.bypass")) {
                                p.sendMessage(ChatUtil.color(fc.getString("general.messages.prefix") +
                                        String.format(fc.getString("general.messages.kill-message"),
                                                attackerName,
                                                entityName,
                                                loc.getBlockX(),
                                                loc.getBlockY(),
                                                loc.getBlockZ())));
                            }
                        }
                    }
                }
            }
        }
    }
}
