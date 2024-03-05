package battlebeacons.commands.trader;

import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TraderZbrane implements CommandExecutor {
    private final VeciNaProdej veciNaProdej;

    public static final String JMENO_OBCHODNIKA = "TRADER ZBRANE";

    public TraderZbrane(VeciNaProdej veciNaProdej) {
        this.veciNaProdej = veciNaProdej;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        var player = (Player) sender;

        var trader = (Villager) player.getWorld().spawnEntity(player.getLocation(), EntityType.VILLAGER);
        trader.setVillagerType(Villager.Type.DESERT);
        trader.setProfession(Villager.Profession.ARMORER);
        trader.setAI(false);
        trader.setCustomName(JMENO_OBCHODNIKA);
        trader.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000000, 999999999, true));

        MerchantRecipe chainmailLeggins = new MerchantRecipe(veciNaProdej.chainmailLeggins(), 999);
        chainmailLeggins.addIngredient(new ItemStack(Material.IRON_INGOT, 20));

        MerchantRecipe chainmailBoots = new MerchantRecipe(veciNaProdej.chainmailBoots(), 999);
        chainmailBoots.addIngredient(new ItemStack(Material.IRON_INGOT, 20));

        MerchantRecipe chainmailChestplate = new MerchantRecipe(veciNaProdej.chainmailChestplate(), 999);
        chainmailChestplate.addIngredient(new ItemStack(Material.IRON_INGOT, 30));

        MerchantRecipe ironLeggins = new MerchantRecipe(veciNaProdej.ironLeggins(), 999);
        ironLeggins.addIngredient(new ItemStack(Material.GOLD_INGOT, 30));

        MerchantRecipe ironBoots = new MerchantRecipe(veciNaProdej.ironlBoots(), 999);
        ironBoots.addIngredient(new ItemStack(Material.GOLD_INGOT, 30));

        MerchantRecipe ironChestplate = new MerchantRecipe(veciNaProdej.ironChestplate(), 999);
        ironChestplate.addIngredient(new ItemStack(Material.GOLD_INGOT, 40));

        MerchantRecipe diaLeggins = new MerchantRecipe(veciNaProdej.diaLeggins(), 999);
        diaLeggins.addIngredient(new ItemStack(Material.EMERALD, 10));

        MerchantRecipe diaBoots = new MerchantRecipe(veciNaProdej.diaBoots(), 999);
        diaBoots.addIngredient(new ItemStack(Material.EMERALD, 10));

        MerchantRecipe diaChestplate = new MerchantRecipe(veciNaProdej.diaChestplate(), 999);
        diaChestplate.addIngredient(new ItemStack(Material.EMERALD, 15));

        trader.setRecipes(Lists.newArrayList(chainmailChestplate, chainmailLeggins, chainmailBoots, ironChestplate,
                ironLeggins, ironBoots, diaChestplate, diaLeggins, diaBoots));

        return true;
    }
}
