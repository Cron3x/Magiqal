package xyz.eburg.cx.magical.listeners;

import net.minecraft.advancements.critereon.UsedTotemTrigger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityResurrectEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.CompassMeta;
import xyz.eburg.cx.magical.Magical;
import xyz.eburg.cx.magical.spells.TransmutationSpell;
import xyz.eburg.cx.magical.tasks.ShowManaTask;
import xyz.eburg.cx.magical.utils.BarCharacter;
import xyz.eburg.cx.magical.utils.ItemUtils;
import xyz.eburg.cx.magical.utils.ManaUtils;

public class ItemListener implements Listener {
  @EventHandler
  public void onJoin(PlayerJoinEvent event){
    if (!Magical.getInstance().getConfig().getBoolean("dynamic_man_bar")) new ShowManaTask().runTaskTimer(Magical.getInstance(), 0L, 30L);
  }

  @EventHandler
  public void onUserItem(PlayerInteractEvent event){
    Player player = event.getPlayer();
    ItemStack item = event.getItem();
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
            if (!(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock().getType() == Material.ENCHANTING_TABLE)) return;
            Location loc = event.getClickedBlock().getLocation();
            World world = event.getClickedBlock().getLocation().getWorld();
            if (!(ManaUtils.hasEnoughMana(player, 52))) return;
            if (!new TransmutationSpell(world, loc).conjure()) return;
            ManaUtils.removeManaAmount(player, 52);
          }
        }
      }
      case COMPASS -> {
          if (!item.getItemMeta().getAsString().contains("LodestoneTracked:1")) return;
          if (!(event.getAction() == Action.RIGHT_CLICK_AIR || !(event.getAction() == Action.RIGHT_CLICK_BLOCK  && event.getClickedBlock().getType() == Material.LODESTONE))) return;
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
      case GOLDEN_APPLE -> manaration = 34;
      case GOLDEN_CARROT -> manaration = 18;
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
