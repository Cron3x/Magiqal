package xyz.eburg.cx.magical.ui;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.eburg.cx.magical.Magical;
import xyz.eburg.cx.magical.spells.MagicSpell;
import xyz.eburg.cx.magical.utils.BarCharacter;
import xyz.eburg.cx.magical.utils.ItemUtils;

import java.util.HashMap;
import java.util.List;

public class CraftingAltarGUI {
  private final Component altarComponent = Component.text("Light Crafting Altar");
  private Player player = null;
  private Inventory inventory = null;
  //> Crafting Slots
  //  10 11 12
  //  19 20 21 25
  //  28 29 30 32
  public CraftingAltarGUI(Player player, Inventory inventory) {
    this.player = player;
    this.inventory = inventory;
  }
  public CraftingAltarGUI(Inventory inventory) {
    this.inventory = inventory;
  }
  public CraftingAltarGUI(Player player) {
    this.player = player;
  }
  public void create(){
    player.closeInventory();
    Inventory customInv = Bukkit.createInventory(player, 45, altarComponent);
    ItemStack gui0 = new ItemStack(Material.GOLDEN_HOE);
    ItemMeta gui0Meta = gui0.getItemMeta();
    gui0Meta.setCustomModelData(1);
    gui0Meta.displayName(Component.text(""));
    gui0Meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    gui0.setItemMeta(gui0Meta);
    customInv.setItem(0, gui0);

    ItemStack barrier = new ItemStack(Material.GOLDEN_HOE);
    ItemMeta barrierMeta = barrier.getItemMeta();
    barrierMeta.setCustomModelData(2147483647);
    barrierMeta.displayName(Component.text(""));
    barrierMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    barrier.setItemMeta(barrierMeta);
    for (int i = 0; i < 45; i++) {
      if (i == 0 || i == 10 || i == 11 || i == 12 || i == 19 || i == 20 || i == 21 || i == 25 || i == 28 || i == 29 || i == 30 || i == 32) continue;
      customInv.setItem(i, barrier);
    }


    player.openInventory(customInv);
  }

  public void closing(){
    if (!player.getOpenInventory().title().equals(altarComponent)) return;
    ItemStack slot10 = player.getOpenInventory().getItem(10);
    ItemStack slot11 = player.getOpenInventory().getItem(11);
    ItemStack slot12 = player.getOpenInventory().getItem(12);
    ItemStack slot19 = player.getOpenInventory().getItem(19);
    ItemStack slot20 = player.getOpenInventory().getItem(20);
    ItemStack slot21 = player.getOpenInventory().getItem(21);
    ItemStack slot28 = player.getOpenInventory().getItem(28);
    ItemStack slot29 = player.getOpenInventory().getItem(29);
    ItemStack slot30 = player.getOpenInventory().getItem(30);
    ItemStack slot32 = player.getOpenInventory().getItem(32);
    if (slot10 != null && !slot10.getType().equals(Material.AIR) ) player.getWorld().dropItem(player.getLocation(), slot10);
    if (slot11 != null && !slot11.getType().equals(Material.AIR) ) player.getWorld().dropItem(player.getLocation(), slot11);
    if (slot12 != null && !slot12.getType().equals(Material.AIR) ) player.getWorld().dropItem(player.getLocation(), slot12);
    if (slot19 != null && !slot19.getType().equals(Material.AIR) ) player.getWorld().dropItem(player.getLocation(), slot19);
    if (slot20 != null && !slot20.getType().equals(Material.AIR) ) player.getWorld().dropItem(player.getLocation(), slot20);
    if (slot21 != null && !slot21.getType().equals(Material.AIR) ) player.getWorld().dropItem(player.getLocation(), slot21);
    if (slot28 != null && !slot28.getType().equals(Material.AIR) ) player.getWorld().dropItem(player.getLocation(), slot28);
    if (slot29 != null && !slot29.getType().equals(Material.AIR) ) player.getWorld().dropItem(player.getLocation(), slot29);
    if (slot30 != null && !slot30.getType().equals(Material.AIR) ) player.getWorld().dropItem(player.getLocation(), slot30);
    if (slot32 != null && !slot32.getType().equals(Material.AIR) ) player.getWorld().dropItem(player.getLocation(), slot32);
  }

  public void update() {
    if (!(inventory.getItem(0) != null && inventory.getItem(0).getType().equals(Material.GOLDEN_HOE) && inventory.getItem(0).getItemMeta().getCustomModelData() == 1)) return;
    ItemStack craftingSlot1 = inventory.getItem(10);
    ItemStack craftingSlot2 = inventory.getItem(11);
    ItemStack craftingSlot3 = inventory.getItem(12);
    ItemStack craftingSlot4 = inventory.getItem(19);
    ItemStack craftingSlot5 = inventory.getItem(20);
    ItemStack craftingSlot6 = inventory.getItem(21);
    ItemStack craftingSlot7 = inventory.getItem(28);
    ItemStack craftingSlot8 = inventory.getItem(29);
    ItemStack craftingSlot9 = inventory.getItem(30);
    ItemStack wantSlot = inventory.getItem(32);


    if (   (craftingSlot1 == null || craftingSlot1.getType().equals(Material.AIR))
        && (craftingSlot2 != null && craftingSlot2.getType().equals(Material.DIAMOND))
        && (craftingSlot3 == null || craftingSlot3.getType().equals(Material.AIR))
        && (craftingSlot4 != null && craftingSlot4.getType().equals(Material.FEATHER))
        && (craftingSlot5 != null && craftingSlot5.getType().equals(Material.ELYTRA))
        && (craftingSlot6 != null && craftingSlot6.getType().equals(Material.FEATHER))
        && (craftingSlot7 == null || craftingSlot7.getType().equals(Material.AIR))
        && (craftingSlot8 != null && craftingSlot8.getType().equals(Material.TOTEM_OF_UNDYING))
        && (craftingSlot9 == null || craftingSlot9.getType().equals(Material.AIR))
    ){
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
      ItemUtils.setItemManaLore(result,"- " + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon + BarCharacter.fullIcon);
      inventory.setItem(25,result);
      for (HumanEntity he : inventory.getViewers()){
        Player pl = (Player) he;
        pl.updateInventory();
      }
      inventory.setItem(10, null);
      inventory.setItem(11, null);
      inventory.setItem(12, null);
      inventory.setItem(19, null);
      inventory.setItem(20, null);
      inventory.setItem(21, null);
      inventory.setItem(28, null);
      inventory.setItem(29, null);
      inventory.setItem(30, null);
    }
  }
}
