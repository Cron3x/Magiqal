package xyz.eburg.cx.magical.listeners;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Tripwire;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import xyz.eburg.cx.magical.Magical;
import xyz.eburg.cx.magical.data_types.Dimension;
import xyz.eburg.cx.magical.recipes.altar.dark.DarkCraftingManager;
import xyz.eburg.cx.magical.data_types.MagicSpell;
import xyz.eburg.cx.magical.spells.*;
import xyz.eburg.cx.magical.tasks.ShowManaTask;
import xyz.eburg.cx.magical.teleport.TeleportManager;
import xyz.eburg.cx.magical.ui.CraftingAltarGUI;
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
  public void onUserItem(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    ItemStack item = event.getItem();

    if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.SEA_LANTERN)) {
      Location beaconLoc = event.getClickedBlock().getLocation();
      Location altarLoc = new Location(beaconLoc.getWorld(), (double) beaconLoc.getBlockX(), (double) beaconLoc.getBlockY() + 1.0, (double) beaconLoc.getBlockZ());

      BlockData blockData = event.getClickedBlock().getWorld().getBlockData(altarLoc);
      if (blockData instanceof Tripwire tripwire) {
        tripwire.setDisarmed(true);
        tripwire.setAttached(false);
        tripwire.setFace(BlockFace.NORTH, true);
        tripwire.setFace(BlockFace.EAST, true);
        tripwire.setFace(BlockFace.SOUTH, true);
        tripwire.setFace(BlockFace.WEST, true);
        tripwire.setPowered(true);
        Bukkit.broadcastMessage("HELLO-adwfeghjki");
        if (event.getClickedBlock().getWorld().getBlockData(altarLoc).equals(tripwire)) {
          new CraftingAltarGUI(player).create();
        }
      }
    }
    //DarkAltar Interaction
    else if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.MAGMA_BLOCK)) {

      Location beaconLoc = event.getClickedBlock().getLocation();
      Location altarLoc = new Location(beaconLoc.getWorld(), beaconLoc.getBlockX(), (double) beaconLoc.getBlockY() + 1.0, beaconLoc.getBlockZ());

      BlockData blockData = event.getClickedBlock().getWorld().getBlockData(altarLoc);
      if (blockData instanceof Tripwire tripwire) {
        tripwire.setDisarmed(true);
        tripwire.setAttached(false);
        tripwire.setFace(BlockFace.NORTH, false);
        tripwire.setFace(BlockFace.EAST, true);
        tripwire.setFace(BlockFace.SOUTH, true);
        tripwire.setFace(BlockFace.WEST, true);
        tripwire.setPowered(true);
        Bukkit.broadcastMessage("HELLO-adwfeghjki-dark");
        if (event.getClickedBlock().getWorld().getBlockData(altarLoc).equals(tripwire)) {
          if (event.getItem() == null && event.getHand().equals(EquipmentSlot.HAND)) {
            Bukkit.broadcastMessage("HELLO-hs-dark");
            for (Item itemEntity : altarLoc.getNearbyEntitiesByType(Item.class, 2)){
              if (!DarkCraftingManager.isMainCraftingItem(itemEntity.getItemStack())) continue;
              itemEntity.setCanPlayerPickup(true);
              itemEntity.setPickupDelay(0);
              break;
            }
            return;
          }
          if (!DarkCraftingManager.isMainCraftingItem(item)) return;
          if (!DarkCraftingManager.spawnMainItem(event.getClickedBlock().getLocation(), item.asOne())) return;

          player.getInventory().getItemInMainHand().setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
          player.updateInventory();
        }
      }
    }


    if (item == null) return;
    if (item.getType() == Material.COMPASS && event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.LODESTONE && !ItemUtils.isMagiqal(item)) {
      ItemUtils.setMagiqal(item, true);
      ItemUtils.setItemManaLore(item, 2);
    }

    if (!ItemUtils.isMagiqal(item)) return;
    switch (item.getType()) {
      case CLOCK -> {
        switch (ItemUtils.getSpell(item)) {
          case FIREBALL -> {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
              player.sendMessage("................:> FIREBALL");
            }
          }
          case TRANSMUTATION -> {
            if ((event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.SEA_LANTERN)) {
              Bukkit.broadcastMessage("> clicked SEA_LANTERN");
              Location loc = event.getClickedBlock().getLocation();
              World world = event.getClickedBlock().getLocation().getWorld();
              if (!(ManaUtils.hasEnoughMana(player, 4))) return;
              Bukkit.broadcastMessage("> You have enugh Mana");
              if (!new LightAltarTransmutationSpell(world, loc).conjure()) return;
              Bukkit.broadcastMessage("> Placment does not failed");
              ManaUtils.removeManaAmount(player, 4);
            }
            if ((event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.MAGMA_BLOCK)) {
              Bukkit.broadcastMessage("> clicked MAGMA_BLOCK");
              Location loc = event.getClickedBlock().getLocation();
              World world = event.getClickedBlock().getLocation().getWorld();
              if (!(ManaUtils.hasEnoughMana(player, 4))) return;
              Bukkit.broadcastMessage("> You have enugh Mana");
              if (!new DarkAltarTransmutationSpell(world, loc).conjure()) return;
              Bukkit.broadcastMessage("> Placment does not failed");
              ManaUtils.removeManaAmount(player, 4);
            }
            if ((event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.LODESTONE)) {
              Bukkit.broadcastMessage("> clicked LODESTONE");
              Location loc = event.getClickedBlock().getLocation();
              World world = event.getClickedBlock().getLocation().getWorld();
              if (!(ManaUtils.hasEnoughMana(player, 4))) return;
              Bukkit.broadcastMessage("> You have enugh Mana");
              if (!new TeleporterTransmutationSpell(world, loc).conjure()) return;
              Bukkit.broadcastMessage("> Placment does not failed");
              ManaUtils.removeManaAmount(player, 4);
            }
          }
          case FLIGHT -> {
            if (!(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK))
              return;
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getState() instanceof Container)
              return;
            Bukkit.broadcastMessage("LOL");
            new FlightRing(item, player);
          }
        }
      }
      case COMPASS -> {
        if (!item.getItemMeta().getAsString().contains("LodestoneTracked:1")) return;
        if (!(event.getAction() == Action.RIGHT_CLICK_AIR || !(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.LODESTONE)))
          return;
        CompassMeta compassMeta = (CompassMeta) item.getItemMeta();

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

  //Migrate to own file
  @EventHandler
  public void onSneak(PlayerToggleSneakEvent event){
    Player player = event.getPlayer();

    Block potTeleporter = event.getPlayer().getWorld().getBlockAt(player.getLocation().subtract(0,-1,0));
    if (!potTeleporter.getType().equals(Material.LODESTONE)) return;

  }

  //Migrate to own file
  @EventHandler
  public void onDrop(PlayerDropItemEvent event){
    Bukkit.broadcastMessage("Drop :: " + event.getItemDrop().getLocation().subtract(0,1,0).getBlock().getType());

    //Location is at player -> Fix!
    if (event.getItemDrop().getLocation().subtract(0,1,0).getBlock().getType().equals(Material.SOUL_CAMPFIRE)) {
      Bukkit.broadcastMessage("::> "+ItemUtils.getDimension(event.getItemDrop().getItemStack()));
      if (ItemUtils.getDimension(event.getItemDrop().getItemStack()).equals(Dimension.NULL)) return;
      TeleportManager.teleport(event.getItemDrop());
      return;
    }
    //if (DarkCraftingManager.isAltar(event.getItemDrop().getLocation().getBlock()))
    if (!DarkCraftingManager.recipes.contains(event.getItemDrop().getItemStack().getType())) return;
    Bukkit.broadcastMessage("is a valid activation");
    for (Item item : event.getItemDrop().getWorld().getNearbyEntitiesByType(Item.class, event.getItemDrop().getLocation(), 4)) {
      if (!DarkCraftingManager.isMainCraftingItem(item.getItemStack())) continue;
      DarkCraftingManager.initCraft(item, event.getItemDrop());
      break;
    }
    //DarkCraftingManager.isAltar(event.getItemDrop().getLocation().getBlock());
  }

}
