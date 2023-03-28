package battlebeacons.generatory;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;

public class GeneratorTimer implements Runnable{
    ItemStack itemStack;
    int cas;
    Location location;
    World world;
    int casPuvodni = cas;
    ArmorStand armorStand;
    String jmeno;

    public GeneratorTimer(ItemStack itemStack, int cas, Location location, World world, ArmorStand armorStand, String jmeno) {
        this.itemStack = itemStack;
        this.cas = cas;
        this.location = location;
        this.world = world;
        this.armorStand = armorStand;
        this.jmeno = jmeno;
    }

    @Override
    public void run() {
        if (cas == 0){
            world.dropItem(location, itemStack);
            cas = casPuvodni;
        }else {
            armorStand.setCustomName(jmeno + " " + cas);
            armorStand.setCustomNameVisible(true);
        }
    }
}
//zelezo 2, zlato 6, emerald 50, netherite 90