package xyz.eburg.cx.magical.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import xyz.eburg.cx.magical.recipes.altar.dark.CraftingRecipe;
import xyz.eburg.cx.magical.recipes.altar.dark.CraftingRecipeDataType;
import xyz.eburg.cx.magical.spells.MagicSpell;
import xyz.eburg.cx.magical.spells.MagicSpellDataType;

import java.util.List;

import static xyz.eburg.cx.magical.utils.BarCharacter.*;

public class ItemUtils {
  public static Boolean isMagiqal(ItemStack item) {
    PersistentDataContainer itemData = item.getItemMeta().getPersistentDataContainer();
    if (itemData.has(NameSpaceKeys.keyItemMagiqal)){
      return true;
    }
    return false;
  }
  public static void setMagiqal(ItemStack item, boolean is_magiqal){            //Toggle would be setMagiqal(item, !isMagiqal(item))
    ItemMeta meta = item.getItemMeta();
    PersistentDataContainer itemData = meta.getPersistentDataContainer();
    itemData.set(NameSpaceKeys.keyItemMagiqal, PersistentDataType.INTEGER, is_magiqal ? 1 : 0);
    item.setItemMeta(meta);
  }
  public static void setItemManaLore(ItemStack item, int mana){
    ItemMeta itemMeta = item.getItemMeta();
    String loreText = ">  - " + switch (mana) {
      case 0  -> empty;
      case 1  -> quarterIcon;
      case 2  -> halfIcon;
      case 3  -> quarterx3Icon;
      case 4  -> fullIcon;
      case 5  -> quarterIcon + fullIcon;
      case 6  -> halfIcon + fullIcon;
      case 7  -> quarterx3Icon + fullIcon;
      case 8  -> fullIcon + fullIcon;
      case 9  -> quarterIcon + fullIcon + fullIcon;
      case 10 -> halfIcon + fullIcon + fullIcon;
      case 11 -> quarterx3Icon + fullIcon + fullIcon;
      case 12 -> fullIcon + fullIcon + fullIcon;
      case 13 -> quarterIcon + fullIcon + fullIcon + fullIcon;
      case 14 -> halfIcon + fullIcon + fullIcon + fullIcon;
      case 15 -> quarterx3Icon + fullIcon + fullIcon + fullIcon;
      case 16 -> fullIcon + fullIcon + fullIcon + fullIcon;
      case 17 -> quarterIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 18 -> halfIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 19 -> quarterx3Icon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 20 -> fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 21 -> quarterIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 22 -> halfIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 23 -> quarterx3Icon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 24 -> fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 25 -> quarterIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 26 -> halfIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 27 -> quarterx3Icon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 28 -> fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 29 -> quarterIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 30 -> halfIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 31 -> quarterx3Icon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 32 -> fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 33 -> quarterIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 34 -> halfIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 35 -> quarterx3Icon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 36 -> fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 37 -> quarterIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 38 -> halfIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 39 -> quarterx3Icon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 40 -> fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 41 -> quarterIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 42 -> halfIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 43 -> quarterx3Icon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 44 -> fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 45 -> quarterIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 46 -> halfIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 47 -> quarterx3Icon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 48 -> fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 49 -> quarterIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 50 -> halfIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 51 -> quarterx3Icon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      case 52 -> fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon + fullIcon;
      default -> fullIcon + ": " + mana;
    };

    TextComponent tc = Component.text(loreText.toString()).color(TextColor.fromHexString("#FFFFFF")).decoration(TextDecoration.ITALIC, false);
    if (itemMeta.lore() != null && itemMeta.lore().contains(tc)) return;
    List<Component> lore = List.of(tc);

    if (itemMeta.lore() != null) {
      lore = itemMeta.lore();
      lore.add(tc);
    }
    itemMeta.lore(lore);
    item.setItemMeta(itemMeta);
  }

  public static MagicSpell getSpell(ItemStack item){
    PersistentDataContainer itemData = item.getItemMeta().getPersistentDataContainer();
    MagicSpell spell = itemData.get(NameSpaceKeys.keyItemSpell, new MagicSpellDataType());
    if (spell == null) return MagicSpell.NULL;
    return spell;
  }
  public static void setSpell(ItemStack item, MagicSpell spell){
    ItemMeta meta = item.getItemMeta();
    PersistentDataContainer itemData = meta.getPersistentDataContainer();
    itemData.set(NameSpaceKeys.keyItemSpell, new MagicSpellDataType(), spell);
    item.setItemMeta(meta);
  }
  public static Boolean getAllowFlight(ItemStack item){
    ItemMeta meta = item.getItemMeta();
    PersistentDataContainer itemData = meta.getPersistentDataContainer();
    if (itemData.get(NameSpaceKeys.keyItemFlying, PersistentDataType.INTEGER) == null) return false;
    int allow = itemData.get(NameSpaceKeys.keyItemFlying, PersistentDataType.INTEGER);
    item.setItemMeta(meta);
    return allow >= 1;
  }
  public static void setAllowFlight(ItemStack item, Boolean allow){
    ItemMeta meta = item.getItemMeta();
    PersistentDataContainer itemData = meta.getPersistentDataContainer();
    itemData.set(NameSpaceKeys.keyItemFlying, PersistentDataType.INTEGER, allow ? 1 : 0);
    //Set lore to show if enabled
    List<Component> lore = item.getItemMeta().lore();
    for (int i = 0; i < lore.toArray().length; i++) {
      if (!(lore.get(i).toString().contains("Active:"))) continue;
      lore.set(i, Component.text("Active: "+(allow ? 1 : 0)).color(TextColor.fromHexString("#275F8F")).decoration(TextDecoration.ITALIC, false));
    }
    meta.lore(lore);
    item.setItemMeta(meta);
  }

    public static void setRecipe(ItemStack item, CraftingRecipe recipe) {
      ItemMeta meta = item.getItemMeta();
      PersistentDataContainer itemData = meta.getPersistentDataContainer();
      itemData.set(NameSpaceKeys.keyItemRecipe, new CraftingRecipeDataType(), recipe);
      item.setItemMeta(meta);
    }
  public static CraftingRecipe getRecipe(ItemStack item){
    PersistentDataContainer itemData = item.getItemMeta().getPersistentDataContainer();
    CraftingRecipe spell = itemData.get(NameSpaceKeys.keyItemRecipe, new CraftingRecipeDataType());
    if (spell == null) return CraftingRecipe.NULL;
    return spell;
  }
}
