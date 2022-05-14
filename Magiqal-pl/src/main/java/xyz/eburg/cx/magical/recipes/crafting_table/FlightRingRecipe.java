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
import xyz.eburg.cx.magical.spells.MagicSpell;
import xyz.eburg.cx.magical.utils.BarCharacter;
import xyz.eburg.cx.magical.utils.ItemUtils;

import java.util.List;

public class FlightRingRecipe {
  public FlightRingRecipe() {
    ItemStack result = new ItemStack(Material.CLOCK);
    ItemMeta meta = result.getItemMeta();
    meta.setCustomModelData(2);
    TextComponent name = Component.text("Flight Ring 186").decorate(TextDecoration.BOLD).decoration(TextDecoration.ITALIC, false);
    meta.displayName(name);
    List<Component> lore = List.of(Component.text("enables creative flight, right click to toggle ").color(TextColor.fromHexString("#CC00FF")).decoration(TextDecoration.ITALIC, false), Component.text("Active: 0").color(TextColor.fromHexString("#42325E")).decoration(TextDecoration.ITALIC, false));
    meta.lore(lore);
    result.setItemMeta(meta);
    ItemUtils.setMagiqal(result, true);
    ItemUtils.setSpell(result, MagicSpell.TRANSMUTATION);
    ItemUtils.setItemManaLore(result,"- " + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon);

    NamespacedKey key = new NamespacedKey(Magical.getInstance(), "flight_ring");

    // Create our custom recipe variable
    ShapedRecipe recipe = new ShapedRecipe(key, result);

    // Here we will set the places. E and S can represent anything, and the letters can be anything. Beware; this is case sensitive.
    recipe.shape( "FTF");

    // Set what the letters represent.
    // E = Emerald, S = Stick
    recipe.setIngredient('F', Material.FEATHER);
    recipe.setIngredient('T', Material.TOTEM_OF_UNDYING);

    // Finally, add the recipe to the bukkit recipes
    Bukkit.addRecipe(recipe);
  }
}
