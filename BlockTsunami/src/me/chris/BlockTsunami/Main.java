package me.chris.BlockTsunami;


import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
	public static Long setDelay;
	public final static Logger logger = Logger.getLogger("Minecraft");
	public static Main instance;
	public static boolean startPlugin;
	public static double pain;
	//This ver uses constructor might be laggier, also set sands to sandstone and gravel to stone
	
	public static Main getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		startPlugin = false;
		pain = .1;
		
		getServer().getPluginManager().registerEvents(new PlayerMove(), this);
		
		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info(pdfFile.getName() + " Version " + pdfFile.getVersion()
		+ " Has been enabled!");	
		setDelay = (long) 20;
	}
	
	@Override
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		logger.info(pdfFile.getName() + " Has been disabled!");
		instance = null;
	}
	
	
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		
		
		if(label.equalsIgnoreCase("blocktsunamistart")) {
			getServer().broadcastMessage(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "" + "BlocktTsunami started! Default delay: 1s!");
			startPlugin = true;
			return true;
		}
		
		if(label.equalsIgnoreCase("blocktsunamistop")) {
			getServer().broadcastMessage(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "" + "BlocktTsunami disabled");
			startPlugin = false;
			return true;
		}
		
		if(label.equalsIgnoreCase("blocktsunamisetpain")) {
			getServer().broadcastMessage(ChatColor.DARK_BLUE + "" + ChatColor.BOLD + "" + "BlocktTsunami started! Default delay: 1s!");
			
			if (player.isOp()) {
				if(isNum(args[0])) {
					//String userInput = label.substring(18);
					
					
					if(isNum(args[0])) {
						pain =  Double.parseDouble(args[0]);
						player.sendMessage("Pain has been set to " + ChatColor.BOLD  + "" + ChatColor.BLUE + pain + ChatColor.RESET + ""  +" (default: .1 ) "); {
							return true;
						}
					}
				} else {
					player.sendMessage(ChatColor.RED + "Usage: /blocktsunamisetpain <number-value .1-20>"); {
						return true;
					}
				}
			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission!"); {
					return true;
				}
			}
			
			return true;
		}
		
		if(label.equalsIgnoreCase("blocktsunamiver")) {
			PluginDescriptionFile pdfFile = this.getDescription();
			player.sendMessage(pdfFile.getVersion()); {
				return true;
			}
		}
		
		if(label.equalsIgnoreCase("blocktsunamiring")) {
			BlockThrower.dustCircle(player);
			return true;
		}
		
		
		if(label.equalsIgnoreCase("blocktsunamisetdelay")) {
			
			if (player.isOp()) {
				if(isNum(args[0])) {
					//String userInput = label.substring(18);
					setDelay = Long.valueOf(args[0]);
					
					if(isNum(args[0])) {
						double delaySeconds = (double)setDelay / 20.0;
						player.sendMessage("Delay has been set to " + ChatColor.BOLD  + "" + ChatColor.BLUE + setDelay + ChatColor.RESET + ""  +" ticks / " + ChatColor.BOLD  + "" + ChatColor.BLUE + delaySeconds + ChatColor.RESET + "" + " seconds"); {
							return true;
						}
					}
				} else {
					player.sendMessage(ChatColor.RED + "Usage: /blocktsunamisetDelay <number-value>"); {
						return true;
					}
				}
			} else {
				player.sendMessage(ChatColor.RED + "You do not have permission!"); {
					return true;
				}
			}
		}
		
		if(label.equalsIgnoreCase("blocktsunamidelay")) {
			
			double delaySeconds = (double)setDelay / 20.0;
			player.sendMessage("Delay is " + ChatColor.BOLD  + "" + ChatColor.BLUE + setDelay + ChatColor.RESET + ""  +" ticks / " + ChatColor.BOLD  + "" + ChatColor.BLUE + delaySeconds + ChatColor.RESET + "" + " seconds"); {
				return true;
			}
		}
		
		
		
		return false;	
	}
	
	public boolean isNum(String num) {
		try {
			Long.valueOf(num);
		} catch (Exception e) {
			return false;
		}

		return true;
	}
	
	
	
	
}
	

	
