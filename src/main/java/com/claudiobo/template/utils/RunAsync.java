package com.claudiobo.template.utils;

import org.bukkit.plugin.Plugin;

public class RunAsync extends RunnableAbstract {
    public RunAsync(Plugin main, Runnable runnable) {
        super(runnable);
        this.runTaskAsynchronously(main);
    }
}