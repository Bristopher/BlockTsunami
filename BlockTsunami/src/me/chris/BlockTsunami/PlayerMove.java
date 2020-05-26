package me.chris.BlockTsunami;

//import java.util.HashMap;
//import java.util.logging.Logger;

//import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

//import net.md_5.bungee.api.ChatColor;

public class PlayerMove implements Listener {
	public static boolean holdingSafeItem;
	public static Location initialLoc;
	public static Location newLoc;
	public static PlayerMoveEvent playerCurrentLoc;
	public EntityChangeBlockEvent sandFall;

	 	@EventHandler
	 	(priority = EventPriority.NORMAL)
		public void onPlayerMoveEvent(PlayerMoveEvent event) {
	 		playerCurrentLoc = event;
	 		if(Main.startPlugin) {
	 			Player player = event.getPlayer();
	 			initialLoc = player.getLocation();
		 		
		 		Location playerBlockLoc = player.getLocation();
		 		playerBlockLoc.add(0, -1, 0);
				Block block = playerBlockLoc.getBlock();
				if (block.getType() != Material.OBSIDIAN && block.getType() != Material.NETHER_PORTAL && block.getType() != Material.BEDROCK && block.getType() != Material.END_PORTAL && block.getType() != Material.END_PORTAL_FRAME) {
					checkHand(player);
					double xSpeed = PlayerMove.playerCurrentLoc.getTo().getX() - PlayerMove.playerCurrentLoc.getFrom().getX();
					double zSpeed = PlayerMove.playerCurrentLoc.getTo().getZ() - PlayerMove.playerCurrentLoc.getFrom().getZ();
					
					if(xSpeed > 0 || zSpeed > 0 && (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getZ() != event.getTo().getZ()) && holdingSafeItem == false) { // full block 
					//if(player.getVelocity().length() != 0 && holdingSafeItem == false) {
					
						new BukkitRunnable() {
							@Override
							public void run() {
								// Code that needs to be delayed here
								//if((player.getVelocity().length() > player.getWalkSpeed()-.3  && newLoc.equals(initialLoc) == false && holdingSafeItem == false) || player.isSprinting() || player.isSneaking()) {
									Material oldBlockMat = block.getType();
									block.setType(Material.AIR);
									new BlockThrower(playerBlockLoc, player, event, block, oldBlockMat, sandFall);
									//player.damage(.1);
									newLoc = player.getLocation();
								
							}
						}.runTaskLater(Main.getInstance(), Main.setDelay);
					}

				}
	 		}
	 		
	 	}
	 	
	 	@EventHandler
	 	(priority = EventPriority.NORMAL, ignoreCancelled = true)
	 	private void onSandFall(EntityChangeBlockEvent event){
	 		sandFall = event;
	 		
	 		
	 	    //if(BlockThrower.sandShootBeingRun == false && event.getEntityType()==EntityType.FALLING_BLOCK && event.getTo()==Material.AIR){
	 		//if(BlockThrower.sandShootBeingRun == false && event.getEntityType()==EntityType.FALLING_BLOCK){
	 		/*
	 		if(event.getEntityType()==EntityType.FALLING_BLOCK){
	 	        if(event.getBlock().getType()==Material.SAND || event.getBlock().getType()==Material.GRAVEL){
	 	            event.setCancelled(true);
	 	            //Update the block to fix a visual client bug, but don't apply physics
	 	            event.getBlock().getState().update(false, false);         
	 	        }
	 	    }
	 	    */
	 	}
	 	
	 	@EventHandler
		public void onPlayerJoin(PlayerJoinEvent event) {
			Player player = event.getPlayer();
			
			initialLoc = player.getLocation();
			newLoc = player.getLocation();
					
			if(player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_BLOCK)) {
	 			holdingSafeItem = true;
	 		} else {
	 			holdingSafeItem = false;
	 		}
		}
	 	
	 	
	 	@EventHandler
	 	public void onHandChange(PlayerItemHeldEvent event) {
	 		Player player = event.getPlayer();
	 		
	 		if(player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_BLOCK)) {
	 			holdingSafeItem = true;
	 		} else {
	 			holdingSafeItem = false;
	 		}
	 		
	 		/*
		 	  List<String> lore = event.getPlayer().getItemInHand().getItemMeta().getLore();
		 	  if(lore.contains("Resistance")) {
		 	    // do something
		 	  }
		 	  */
	 	}
	 	
		public void checkHand(Player player) {			
			initialLoc = player.getLocation();
			newLoc = player.getLocation();
					
			if(player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_BLOCK)) {
	 			holdingSafeItem = true;
	 		} else {
	 			holdingSafeItem = false;
	 		}
		}
	 	
	 	
}