package xyz.eburg.cx.magical.listeners;

import net.kyori.adventure.text.Component;
import net.minecraft.world.level.block.ChestBlock;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Tripwire;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import xyz.eburg.cx.magical.Magical;
import xyz.eburg.cx.magical.spells.FlightRing;
import xyz.eburg.cx.magical.spells.MagicSpell;
import xyz.eburg.cx.magical.spells.TransmutationSpell;
import xyz.eburg.cx.magical.tasks.ShowManaTask;
import xyz.eburg.cx.magical.utils.BarCharacter;
import xyz.eburg.cx.magical.utils.ItemUtils;
import xyz.eburg.cx.magical.utils.ManaUtils;

public class ItemListener implements Listener {
  @EventHandler
  public void onJoin(PlayerJoinEvent event){
    Player player = event.getPlayer();

    if (!Magical.getInstance().getConfig().getBoolean("dynamic_man_bar")) new ShowManaTask().runTaskTimer(Magical.getInstance(), 0L, 30L);
    if (player.getGameMode().equals(GameMode.ADVENTURE) || player.getGameMode().equals(GameMode.SURVIVAL)) {
      player.setAllowFlight(true);
      player.setFlying(true);
      ItemStack[] items = player.getInventory().getContents();
      for (ItemStack item : items){
        if (item == null) continue;
        System.out.println("@ join: item ("+item.getType()+") is not null");
        if (!item.getType().equals(Material.CLOCK)) continue;
        System.out.println("@ join: item ("+item.getType()+") is a clock");
        if (!ItemUtils.isMagiqal(item)) continue;
        System.out.println("@ join: item ("+item.getType()+") is magiqal");
        if (!ItemUtils.getSpell(item).equals(MagicSpell.FLIGHT)) continue;
        System.out.println("@ join: item ("+item.getType()+") has Flight Spell");
        if (!ItemUtils.getAllowFlight(item)) continue;
        System.out.println("@ join: item ("+item.getType()+") allows flight");
        new FlightRing(item, player, true);
        return;
      }
      player.setAllowFlight(false);
      player.setFlying(false);
    }

  }

  @EventHandler
  public void onUserItem(PlayerInteractEvent event){
    Player player = event.getPlayer();
    ItemStack item = event.getItem();

    if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.SEA_LANTERN)){
      Location beaconLoc = event.getClickedBlock().getLocation();
      Location altarLoc = new Location(beaconLoc.getWorld(), (double) beaconLoc.getBlockX(), (double) beaconLoc.getBlockY()+1.0, (double) beaconLoc.getBlockZ());

      BlockData blockData = event.getClickedBlock().getWorld().getBlockData(altarLoc);
      if (!(blockData instanceof Tripwire tripwire)) return;
      tripwire.setDisarmed(true);
      tripwire.setAttached(false);
      tripwire.setFace(BlockFace.NORTH, true);
      tripwire.setFace(BlockFace.EAST, true);
      tripwire.setFace(BlockFace.SOUTH, true);
      tripwire.setFace(BlockFace.WEST, true);
      tripwire.setPowered(true);
      Bukkit.broadcastMessage("HELLO-adwfeghjki");
      if (!event.getClickedBlock().getWorld().getBlockData(altarLoc).equals(tripwire)) return;
      player.closeInventory();
      Inventory customInv = Bukkit.createInventory(player, 9, Component.text("Light Crafing Altar"));
      ItemStack gui0 = new ItemStack(Material.GOLDEN_HOE);
      customInv.setItem(0, gui0);
      player.openInventory(customInv);
    }

    if (item == null ) return;
    if (item.getType() == Material.COMPASS && event.getAction() == Action.RIGHT_CLICK_BLOCK  && event.getClickedBlock().getType() == Material.LODESTONE && !ItemUtils.isMagiqal(item)) {
      ItemUtils.setMagiqal(item, true);
      ItemUtils.setItemManaLore(item, ">  - " + BarCharacter.fullIcon + BarCharacter.fullIcon);
    }

    if (!ItemUtils.isMagiqal(item)) return;
    switch (item.getType()){
      case CLOCK -> { //Maybe something like command_block
        switch (ItemUtils.getSpell(item)) {
          case FIREBALL -> {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
              player.sendMessage("................:> FIREBALL");
            }
          }
          case TRANSMUTATION -> {
            if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.SEA_LANTERN)) return;
            event.setCancelled(true);
            Location loc = event.getClickedBlock().getLocation();
            World world = event.getClickedBlock().getLocation().getWorld();
            if (!(ManaUtils.hasEnoughMana(player, 52))) return;
            if (!new TransmutationSpell(world, loc).conjure()) return;
            ManaUtils.removeManaAmount(player, 52);
          }
          case FLIGHT ->{
            if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getState() instanceof Container) return;
            Bukkit.broadcastMessage("LOL");
            new FlightRing(item, player);
          }
        }
      }
      case COMPASS -> {
          if (!item.getItemMeta().getAsString().contains("LodestoneTracked:1")) return;
          if (!(event.getAction() == Action.RIGHT_CLICK_AIR || !(event.getAction() == Action.RIGHT_CLICK_BLOCK  && event.getClickedBlock().getType() == Material.LODESTONE))) return;
          CompassMeta compassMeta = (CompassMeta) item.getItemMeta();

        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Magical.getInstance(), () -> {

        }, 0, 20);

          double lodeposX = compassMeta.getLodestone().getBlockX();
          double lodeposY = compassMeta.getLodestone().getBlockY();
          double lodeposZ = compassMeta.getLodestone().getBlockZ();
          World world = compassMeta.getLodestone().getWorld();

          int cost = 8; // (2 drips); add to config file
          if (!ManaUtils.hasEnoughMana(player, cost)) return;
          ManaUtils.removeManaAmount(player, cost);
          player.teleport(new Location(world, lodeposX + 0.5, lodeposY + 1, lodeposZ + 0.5));
      }
    }
  }

  @EventHandler
  public void onPlayerEat(PlayerItemConsumeEvent event){
    Player player = event.getPlayer();
    int manaration = 0;
    switch(event.getItem().getType()) {
      case GOLDEN_APPLE           -> manaration = 34;
      case GOLDEN_CARROT          -> manaration = 18;
      case ENCHANTED_GOLDEN_APPLE -> manaration = 18;
    }

    int oldScore = ManaUtils.getManaAmount(player);
    if (oldScore >= 52 ) return;
    int newScore = oldScore + manaration;
    while (newScore>52){
      newScore -= 1;
    }
    ManaUtils.setManaAmount(player, newScore);
  }

  @EventHandler
  public void triggerTotem(EntityResurrectEvent event){
    if (event.isCancelled() || !(event.getEntity() instanceof Player)) return;
    Player player = (Player) event.getEntity();
    ManaUtils.setManaAmount(player, 52);
  }
}
