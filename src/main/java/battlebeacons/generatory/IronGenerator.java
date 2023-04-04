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


    public void spawnIronGenerator(World world, Location location){
        armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setGlowing(true);
        armorStand.setInvulnerable(true);
        armorStand.setCustomName(name);
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
    }

    /*public void startTimer(){
        Timer timer = new Timer();
        timer.schedule(new GeneratorTimer(new ItemStack(Material.IRON_INGOT),2, new Location()),1000);
    }*/
}
