package battlebeacons.trader;

import battlebeacons.factory.ArmorFactory;
import battlebeacons.factory.Weapons;
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

public class Obchodnik implements CommandExecutor {

    private final ArmorFactory tovarnaNaZbroje;
    private final Weapons tovarnaNaZbrane;

    public static final String JMENO_OBCHODNIKA = "ObchodnikProHrace";

    public Obchodnik(ArmorFactory armorFactory, Weapons weapons) {
        this.tovarnaNaZbroje = armorFactory;
        this.tovarnaNaZbrane = weapons;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if (!(commandSender instanceof Player)) return false;
        var player = (Player) commandSender;

        var trader = (WanderingTrader) player.getWorld().spawnEntity(player.getLocation(), EntityType.WANDERING_TRADER);
        trader.setAI(false);
        trader.setCustomName(JMENO_OBCHODNIKA);
        trader.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 1000000000, 999999999, true));

        //Gladiator
        MerchantRecipe gladiatorSword = new MerchantRecipe(tovarnaNaZbrane.sword(), 999);
        gladiatorSword.addIngredient(new ItemStack(Material.GOLD_NUGGET, 60));
        
        MerchantRecipe arrow = new MerchantRecipe(tovarnaNaZbrane.arrow(), 999);
        arrow.addIngredient(new ItemStack(Material.GOLD_NUGGET, 1));

        MerchantRecipe bow = new MerchantRecipe(tovarnaNaZbrane.bow(), 999);
        bow.addIngredient(new ItemStack(Material.GOLD_NUGGET, 30));

        MerchantRecipe pickaxe = new MerchantRecipe(tovarnaNaZbrane.pickaxe(), 999);
        pickaxe.addIngredient(new ItemStack(Material.GOLD_NUGGET, 40));

        MerchantRecipe shield = new MerchantRecipe(tovarnaNaZbrane.shield(), 999);
        shield.addIngredient(new ItemStack(Material.GOLD_NUGGET, 64));

        MerchantRecipe gladiatorHelmet = new MerchantRecipe(tovarnaNaZbroje.helmet(), 999);
        gladiatorHelmet.addIngredient(new ItemStack(Material.GOLD_NUGGET, 50));

        MerchantRecipe gladiatorChestPlate = new MerchantRecipe(tovarnaNaZbroje.chestplate(), 999);
        gladiatorChestPlate.addIngredient(new ItemStack(Material.GOLD_NUGGET, 50));

        MerchantRecipe gladiatorLeggins = new MerchantRecipe(tovarnaNaZbroje.leggins(), 999);
        gladiatorLeggins.addIngredient(new ItemStack(Material.GOLD_NUGGET, 50));

        MerchantRecipe gladiatorBoots = new MerchantRecipe(tovarnaNaZbroje.boots(), 999);
        gladiatorBoots.addIngredient(new ItemStack(Material.GOLD_NUGGET, 50));

        trader.setRecipes(Lists.newArrayList
                (gladiatorSword, gladiatorHelmet, gladiatorChestPlate, gladiatorLeggins, gladiatorBoots, gladiatorSword, arrow, bow, pickaxe, shield));

        return true;
    }
}
