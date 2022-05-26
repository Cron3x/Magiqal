package xyz.eburg.cx.magical.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import xyz.eburg.cx.magical.Magical;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class TeleporterUtils {
  public static void add(String name, Color color, Location location) throws IOException {
    Gson gson = new Gson();

    String filePath = Magical.getInstance().getDataFolder().getPath()+ "teleporters.json";
    Bukkit.broadcastMessage(filePath);
    // create File
    List<Teleporter> teleporterList = gson.fromJson(filePath,""), new TypeToken<List<Teleporter>>() {}.getType());

    Teleporter teleporter = new Teleporter(teleporterList.size(), name, color, location);
    teleporterList.add(teleporter);
    String json = gson.toJson(teleporterList); // Remember pretty printing? This is needed here.
    new File(filePath).delete(); // won't throw an exception, don't worry.
    Files.write(new File(filePath).toPath(), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
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
