package xyz.eburg.cx.magical.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.GameMode;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import xyz.eburg.cx.magical.Magical;

public class ManaUtils {
  public static void setManaAmount(Player player, int amount) {
    PersistentDataContainer dataContainer = player.getPersistentDataContainer();
    NamespacedKey keyManaAmount = new NamespacedKey(Magical.getInstance(), "mana_amount");
    if (!dataContainer.has(keyManaAmount)) NameSpaceKeys.addPersistenceKeys(player);
    dataContainer.set(keyManaAmount, PersistentDataType.INTEGER, amount);
  }
  public static int getManaAmount(Player player) {
    PersistentDataContainer dataContainer = player.getPersistentDataContainer();
    if (!dataContainer.has(NameSpaceKeys.keyManaAmount)) NameSpaceKeys.addPersistenceKeys(player);
    return dataContainer.get(NameSpaceKeys.keyManaAmount, PersistentDataType.INTEGER);
  }
  public static void addManaAmount(Player player, int amount) {
    setManaAmount(player, getManaAmount(player)+amount);
  }
  public static void removeManaAmount(Player player, int amount){
    if (player.getGameMode() == GameMode.CREATIVE) return;
    setManaAmount(player, getManaAmount(player) - amount);
  }
  public static Boolean hasEnoughMana(Player player, int cost){
    if (player.getGameMode() == GameMode.CREATIVE) return true;
    if (getManaAmount(player) < cost) {
      @NotNull TextComponent message = Component.text("\uF82B\uF829\uF829"+"\uE005\uF803\uE005\uF803\uE005\uF803\uE005\uF803\uE005\uF803\uE005\uF803\uE005\uF803\uE005\uF803\uE005\uF803\uE005\uF803\uE005\uF803\uE005\uF803\uE005\uF803");
      player.sendActionBar(message);
      player.sendActionBar(message);
      return false;
    }
    return true;
  }
  public static void setShowManaBar( Player player, boolean showManaBar){
    PersistentDataContainer dataContainer = player.getPersistentDataContainer();
    if (!dataContainer.has(NameSpaceKeys.keyShowManaBar)) NameSpaceKeys.addPersistenceKeys(player);
    dataContainer.set(NameSpaceKeys.keyShowManaBar, PersistentDataType.INTEGER, showManaBar ? 1 : 0);
  }
  public static Boolean hasManaBar(Player player){
    PersistentDataContainer dataContainer = player.getPersistentDataContainer();
    if (!dataContainer.has(NameSpaceKeys.keyShowManaBar)) NameSpaceKeys.addPersistenceKeys(player);
    return dataContainer.get(NameSpaceKeys.keyShowManaBar, PersistentDataType.INTEGER) > 0;
  }
}
