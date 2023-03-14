package com.claudiobo.template.hooks;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import com.claudiobo.template.Main;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class PlaceholderHook extends PlaceholderExpansion {

    Main main;

    public PlaceholderHook(Main main) {
        this.main = main;
    }

    @Override
    public String getIdentifier() {
        return "template";
    }

    public String onPlaceholderRequest(Player p, String identifier) {
        return "test";
    }

    @Override
    public @NotNull String getAuthor() {
        return main.getDescription().getAuthors().get(0);
    }

    @Override
    public @NotNull String getVersion() {
        return main.getDescription().getVersion();
    }
}
