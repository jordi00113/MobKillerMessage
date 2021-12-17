package be.jordiperreman.mobkillermessage.services;


import be.jordiperreman.mobkillermessage.MobKillerMessage;
import be.jordiperreman.mobkillermessage.interfaces.IConfigService;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigService implements IConfigService {

    public final FileConfiguration file;

    public ConfigService(MobKillerMessage main) {
        this.file = main.getConfig();
    }

    @Override
    public void loadConfig() {
        file.options().copyDefaults(true);
    }

}
