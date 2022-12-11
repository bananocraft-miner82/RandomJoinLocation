package miner82.minecraft.randomjoinlocation.configuration;

import miner82.minecraft.randomjoinlocation.RandomJoinLocation;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Boat.Type;

public class ConfigEngine {

    private final RandomJoinLocation main;

    private final int DEFAULT_MAX_SPAWN_RADIUS = 1000;

    private boolean showDebug = false;

    private int maxSpawnRadius = DEFAULT_MAX_SPAWN_RADIUS;
    private boolean spawnInBoatOnWater = true;
    private org.bukkit.entity.Boat.Type spawnInBoatType = org.bukkit.entity.Boat.Type.OAK;
    private int maxSearchXIfLavaSpawn = 10;

    public ConfigEngine(RandomJoinLocation main) {

        this.main = main;

        initialiseConfig(main.getConfig());

    }

    public boolean canShowDebug() {
        return this.showDebug;
    }

    public void setShowDebug(boolean showDebug) {

        if(this.showDebug != showDebug) {

            this.showDebug = showDebug;

            save();

        }

    }

    public int getMaxSpawnRadius() {
        return this.maxSpawnRadius;
    }

    public void setMaxSpawnRadius(int newRadius) {

        if(this.maxSpawnRadius != newRadius) {

            this.maxSpawnRadius = newRadius;

            save();

        }

    }

    public boolean spawnPlayerInBoatOnWater() {
        return this.spawnInBoatOnWater;
    }

    public void setSpawnInBoatOnWater(boolean giveBoat) {

        if(this.spawnInBoatOnWater != giveBoat) {

            this.spawnInBoatOnWater = giveBoat;

            save();

        }

    }

    public org.bukkit.entity.Boat.Type getSpawnInBoatType() {
        return this.spawnInBoatType;
    }

    public void setSpawnInBoatType(org.bukkit.entity.Boat.Type boatType) {

        if(this.spawnInBoatType != boatType) {

            this.spawnInBoatType = boatType;

            save();

        }

    }

    public int getMaxSearchXIfLavaSpawn() {
        return this.maxSearchXIfLavaSpawn;
    }

    public void setMaxSearchXIfLavaSpawn(int maxDistance) {

        if(this.maxSearchXIfLavaSpawn != maxDistance) {

            this.maxSearchXIfLavaSpawn = maxDistance;

            save();

        }

    }

    public boolean save() {

        FileConfiguration config = this.main.getConfig();

        config.set("ShowDebug", this.showDebug);
        config.set("MaximumSpawnRadius", this.maxSpawnRadius);
        config.set("SpawnInBoatOnWater", this.spawnInBoatOnWater);
        config.set("SpawnInBoatType", this.spawnInBoatType.name());
        config.set("MaxSearchXOnLava", this.maxSearchXIfLavaSpawn);

        this.main.saveConfig();

        return true;

    }

    public void reload() {
        main.reloadConfig();
        initialiseConfig(main.getConfig());
    }

    private void initialiseConfig(FileConfiguration configuration) {

        System.out.println("Initialising Random Spawn Location Config...");

        if(configuration != null) {

            if(configuration.contains("ShowDebug")) {

                try {

                    this.showDebug = configuration.getBoolean("ShowDebug");

                }
                catch (Exception ex) {

                    System.out.println("Invalid ShowDebug value... using default instead.");

                }

            }

            System.out.println("- Show debug in logs set: " + this.showDebug);

            if(configuration.contains("MaximumSpawnRadius")) {

                try {

                    this.maxSpawnRadius = configuration.getInt("MaximumSpawnRadius");

                }
                catch (Exception ex) {

                    System.out.println("Invalid MaximumSpawnRadius value... using default instead.");

                }

            }

            System.out.println("- Maximum join location radius from world spawn set: " + this.maxSpawnRadius);

            if(configuration.contains("SpawnInBoatOnWater")) {

                try {

                    this.spawnInBoatOnWater = configuration.getBoolean("SpawnInBoatOnWater");

                }
                catch (Exception ex) {

                    System.out.println("Invalid SpawnInBoatOnWater value... using default instead.");

                }

            }

            System.out.println("- Player will spawn in a boat if on water: " + this.spawnInBoatOnWater);

            if(configuration.contains("SpawnInBoatType")) {

                try {

                    this.spawnInBoatType = org.bukkit.entity.Boat.Type.valueOf(configuration.getString("SpawnInBoatType"));

                }
                catch (Exception ex) {

                    System.out.println("Invalid SpawnInBoatType value... using default instead.");

                }

            }

            System.out.println("- Player will spawn with boat type if on water: " + this.spawnInBoatType.name());

            if(configuration.contains("MaxSearchXOnLava")) {

                try {

                    this.maxSearchXIfLavaSpawn = configuration.getInt("MaxSearchXOnLava");

                }
                catch (Exception ex) {

                    System.out.println("Invalid MaxSearchXOnLava value... using default instead.");

                }

            }

            System.out.println("- Maximum distance to search for safety if spawning on lava set: " + this.maxSearchXIfLavaSpawn);

        }

        System.out.println("Random Join Location Config initialisation complete!");

    }
}
