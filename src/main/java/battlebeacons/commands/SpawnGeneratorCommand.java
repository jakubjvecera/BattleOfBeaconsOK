package battlebeacons.commands;

import battlebeacons.generatory.IronGenerator;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SpawnGeneratorCommand implements CommandExecutor {

    private final Plugin plugin;

    public SpawnGeneratorCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))return false;

        Player player = (Player) sender;
        Location location = player.getLocation();
        //chyba bude pravdepodobne tady
        IronGenerator ironGenerator = new IronGenerator(plugin);

        ironGenerator.spawnIronGenerator(location.getWorld(), location);
        return true;
    }
}
