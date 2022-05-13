package xyz.eburg.cx.magical.spells;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Candle;
import org.bukkit.block.data.type.Light;
import org.bukkit.block.data.type.Tripwire;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.material.MaterialData;
import org.bukkit.metadata.MetadataValue;

import java.util.Arrays;

public class TransmutationSpell {
  private final World world;
  private final Location loc;
  public TransmutationSpell(World world, Location location) {
    this.loc = location;
    this.world = world;
  }

  public Boolean conjure() {

    Location altarPos = new Location(this.loc.getWorld() ,this.loc.getBlockX(), this.loc.getBlockY()+1, this.loc.getBlockZ()).toCenterLocation();

    //South East
    Location pillarSE0 = new Location(this.loc.getWorld() ,this.loc.getBlockX() + 2, this.loc.getBlockY(), this.loc.getBlockZ() + 2).toCenterLocation();
    Location pillarSE1 = new Location(this.loc.getWorld() ,this.loc.getBlockX() + 2, this.loc.getBlockY()+1, this.loc.getBlockZ() + 2).toCenterLocation();
    Location pillarSE2 = new Location(this.loc.getWorld() ,this.loc.getBlockX() + 2, this.loc.getBlockY()+2, this.loc.getBlockZ() + 2).toCenterLocation();

    //South West
    Location pillarSW0 = new Location(this.loc.getWorld() ,this.loc.getBlockX() - 2, this.loc.getBlockY(), this.loc.getBlockZ() + 2).toCenterLocation();
    Location pillarSW1 = new Location(this.loc.getWorld() ,this.loc.getBlockX() - 2, this.loc.getBlockY()+1, this.loc.getBlockZ() + 2).toCenterLocation();
    Location pillarSW2 = new Location(this.loc.getWorld() ,this.loc.getBlockX() - 2, this.loc.getBlockY()+2, this.loc.getBlockZ() + 2).toCenterLocation();

    //North East
    Location pillarNE0 = new Location(this.loc.getWorld() ,this.loc.getBlockX() + 2, this.loc.getBlockY(), this.loc.getBlockZ() - 2).toCenterLocation();
    Location pillarNE1 = new Location(this.loc.getWorld() ,this.loc.getBlockX() + 2, this.loc.getBlockY()+1, this.loc.getBlockZ() - 2).toCenterLocation();
    Location pillarNE2 = new Location(this.loc.getWorld() ,this.loc.getBlockX() + 2, this.loc.getBlockY()+2, this.loc.getBlockZ() - 2).toCenterLocation();

    //North West
    Location pillarNW0 = new Location(this.loc.getWorld() ,this.loc.getBlockX() - 2, this.loc.getBlockY(), this.loc.getBlockZ() - 2).toCenterLocation();
    Location pillarNW1 = new Location(this.loc.getWorld() ,this.loc.getBlockX() - 2, this.loc.getBlockY()+1, this.loc.getBlockZ() - 2).toCenterLocation();
    Location pillarNW2 = new Location(this.loc.getWorld() ,this.loc.getBlockX() - 2, this.loc.getBlockY()+2, this.loc.getBlockZ() - 2).toCenterLocation();

    Bukkit.broadcastMessage("iadhwiudh");

    if (!(this.world.getBlockAt(pillarNE0).getType().equals( Material.QUARTZ_PILLAR)
      && this.world.getBlockAt(pillarNE1).getType().equals( Material.QUARTZ_PILLAR)
      && this.world.getBlockAt(pillarNE2).getType().equals( Material.AMETHYST_BLOCK)
      && this.world.getBlockAt(pillarNW0).getType().equals( Material.QUARTZ_PILLAR)
      && this.world.getBlockAt(pillarNW1).getType().equals( Material.QUARTZ_PILLAR)
      && this.world.getBlockAt(pillarNW2).getType().equals( Material.AMETHYST_BLOCK)
      && this.world.getBlockAt(pillarSE0).getType().equals( Material.QUARTZ_PILLAR)
      && this.world.getBlockAt(pillarSE1).getType().equals( Material.QUARTZ_PILLAR)
      && this.world.getBlockAt(pillarSE2).getType().equals( Material.AMETHYST_BLOCK)
      && this.world.getBlockAt(pillarSW0).getType().equals( Material.QUARTZ_PILLAR)
      && this.world.getBlockAt(pillarSW1).getType().equals( Material.QUARTZ_PILLAR)
      && this.world.getBlockAt(pillarSW2).getType().equals( Material.AMETHYST_BLOCK)
      && this.world.getBlockAt(altarPos).getType().equals(Material.AIR)
    )) return false;
    this.world.strikeLightningEffect(pillarSE2);
    this.world.getBlockAt(pillarSE2).setType(Material.BUDDING_AMETHYST, true);
    this.world.strikeLightningEffect(pillarSW2);
    this.world.getBlockAt(pillarSW2).setType(Material.BUDDING_AMETHYST, true);
    this.world.strikeLightningEffect(pillarNE2);
    this.world.getBlockAt(pillarNE2).setType(Material.BUDDING_AMETHYST, true);
    this.world.strikeLightningEffect(pillarNW2);
    this.world.getBlockAt(pillarNW2).setType(Material.BUDDING_AMETHYST, true);
    Block customBlock = this.world.getBlockAt(altarPos);
    customBlock.setType(Material.TRIPWIRE, true);
    Tripwire tripwire = (Tripwire) customBlock.getBlockData();
    tripwire.setDisarmed(true);
    tripwire.setAttached(false);
    tripwire.setFace(BlockFace.NORTH, true);
    tripwire.setFace(BlockFace.EAST, true);
    tripwire.setFace(BlockFace.SOUTH, true);
    tripwire.setFace(BlockFace.WEST, true);
    tripwire.setPowered(true);
    this.world.getBlockAt(altarPos).setBlockData(tripwire);
    //MetadataValue
    //this.world.getBlockAt(altarPos).setMetadata("west", );
    Bukkit.broadcastMessage("> "+ tripwire);
    //this.world.getBlockAt(altarPos).setBlockData();
    return true;
  }
}
