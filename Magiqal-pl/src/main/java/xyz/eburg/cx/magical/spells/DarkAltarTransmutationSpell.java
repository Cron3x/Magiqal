package xyz.eburg.cx.magical.spells;

import net.kyori.adventure.sound.Sound;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Candle;
import org.bukkit.block.data.type.Tripwire;

public class DarkAltarTransmutationSpell {
  private final World world;
  private final Location loc;
  public DarkAltarTransmutationSpell(World world, Location location) {
    this.loc = location;
    this.world = world;
  }

  public Boolean conjure() {

    Location altarPos = new Location(this.loc.getWorld() ,this.loc.getBlockX(), this.loc.getBlockY()+1, this.loc.getBlockZ()).toCenterLocation();

    //South East
    Location pillarS0 = new Location(this.loc.getWorld() ,this.loc.getBlockX(),    this.loc.getBlockY(),   this.loc.getBlockZ() + 3).toCenterLocation();
    Location pillarS1 = new Location(this.loc.getWorld() ,this.loc.getBlockX(), this.loc.getBlockY()+1, this.loc.getBlockZ() + 3).toCenterLocation();
    Location pillarS2 = new Location(this.loc.getWorld() ,this.loc.getBlockX(), this.loc.getBlockY()+2, this.loc.getBlockZ() + 3).toCenterLocation();
    Location pillarS3 = new Location(this.loc.getWorld() ,this.loc.getBlockX(), this.loc.getBlockY()+3, this.loc.getBlockZ() + 3).toCenterLocation();
    Location pillarS4 = new Location(this.loc.getWorld() ,this.loc.getBlockX(), this.loc.getBlockY()+4, this.loc.getBlockZ() + 3).toCenterLocation();

    //South West
    Location pillarN0 = new Location(this.loc.getWorld() ,this.loc.getBlockX(),    this.loc.getBlockY(),   this.loc.getBlockZ() - 3).toCenterLocation();
    Location pillarN1 = new Location(this.loc.getWorld() ,this.loc.getBlockX(), this.loc.getBlockY()+1, this.loc.getBlockZ() - 3).toCenterLocation();
    Location pillarN2 = new Location(this.loc.getWorld() ,this.loc.getBlockX(), this.loc.getBlockY()+2, this.loc.getBlockZ() - 3).toCenterLocation();
    Location pillarN3 = new Location(this.loc.getWorld() ,this.loc.getBlockX(), this.loc.getBlockY()+3, this.loc.getBlockZ() - 3).toCenterLocation();
    Location pillarN4 = new Location(this.loc.getWorld() ,this.loc.getBlockX(), this.loc.getBlockY()+4, this.loc.getBlockZ() - 3).toCenterLocation();

    //North East
    Location pillarE0 = new Location(this.loc.getWorld() ,this.loc.getBlockX() + 3,    this.loc.getBlockY(),   this.loc.getBlockZ()).toCenterLocation();
    Location pillarE1 = new Location(this.loc.getWorld() ,this.loc.getBlockX() + 3, this.loc.getBlockY()+1, this.loc.getBlockZ()).toCenterLocation();
    Location pillarE2 = new Location(this.loc.getWorld() ,this.loc.getBlockX() + 3, this.loc.getBlockY()+2, this.loc.getBlockZ()).toCenterLocation();
    Location pillarE3 = new Location(this.loc.getWorld() ,this.loc.getBlockX() + 3, this.loc.getBlockY()+3, this.loc.getBlockZ()).toCenterLocation();
    Location pillarE4 = new Location(this.loc.getWorld() ,this.loc.getBlockX() + 3, this.loc.getBlockY()+4, this.loc.getBlockZ()).toCenterLocation();

    //North West
    Location pillarW0 = new Location(this.loc.getWorld() ,this.loc.getBlockX() - 3,    this.loc.getBlockY(),   this.loc.getBlockZ()).toCenterLocation();
    Location pillarW1 = new Location(this.loc.getWorld() ,this.loc.getBlockX() - 3, this.loc.getBlockY()+1, this.loc.getBlockZ()).toCenterLocation();
    Location pillarW2 = new Location(this.loc.getWorld() ,this.loc.getBlockX() - 3, this.loc.getBlockY()+2, this.loc.getBlockZ()).toCenterLocation();
    Location pillarW3 = new Location(this.loc.getWorld() ,this.loc.getBlockX() - 3, this.loc.getBlockY()+3, this.loc.getBlockZ()).toCenterLocation();
    Location pillarW4 = new Location(this.loc.getWorld() ,this.loc.getBlockX() - 3, this.loc.getBlockY()+4, this.loc.getBlockZ()).toCenterLocation();

    if (!(this.world.getBlockAt(pillarE0).getType().equals( Material.POLISHED_BASALT)
      && this.world.getBlockAt(pillarE1).getType().equals( Material.DEEPSLATE_TILE_WALL)
      && this.world.getBlockAt(pillarE2).getType().equals( Material.NETHER_BRICK_FENCE)
      && this.world.getBlockAt(pillarE3).getType().equals( Material.LIGHTNING_ROD)
      && this.world.getBlockAt(pillarE4).getType().equals( Material.ORANGE_CANDLE)
      && this.world.getBlockAt(pillarW0).getType().equals( Material.POLISHED_BASALT)
      && this.world.getBlockAt(pillarW1).getType().equals( Material.DEEPSLATE_TILE_WALL)
      && this.world.getBlockAt(pillarW2).getType().equals( Material.NETHER_BRICK_FENCE)
      && this.world.getBlockAt(pillarW3).getType().equals( Material.LIGHTNING_ROD)
      && this.world.getBlockAt(pillarW4).getType().equals( Material.ORANGE_CANDLE)
      && this.world.getBlockAt(pillarS0).getType().equals( Material.POLISHED_BASALT)
      && this.world.getBlockAt(pillarS1).getType().equals( Material.DEEPSLATE_TILE_WALL)
      && this.world.getBlockAt(pillarS2).getType().equals( Material.NETHER_BRICK_FENCE)
      && this.world.getBlockAt(pillarS3).getType().equals( Material.LIGHTNING_ROD)
      && this.world.getBlockAt(pillarS4).getType().equals( Material.ORANGE_CANDLE)
      && this.world.getBlockAt(pillarN0).getType().equals( Material.POLISHED_BASALT)
      && this.world.getBlockAt(pillarN1).getType().equals( Material.DEEPSLATE_TILE_WALL)
      && this.world.getBlockAt(pillarN2).getType().equals( Material.NETHER_BRICK_FENCE)
      && this.world.getBlockAt(pillarN3).getType().equals( Material.LIGHTNING_ROD)
      && this.world.getBlockAt(pillarN4).getType().equals( Material.ORANGE_CANDLE)
      && (this.world.getBlockAt(altarPos).getType().equals(Material.AIR)
          || this.world.getBlockAt(altarPos).getType().equals(Material.CAVE_AIR)
          || this.world.getBlockAt(altarPos).getType().equals(Material.VOID_AIR)
      ))) return false;

    Candle candle = (Candle)pillarN4.getBlock().getBlockData();
    candle.setLit(true);
    pillarN4.getBlock().setBlockData(candle);

    pillarE4.getBlock().setBlockData(candle);

    double h = 0.0;
    altarPos.getWorld().spawnParticle(Particle.FLASH, new Location(altarPos.getWorld(), altarPos.getX(), altarPos.getY()+1, altarPos.getZ()), 1);
    for (int d = 0; d <= 90; d += 1) {
      Location particleLoc = new Location(altarPos.getWorld(), altarPos.getX(), altarPos.getY()-1+h, altarPos.getZ());
      particleLoc.setX(altarPos.getX() + Math.cos(d) * 2);
      particleLoc.setZ(altarPos.getZ() + Math.sin(d) * 2);
      altarPos.getWorld().spawnParticle(Particle.TOTEM, particleLoc, 1);
      altarPos.getWorld().spawnParticle(Particle.REDSTONE, particleLoc, 10, new Particle.DustOptions(Color.fromRGB(255,100,0), 5));
      if (d == 0) {
        this.world.strikeLightningEffect(pillarS4);
        pillarE4.getBlock().setBlockData(candle);
      }
      if (d == 11) {
        this.world.strikeLightningEffect(pillarN4);
        pillarS4.getBlock().setBlockData(candle);
      }
      if (d == 22) {
        this.world.strikeLightningEffect(pillarE4);
        pillarW4.getBlock().setBlockData(candle);
      }
      if (d == 33) {
        this.world.strikeLightningEffect(pillarW4);
        pillarS4.getBlock().setBlockData(candle);
      }

      //if (d == 90) ((Candle) pillarE4.getBlock().getBlockData()).setLit(true);
      h += 0.025;
    }
    Block customBlock = this.world.getBlockAt(altarPos);
    customBlock.setType(Material.TRIPWIRE, true);
    Tripwire tripwire = (Tripwire) customBlock.getBlockData();
    tripwire.setDisarmed(true);
    tripwire.setAttached(false);
    tripwire.setFace(BlockFace.NORTH, false);
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
