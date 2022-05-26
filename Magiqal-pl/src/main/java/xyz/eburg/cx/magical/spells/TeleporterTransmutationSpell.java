package xyz.eburg.cx.magical.spells;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import xyz.eburg.cx.magical.Magical;
import xyz.eburg.cx.magical.utils.TeleporterUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

public class TeleporterTransmutationSpell {
  private final World world;
  private final Location loc;
  public TeleporterTransmutationSpell(World world, Location location) {
    this.loc = location;
    this.world = world;
  }

  public Boolean conjure() {

    Location teleporterPos = new Location(this.loc.getWorld() ,this.loc.getBlockX(), this.loc.getBlockY(), this.loc.getBlockZ()).toCenterLocation();

    teleporterPos.getBlock().setType(Material.JIGSAW, false);
    try {
      TeleporterUtils.add("", Color.AQUA, teleporterPos);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    return true;
  }
}
