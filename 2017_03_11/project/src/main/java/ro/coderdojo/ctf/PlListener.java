/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.coderdojo.ctf;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 *
 * @author mihai
 */
public class PlListener implements Listener {
	
	@EventHandler
	public void PlayersListener(PlayerJoinEvent event) throws Exception {
		event.getPlayer().sendMessage(ChatColor.YELLOW 
				+ " Bine ai venit " + ChatColor.RED + " " + event.getPlayer().getDisplayName());
		event.getPlayer().setGameMode(GameMode.SURVIVAL);
	}
	
}
