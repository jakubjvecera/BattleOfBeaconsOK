package battlebeacons;

import battlebeacons.generatory.IronGenerator;
import battlebeacons.listenery.SpravaBloku;
import battlebeacons.teleporter.TeleportDoAreny;
import battlebeacons.teleporter.TeleportDoLoby;
import battlebeacons.tymy.Skore;
import battlebeacons.tymy.Tymy;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;


public class StavHry {

    private final Tymy tymy;
    private final TeleportDoLoby teleportDoLoby;
    private final SpravaBloku spravaBloku;
    private final TeleportDoAreny teleport;
    private final Skore skore;
    private final Main main;
    private final Plugin plugin;
    public List<IronGenerator> ironGenerators = new ArrayList<>(4);


    private boolean gameRunning;

    public StavHry(Tymy tymy, TeleportDoLoby teleportDoLoby, SpravaBloku spravaBloku, TeleportDoAreny teleport, Skore skore, Main main, Plugin plugin) {
        this.tymy = tymy;
        this.teleportDoLoby = teleportDoLoby;
        this.spravaBloku = spravaBloku;
        this.teleport = teleport;
        this.skore = skore;
        this.gameRunning = false;
        this.main = main;
        this.plugin = plugin;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void startGame() {
        teleport.teleportPriStartuHry();
        skore.inicializace();

        this.gameRunning = true;
        main.ironGeneratorsLocation.forEach(ironGenerator -> ironGenerators.add(new IronGenerator(plugin).spawnIronGenerator(main.getServer().getWorlds().get(0), ironGenerator)));
    }

    public void stopGame() {
        this.gameRunning = false;
        teleportDoLoby.teleport();
        tymy.smazTymy();
        spravaBloku.znicPolozeneBloky();
        spravaBloku.znicOdhozeneVeci();
        ironGenerators.forEach(ironGenerator -> ironGenerator.destroy());
    }
}
