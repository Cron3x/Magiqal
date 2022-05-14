package xyz.eburg.cx.magical.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import xyz.eburg.cx.magical.spells.MagicSpell;
import xyz.eburg.cx.magical.spells.MagicSpellDataType;

import java.util.List;

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
  public static void setItemManaLore(ItemStack item, String loreText){
    ItemMeta itemMeta = item.getItemMeta();
    TextComponent tc = Component.text(loreText).color(TextColor.fromHexString("#FFFFFF")).decoration(TextDecoration.ITALIC, false);
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
    if (itemData.get(NameSpaceKeys.keyItemSpell, PersistentDataType.INTEGER) == null) return false;
    int allow = itemData.get(NameSpaceKeys.keyItemSpell, PersistentDataType.INTEGER);
    item.setItemMeta(meta);
    return allow >= 1;
  }
  public static void setAllowFlight(ItemStack item, Boolean allow){
    ItemMeta meta = item.getItemMeta();
    PersistentDataContainer itemData = meta.getPersistentDataContainer();
    itemData.set(NameSpaceKeys.keyItemSpell, PersistentDataType.INTEGER, allow ? 1 : 0);
    //Set lore to show if enabled
    List<Component> lore = item.getItemMeta().lore();
    for (Component comp : lore) {
      Bukkit.broadcast(comp);
    }
    item.setItemMeta(meta);
  }
}
