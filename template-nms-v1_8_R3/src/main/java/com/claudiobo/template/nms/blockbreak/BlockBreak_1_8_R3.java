package com.claudiobo.template.nms.blockbreak;

import java.util.Random;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.PacketPlayOutBlockBreakAnimation;

public class BlockBreak_1_8_R3 implements BlockBreak {

    public void sendBlockBreak(Location loc) {
        PacketPlayOutBlockBreakAnimation packet = new PacketPlayOutBlockBreakAnimation(new Random().nextInt(), new BlockPosition(loc.getBlock().getX(), loc.getBlock().getY(), loc.getBlock().getZ()), new Random().nextInt(9));

        for (Entity et : loc.getWorld().getNearbyEntities(loc, 24d, 24d, 24d)) {
            if (et instanceof Player) {
                Player player = (Player) et;
                int dimension = ((CraftWorld) player.getWorld()).getHandle().dimension;
                ((CraftServer) player.getServer()).getHandle().sendPacketNearby(null, loc.getBlock().getX(), loc.getBlock().getY(), loc.getBlock().getZ(), 24, dimension, packet);
                player.playEffect(loc, Effect.STEP_SOUND, loc.getBlock().getTypeId());
            }
        }

    }

}