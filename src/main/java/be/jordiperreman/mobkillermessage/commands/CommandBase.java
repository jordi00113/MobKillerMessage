package be.jordiperreman.mobkillermessage.commands;

import be.jordiperreman.mobkillermessage.MobKillerMessage;
import be.jordiperreman.mobkillermessage.interfaces.IConfigService;
import be.jordiperreman.mobkillermessage.interfaces.ISubCommand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CommandBase implements CommandExecutor, TabCompleter {
    protected final IConfigService configService;
    protected CommandSender sender;
    protected Command command;
    protected String[] args;
    protected Map<String, ISubCommand> commands = new HashMap<>();
    protected MobKillerMessage plugin;

    public CommandBase(MobKillerMessage plugin) {
        this.plugin = plugin;
        this.configService = plugin.getConfigService();
    }

    /**
     * Overriding default command to use doCommand and set some initial variables;
     *
     * @param csender sender of the command
     * @param cmd     command
     * @param label   alias of command used
     * @param cargs   command arguments
     */
    @Override
    public boolean onCommand(@NotNull CommandSender csender, @NotNull Command cmd, @NotNull String label, String[] cargs) {
        sender = csender;
        command = cmd;
        args = cargs;
        return doCommand(sender, command, args);
    }

    /**
     * Abstract method that base classes need to override
     *
     * @return false if invalid arguments, true otherwise
     */
    protected abstract boolean doCommand(CommandSender sender, Command command, String[] args);

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return new ArrayList<>(commands.keySet());
    }

    /**
     * Add subcommands to list command.
     */
    public abstract void registerSubCommands();

    /**
     * Get permission of command.
     */
    public abstract String getPermission();

}
