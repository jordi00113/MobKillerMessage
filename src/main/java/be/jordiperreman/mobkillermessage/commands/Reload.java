package be.jordiperreman.mobkillermessage.commands;

import be.jordiperreman.mobkillermessage.MobKillerMessage;
import be.jordiperreman.mobkillermessage.interfaces.ISubCommand;
import be.jordiperreman.mobkillermessage.utils.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Reload extends MobKillerMessageCmd implements ISubCommand {


    public Reload(MobKillerMessage plugin) {
        super(plugin);
    }

    @Override
    public boolean doCommand(CommandSender sender, Command command, String[] args) {

        if (!sender.hasPermission(getPermission())) {
            sender.sendMessage(ChatUtil.color(fc.getString("general.messages.no_perm")));
            return true;
        }
        configService.loadConfig();
        sender.sendMessage(ChatUtil.color(fc.getString("general.messages.reload")));
        return true;
    }

    @Override
    public String getPermission() {
        return "mobkillermessage.reload";
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }
}
