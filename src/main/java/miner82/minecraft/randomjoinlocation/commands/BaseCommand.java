package miner82.minecraft.randomjoinlocation.commands;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class BaseCommand {

    protected void SendMessage(Player player, String message, ChatColor messageColour) {
        if(player != null) {
            player.sendMessage(messageColour + message);
        }
        else {
            System.out.println(message);
        }
    }

}
