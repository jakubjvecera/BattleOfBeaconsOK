package battlebeacons.commands.trader;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class VeciNaProdej {
    public ItemStack enderpearl() {
        var perla = new ItemStack(Material.ENDER_PEARL);
        return perla;
    }

    public ItemStack wool() {
        var wool = new ItemStack(Material.BLACK_WOOL, 16);
        return wool;
    }

    public ItemStack endstone() {
        var endstone = new ItemStack(Material.END_STONE, 16);
        return endstone;
    }

    public ItemStack elytra() {
        var elytra = new ItemStack(Material.ELYTRA, 1);
        return elytra;
    }

    public ItemStack snowball() {
        var snowball = new ItemStack(Material.SNOWBALL, 16);
        return snowball;
    }

    public ItemStack bucketOfPowederSnow() {
        var bucketOfPowederSnow = new ItemStack(Material.POWDER_SNOW_BUCKET, 1);
        return bucketOfPowederSnow;
    }
    public ItemStack lavaBucket() {
        var lavaBucket = new ItemStack(Material.LAVA_BUCKET, 1);
        return lavaBucket;
    }
    public ItemStack chainmailLeggins() {
        var chainmailLeggins = new ItemStack(Material.CHAINMAIL_LEGGINGS, 1);
        return chainmailLeggins;
    }
    public ItemStack chainmailBoots() {
        var hainmailBoots = new ItemStack(Material.CHAINMAIL_BOOTS, 1);
        return hainmailBoots;
    }
    public ItemStack ironLeggins() {
        var ironLeggins = new ItemStack(Material.IRON_LEGGINGS, 1);
        return ironLeggins;
    }
    public ItemStack ironlBoots() {
        var ironBoots = new ItemStack(Material.IRON_BOOTS, 1);
        return ironBoots;
    }
}