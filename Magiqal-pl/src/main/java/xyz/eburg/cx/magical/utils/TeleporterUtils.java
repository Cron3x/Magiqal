package xyz.eburg.cx.magical.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import xyz.eburg.cx.magical.Magical;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class TeleporterUtils {
  public static void add(String name, Color color, Location location) throws IOException {
    Gson gson = new Gson();

    File confFile = new File(Magical.getInstance().getDataFolder(), "teleporters.json");
    Bukkit.broadcastMessage(confFile.getPath());
    // create File
    List<Teleporter> teleporterList = gson.fromJson(new FileReader(confFile), new TypeToken<List<Teleporter>>() {}.getType());

    Teleporter teleporter = new Teleporter(teleporterList.size(), name, color, location);
    teleporterList.add(teleporter);
    String json = gson.toJson(teleporterList); // Remember pretty printing? This is needed here.
    new File(confFile, "teleporters.json").delete(); // won't throw an exception, don't worry.
    Files.write(confFile.toPath(), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
  }
}

class Teleporter {
  private int id;
  private String name;
  private Color color;
  private Location location;

  public Teleporter(int id, String name, Color color, Location location) {
    this.id = id;
    this.name = name;
    this.color = color;
    this.location = location;
  }
}
