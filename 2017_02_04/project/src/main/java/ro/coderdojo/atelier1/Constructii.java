/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.coderdojo.atelier1;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

/**
 *
 * @author mihai
 */
public class Constructii implements Listener {

	public static List<Location> locations = new ArrayList<Location>();

	@EventHandler
	public void locatii(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (!(event.getHand() == EquipmentSlot.HAND)) {
			return;
		}
		if (event.getClickedBlock() == null) {
			return;
		}
		if (locations.size() > 0) {
			player.sendMessage(ChatColor.RED + "Locatia : " +
					ChatColor.GREEN + locations.get(locations.size() - 1).getX() + " " +
					locations.get(locations.size() - 1).getY() + " " +
					locations.get(locations.size() - 1).getZ());
		}
		locations.add(event.getClickedBlock().getLocation());
		if (locations.size() > 2) {
			locations.remove(0);
		}
	}

}
