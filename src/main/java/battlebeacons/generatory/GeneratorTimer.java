package battlebeacons.generatory;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;

public final class GeneratorTimer implements Runnable {
    private final Material material;
    private final int cas;
    private final Location location;
    private final World world;
    private final Entity nameableEntity;
    private final String jmeno;

    //non - final variables
    private int odpocet;

    public GeneratorTimer(Material material, int cas, Location location, Entity nameableEntity, String jmeno) {
        this.material = material;
        this.location = location;
        this.world = location.getWorld();
        this.nameableEntity = nameableEntity;
        this.jmeno = jmeno;
        this.cas = cas;
        this.odpocet = cas;
    }


    @Override
    public void run() {

        if (odpocet < 0) {
            world.dropItem(location, new ItemStack(material, 1));
            odpocet = cas;
        } else {
            nameableEntity.setCustomName(jmeno + " " + odpocet);
            nameableEntity.setCustomNameVisible(true);
            odpocet--;
        }
    }
}
//zelezo 2, zlato 6, emerald 50, netherite 90