package battlebeacons.generatory;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import java.util.Timer;

public class IronGenerator {
    ArmorStand armorStand;
    String name = ChatColor.WHITE + "Iron Generator";
    Location location;
    Timer timer = new Timer();

    public IronGenerator(Location location) {
        this.location = location;
    }

    public void spawnIronGenerator(World world, Location location){
        armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setGlowing(true);
        armorStand.setInvulnerable(true);
        armorStand.setCustomName(name);
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
        startTimer();
    }

    public void startTimer(){
        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        timer.schedule(new GeneratorTimer(iron, 2, location, armorStand, name), 1000);
    }

    public void stopTimer(){
        timer.cancel();
    }
}
