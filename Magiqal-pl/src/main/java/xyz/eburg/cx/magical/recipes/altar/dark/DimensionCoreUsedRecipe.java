package xyz.eburg.cx.magical.recipes.altar.dark;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.eburg.cx.magical.data_types.Dimension;
import xyz.eburg.cx.magical.utils.EnchantGlow;
import xyz.eburg.cx.magical.utils.ItemUtils;

public class DimensionCoreUsedRecipe {

  public static ItemStack edit(ItemStack item, Dimension dimension) {
    Bukkit.broadcastMessage("DimensionCoreUsedRecipe");
    ItemUtils.setDimension(item, dimension);
    ItemMeta meta  = item.getItemMeta();
    meta.setCustomModelData(101);
    item.setItemMeta(meta);
    item.addEnchantment(new EnchantGlow(), 0);
    item.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    return item;
  }
}
