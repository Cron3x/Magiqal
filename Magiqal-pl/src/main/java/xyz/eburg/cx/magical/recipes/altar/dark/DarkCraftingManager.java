package xyz.eburg.cx.magical.recipes.altar.dark;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.eburg.cx.magical.utils.ItemUtils;

public class DarkCraftingManager {
  public DarkCraftingManager(ItemStack item, Location location) {
    switch (ItemUtils.getRecipe(item)) {
      case DIMENSION_SHARD -> new DimensionShardDARecipe(item, location);
    }
  }
}
