package xyz.eburg.cx.magical;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.eburg.cx.magical.listeners.ItemListener;
import xyz.eburg.cx.magical.recipes.crafting_table.GuideBookRecipe;
import xyz.eburg.cx.magical.recipes.crafting_table.TransmutationStaffRecipe;
import xyz.eburg.cx.magical.tasks.ShowManaTask;

public final class Magical extends JavaPlugin implements Listener {
  FileConfiguration config = getConfig();
  private static Magical instance;
  public static Magical getInstance() {
    return instance;
  }
  @Override
  public void onEnable() {
    instance = this;

    config.addDefault("enable_magic_recipes", true);
    config.addDefault("enable_magic_destruction", false);
    config.addDefault("max_mana_amount", 52);
    config.addDefault("max_mage_level", -1);
    config.addDefault("dynamic_mana_bar", false);
    config.options().copyDefaults(true);
    saveConfig();

    System.out.println("(DEBUG) MAGIQAL: Enabled!");

    this.getServer().getPluginManager().registerEvents(new ItemListener(), this);

    new ShowManaTask().runTaskTimer(this, 0, 20L);

    this.getServer().getPluginManager().registerEvents(this, this);
    //this.getCommand("wand").setExecutor(new DebugCommands());

    /* Recipes */
    new GuideBookRecipe();
    new TransmutationStaffRecipe();
  }
}
