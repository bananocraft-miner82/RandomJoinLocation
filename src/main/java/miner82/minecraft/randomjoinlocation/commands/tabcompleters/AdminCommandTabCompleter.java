package miner82.minecraft.randomjoinlocation.commands.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class AdminCommandTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> results = new ArrayList<>();

        if(sender instanceof Player) {

            Player player = (Player) sender;

            if(!player.hasPermission(command.getPermission())){

                return results;

            }

        }

        if(args.length == 1) {

            results.add("set");
            results.add("get");
            results.add("reloadconfigfile");
            results.add("displayconfig");

        }
        else if(args.length == 2
            && !args[0].equalsIgnoreCase("reloadconfigfile")
            && !args[0].equalsIgnoreCase("displayconfig")) {

            results.add("showdebug");
            results.add("maxspawnradius");
            results.add("giveboatonwater");
            results.add("boattypeonwater");
            results.add("maxsafespotsearchonlava");

        }
        else if(args.length == 3
                 && args[0].equalsIgnoreCase("set")) {

            if(args[1].equalsIgnoreCase("showdebug")
                  || args[1].equalsIgnoreCase("giveboatonwater")) {

                results.add("true");
                results.add("false");

            }
            else if(args[1].equalsIgnoreCase("boattypeonwater")) {

                for(Boat.Type boatType : Boat.Type.values()) {

                    results.add(boatType.name());

                }

            }

        }

        return StringUtil.copyPartialMatches(args[args.length - 1], results, new ArrayList<>());
    }
}