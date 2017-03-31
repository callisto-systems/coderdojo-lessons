package ro.coderdojo.atelier1;

import java.util.logging.Level;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class CoderDojoSpigot extends JavaPlugin {
	

	@Override
	public void onEnable() {
		this.getCommand("kit").setExecutor(new CommandKit());
		getServer().getPluginManager().registerEvents(new Particule(this), this);
		recipe();
	}
	
	public void recipe() {
		ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta im = sword.getItemMeta();
		im.setDisplayName("Emerald Sword");
		sword.setItemMeta(im);
		sword.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 999 );
		ShapedRecipe recipe = new ShapedRecipe(sword);
		recipe.shape(" E ", " E ", " S ");
		recipe.setIngredient('E', Material.EMERALD);
		recipe.setIngredient('S', Material.STICK);
		getServer().addRecipe(recipe);
	}
	
	@Override
	public void onDisable() {
		getLogger().log(Level.INFO, "{0}.onDisable()", this.getClass().getName());
	}
}
