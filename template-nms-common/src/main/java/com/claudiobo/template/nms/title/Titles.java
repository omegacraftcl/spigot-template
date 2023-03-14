package com.claudiobo.template.nms.title;

import org.bukkit.entity.Player;

public interface Titles {
    public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut);

    public void sendActionBar(Player p, String msg);

}