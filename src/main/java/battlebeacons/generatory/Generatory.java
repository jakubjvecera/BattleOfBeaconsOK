package battlebeacons.generatory;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public final class Generatory {
    private final List<Generator> generatory = new ArrayList<>();
    private final Plugin plugin;

    public Generatory(Plugin plugin) {
        this.plugin = plugin;
    }

    public void add(Generator generator) {
        generatory.add(generator);
    }

    public void destroyAll() {
        generatory.forEach(Generator::destroy);
        generatory.clear();
    }

    public void loadFromConfig() {
        var config = plugin.getConfig();
        add(createIronGenerator(config.getLocation("ironGenerator1Location")));
        add(createIronGenerator(config.getLocation("ironGenerator2Location")));
        add(createIronGenerator(config.getLocation("ironGenerator3Location")));
        add(createIronGenerator(config.getLocation("ironGenerator4Location")));
        add(createGoldGenerator(config.getLocation("goldGenerator1Location")));
        add(createGoldGenerator(config.getLocation("goldGenerator2Location")));
        add(createGoldGenerator(config.getLocation("goldGenerator3Location")));
        add(createGoldGenerator(config.getLocation("goldGenerator4Location")));
    }

    public Generator createGoldGenerator(Location location)
    {
        Generator generator = new Generator("GOLD GENERATOR", plugin, Material.GOLD_INGOT, location);
        generator.spawn();
        add(generator);
        return generator;
    }

    public Generator createIronGenerator(Location location)
    {
        Generator generator = new Generator("IRON GENERATOR", plugin, Material.IRON_INGOT, location);
        generator.spawn();
        add(generator);
        return generator;
    }
}
