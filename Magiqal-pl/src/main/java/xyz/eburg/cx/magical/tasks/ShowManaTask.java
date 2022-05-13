package xyz.eburg.cx.magical.tasks;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import xyz.eburg.cx.magical.utils.ManaUtils;

import static xyz.eburg.cx.magical.utils.BarCharacter.*;

public class ShowManaTask extends BukkitRunnable {
  @Override
  public void run() {
    for (Player player : Bukkit.getOnlinePlayers()){
      if (player.getScoreboardTags().contains("skip")) continue;
      //Bukkit.broadcastMessage("Task: " + this.getTaskId());
      int mana = ManaUtils.getManaAmount(player);
      @NotNull TextComponent message = Component.text(manaBar(mana));
      player.sendActionBar(message);
    }
  }
  private String manaBar(int mana){

    return switch (mana) {
      case 0  -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty;
      case 1  -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + quarter;
      case 2  -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + half;
      case 3  -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + quarterx3;
      case 4  -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + full;
      case 5  -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + quarter + full;
      case 6  -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + half + full;
      case 7  -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + quarterx3 + full;
      case 8  -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + full + full;
      case 9  -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + quarter + full + full;
      case 10 -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + half + full + full;
      case 11 -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + quarterx3 + full + full;
      case 12 -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + empty + full + full + full;
      case 13 -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + quarter + full + full + full;
      case 14 -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + half + full + full + full;
      case 15 -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + quarterx3 + full + full + full;
      case 16 -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + empty + full + full + full + full;
      case 17 -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + quarter + full + full + full + full;
      case 18 -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + half + full + full + full + full;
      case 19 -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + quarterx3 + full + full + full + full;
      case 20 -> prefix + empty + empty + empty + empty + empty + empty + empty + empty + full + full + full + full + full;
      case 21 -> prefix + empty + empty + empty + empty + empty + empty + empty + quarter + full + full + full + full + full;
      case 22 -> prefix + empty + empty + empty + empty + empty + empty + empty + half + full + full + full + full + full;
      case 23 -> prefix + empty + empty + empty + empty + empty + empty + empty + quarterx3 + full + full + full + full + full;
      case 24 -> prefix + empty + empty + empty + empty + empty + empty + empty + full + full + full + full + full + full;
      case 25 -> prefix + empty + empty + empty + empty + empty + empty + quarter + full + full + full + full + full + full;
      case 26 -> prefix + empty + empty + empty + empty + empty + empty + half + full + full + full + full + full + full;
      case 27 -> prefix + empty + empty + empty + empty + empty + empty + quarterx3 + full + full + full + full + full + full;
      case 28 -> prefix + empty + empty + empty + empty + empty + empty + full + full + full + full + full + full + full;
      case 29 -> prefix + empty + empty + empty + empty + empty + quarter + full + full + full + full + full + full + full;
      case 30 -> prefix + empty + empty + empty + empty + empty + half + full + full + full + full + full + full + full;
      case 31 -> prefix + empty + empty + empty + empty + empty + quarterx3 + full + full + full + full + full + full + full;
      case 32 -> prefix + empty + empty + empty + empty + empty + full + full + full + full + full + full + full + full;
      case 33 -> prefix + empty + empty + empty + empty + quarter + full + full + full + full + full + full + full + full;
      case 34 -> prefix + empty + empty + empty + empty + half + full + full + full + full + full + full + full + full;
      case 35 -> prefix + empty + empty + empty + empty + quarterx3 + full + full + full + full + full + full + full + full;
      case 36 -> prefix + empty + empty + empty + empty + full + full + full + full + full + full + full + full + full;
      case 37 -> prefix + empty + empty + empty + quarter + full + full + full + full + full + full + full + full + full;
      case 38 -> prefix + empty + empty + empty + half + full + full + full + full + full + full + full + full + full;
      case 39 -> prefix + empty + empty + empty + quarterx3 + full + full + full + full + full + full + full + full + full;
      case 40 -> prefix + empty + empty + empty + full + full + full + full + full + full + full + full + full + full;
      case 41 -> prefix + empty + empty + quarter + full + full + full + full + full + full + full + full + full + full;
      case 42 -> prefix + empty + empty + half + full + full + full + full + full + full + full + full + full + full;
      case 43 -> prefix + empty + empty + quarterx3 + full + full + full + full + full + full + full + full + full + full;
      case 44 -> prefix + empty + empty + full + full + full + full + full + full + full + full + full + full + full;
      case 45 -> prefix + empty + quarter + full + full + full + full + full + full + full + full + full + full + full;
      case 46 -> prefix + empty + half + full + full + full + full + full + full + full + full + full + full + full;
      case 47 -> prefix + empty + quarterx3 + full + full + full + full + full + full + full + full + full + full + full;
      case 48 -> prefix + empty + full + full + full + full + full + full + full + full + full + full + full + full;
      case 49 -> prefix + quarter + full + full + full + full + full + full + full + full + full + full + full + full;
      case 50 -> prefix + half + full + full + full + full + full + full + full + full + full + full + full + full;
      case 51 -> prefix + quarterx3 + full + full + full + full + full + full + full + full + full + full + full + full;
      case 52 -> prefix + full + full + full + full + full + full + full + full + full + full + full + full + full;
      default -> prefix + full + mana;
    };
  }
}
