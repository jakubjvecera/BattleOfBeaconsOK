package battlebeacons.listenery;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDamageEvent;

public class BlokPoskozen implements Listener {

    private final SpravaBloku spravaBloku;

    public BlokPoskozen(SpravaBloku spravaBloku) {
        this.spravaBloku = spravaBloku;
    }

    @EventHandler
    public void blockPoskozen(BlockDamageEvent event) {
        if (SpravaBloku.jeBlokZakazany(event.getBlock().getType())) event.setCancelled(true);
        if (!spravaBloku.jeBlokPolozenyVeHre(event.getBlock())) event.setCancelled(true);
    }
}
