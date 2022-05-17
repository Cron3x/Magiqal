package xyz.eburg.cx.magical.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import xyz.eburg.cx.magical.spells.FlightRing;
import xyz.eburg.cx.magical.spells.MagicSpell;
import xyz.eburg.cx.magical.utils.ItemUtils;

public class InventoryListener implements Listener {

  @EventHandler
  public void onInvChange(InventoryCloseEvent event){
    Player player = (Player) event.getView().getPlayer();
    ItemStack[] items = player.getInventory().getContents();
    for (ItemStack item : items){
      if (item == null) continue;
      System.out.println("@ InventoryEvent: item ("+item.getType()+") is not null");
      if (!item.getType().equals(Material.CLOCK)) continue;
      System.out.println("@ InventoryEvent: item ("+item.getType()+") is a clock");
      if (!ItemUtils.isMagiqal(item)) continue;
      System.out.println("@ InventoryEvent: item ("+item.getType()+") is magiqal");
      if (!ItemUtils.getSpell(item).equals(MagicSpell.FLIGHT)) continue;
      System.out.println("@ InventoryEvent: item ("+item.getType()+") has Flight Spell");
      if (!ItemUtils.getAllowFlight(item)) continue;
      System.out.println("@ InventoryEvent: item ("+item.getType()+") allows flight");
      new FlightRing(item, player, true);
      return;
    }
    player.setAllowFlight(false);
  }


  @EventHandler
  public void onDropChange(PlayerDropItemEvent e){
    if (!ItemUtils.getAllowFlight(e.getItemDrop().getItemStack())) return;
    Bukkit.broadcastMessage("droped Flight ring");
    ItemUtils.setAllowFlight(e.getItemDrop().getItemStack(), false);
    e.getPlayer().setAllowFlight(false);
  }
}
