package xyz.eburg.cx.magical.utils;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import xyz.eburg.cx.magical.Magical;

public interface NameSpaceKeys {
  NamespacedKey keyManaAmount = new NamespacedKey(Magical.getInstance(), "mana_amount");
  NamespacedKey keyShowManaBar = new NamespacedKey(Magical.getInstance(), "show_mana_bar");
  NamespacedKey keyMageLevel = new NamespacedKey(Magical.getInstance(), "mage_level");
  NamespacedKey keyMageXP = new NamespacedKey(Magical.getInstance(), "mage_xp");
  NamespacedKey keyItemMagiqal = new NamespacedKey(Magical.getInstance(), "magiqal");
  NamespacedKey keyItemSpell = new NamespacedKey(Magical.getInstance(), "spell");

  public static void addPersistenceKeys(Player player) {
    PersistentDataContainer dataContainer = player.getPersistentDataContainer();
    if (!dataContainer.has(keyManaAmount)) dataContainer.set(keyManaAmount, PersistentDataType.INTEGER, 0); //should 52
    if (!dataContainer.has(keyShowManaBar)) dataContainer.set(keyShowManaBar, PersistentDataType.INTEGER, 0);
    if (!dataContainer.has(keyMageLevel)) dataContainer.set(keyMageLevel, PersistentDataType.INTEGER, 0);
    if (!dataContainer.has(keyMageXP)) dataContainer.set(keyMageXP, PersistentDataType.INTEGER, 0);
  }
}
