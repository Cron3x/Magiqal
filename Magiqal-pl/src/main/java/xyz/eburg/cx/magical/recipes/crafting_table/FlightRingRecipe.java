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
import xyz.eburg.cx.magical.data_types.MagicSpell;
import xyz.eburg.cx.magical.utils.ItemUtils;

import java.util.List;

public class FlightRingRecipe {
  public FlightRingRecipe() {
    ItemStack result = new ItemStack(Material.CLOCK);
    ItemMeta meta = result.getItemMeta();
/**/meta.setCustomModelData(2);
    TextComponent name = Component.text("Flight Ring").decorate(TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    meta.displayName(name);
    List<Component> lore = List.of(Component.text("Enables creative flight, right click to toggle").color(TextColor.fromHexString("#CC00FF")).decoration(TextDecoration.ITALIC, false), Component.text("Active: 0").color(TextColor.fromHexString("#275F8F")).decoration(TextDecoration.ITALIC, false), Component.text("").color(TextColor.fromHexString("#000000")).decoration(TextDecoration.ITALIC, false));
    meta.lore(lore);
    result.setItemMeta(meta);
    ItemUtils.setMagiqal(result, true);
/**/ItemUtils.setSpell(result, MagicSpell.FLIGHT);
    ItemUtils.setItemManaLore(result,52);

    NamespacedKey key = new NamespacedKey(Magical.getInstance(), "flight_ring");

    ShapedRecipe recipe = new ShapedRecipe(key, result);

    recipe.shape( "FTF");
    recipe.setIngredient('F', Material.FEATHER);
    recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);

    Bukkit.addRecipe(recipe);
  }
}
