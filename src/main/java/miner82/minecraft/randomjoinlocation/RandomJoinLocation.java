package miner82.minecraft.randomjoinlocation;

import miner82.minecraft.randomjoinlocation.classes.SpawnLocationRandomiser;
import miner82.minecraft.randomjoinlocation.commands.AdminCommand;
import miner82.minecraft.randomjoinlocation.commands.tabcompleters.AdminCommandTabCompleter;
import miner82.minecraft.randomjoinlocation.events.OnPlayerFirstJoin;
import miner82.minecraft.randomjoinlocation.configuration.ConfigEngine;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomJoinLocation extends JavaPlugin {

    private ConfigEngine configEngine;

    private SpawnLocationRandomiser locationRandomiser;

    @Override
    public void onEnable() {

        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        this.configEngine = new ConfigEngine(this);
        this.locationRandomiser = new SpawnLocationRandomiser(this.configEngine);

        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new OnPlayerFirstJoin(this.configEngine, this.locationRandomiser), this);

        getCommand("rjl").setExecutor(new AdminCommand(this.configEngine));
        getCommand("rjl").setTabCompleter(new AdminCommandTabCompleter());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
