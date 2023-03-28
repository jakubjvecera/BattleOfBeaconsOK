package battlebeacons.commands.trader;

import com.google.common.collect.Lists;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.management.timer.Timer;

public class Trader implements CommandExecutor {
    private final VeciNaProdej veciNaProdej;

    public static final String JMENO_OBCHODNIKA = "TRADER";

    public Trader(VeciNaProdej veciNaProdej) {
        this.veciNaProdej = veciNaProdej;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;
        var player = (Player) sender;

        var trader = (WanderingTrader) player.getWorld().spawnEntity(player.getLocation(), EntityType.WANDERING_TRADER);
        trader.setAI(false);
        trader.setCustomName(JMENO_OBCHODNIKA);
        trader.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000000, 999999999, true));

        MerchantRecipe enderPearl = new MerchantRecipe(veciNaProdej.enderpearl(), 1);
        enderPearl.addIngredient(new ItemStack(Material.EMERALD, 3));

        MerchantRecipe wool = new MerchantRecipe(veciNaProdej.wool(), 1);
        wool.addIngredient(new ItemStack(Material.IRON_INGOT, 4));

        MerchantRecipe endstone = new MerchantRecipe(veciNaProdej.endstone(), 1);
        wool.addIngredient(new ItemStack(Material.IRON_INGOT, 4));

        MerchantRecipe bucketOfPowederSnow = new MerchantRecipe(veciNaProdej.bucketOfPowederSnow(), 1);
        bucketOfPowederSnow.addIngredient(new ItemStack(Material.EMERALD, 3));

        MerchantRecipe elytra = new MerchantRecipe(veciNaProdej.elytra(), 1);
        elytra.addIngredient(new ItemStack(Material.NETHERITE_INGOT, 3));

        MerchantRecipe snowball = new MerchantRecipe(veciNaProdej.snowball(), 1);
        snowball.addIngredient(new ItemStack(Material.GOLD_INGOT, 2));

        MerchantRecipe lavaBucket = new MerchantRecipe(veciNaProdej.lavaBucket(), 1);
        lavaBucket.addIngredient(new ItemStack(Material.GOLD_INGOT, 10));

        MerchantRecipe chainmailLeggins = new MerchantRecipe(veciNaProdej.chainmailLeggins(), 1);
        chainmailLeggins.addIngredient(new ItemStack(Material.IRON_INGOT, 30));

        MerchantRecipe chainmailBoots = new MerchantRecipe(veciNaProdej.chainmailBoots(), 1);
        chainmailBoots.addIngredient(new ItemStack(Material.IRON_INGOT, 30));

        trader.setRecipes(Lists.newArrayList(enderPearl, wool, endstone, bucketOfPowederSnow, elytra, snowball, lavaBucket, chainmailLeggins, chainmailBoots));

        return true;
    }
}
