package xyz.eburg.cx.magical.recipes.altar.dark;

import com.destroystokyo.paper.event.entity.EntityTeleportEndGatewayEvent;
import org.apache.logging.log4j.core.jmx.LoggerConfigAdmin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.EndGateway;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Tripwire;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftEnderCrystal;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityPortalEnterEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;
import xyz.eburg.cx.magical.Magical;
import xyz.eburg.cx.magical.utils.ItemUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DarkCraftingManager {

  //Todo: Recipe Manager (Load recipes from json file)

  public static List<Material> recipes = Arrays.stream(new Material[]{Material.CLOCK, Material.AMETHYST_SHARD}).toList();

  public static boolean isMainCraftingItem(ItemStack item) {
    return ItemUtils.getRecipe(item).equals(CraftingRecipe.DIMENSION_SHARD);
  }

  public static boolean spawnMainItem(Location location, ItemStack item) {
    for (Item entity : location.getNearbyEntitiesByType(Item.class, 2)) {
      if (isMainCraftingItem(entity.getItemStack())) return false;
    }

    Item itemEntity = location.getWorld().dropItemNaturally(location.add(0, 1, 0), item);
    itemEntity.setPickupDelay(32767);
    itemEntity.setWillAge(false);
    itemEntity.setUnlimitedLifetime(true);
    itemEntity.setCanPlayerPickup(false);
    itemEntity.setVelocity(new Vector(0, 0, 0));
    return true;
  }

  public static boolean isAltar(Block block) {
    Bukkit.broadcastMessage("" + block.getType());
    BlockData blockData = block.getBlockData();
    if (blockData instanceof Tripwire tripwire) {
      tripwire.setDisarmed(true);
      tripwire.setAttached(false);
      tripwire.setFace(BlockFace.NORTH, false);
      tripwire.setFace(BlockFace.EAST, true);
      tripwire.setFace(BlockFace.SOUTH, true);
      tripwire.setFace(BlockFace.WEST, true);
      tripwire.setPowered(true);
      Bukkit.broadcastMessage(">Dark Altar ?");
      return block.getBlockData().equals(tripwire);
    }
    return false;
  }

  public static void createPortal(Item core, Item activation_key) {
    Location loc = core.getLocation();
    double orginalY = loc.getY();

    loc.getWorld().spawnParticle(Particle.FLASH,loc, 1);

    Location ecLoc0 = new Location(loc.getWorld(), loc.getX()+3,loc.getY()+4, loc.getZ());
    Location ecLoc1 = new Location(loc.getWorld(), loc.getX()-3,loc.getY()+4, loc.getZ());
    Location ecLoc2 = new Location(loc.getWorld(), loc.getX(),loc.getY()+4, loc.getZ()+3);
    Location ecLoc3 = new Location(loc.getWorld(), loc.getX(),loc.getY()+4, loc.getZ()-3);
    Location startBeamLoc = new Location(loc.getWorld(), loc.getX(),loc.getY()-3, loc.getZ());
    EnderCrystal ec0 = (EnderCrystal) loc.getWorld().spawnEntity(ecLoc0, EntityType.ENDER_CRYSTAL);
    ec0.setShowingBottom(false);
    ec0.setBeamTarget(startBeamLoc);
    Bukkit.broadcastMessage("@>> "+ec0.getBeamTarget());
    ec0.setInvulnerable(true);

    EnderCrystal ec1 = (EnderCrystal) loc.getWorld().spawnEntity(ecLoc1, EntityType.ENDER_CRYSTAL);
    ec1.setShowingBottom(false);
    ec1.setBeamTarget(startBeamLoc);
    Bukkit.broadcastMessage("@>> "+ec1.getBeamTarget());
    ec1.setInvulnerable(true);

    EnderCrystal ec2 = (EnderCrystal) loc.getWorld().spawnEntity(ecLoc2, EntityType.ENDER_CRYSTAL);
    ec2.setShowingBottom(false);
    ec2.setBeamTarget(startBeamLoc);
    Bukkit.broadcastMessage("@>> "+ec2.getBeamTarget());
    ec2.setInvulnerable(true);

    EnderCrystal ec3 = (EnderCrystal) loc.getWorld().spawnEntity(ecLoc3, EntityType.ENDER_CRYSTAL);
    ec3.setShowingBottom(false);
    ec3.setBeamTarget(startBeamLoc);
    Bukkit.broadcastMessage("@>> "+ec3.getBeamTarget());
    ec3.setInvulnerable(true);
    //((CraftEnderCrystal) ec).getHandle().setInvisible(true);

    activation_key.teleport(new Location(core.getWorld(), 0,-100,0));

    new BukkitRunnable() {
      final Location beamBorderY = new Location(loc.getWorld(), loc.getX(), orginalY, loc.getZ());
      @Override
      public void run() {
        Location curLoc = startBeamLoc.add(0,0.05,0);
        ec0.setBeamTarget(curLoc);
        ec1.setBeamTarget(curLoc);
        ec2.setBeamTarget(curLoc);
        ec3.setBeamTarget(curLoc);
        loc.getWorld().spawnParticle(Particle.PORTAL, new Location(curLoc.getWorld(),curLoc.getX(),curLoc.getY()+2,curLoc.getZ()), 3,0, 0, 0, null);
        loc.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, new Location(curLoc.getWorld(),curLoc.getX(),curLoc.getY()+2,curLoc.getZ()), 10,0, 0, 0, null);
        loc.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, new Location(curLoc.getWorld(),curLoc.getX(),curLoc.getY()+1,curLoc.getZ()), 2,0, 0, 0, null);
        loc.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, curLoc, 1,0, 0, 0, null);
        if (startBeamLoc.getY() >= beamBorderY.getY()) {
          startBeamLoc.getWorld().strikeLightningEffect(beamBorderY.add(0,2,0));


          ItemUtils.Dimension.add(core.getItemStack());

          this.cancel();
        }
      }
    }.runTaskTimer(Magical.getInstance(), 20L*2, 1L);
  }


}


/* {
 *  "core": "",
 *  "activation":"",
 *  "dimension": "",
 *  "preferences":{
 *    "particle_color": #FFFFFF,
 *    "particle_
 *  }
 * }
 */
