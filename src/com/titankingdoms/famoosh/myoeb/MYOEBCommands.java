package com.titankingdoms.famoosh.myoeb;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MYOEBCommands implements CommandExecutor{

	private MYOEBMain plugin;
	
	
	public MYOEBCommands(MYOEBMain that) {
		plugin = that;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("bottleitup")){	
			if(!(sender instanceof Player)){
				sender.sendMessage("You must be a player to use this command!");
			}else if(sender instanceof Player){
				Player p = (Player) sender;
				if(p.hasPermission("myoeb.create")){
						int level = p.getLevel();
						int bottles = p.getInventory().all(374).size();
						int config = plugin.getConfig().getInt("bottles.per.level");
						if(config > bottles){
							sender.sendMessage(ChatColor.GREEN + "You do not have enough bottles for one level");
						}
						if(bottles > level * config){
							int newBottles = level * config;
							int leftover = bottles - level * config;
							ItemStack expBottles = new ItemStack(384, newBottles);
							ItemStack leftoverBottles = new ItemStack(374, leftover);
							p.getInventory().remove(374);
							p.getInventory().addItem(leftoverBottles);
							p.getInventory().addItem(expBottles);
							p.setLevel(0);
							p.sendMessage(ChatColor.GREEN + "Bottling complete! You bottled:" + expBottles + " experience bottles!");
						}else if(level * config > bottles){
							int newBottles = bottles/config;
							int leftover = bottles%config;
							ItemStack expBottles = new ItemStack(384, newBottles);
							p.getInventory().remove(374);
							p.getInventory().addItem(expBottles);
							p.setLevel(level - leftover);
						}
						}
				}else{
					sender.sendMessage(ChatColor.GREEN + "You do not have permission to use that!");
				}
			}	
		return false;
	}

}
