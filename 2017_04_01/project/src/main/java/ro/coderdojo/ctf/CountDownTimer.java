/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.coderdojo.ctf;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDownTimer extends BukkitRunnable {

	PlayersListener lobbyListener;
	World arena;

	private int counter = 10 * 20;

	public CountDownTimer(PlayersListener lobbyListener) {
		this.lobbyListener = lobbyListener;
	}

	@Override
	public void run() {
		if (counter > 0) {
			if (counter % 20 == 0) {
				lobbyListener.plugin.getServer().broadcastMessage(ChatColor.WHITE +
						"Jocul porneste in " + ChatColor.RED + counter / 20 + ChatColor.WHITE + " secunde");
			}
			counter = counter - 1;
		} else {
			lobbyListener.plugin.getServer().broadcastMessage("Start!");
			this.cancel();
		}
	}

	

}
