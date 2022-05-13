package xyz.eburg.cx.magical.mobs.boss.gian_phantom;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class GigantPhantom {
  private BossBar bossBar;
  public GigantPhantom(Player spawner) {

    bossBar = Bukkit.createBossBar("Gigant Phantom", BarColor.BLUE, BarStyle.SOLID);
    bossBar.hasFlag(BarFlag.DARKEN_SKY);

    bossBar.addPlayer(spawner);
  }
}
