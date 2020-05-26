package me.chris.BlockTsunami;


import org.bukkit.Bukkit;
import org.bukkit.Effect;
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
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
//import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.plugin.java.JavaPlugin;
//import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;



//import net.md_5.bungee.api.ChatColor;

public class BlockThrower implements Listener {
	public static int degree;
	public static boolean sandShootBeingRun;
	
	
	public BlockThrower(Location playerBlockLoc, Player player, PlayerMoveEvent event, Block b, Material oldBlockMat, EntityChangeBlockEvent sandFall) {
		if(player.getVelocity().length() != 0 && PlayerMove.holdingSafeItem == false) {
			Player playerNow = event.getPlayer();
			/*
			playerBlockLoc.add(0, 2, 0);
			
			Vector dir = loc.getDirection();
			*/
			Location playerLoc = playerNow.getLocation();
			/*
			
			Vector loc = player.getLocation().toVector(); // get player's location vector
			Vector dir = player.getLocation().getDirection(); // get player's facing
			loc = loc.add(dir.multiply(5)); // add player's direction * 3 to the location (getting ~3 blocks in front of player)
			Location location = loc.toLocation(player.getWorld()); // convert back to location
			dir.normalize();
			//dir.multiply(5); //5 blocks a way
			
			loc.add(dir);
			*/
			//loc.add(0, 4, 0);
			
			//Block newBlock = loc.getBlock();
			//ewBlock.setType(b.getType());
			//final WorldServer worldServer =  (WorldServer) loc.getWorld();
			
			//FallingBlock blockfall = event.getPlayer().getWorld().spawnFallingBlock(loc, b.getBlockData());
			
			  //float x = (float) -1 + (float) (Math.random() * ((1 - -1) + 1));
	          //float y = (float) -5 + (float)(Math.random() * ((5 - -5) + 1));
	          //float z = (float) -0.3 + (float)(Math.random() * ((0.3 - -0.3) + 1));
	        //worldServer.addEntity((Entity) blockfall, CreatureSpawnEvent.SpawnReason.CUSTOM);
	        //player.getWorld().spawnEntity(loc,  blockfalling);
	        //player.getWorld().spawnEntity(loc,  blockfalling).setVelocity(new Vector(x, y, z));	
			
			if((PlayerMove.playerCurrentLoc.getFrom().getX() != PlayerMove.playerCurrentLoc.getTo().getX() || PlayerMove.playerCurrentLoc.getFrom().getZ() != PlayerMove.playerCurrentLoc.getTo().getZ()) && PlayerMove.holdingSafeItem == false) {
				//new BlockThrower(player, location, playerBlockLoc, oldBlockMat);
				if (degree < 360) {

					degree = degree + 5;
					double radian1 = Math.toRadians(degree);
					double radian2 = Math.toRadians(degree + 120);
					double radian3 = Math.toRadians(degree + 240);

					//Location loc = playerNow.getLocation();
					
					int rando = (int) Math.floor(Math.random() * 3);
					
					Location playerIsCurrently = player.getLocation();
					playerLoc.add(0, 5, 0);
					
					
					//Vector directionOfSpeed = initialSpeed.normalize();
					//Vector finalSpeed = directionOfSpeed.setVelocity();
					//Vector finalSpeed = directionOfSpeed.divide(new Vector(initialSpeed.getX()*2, initialSpeed.getY()*2, (initialSpeed.getZ()*2)));
					double xSpeed = PlayerMove.playerCurrentLoc.getTo().getX() - PlayerMove.playerCurrentLoc.getFrom().getX();
					double zSpeed = PlayerMove.playerCurrentLoc.getTo().getZ() - PlayerMove.playerCurrentLoc.getFrom().getZ();
					
					//Makes block fall again and sets var to true so on blockfall event it doesn'tmake it stiff again
					//if(sandFall != null && sandFall.getEntityType() == EntityType.FALLING_BLOCK && sandFall.getTo() == Material.AIR){
					/*
					if(sandFall != null && sandFall.getEntityType() == EntityType.FALLING_BLOCK ){
						sandShootBeingRun = true;
						event.setCancelled(false);
		 	            //Update the block to fix a visual client bug, but don't apply physics
						sandFall.getBlock().getState().update(true, true);
					}
					sandShootBeingRun = false;
					*/
					
					
					if(oldBlockMat !=null && oldBlockMat == Material.SAND) {
						oldBlockMat = Material.SANDSTONE;
					}
					
					if(oldBlockMat !=null && oldBlockMat == Material.GRAVEL) {
						oldBlockMat = Material.STONE;
					}
					
					switch(rando) {
						case 0:
							playerLoc.add(Math.cos(radian1) * 7, 0, Math.sin(radian1) * 7);
							//playerNow.getWorld().playEffect(loc, Effect.DRAGON_BREATH, 20);
							playerLoc.getBlock().setType(oldBlockMat);
							
							//add this to try to fix sandfall**************************
							//stopBlockFall(sandFall);
							
							new BukkitRunnable() {
								@Override
								public void run() {
									// Code that needs to be delayed here
									if((xSpeed > 0 || zSpeed > 0 && PlayerMove.playerCurrentLoc.getFrom().getX() != PlayerMove.playerCurrentLoc.getTo().getX() || PlayerMove.playerCurrentLoc.getFrom().getZ() != PlayerMove.playerCurrentLoc.getTo().getZ()) && PlayerMove.holdingSafeItem == false) { // full block 
										Vector initialSpeed = playerIsCurrently.toVector().subtract(playerLoc.toVector());
										
										//add this to try to fix sandfall**************************
										//makeBlockFall(sandFall);
										
										playerNow.getWorld().spawnEntity(playerLoc,  EntityType.FALLING_BLOCK).setVelocity(initialSpeed);
										playerLoc.subtract(Math.cos(radian1) * 7, 0, Math.sin(radian1) * 7);
										player.damage(Main.pain);
									}
								}
							}.runTaskLater(Main.getInstance(), Main.setDelay);
							break;
						case 1:
							playerLoc.add(Math.cos(radian2) * 7, 0, Math.sin(radian2) * 7);
							//playerNow.getWorld().playEffect(loc, Effect.DRAGON_BREATH, 20);
							playerLoc.getBlock().setType(oldBlockMat);
							
							//add this to try to fix sandfall**************************
							//stopBlockFall(sandFall);
							
							new BukkitRunnable() {
								@Override
								public void run() {
									// Code that needs to be delayed here
									if((xSpeed > 0 || zSpeed > 0 && PlayerMove.playerCurrentLoc.getFrom().getX() != PlayerMove.playerCurrentLoc.getTo().getX() || PlayerMove.playerCurrentLoc.getFrom().getZ() != PlayerMove.playerCurrentLoc.getTo().getZ()) && PlayerMove.holdingSafeItem == false) { // full block 
										Vector initialSpeed = playerIsCurrently.toVector().subtract(playerLoc.toVector());
										
										//add this to try to fix sandfall**************************
										//makeBlockFall(sandFall);
										
										playerNow.getWorld().spawnEntity(playerLoc,  EntityType.FALLING_BLOCK).setVelocity(initialSpeed);	
										playerLoc.subtract(Math.cos(radian2) * 7, 0, Math.sin(radian2) * 7);
										player.damage(Main.pain);
									}
								}
							}.runTaskLater(Main.getInstance(), Main.setDelay);
							
							break;
						case 2:
							playerLoc.add(Math.cos(radian3) * 7, 0, Math.sin(radian3) * 7);
							//playerNow.getWorld().playEffect(loc, Effect.DRAGON_BREATH, 20);
							playerLoc.getBlock().setType(oldBlockMat);
							
							//add this to try to fix sandfall**************************
							//stopBlockFall(sandFall);
							
							new BukkitRunnable() {
								@Override
								public void run() {
									// Code that needs to be delayed here
									if((xSpeed > 0 || zSpeed > 0 && PlayerMove.playerCurrentLoc.getFrom().getX() != PlayerMove.playerCurrentLoc.getTo().getX() || PlayerMove.playerCurrentLoc.getFrom().getZ() != PlayerMove.playerCurrentLoc.getTo().getZ()) && PlayerMove.holdingSafeItem == false) { // full block 
										Vector initialSpeed = playerIsCurrently.toVector().subtract(playerLoc.toVector());
										
										//add this to try to fix sandfall**************************
										//makeBlockFall(sandFall);
										
										playerNow.getWorld().spawnEntity(playerLoc,  EntityType.FALLING_BLOCK).setVelocity(initialSpeed);	
										playerLoc.subtract(Math.cos(radian3) * 7, 0, Math.sin(radian3) * 7);
										player.damage(Main.pain);
									}
								}
							}.runTaskLater(Main.getInstance(), Main.setDelay);
							break;
					}
				} else {
				degree = 0;
				}
			}
			

	       	        //player.damage(int damage amount, entity damage source)
		}
		
	}
	
	public void stopBlockFall(EntityChangeBlockEvent sandFall) {
		if(sandFall != null && sandFall.getEntityType()==EntityType.FALLING_BLOCK){
 	        if(sandFall.getBlock().getType()==Material.SAND || sandFall.getBlock().getType()==Material.GRAVEL){
 	        	sandFall.setCancelled(true);
 	            //Update the block to fix a visual client bug, but don't apply physics
 	        	sandFall.getBlock().getState().update(false, false);         
 	        }
 	    }
	}
	
	public void makeBlockFall(EntityChangeBlockEvent sandFall) {
		if(sandFall != null && sandFall.getEntityType()==EntityType.FALLING_BLOCK){
 	        if(sandFall.getBlock().getType()==Material.SAND || sandFall.getBlock().getType()==Material.GRAVEL){
 	        	sandFall.setCancelled(false);
 	            //Update the block to fix a visual client bug, but don't apply physics
 	        	sandFall.getBlock().getState().update(false, false);         
 	        }
 	    }
	}
	
	
	
	
	public BlockThrower(Player playerNow, Location location, Location playerBlockLoc, Material oldBlockMat) {
		
		if (degree < 360) {

			degree = degree + 5;
			double radian1 = Math.toRadians(degree);
			double radian2 = Math.toRadians(degree + 120);
			double radian3 = Math.toRadians(degree + 240);

			Location loc = playerNow.getLocation();
			
			int rando = (int) Math.floor(Math.random() * 3);
			
			switch(rando) {
				case 0:
					loc.add(Math.cos(radian1) * 2, 0, Math.sin(radian1) * 2);
					//playerNow.getWorld().playEffect(loc, Effect.DRAGON_BREATH, 20);
					location.getBlock().setType(oldBlockMat);
					playerNow.getWorld().spawnEntity(location,  EntityType.FALLING_BLOCK).setVelocity(new Vector(playerBlockLoc.getBlockX(), playerBlockLoc.getBlockY(), playerBlockLoc.getBlockZ()));	
					loc.subtract(Math.cos(radian1) * 2, 0, Math.sin(radian1) * 2);
					break;
				case 1:
					loc.add(Math.cos(radian2) * 2, 0, Math.sin(radian2) * 2);
					//playerNow.getWorld().playEffect(loc, Effect.DRAGON_BREATH, 20);
					location.getBlock().setType(oldBlockMat);
					playerNow.getWorld().spawnEntity(location,  EntityType.FALLING_BLOCK).setVelocity(new Vector(playerBlockLoc.getBlockX(), playerBlockLoc.getBlockY(), playerBlockLoc.getBlockZ()));	
					loc.subtract(Math.cos(radian2) * 2, 0, Math.sin(radian2) * 2);
					break;
				case 2:
					loc.add(Math.cos(radian3) * 2, 0, Math.sin(radian3) * 2);
					//playerNow.getWorld().playEffect(loc, Effect.DRAGON_BREATH, 20);
					location.getBlock().setType(oldBlockMat);
					playerNow.getWorld().spawnEntity(location,  EntityType.FALLING_BLOCK).setVelocity(new Vector(playerBlockLoc.getBlockX(), playerBlockLoc.getBlockY(), playerBlockLoc.getBlockZ()));	
					loc.subtract(Math.cos(radian3) * 2, 0, Math.sin(radian3) * 2);
					break;
			}
		} else {
		degree = 0;
		}
	}
	
	public static void dustCircle(Player player) {		
		new BukkitRunnable() {
			@Override
			public void run() {
				int degree = 0;
				if (degree < 360) {

					degree = degree + 5;
					double radian1 = Math.toRadians(degree);
					double radian2 = Math.toRadians(degree + 120);
					double radian3 = Math.toRadians(degree + 240);

					Location loc = player.getLocation();
					loc.add(Math.cos(radian1) * 2, 0, Math.sin(radian1) * 2);
					player.getWorld().playEffect(loc, Effect.SMOKE, 20);
					loc.subtract(Math.cos(radian1) * 2, 0, Math.sin(radian1) * 2);
					loc.add(Math.cos(radian2) * 2, 0, Math.sin(radian2) * 2);
					player.getWorld().playEffect(loc, Effect.SMOKE, 20);
					loc.subtract(Math.cos(radian2) * 2, 0, Math.sin(radian2) * 2);
					loc.add(Math.cos(radian3) * 2, 0, Math.sin(radian3) * 2);
					player.getWorld().playEffect(loc, Effect.SMOKE, 20);
					loc.subtract(Math.cos(radian3) * 2, 0, Math.sin(radian3) * 2);
				} else {
				degree = 0;
				}
			}
		}.runTaskTimer(Main.getInstance(), 0, Main.setDelay);
	}
	
	
	/*
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        if(event.getAction().equals(Action.LEFT_CLICK_AIR)){
            FallingBlock block = event.getPlayer().getWorld().spawnFallingBlock(event.getPlayer().getLocation(), Material.LAVA, (byte) 0);
            float x = (float) -1 + (float) (Math.random() * ((1 - -1) + 1));
            float y = (float) -5 + (float)(Math.random() * ((5 - -5) + 1));
            float z = (float) -0.3 + (float)(Math.random() * ((0.3 - -0.3) + 1));
            Bukkit.broadcastMessage("§c" + x + ", §a" + y + ", §d" + z);
            block.setVelocity(new Vector(x, y, z));
        }
    }
    
    
    public String getCardinalDirection(Entity e) {
		Location loc = player.getLocation();
		Vector dir = loc.getDirection();
		dir.normalize();
		dir.multiply(5); //5 blocks a way
		loc.add(dir);
		player.teleport(loc);
	}
	 	*/
	 	
}