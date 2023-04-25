package battlebeacons;

import battlebeacons.generatory.Generatory;
import battlebeacons.listenery.SpravaBloku;
import battlebeacons.teleporter.TeleportDoAreny;
import battlebeacons.teleporter.TeleportDoLoby;
import battlebeacons.tymy.Skore;
import battlebeacons.tymy.Tymy;


public class StavHry {

    private final Tymy tymy;
    private final TeleportDoLoby teleportDoLoby;
    private final SpravaBloku spravaBloku;
    private final TeleportDoAreny teleport;
    private final Skore skore;
    private final Generatory generatory;

    private boolean gameRunning;

    public StavHry(Tymy tymy, TeleportDoLoby teleportDoLoby, SpravaBloku spravaBloku, TeleportDoAreny teleport, Skore skore, Generatory generatory) {
        this.tymy = tymy;
        this.teleportDoLoby = teleportDoLoby;
        this.spravaBloku = spravaBloku;
        this.teleport = teleport;
        this.skore = skore;
        this.generatory = generatory;
        this.gameRunning = false;
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void startGame() {
        teleport.teleportPriStartuHry();
        skore.inicializace();
        generatory.loadFromConfig();
        this.gameRunning = true;
    }

    public void stopGame() {
        this.gameRunning = false;
        teleportDoLoby.teleport();
        tymy.smazTymy();
        spravaBloku.znicPolozeneBloky();
        spravaBloku.znicOdhozeneVeci();
        generatory.destroyAll();
    }
}
