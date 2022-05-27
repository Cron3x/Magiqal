package xyz.eburg.cx.magical.recipes.altar.dark;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DimensionShardDARecipe {
  public DimensionShardDARecipe(ItemStack item, Location location) {
    location.getWorld().dropItem(location, item);
    item.setType(Material.AIR);
  }
}
