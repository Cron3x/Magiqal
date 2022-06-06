package xyz.eburg.cx.magical.utils;

import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.eburg.cx.magical.Magical;

import java.lang.reflect.Field;
import java.util.Set;

public class EnchantGlow extends Enchantment {

  public EnchantGlow(NamespacedKey id) {
    super(id);
  }

  public EnchantGlow() {
    super(new NamespacedKey(Magical.getInstance(), "enchanted_glow"));
  }

  @Override
  public @NotNull String getName() {
    return null;
  }

  @Override
  public int getMaxLevel() {
    return 0;
  }

  @Override
  public int getStartLevel() {
    return 0;
  }

  @Override
  public @NotNull EnchantmentTarget getItemTarget() {
    return null;
  }

  @Override
  public boolean isTreasure() {
    return false;
  }

  @Override
  public boolean isCursed() {
    return false;
  }

  @Override
  public boolean conflictsWith(@NotNull Enchantment other) {
    return false;
  }

  @Override
  public boolean canEnchantItem(@NotNull ItemStack item) {
    return true;
  }

  @Override
  public @NotNull Component displayName(int level) {
    return null;
  }

  @Override
  public boolean isTradeable() {
    return false;
  }

  @Override
  public boolean isDiscoverable() {
    return false;
  }

  @Override
  public @NotNull EnchantmentRarity getRarity() {
    return null;
  }

  @Override
  public float getDamageIncrease(int level, @NotNull EntityCategory entityCategory) {
    return 0;
  }

  @Override
  public @NotNull Set<EquipmentSlot> getActiveSlots() {
    return null;
  }

  @Override
  public @NotNull String translationKey() {
    return null;
  }
  public static void registerGlow() {
    try {
      Field f = Enchantment.class.getDeclaredField("acceptingNew");
      f.setAccessible(true);
      f.set(null, true);
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    try {
      EnchantGlow glow = new EnchantGlow(new NamespacedKey(Magical.getInstance(), "enchanted_glow"));
      Enchantment.registerEnchantment(glow);
    }
    catch (IllegalArgumentException e){
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }
}
