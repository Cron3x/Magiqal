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

public class TransmutationStaffRecipe {
  public TransmutationStaffRecipe() {
    ItemStack result = new ItemStack(Material.CLOCK);
    ItemMeta meta = result.getItemMeta();
    meta.setCustomModelData(1);
    TextComponent name = Component.text("Staff of Transmutation").decorate(TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    meta.displayName(name);
    List<Component> lore = List.of(Component.text("Use this Staff to activate Magiqal Blocks").color(TextColor.fromHexString("#CC00FF")).decoration(TextDecoration.ITALIC, false));
    meta.lore(lore);
    result.setItemMeta(meta);
    ItemUtils.setMagiqal(result, true);
    ItemUtils.setSpell(result, MagicSpell.TRANSMUTATION);
    ItemUtils.setItemManaLore(result,4);

    NamespacedKey key = new NamespacedKey(Magical.getInstance(), "transmutation_staff");

    // Create our custom recipe variable
    ShapedRecipe recipe = new ShapedRecipe(key, result);

    // Here we will set the places. E and S can represent anything, and the letters can be anything. Beware; this is case sensitive.
    recipe.shape( "  A",
                  " S ",
                  "S  ");

    // Set what the letters represent.
    // E = Emerald, S = Stick
    recipe.setIngredient('A', Material.AMETHYST_SHARD);
    recipe.setIngredient('S', Material.STICK);

    // Finally, add the recipe to the bukkit recipes
    Bukkit.addRecipe(recipe);
  }
}
