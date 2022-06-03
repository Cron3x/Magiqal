package xyz.eburg.cx.magical.recipes.altar.dark;

import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import xyz.eburg.cx.magical.utils.ItemUtils;


public class DarkCraftingManager {
  public static boolean isMainCraftingItem(ItemStack item){
    return ItemUtils.getRecipe(item).equals(CraftingRecipe.DIMENSION_SHARD);
  }

  public static void spawnMainItem(Location location, ItemStack item) {
    Item itemEntity = location.getWorld().dropItem(location.add(0,1.5,0), item);
    itemEntity.setPickupDelay(32767);
    itemEntity.setWillAge(false);
    itemEntity.setUnlimitedLifetime(true);
  }
}
