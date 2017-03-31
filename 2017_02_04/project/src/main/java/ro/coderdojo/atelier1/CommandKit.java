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
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 *
 * @author mihai
 */
public class CommandKit implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (Constructii.locations.size() < 2) {
				player.sendMessage(ChatColor.RED + " Mai trebuie un punct");
				return false;
			}
			player.sendMessage(ChatColor.RED + " Build");
			List<Location> locs = sfera(Constructii.locations.get(0));
			for (Location loc : locs) {

				loc.getBlock().setType(player.getInventory().getItemInMainHand().getType());
			}
		}
		return true;
	}

	public List<Location> location(Location a, Location b) {
		double DT = Math.sqrt(Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getZ() - a.getZ()), 2));
		List<Location> locs = new ArrayList<Location>();

		int y = (int) a.getY();
		for (int distance = 1; distance < DT; distance++) {
			double T = distance / DT;
			double x = (1 - T) * a.getX() + T * b.getX();
			double z = (1 - T) * a.getZ() + T * b.getBlockZ();
			World localWorld = a.getWorld();
			locs.add(new Location(localWorld, x, a.getY(), z));
			for (int y1 = 0; y1 < 10; y1++) {
				if ((y1 + distance) % 2 == 1) {
					locs.add(new Location(localWorld, x, a.getY() + y1, z));

				}
			}

		}
		return locs;
	}

	/*public List<Location> covor(Location a, Location b) {
		List<Location> locs = new ArrayList<Location>();
		double z2 = b.getZ();
		double z1 = a.getZ();
		double x1 = a.getX();
		double x2 = b.getX();
		double lungime = Math.abs(z2 - z1);
		double latime = Math.abs(x2 - x1);
		World localWorld = a.getWorld();
		for (int i = 0; i <= lungime; i++) {
			for (int j = 0; j <= latime; j++) {
				double x = Math.min(x2, x1) + i;
				double y = a.getY();
				double z = Math.min(z2, z1) + j;
				locs.add(new Location(localWorld, x, y + 1, z));

			}
		}
		return locs;

	}
	 */
	public List<Location> sfera(Location a) {
		List<Location> locs = new ArrayList<Location>();
		double raza = 4;

		World localWorld = a.getWorld();
		for (int i = 0; i <= raza; i++) {
			for (int j = 0; j <= raza; j++) {
				for (int m = 0; m <= raza; m++) {
					double x = a.getX() + i;
					double y = a.getY() + j;
					double z = a.getZ() + m;
					double dist = Math.sqrt(Math.pow((x - a.getX()), 2) + Math.pow((y - a.getY()), 2) + Math.pow((z - a.getZ()), 2));
					if (dist < raza) {
						locs.add(new Location(localWorld, x, y, z));
					}

					x = a.getX() - i;
					y = a.getY() - j;
					z = a.getZ() + m;
					dist = Math.sqrt(Math.pow((x - a.getX()), 2) + Math.pow((y - a.getY()), 2) + Math.pow((z - a.getZ()), 2));
					if (dist < raza) {
						locs.add(new Location(localWorld, x, y, z));
					}

					x = a.getX() - i;
					y = a.getY() + j;
					z = a.getZ() + m;
					dist = Math.sqrt(Math.pow((x - a.getX()), 2) + Math.pow((y - a.getY()), 2) + Math.pow((z - a.getZ()), 2));
					if (dist < raza) {
						locs.add(new Location(localWorld, x, y, z));
					}
					x = a.getX() - i;
					y = a.getY() + j;
					z = a.getZ() - m;
					dist = Math.sqrt(Math.pow((x - a.getX()), 2) + Math.pow((y - a.getY()), 2) + Math.pow((z - a.getZ()), 2));
					if (dist < raza) {
						locs.add(new Location(localWorld, x, y, z));
					}
					x = a.getX() - i;
					y = a.getY() - j;
					z = a.getZ() - m;
					dist = Math.sqrt(Math.pow((x - a.getX()), 2) + Math.pow((y - a.getY()), 2) + Math.pow((z - a.getZ()), 2));
					if (dist < raza) {
						locs.add(new Location(localWorld, x, y, z));
					}
					x = a.getX() + i;
					y = a.getY() + j;
					z = a.getZ() - m;
					dist = Math.sqrt(Math.pow((x - a.getX()), 2) + Math.pow((y - a.getY()), 2) + Math.pow((z - a.getZ()), 2));
					if (dist < raza) {
						locs.add(new Location(localWorld, x, y, z));
					}
					x = a.getX() + i;
					y = a.getY() - j;
					z = a.getZ() - m;
					dist = Math.sqrt(Math.pow((x - a.getX()), 2) + Math.pow((y - a.getY()), 2) + Math.pow((z - a.getZ()), 2));
					if (dist < raza) {
						locs.add(new Location(localWorld, x, y, z));
					}
				}
			}

		}

		return locs;

	}

}
