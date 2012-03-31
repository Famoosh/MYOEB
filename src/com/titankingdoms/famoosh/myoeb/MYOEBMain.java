package com.titankingdoms.famoosh.myoeb;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class MYOEBMain extends JavaPlugin{
	Logger log = Logger.getLogger("Minecraft");
	
	private MYOEBCommands cmdExec;
	
	@Override
	public void onEnable(){
		log.info("[MYOEB] enabling...");
		cmdExec = new MYOEBCommands(this);
		getCommand("bottleitup").setExecutor(cmdExec);
		log.info("[MYOEB] v. " + this.getDescription().getVersion() + " enabled!");	
	}
	@Override
	public void onDisable(){
		log.info("[MYOEB] disabled!");
	}
	
	
}
