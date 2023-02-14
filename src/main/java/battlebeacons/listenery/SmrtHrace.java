package battlebeacons.listenery;


import battlebeacons.StavHry;
import battlebeacons.tymy.Skore;
import battlebeacons.tymy.Tym;
import battlebeacons.tymy.Tymy;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class SmrtHrace implements Listener {

    private final Tymy tymy;
    private final Skore skore;

    private final StavHry stavHry;

    public SmrtHrace(Tymy tymy, Skore skore, StavHry stavHry) {
        this.tymy = tymy;
        this.skore = skore;
        this.stavHry = stavHry;
    }

    @EventHandler
    public void smrtHrace(PlayerDeathEvent playerDeathEvent) {
        if (!stavHry.isGameRunning()) return;
        var player = playerDeathEvent.getEntity();

        //vypadnou z nej suroviny
        int netherite = 0;
        int emerald = 0;
        int gold = 0;
        int iron = 0;


        for (int i = 0; i <= 35; i++) {
            ItemStack itemStack = player.getInventory().getItem(i);

            if (itemStack.equals(new ItemStack(Material.NETHERITE_INGOT)))
                netherite++;

            if (itemStack.equals(new ItemStack(Material.EMERALD)))
                emerald++;


            if (itemStack.equals(new ItemStack(Material.IRON_INGOT)))
                iron++;

            if (itemStack.equals(new ItemStack(Material.GOLD_INGOT)))
                gold++;
        }

        playerDeathEvent.getDrops().clear();

        playerDeathEvent.getDrops().add(new ItemStack(Material.NETHERITE_INGOT, netherite));
        playerDeathEvent.getDrops().add(new ItemStack(Material.EMERALD, emerald));
        playerDeathEvent.getDrops().add(new ItemStack(Material.GOLD_INGOT, gold));
        playerDeathEvent.getDrops().add(new ItemStack(Material.IRON_INGOT, iron));

        {
            //spectator mod pokud uz neni beacon
            var tym = tymy.vratTym(player);
            if (!tym.isAlive()) {
                player.setGameMode(GameMode.SPECTATOR);
                skore.update();
            }
        }

        //test konce hty
        List<Tym> ziveTymy = vratZiveTymy();
        if (ziveTymy.size() <= 1) {
            if (ziveTymy.isEmpty()) {
                //asi nenastane
                tymy.vratTymy().forEach(vsechnyTymy -> vsechnyTymy.zprava("Nikdo nezvitezil", "Remiza poslednich dvou"));
            } else {
                var vitez = ziveTymy.get(0);
                tymy.vratTymy().forEach(tym -> {
                    if (tym.equals(vitez)) {
                        tym.zprava("Vas tym zvitezil", "");
                    } else {
                        tym.zprava("Zvitezil tym " + ziveTymy.get(0).getNastaveniTymu(), "");
                    }
                });
            }
            stavHry.stopGame();
        }
    }

    private List<Tym> vratZiveTymy() {
        List<Tym> tymySZivymi = new ArrayList<>();
        for (var tym : tymy.vratTymy()) {
            boolean zivyHracVTeamu = tym.getHraci().stream().anyMatch(hrac -> hrac.getGameMode() != GameMode.SPECTATOR);
            if (zivyHracVTeamu) {
                tymySZivymi.add(tym);
            }
        }
        return tymySZivymi;
    }
}
