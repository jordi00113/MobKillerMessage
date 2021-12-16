package be.jordiperreman.mobkillermessage.Listeners;

import be.jordiperreman.mobkillermessage.MobKillerMessage;
import be.jordiperreman.mobkillermessage.interfaces.IConfigService;
import be.jordiperreman.mobkillermessage.services.ConfigService;
import be.jordiperreman.mobkillermessage.utils.ChatUtil;
import jdk.nashorn.internal.runtime.regexp.joni.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.logging.Logger;

public class MobDeathListener implements Listener {

    public Logger logger = MobKillerMessage.LOGGER;
    public MobKillerMessage plugin;
    public ConfigService config;

    public MobDeathListener(MobKillerMessage plugin){
        this.plugin = plugin;
        this.config = (ConfigService) plugin.getConfigService();
    }

    @EventHandler
    public void onEntityDeathEvent(EntityDeathEvent event) {
        // Check player bypass
        // Check player permission?

        final Entity entity = event.getEntity();
        if(entity instanceof Mob){
            final Mob mob = (Mob) entity;
            final String entityName = mob.getName();
            logger.warning(entityName);
            if(config.mobsToDisplay.contains(entityName)){
                logger.warning(entityName + "IT CONTAINS");
                final Location loc = mob.getKiller().getLocation();
                final String attackerName = mob.getKiller().getName();
                for (Player p : Bukkit.getOnlinePlayers()) {
                    //Bypass
                    p.sendMessage(ChatUtil.color(config.prefix + String.format(config.killMessage,
                            attackerName,
                            entityName,
                            loc.getBlockX(),
                            loc.getBlockY(),
                            loc.getBlockZ())));
                }
            }
        }

        // If player permission?

        // if list contains animal

        // Print message


    }
}
