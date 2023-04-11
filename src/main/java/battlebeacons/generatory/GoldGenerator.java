package battlebeacons.generatory;

import org.bukkit.*;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.Plugin;

public final class GoldGenerator {
    private static final String NAME = ChatColor.WHITE + "Gold Generator";
    private final Plugin plugin;

    //non final variables
    private int taskId = 0;

    public GoldGenerator(Plugin plugin) {
        this.plugin = plugin;
    }

    public void spawnGoldGenerator(World world, Location location) {
        ArmorStand armorStand = (ArmorStand) world.spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setGlowing(true);
        armorStand.setInvulnerable(true);
        armorStand.setCustomName(NAME);
        armorStand.setCustomNameVisible(true);
        armorStand.setInvisible(true);
        if (taskId != 0) stopTimer();
        taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin,
                new GeneratorTimer(Material.GOLD_INGOT, 6, location, armorStand, NAME),
                0, 20); //20 ticks is one second in spigot
    }

    public void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskId);
    }
}
