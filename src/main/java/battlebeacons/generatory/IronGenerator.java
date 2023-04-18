package battlebeacons.generatory;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

public final class IronGenerator {
    private static final String NAME = ChatColor.WHITE + "Iron Generator";
    private final Plugin plugin;
    ArmorStand armorStand;

    //non final variables
    private int taskId = 0;

    public IronGenerator(Plugin plugin) {
        this.plugin = plugin;
    }

    public IronGenerator spawnIronGenerator(World world, Location location) {
        ArmorStand armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setGlowing(true);
        armorStand.setInvulnerable(true);
        armorStand.setCustomName(NAME);
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
        this.armorStand = armorStand;
        return this;
    }

    public void startTimer(Location location, ArmorStand armorStand){
        if (taskId != 0) stopTimer();
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,
                new GeneratorTimer(Material.IRON_INGOT, 3, location, armorStand, NAME),
                0, 20); //20 ticks is one second in spigot
    }

    public void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskId);
    }

    public void destroy(){
        armorStand.damage(40.0);
    }
}
