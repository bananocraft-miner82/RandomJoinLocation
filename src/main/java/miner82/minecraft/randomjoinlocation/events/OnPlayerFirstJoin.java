package miner82.minecraft.randomjoinlocation.events;

import miner82.minecraft.randomjoinlocation.classes.SpawnLocationRandomiser;
import miner82.minecraft.randomjoinlocation.configuration.ConfigEngine;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Boat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class OnPlayerFirstJoin implements Listener {

    private final ConfigEngine configEngine;
    private final SpawnLocationRandomiser locationRandomiser;

    public OnPlayerFirstJoin(ConfigEngine configEngine, SpawnLocationRandomiser locationRandomiser) {
        this.configEngine = configEngine;
        this.locationRandomiser = locationRandomiser;
    }

    @EventHandler
    public void onPlayerFirstJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        if(!player.hasPlayedBefore()) {

            Location newSpawnLocation = locationRandomiser.calculateRandomSpawnLocation(player);

            // Enforce the player's spawn location
            player.setBedSpawnLocation(newSpawnLocation, true);

            // Now teleport them to the new spawn location
            player.teleport(newSpawnLocation);

            if(locationRandomiser.setPlayerInBoatIfWaterSpawn(player, newSpawnLocation)
                 && this.configEngine.canShowDebug())
            {

                System.out.println("Player spawned in water: assigned a boat.");

            }

            locationRandomiser.givePlayerSpawnLocationAir(player, newSpawnLocation);

        }

    }

}
