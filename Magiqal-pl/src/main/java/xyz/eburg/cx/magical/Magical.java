package xyz.eburg.cx.magical;


import net.minecraft.ChatFormatting;
import net.minecraft.Util;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_18_R2.CraftServer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.eburg.cx.magical.listeners.InventoryListener;
import xyz.eburg.cx.magical.listeners.ItemListener;
import xyz.eburg.cx.magical.recipes.crafting_table.FlightRingRecipe;
import xyz.eburg.cx.magical.recipes.crafting_table.GuideBookRecipe;
import xyz.eburg.cx.magical.recipes.crafting_table.TransmutationStaffRecipe;
import xyz.eburg.cx.magical.tasks.ShowManaTask;

import java.util.Collection;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import static net.minecraft.commands.arguments.EntityArgument.players;

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
    this.getServer().getPluginManager().registerEvents(new InventoryListener(), this);

    new ShowManaTask().runTaskTimer(this, 0, 20L);

    this.getServer().getPluginManager().registerEvents(this, this);

    /* Recipes */
    new GuideBookRecipe();
    new TransmutationStaffRecipe();
    new FlightRingRecipe();

    /* Debug Commands */
    ((CraftServer) this.getServer()).getServer().vanillaCommandDispatcher.getDispatcher().register(
      literal("paperweight")
        .requires(stack -> stack.hasPermission(stack.getServer().getOperatorUserPermissionLevel()))
        .then(argument("players", players())
          .executes(ctx -> {
            final Collection<ServerPlayer> players = EntityArgument.getPlayers(ctx, "players");
            for (final ServerPlayer player : players) {
              player.sendMessage(
                new TextComponent("reloading Recipes")
                  .withStyle(ChatFormatting.ITALIC, ChatFormatting.GREEN)
                  .withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/paperweight @a"))),
                Util.NIL_UUID
              );
              //TODO: Dos not work, fix
              new GuideBookRecipe();
              new TransmutationStaffRecipe();
              new FlightRingRecipe();
            }
            return players.size();
          }))
    );
  }
}
