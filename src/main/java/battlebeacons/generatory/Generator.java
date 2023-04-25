package battlebeacons.generatory;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

class Generator {

    private final String name;
    private final Plugin plugin;
    private final Material material;
    private final Location location;
    private ArmorStand armorStand;

    //non final variables
    private int taskId = 0;

    public Generator(String name, Plugin plugin, Material material, Location location) {
        this.name = name;
        this.plugin = plugin;
        this.material = material;
        this.location = location;

    }

    public void spawn() {
        this.armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setGlowing(true);
        armorStand.setInvulnerable(true);
        armorStand.setCustomName(name);
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
        startTimer();
    }

    public void startTimer(){
        if (taskId != 0) throw new RuntimeException("Timer pro generator jiz bezi");
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,
                new GeneratorTimer(material, 3, armorStand.getLocation(), armorStand, name),
                0, 20); //20 ticks is one second in spigot
    }

    public void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskId);
    }

    public void destroy(){
        armorStand.remove();
        stopTimer();
    }
}
