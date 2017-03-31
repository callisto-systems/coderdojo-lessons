package ro.coderdojo.atelier1;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.Random;
import net.minecraft.server.v1_11_R1.EnumParticle;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import net.minecraft.server.v1_11_R1.PacketPlayOutWorldParticles;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_11_R1.entity.CraftPlayer;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Particule implements Listener {

	Plugin plugin;

	public Particule(Plugin plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void Particule_Foc(PlayerInteractEvent event) throws Exception {
		Player player = event.getPlayer();
		Location location = player.getLocation().add(player.getLocation().getDirection().setY(0).normalize().multiply(10));
		final double px = location.getX();
		final double py = location.getY();
		final double pz = location.getZ();
		if (player.getInventory().getItemInMainHand().getType() == Material.DIAMOND_SWORD) {
			new BukkitRunnable() {
				EnumParticle particle = EnumParticle.FLAME;
				double i = 0;
				double k = 0;

				{
					int noOfParticlesType = EnumParticle.values().length;
					Random random = new Random();
					int rndPart = random.nextInt(noOfParticlesType);
					EnumParticle tmpparticle = EnumParticle.values()[rndPart];
					System.out.println("~~~TMP:" + tmpparticle);
					if (tmpparticle == EnumParticle.BLOCK_DUST
							|| tmpparticle == EnumParticle.FALLING_DUST
							|| tmpparticle == EnumParticle.ITEM_CRACK
							|| tmpparticle == EnumParticle.BLOCK_CRACK
							|| tmpparticle == EnumParticle.MOB_APPEARANCE) {
					} else {
						System.out.println("~~~Particle:" + particle);
						particle = tmpparticle;
					}
				}

				@Override
				public void run() {
					i += 0.1;
					float height = (float) (py + i / 3);
					if (height > 128) {
						cancel();
					}
					k += 0.005;
					PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particle, true, (float) (px + 3 * sin(i) * k), height, (float) (pz + 3 * cos(i) * k), 0, 0, 0, 0, 1);
					for (Player online : Bukkit.getOnlinePlayers()) {
						((CraftPlayer) online).getHandle().playerConnection.sendPacket(packet);
					}

				}
			}.runTaskTimer(plugin, 0, 1);
		} else {
			int noOfParticlesType = EnumParticle.values().length;
			Random random = new Random();
			int rndPart = random.nextInt(noOfParticlesType);
			EnumParticle particle = EnumParticle.values()[rndPart];
			if (particle == EnumParticle.BLOCK_DUST
					|| particle == EnumParticle.FALLING_DUST
					|| particle == EnumParticle.ITEM_CRACK
					|| particle == EnumParticle.BLOCK_CRACK) {
				return;
			}
			System.out.println("~~~~~~~~~~ " + particle);
			double i = 0;
			double k = 0;
			while (true) {
				i += 0.08;
				float height = (float) (py + i / 3);
				if (height > 128) {
					break;
				}
				k += 0.005;
				PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particle, true, (float) (px + 3 * sin(i) * k), height, (float) (pz + 3 * cos(i) * k), 0, 0, 0, 0, 1);
				for (Player online : Bukkit.getOnlinePlayers()) {
					((CraftPlayer) online).getHandle().playerConnection.sendPacket(packet);
				}
			}
		}
	}

}
