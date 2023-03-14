package com.claudiobo.template.utils;

import org.bukkit.plugin.Plugin;

public class RunSync extends RunnableAbstract {
    public RunSync(Plugin main, Runnable runnable) {
        super(runnable);
        this.runTask(main);
    }
}