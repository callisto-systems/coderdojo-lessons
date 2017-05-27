package ro.coderdojo.ctf;

import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

public class CaptureTheFlagPlugin extends JavaPlugin {

	World lobby;
	World arena;

	@Override
	public void onEnable() {
		System.out.println("***********************  CAPTURE THE FLAG PLUGIN ***********************");
		loadWorld();
		getServer().getPluginManager().registerEvents(new PlayersListener(this, lobby), this);
		getServer().getPluginManager().registerEvents(new ArenaListener(), this);
	}

	public void loadWorld() {
		lobby = Bukkit.getServer().createWorld(new WorldCreator("world_lobby"));
		arena = Bukkit.getServer().createWorld(new WorldCreator("world_arena"));
	}

	@Override
	public void onDisable() {
		getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
	}
}
