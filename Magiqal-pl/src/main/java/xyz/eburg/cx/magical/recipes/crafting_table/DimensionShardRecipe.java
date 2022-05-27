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
import xyz.eburg.cx.magical.recipes.altar.dark.CraftingRecipe;
import xyz.eburg.cx.magical.spells.MagicSpell;
import xyz.eburg.cx.magical.utils.ItemUtils;

import java.util.List;

public class DimensionShardRecipe {
  public DimensionShardRecipe() {
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

    NamespacedKey key = new NamespacedKey(Magical.getInstance(), "unused_dimension_shard");

    // Create our custom recipe variable
    ShapedRecipe recipe = new ShapedRecipe(key, result);

    // Here we will set the places. E and S can represent anything, and the letters can be anything. Beware; this is case sensitive.
    recipe.shape( " A ",
                  " S ",
                  " A ");

    // Set what the letters represent.
    // E = Emerald, S = Stick
    recipe.setIngredient('A', Material.AMETHYST_SHARD);
    recipe.setIngredient('S', Material.STONE);

    // Finally, add the recipe to the bukkit recipes
    Bukkit.addRecipe(recipe);
  }
}
