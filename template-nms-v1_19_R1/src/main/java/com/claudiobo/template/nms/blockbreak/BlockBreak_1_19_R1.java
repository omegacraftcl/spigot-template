package com.claudiobo.banky.nms.blockbreak;

import java.util.Random;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_19_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import net.minecraft.core.BlockPosition;
import net.minecraft.network.protocol.game.PacketPlayOutBlockBreakAnimation;

public class BlockBreak_1_19_R1 implements BlockBreak {

    public void sendBlockBreak(Location loc) {
        PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(new Random().nextInt(), new BlockPosition(loc.getBlock().getX(), loc.getBlock().getY(), loc.getBlock().getZ()), new Random().nextInt(9));
        for (Entity et : loc.getWorld().getNearbyEntities(loc, 24d, 24d, 24d)) {
            if (et instanceof Player) {
                Player player = (Player) et;
                ((CraftPlayer) player).getHandle().b.a(packet);
                player.playEffect(loc, Effect.STEP_SOUND, loc.getBlock().getType());
            }
        }
    }

}