package battlebeacons.commands;

import battlebeacons.generatory.IronGenerator;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnGeneratorCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))return false;

        Player player = (Player) sender;
        Location location = player.getLocation();
        IronGenerator ironGenerator = new IronGenerator(location);

        ironGenerator.spawnIronGenerator(location.getWorld(), location);
        return true;
    }
}
