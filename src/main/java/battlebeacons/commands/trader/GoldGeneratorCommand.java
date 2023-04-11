package battlebeacons.commands.trader;

import battlebeacons.generatory.GoldGenerator;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class GoldGeneratorCommand implements CommandExecutor {

    private final Plugin plugin;

    public GoldGeneratorCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))return false;

        Player player = (Player) sender;
        Location location = player.getLocation();

        GoldGenerator goldGenerator = new GoldGenerator(plugin);

        goldGenerator.spawnGoldGenerator(location.getWorld(), location);
        return true;
    }
}
