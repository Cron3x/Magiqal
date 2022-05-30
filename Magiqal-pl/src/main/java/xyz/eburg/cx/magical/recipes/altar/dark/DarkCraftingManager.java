package xyz.eburg.cx.magical.recipes.altar.dark;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.jetbrains.annotations.NotNull;
import xyz.eburg.cx.magical.utils.ItemUtils;

import java.util.Collection;

public class DarkCraftingManager {
  public DarkCraftingManager(Location location) {

    for (Entity entity : location.getNearbyEntities(1, 1, 1))  {
      if (entity instanceof Item item) {
        if (ItemUtils.getRecipe(item.getItemStack()).equals(CraftingRecipe.DIMENSION_SHARD)){

        }
      }
    }
  }
}
