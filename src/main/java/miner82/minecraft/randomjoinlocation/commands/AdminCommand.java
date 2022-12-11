package miner82.minecraft.randomjoinlocation.commands;

import miner82.minecraft.randomjoinlocation.configuration.ConfigEngine;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Player;

public class AdminCommand extends BaseCommand implements CommandExecutor {

    private final ConfigEngine configEngine;

    public AdminCommand(ConfigEngine configEngine) {
        this.configEngine = configEngine;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(args.length < 1) {
            return false;
        }

        if(args[0].equalsIgnoreCase("set")
            && args.length < 3) {

            return false;

        }

        Player player = (Player) sender;

        if(args[0].equalsIgnoreCase("reloadconfigfile")) {

            this.configEngine.reload();

            SendMessage(player, "The configuration has been reloaded from the configuration file.", ChatColor.GREEN);

            return true;

        }
        else if(args[0].equalsIgnoreCase("displayconfig")) {

            SendMessage(player, "Random Join Location Configuration", ChatColor.AQUA);
            SendMessage(player, "----------------------------------", ChatColor.AQUA);
            SendMessage(player, "Debug Info: " + (this.configEngine.canShowDebug() ? "ON" : "OFF"), ChatColor.AQUA);
            SendMessage(player, "Max. Spawn Radius: " + this.configEngine.getMaxSpawnRadius() + " blocks", ChatColor.AQUA);
            SendMessage(player, "Lava Safety Search Range: " + this.configEngine.getMaxSearchXIfLavaSpawn() + " blocks", ChatColor.AQUA);
            SendMessage(player, "Give Boat On Water Spawn: " + (this.configEngine.spawnPlayerInBoatOnWater() ? "YES" : "NO"), ChatColor.AQUA);

            if(this.configEngine.spawnPlayerInBoatOnWater()) {

                SendMessage(player, "Boat Type: " + this.configEngine.getSpawnInBoatType().name(), ChatColor.AQUA);

            }

            return true;

        }
        else if(args[0].equalsIgnoreCase("set")) {

            String newValue = args[2];

            if(args[1].equalsIgnoreCase("showdebug")) {

                if(newValue.equalsIgnoreCase("true")
                     || newValue.equalsIgnoreCase("false")) {

                    this.configEngine.setShowDebug(newValue.equalsIgnoreCase("true"));

                    if(this.configEngine.canShowDebug()) {

                        SendMessage(player, "Debugging in the logs is switched ON.", ChatColor.GREEN);

                    }
                    else {

                        SendMessage(player, "Debugging in the logs is switched OFF.", ChatColor.GREEN);

                    }

                    return true;

                }
                else {

                    SendMessage(player, "Invalid value!", ChatColor.RED);

                }

            }
            else if(args[1].equalsIgnoreCase("maxspawnradius")) {

                try {

                    int newRadius = Integer.parseInt(newValue);

                    if(newRadius > 0) {

                        this.configEngine.setMaxSpawnRadius(newRadius);

                        SendMessage(player, "The maximum spawn radius has been set to " + newRadius + ".", ChatColor.GREEN);

                        return true;

                    }
                    else {

                        SendMessage(player, "The maximum spawn radius must be greater than zero!", ChatColor.RED);

                    }

                }
                catch (Exception ex) {

                    SendMessage(player, "Invalid value!", ChatColor.RED);

                }

            }
            else if(args[1].equalsIgnoreCase("maxsafespotsearchonlava")) {

                try {

                    int newRadius = Integer.parseInt(newValue);

                    if(newRadius > 0) {

                        this.configEngine.setMaxSearchXIfLavaSpawn(newRadius);

                        SendMessage(player, "The maximum X distance to search for a safe spot when a player spawns on lava has been set to " + newRadius + ".", ChatColor.GREEN);

                        return true;

                    }
                    else {

                        SendMessage(player, "The maximum X distance to search for a safe spot when a player spawns on lava must be greater than zero!", ChatColor.RED);

                    }

                }
                catch (Exception ex) {

                    SendMessage(player, "Invalid value!", ChatColor.RED);

                }

            }
            else if(args[1].equalsIgnoreCase("giveboatonwater")) {

                if(newValue.equalsIgnoreCase("true")
                        || newValue.equalsIgnoreCase("false")) {

                    this.configEngine.setSpawnInBoatOnWater(newValue.equalsIgnoreCase("true"));

                    if(this.configEngine.spawnPlayerInBoatOnWater()) {

                        SendMessage(player, "Players WILL be spawned in/with a boat when spawning on water.", ChatColor.GREEN);

                    }
                    else {

                        SendMessage(player, "Players WILL NOT be spawned in/with a boat when spawning on water.", ChatColor.GREEN);

                    }

                    return true;

                }
                else {

                    SendMessage(player, "Invalid value!", ChatColor.RED);

                }

            }
            else if(args[1].equalsIgnoreCase("boattypeonwater")) {

                try {

                    Boat.Type boatType = Boat.Type.valueOf(newValue);

                    this.configEngine.setSpawnInBoatType(boatType);

                    SendMessage(player, "Players will be spawned in/with an " + this.configEngine.getSpawnInBoatType().name() + ".", ChatColor.GREEN);

                    return true;

                }
                catch (Exception ex) {

                    SendMessage(player, "Invalid value!", ChatColor.RED);

                }


                SendMessage(player, "Players will be spawned in/with an " + this.configEngine.getSpawnInBoatType().name() + ".", ChatColor.GOLD);

            }

        }
        else if(args[0].equalsIgnoreCase("get")) {

            if(args[1].equalsIgnoreCase("showdebug")) {

                if(this.configEngine.canShowDebug()) {

                    SendMessage(player, "Debugging in the logs is switched ON.", ChatColor.GOLD);

                }
                else {

                    SendMessage(player, "Debugging in the logs is switched OFF.", ChatColor.GOLD);

                }

            }
            else if(args[1].equalsIgnoreCase("maxspawnradius")) {

                SendMessage(player, "The maximum spawn radius is set to " + this.configEngine.getMaxSpawnRadius() + " blocks.", ChatColor.GOLD);

            }
            else if(args[1].equalsIgnoreCase("maxsafespotsearchonlava")) {

                SendMessage(player, "The maximum search distance for a safe spot when a player spawns on lava is set to " + this.configEngine.getMaxSearchXIfLavaSpawn() + " blocks.", ChatColor.GOLD);

            }
            else if(args[1].equalsIgnoreCase("giveboatonwater")) {

                if(this.configEngine.spawnPlayerInBoatOnWater()) {

                    SendMessage(player, "Players WILL be spawned in/with a boat when spawning on water.", ChatColor.GOLD);

                }
                else {

                    SendMessage(player, "Players WILL NOT be spawned in/with a boat when spawning on water.", ChatColor.GOLD);

                }

            }
            else if(args[1].equalsIgnoreCase("boattypeonwater")) {

                SendMessage(player, "Players will be spawned in/with an " + this.configEngine.getSpawnInBoatType().name() + ".", ChatColor.GOLD);

            }

            return true;

        }

        return false;

    }

}
