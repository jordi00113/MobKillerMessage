package be.jordiperreman.mobkillermessage;

import be.jordiperreman.mobkillermessage.Listeners.MobDeathListener;
import be.jordiperreman.mobkillermessage.interfaces.IConfigService;
import be.jordiperreman.mobkillermessage.services.ConfigService;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class MobKillerMessage extends JavaPlugin {
    public final static Logger LOGGER = PluginLogger.getLogger("MobKillerMessage");
    private final IConfigService configService = new ConfigService(this);

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new MobDeathListener(this),this);

        //Loads config
        this.saveDefaultConfig();
        this.configService.loadConfig();

        // RELOAD COMMANd
        // version command
        // Author command
        // BYPASS????
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public IConfigService getConfigService() {
        return this.configService;
    }
}
