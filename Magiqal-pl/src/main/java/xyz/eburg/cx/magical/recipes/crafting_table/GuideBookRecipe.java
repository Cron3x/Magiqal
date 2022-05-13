package xyz.eburg.cx.magical.recipes.crafting_table;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.BookMeta;
import xyz.eburg.cx.magical.Magical;

import java.util.List;

public class GuideBookRecipe {

  public GuideBookRecipe() {
    //Page 0
    Component page0 = Component.text("The Magiqal Guid\n\n").color(TextColor.color(0xCC00FF)).decorate(TextDecoration.BOLD)
      .append(Component.text("This Icon: ").color(TextColor.color(0x000000)).decoration(TextDecoration.BOLD, false))
      .append(Component.text("\uEE03").color(TextColor.color(0xFFFFFF)))
      .append(Component.text(" is Mana, u need this to conjure.").color(TextColor.color(0x000000)).decoration(TextDecoration.BOLD, false));

    Component pageContent = Component.text("Content\n\n").color(TextColor.color(0xCC00FF)).decorate(TextDecoration.BOLD)
      .append(Component.text( "> Crafting Altar").color(TextColor.color(0x000000)).decoration(TextDecoration.BOLD, false).clickEvent(ClickEvent.changePage(3)));

    Component pageCraftingAltar0 = Component.text("The Crafting Altar\n").color(TextColor.color(0xCC00FF)).decoration(TextDecoration.BOLD,false)
      .append(Component.text( "\n\n"+
        "\uEA00\uEA04\uEA00\uEA00\uEA00\uEA04\n\n" +
        "\uEA00\uEA00\uEA00\uEA00\uEA00\uEA00\n\n" +
        "\uEA00\uEA00\uEA00\uEA01\uEA00\uEA00\n\n" +
        "\uEA00\uEA00\uEA00\uEA00\uEA00\uEA00\n\n" +
        "\uEA00\uEA04\uEA00\uEA00\uEA00\uEA04\n"
      ).color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.BOLD, false));

    Component pageCraftingAltar1 = Component.text("The Crafting Altar\n").color(TextColor.color(0xCC00FF)).decoration(TextDecoration.BOLD,false)
      .append(Component.text( "\n\n" +
        "\uEA00\uEA04\uEA00\uEA00\uEA00\uEA04\n\n" +
        "\uEA00\uEA03\uEA00\uEA00\uEA00\uEA03\n\n" +
        "\uEA00\uEA03\uEA00\uEA02\uEA00\uEA03\n\n"
      ).color(TextColor.color(0xFFFFFF)).decoration(TextDecoration.BOLD, false))
      .append(Component.text( "Click here").decoration(TextDecoration.UNDERLINED, true)
        .color(TextColor.color(0x000000)).decoration(TextDecoration.BOLD, false)
        .clickEvent(ClickEvent.runCommand("/give @s minecraft:knowledge_book{Recipes:[\"magiqal:transmutation_staff\"]}")))
      .append(Component.text(" to get the recipe for the Staff of Transmutation"))
        .decoration(TextDecoration.UNDERLINED, false).color(TextColor.color(0x000000)).decoration(TextDecoration.BOLD, false);

    ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
    BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
    bookMeta.setAuthor("Quandalf the wise");
    bookMeta.setTitle("The Magiqal Guid");
    bookMeta.setGeneration(BookMeta.Generation.ORIGINAL);
    bookMeta.addPages(page0);
    bookMeta.page(1, page0);
    bookMeta.addPages(pageContent);
    bookMeta.addPages(pageCraftingAltar0);
    bookMeta.addPages(pageCraftingAltar1);
    bookMeta.setCustomModelData(1);
    writtenBook.setItemMeta(bookMeta);


    NamespacedKey key = new NamespacedKey(Magical.getInstance(), "magiqal_guide");

  // Create our custom recipe variable
    ShapedRecipe recipe = new ShapedRecipe(key, writtenBook);

  // Here we will set the places. E and S can represent anything, and the letters can be anything. Beware; this is case sensitive.
    recipe.shape( " A ",
                  "ABA",
                  " A ");

  // Set what the letters represent.
  // E = Emerald, S = Stick
    recipe.setIngredient('A', Material.AMETHYST_SHARD);
    recipe.setIngredient('B', Material.BOOK);

  // Finally, add the recipe to the bukkit recipes
    Bukkit.addRecipe(recipe);
  }
}
