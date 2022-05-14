package xyz.eburg.cx.magical.listeners;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import xyz.eburg.cx.magical.spells.MagicSpell;
import xyz.eburg.cx.magical.utils.ItemUtils;

public class InventoryListener implements Listener {
  @EventHandler
  public void onInvChange(InventoryClickEvent e){
  }
  @EventHandler
  public void onInvChange(InventoryEvent e){
    if (!e.getInventory().getType().equals(InventoryType.PLAYER)) return;
    Player player = (Player) e.getView().getPlayer();
    if (!player.getAllowFlight() || player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) return;

    for (ItemStack it : e.getInventory().getContents()){
      assert it != null;
      if (ItemUtils.getSpell(it).equals(MagicSpell.FLIGHT)) return;
    }
    player.setAllowFlight(false);
  }

}