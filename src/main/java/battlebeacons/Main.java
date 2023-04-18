package battlebeacons;

import battlebeacons.commands.KonecHry;
import battlebeacons.commands.GoldGeneratorCommand;
import battlebeacons.commands.VytvorTeleportera;
import battlebeacons.commands.IronGeneratorCommand;
import battlebeacons.commands.trader.Trader;
import battlebeacons.commands.trader.VeciNaProdej;
import battlebeacons.listenery.*;
import battlebeacons.lobby.Lobby;
import battlebeacons.lobby.LobbyCreator;
import battlebeacons.teleporter.TeleportDoAreny;
import battlebeacons.teleporter.TeleportDoLoby;
import battlebeacons.tymy.Skore;
import battlebeacons.tymy.Tymy;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {
    List<Location> ironGenerators = new ArrayList<>();
    List<Location> goldGenerators = new ArrayList<>();

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        //servicy
        LobbyCreator lobbyCreator = new LobbyCreator(this);
        Lobby lobby = lobbyCreator.createLobby();
        Tymy tymy = new Tymy();
        TeleportDoAreny teleportDoAreny = new TeleportDoAreny(this, lobby, tymy);
        TeleportDoLoby teleportDoLoby = new TeleportDoLoby(lobby, tymy);
        Skore skore = new Skore(tymy);
        SpravaBloku spravaBloku = new SpravaBloku();
        StavHry stavHry = new StavHry(tymy, teleportDoLoby, spravaBloku, teleportDoAreny, skore, this, this);

        //udelat seznam lokaci, ktere se nactou z configu a potom vsude vyspawnuji iron generatory
        Location ironGenerator1 = getConfig().getLocation("ironGenerator1Location");
        Location ironGenerator2 = getConfig().getLocation("ironGenerator2Location");
        Location ironGenerator3 = getConfig().getLocation("ironGenerator3Location");
        Location ironGenerator4 = getConfig().getLocation("ironGenerator4Location");
        ironGenerators.add(ironGenerator1);
        ironGenerators.add(ironGenerator2);
        ironGenerators.add(ironGenerator3);
        ironGenerators.add(ironGenerator4);


        Location goldGenerator1 = getConfig().getLocation("goldGenerator1Location");
        Location goldGenerator2 = getConfig().getLocation("goldGenerator2Location");
        Location goldGenerator3 = getConfig().getLocation("goldGenerator3Location");
        Location goldGenerator4 = getConfig().getLocation("goldGenerator4Location");
        goldGenerators.add(goldGenerator1);
        goldGenerators.add(goldGenerator2);
        goldGenerators.add(goldGenerator3);
        goldGenerators.add(goldGenerator4);


        //listeners
        getServer().getPluginManager().registerEvents(new PripojeniDoLobby(lobby), this);
        getServer().getPluginManager().registerEvents(new StartHry(stavHry), this);
        getServer().getPluginManager().registerEvents(new OchranaSpoluhrace(tymy, stavHry), this);
        getServer().getPluginManager().registerEvents(new OdpocetZakazPohybu(tymy, teleportDoAreny, stavHry), this);
        getServer().getPluginManager().registerEvents(new RespawnHrace(tymy, stavHry), this);
        getServer().getPluginManager().registerEvents(new SmrtHrace(tymy, skore, stavHry), this);
        getServer().getPluginManager().registerEvents(new BeaconZnicen(tymy, stavHry), this);
        getServer().getPluginManager().registerEvents(new BlokPoskozen(), this);
        getServer().getPluginManager().registerEvents(new BlokPolozen(tymy, spravaBloku, stavHry), this);
        getServer().getPluginManager().registerEvents(new BlockDropEvent(spravaBloku), this);
        getServer().getPluginManager().registerEvents(new PlayerDropEvent(spravaBloku), this);

        //commandy
        getCommand("+vytvorTeleportera").setExecutor(new VytvorTeleportera());
        getCommand("+konec").setExecutor(new KonecHry(stavHry, tymy));
        getCommand("+vytvorTradera").setExecutor(new Trader(new VeciNaProdej()));
        getCommand("+vytvorIronGenerator").setExecutor(new IronGeneratorCommand(this));
        getCommand("+vytvorGoldGenerator").setExecutor(new GoldGeneratorCommand(this));
    }
}
