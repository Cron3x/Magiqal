package xyz.eburg.cx.magical.spells;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.eburg.cx.magical.utils.ItemUtils;

public class FlightRing {
  public FlightRing(ItemStack item, Player player) {
    if (player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR)) return;
    boolean allow_flight = ItemUtils.getAllowFlight(item);
    ItemUtils.setAllowFlight(item, !allow_flight);
    player.setAllowFlight(!allow_flight);
  }
  public FlightRing(ItemStack item, Player player, Boolean allow) {
    if (player.getGameMode().equals(GameMode.CREATIVE) || player.getGameMode().equals(GameMode.SPECTATOR)) return;
    ItemUtils.setAllowFlight(item, allow);
    player.setAllowFlight(allow);
  }
}
