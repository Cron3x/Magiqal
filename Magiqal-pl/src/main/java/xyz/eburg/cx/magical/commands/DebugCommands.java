package xyz.eburg.cx.magical.commands;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import xyz.eburg.cx.magical.utils.ItemUtils;

public class DebugCommands implements CommandExecutor {
  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (!(sender instanceof Player)) return false;
    Player player = (Player) sender;
    ItemStack item = new ItemStack(Material.CLOCK);
    ItemUtils.setMagiqal(item, true);
    player.getInventory().addItem(item);

    player.updateInventory();
    return true;
  }
}
