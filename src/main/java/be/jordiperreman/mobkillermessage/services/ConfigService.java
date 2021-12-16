package be.jordiperreman.mobkillermessage.services;


import be.jordiperreman.mobkillermessage.MobKillerMessage;
import be.jordiperreman.mobkillermessage.interfaces.IConfigService;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public class ConfigService implements IConfigService {

    public final FileConfiguration file;
    public List<String> mobsToDisplay;
    public String prefix;
    public String killMessage;

    public ConfigService(MobKillerMessage main) {
        this.file = main.getConfig();
    }

    @Override
    public void loadConfig() {
        file.options().copyDefaults(true);
        mobsToDisplay = file.getStringList("general.type-mobs-to-display-kill-message");
        prefix = file.getString("general.prefix");
        killMessage = file.getString("general.kill-message");

    }


}
