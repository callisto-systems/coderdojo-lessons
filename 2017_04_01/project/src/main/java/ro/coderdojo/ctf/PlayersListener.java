package ro.coderdojo.ctf;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.material.Wool;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;

public class PlayersListener implements Listener {

	Plugin plugin;
	World lobby;
        Scoreboard lobbyBoard;

	public static List<Player> redPlayers = new ArrayList<>();
	public static List<Player> bluePlayers = new ArrayList<>();

	public PlayersListener(Plugin plugin, World lobby) {
		this.plugin = plugin;
		this.lobby = lobby;
	}

	@EventHandler
	public void PlayersListener(PlayerJoinEvent event) throws Exception {
		event.getPlayer().setGameMode(GameMode.ADVENTURE);
		event.getPlayer().teleport(new Location(lobby, 19.589, 231, 21.860));
                
                if(lobbyBoard == null) {
                    lobbyBoard = Bukkit.getScoreboardManager().getNewScoreboard();
                    lobbyBoard.registerNewObjective("noPlayers", "dummy") ;
                    
                }
                lobbyBoard.getObjective("noPlayers");
	}

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		System.out.println("PlayerRespawnEvent");
		event.setRespawnLocation(new Location(lobby, 19.589, 231, 21.860));
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onBlockBreak(BlockBreakEvent event) {
		event.getPlayer().sendMessage(ChatColor.YELLOW + " Nu poti sparge " + ChatColor.RED + "arena!");
		event.setCancelled(true);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Block walkingBlock = event.getTo().getBlock().getRelative(BlockFace.DOWN);
		if (walkingBlock.getType() == Material.WOOL) {
			Wool wool = (Wool) walkingBlock.getState().getData();
			if (wool.getColor() == DyeColor.RED) {
				if (redPlayers.contains(event.getPlayer())) {
					return;
				}
				event.getPlayer().sendMessage(ChatColor.RED + " Esti in echipa rosie!");
				redPlayers.add(event.getPlayer());
				bluePlayers.remove(event.getPlayer());
			}
			if (wool.getColor() == DyeColor.BLUE) {
				if (bluePlayers.contains(event.getPlayer())) {
					return;
				}
				event.getPlayer().sendMessage(ChatColor.BLUE + " Esti in echipa albastra!");
				bluePlayers.add(event.getPlayer());
				redPlayers.remove(event.getPlayer());
			}
		}
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Action action = event.getAction();
		Material material = event.getClickedBlock().getState().getType();
		Location location = event.getClickedBlock().getState().getLocation();
		if (action == Action.RIGHT_CLICK_BLOCK && material == Material.STONE_BUTTON) {
			Location b1 = new Location(lobby, 21.0, 233.0, 21.0, location.getYaw(), location.getPitch());
			Location b2 = new Location(lobby, 19.0, 233.0, 19.0, location.getYaw(), location.getPitch());
			Location b3 = new Location(lobby, 17.0, 233.0, 21.0, location.getYaw(), location.getPitch());
			Location b4 = new Location(lobby, 19.0, 233.0, 23.0, location.getYaw(), location.getPitch());
			if (location.equals(b1) || location.equals(b2) || location.equals(b3) || location.equals(b4)) {
				timer();
				player.sendMessage("Ai pornit meciul!");
				plugin.getServer().broadcastMessage("Meciul a fost pornit!");
			}
		}
	}

	private void timer() {
		CountDownTimer timer = new CountDownTimer(this);
		timer.runTaskTimer(plugin, 3, 1);
	}

}
