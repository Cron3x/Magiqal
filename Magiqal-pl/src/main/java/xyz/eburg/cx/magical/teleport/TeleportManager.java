package xyz.eburg.cx.magical.teleport;

import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.item.ItemStack;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;
import xyz.eburg.cx.magical.Magical;
import xyz.eburg.cx.magical.utils.ItemUtils;

public class TeleportManager {


  public static void teleport(Item item){

    World world = item.getWorld();
    Location location = item.getLocation().add(0,1,0);

    switch (ItemUtils.getDimension(item.getItemStack())) {
      case NETHER -> {}
      case WORLD -> {}
      case END -> {}
    }

    new BukkitRunnable() {
      @Override
      public void run() {
        Location loc = location;
        item.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, loc, 1);
        item.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, loc.add(0,1,0), 1);
        item.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, loc, 1);
        this.cancel();
      }
    }.runTaskTimer(Magical.getInstance(), 0L, 10L);
  }
}
