package xyz.eburg.cx.magical.recipes.crafting_table;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.eburg.cx.magical.Magical;
import xyz.eburg.cx.magical.data_types.CraftingRecipe;
import xyz.eburg.cx.magical.data_types.MagicSpell;
import xyz.eburg.cx.magical.utils.ItemUtils;

import java.util.List;

public class DimensionCoreRecipe {
  public DimensionCoreRecipe() {
    ItemStack result = new ItemStack(Material.CLOCK);
    ItemMeta meta = result.getItemMeta();
    meta.setCustomModelData(100);
    TextComponent name = Component.text("Unused Dimension Shard").decoration(TextDecoration.ITALIC, false);
    meta.displayName(name);
    List<Component> lore = List.of(Component.text("More Information in ").color(TextColor.fromHexString("#8d00ff")).decoration(TextDecoration.ITALIC, false), Component.text("Quandalfs Guide").color(TextColor.fromHexString("#CC00FF")).decoration(TextDecoration.ITALIC, false)); // 8d00ff
    meta.lore(lore);
    result.setItemMeta(meta);
    ItemUtils.setMagiqal(result, true);
    ItemUtils.setSpell(result, MagicSpell.CRAFTING_ITEM);
    ItemUtils.setRecipe(result, CraftingRecipe.DIMENSION_SHARD);
    ItemUtils.setItemManaLore(result,1);

    NamespacedKey key = new NamespacedKey(Magical.getInstance(), "dimension_core");

    ShapedRecipe recipe = new ShapedRecipe(key, result);

    recipe.shape( " A ",
                  " S ",
                  " A ");

    recipe.setIngredient('A', Material.AMETHYST_SHARD);
    recipe.setIngredient('S', Material.STONE);

    // Finally, add the recipe to the bukkit recipes
    Bukkit.addRecipe(recipe);
  }
}
