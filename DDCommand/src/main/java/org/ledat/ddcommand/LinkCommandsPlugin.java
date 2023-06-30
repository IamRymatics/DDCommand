package org.ledat.ddcommand;

import com.destroystokyo.paper.Title;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LinkCommandsPlugin extends JavaPlugin {

    private static LinkCommandsPlugin instance;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("discord").setExecutor(new CM1());
        getCommand("donate").setExecutor(new CM2());
        getCommand("developer").setExecutor(new CM3());
    }

    public static class CM1 implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                player.sendMessage((ChatColor.AQUA + "Discord: " + LinkCommandsPlugin.instance.getConfig().getString("discordLink")));
                player.sendTitle(new Title(ChatColor.AQUA + "DISCORD", ChatColor.AQUA + "discordLink", 1, 20, 1));
            }
            return true;
        }
    }


    public static class CM2 implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                player.sendMessage(ChatColor.GREEN + "Donate: " + LinkCommandsPlugin.instance.getConfig().getString("donateLink"));
                player.sendTitle(new Title(ChatColor.GREEN + "DONATE", ChatColor.GREEN + "donateLink", 1, 20, 1));
            }
            return true;
        }
    }

    public static class CM3 implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
            if (commandSender instanceof Player) {
                Player player = (Player) commandSender;
                player.sendMessage(ChatColor.GOLD + "Developer: https://fb.com/ledatP.vn");
                player.sendTitle(new Title(ChatColor.GOLD + "NHÀ PHÁT TRIỂN", ChatColor.GOLD + "devRealLink", 1, 20, 1));
            }
            return true;
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("joindate")) {
            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "Chỉ người chơi mới dùng được! (CONSOLE: /joindate <tên>)");
                    return true;
                }

                Player player = (Player) sender;
                Date joinDate = new Date(player.getFirstPlayed());
                SimpleDateFormat dateFormat = new SimpleDateFormat("H:mm a dd/MM/yyyy");

                String message = ChatColor.GREEN + "Ngày tham gia của bạn: " + dateFormat.format(joinDate);
                player.sendMessage(message);

                return true;
            } else if (args.length == 1) {
                if (!sender.hasPermission("joindate.staff")) {
                    sender.sendMessage(ChatColor.RED + "Bạn không có quyền để sử dụng lệnh!");
                    return true;
                }

                String playerName = args[0];
                Player targetPlayer = getServer().getPlayerExact(playerName);

                if (targetPlayer == null) {
                    sender.sendMessage(ChatColor.RED +"Người chơi '" + playerName + "' không tồn tại!");
                    return true;
                }

                Date joinDate = new Date(targetPlayer.getFirstPlayed());
                SimpleDateFormat dateFormat = new SimpleDateFormat("H:mm a dd/MM/yyyy");

                String message = ChatColor.GREEN + "Ngày tham gia của " + targetPlayer.getName() + " là: " + dateFormat.format(joinDate);
                sender.sendMessage(message);

                return true;
            }
        }

        return false;
    }
}