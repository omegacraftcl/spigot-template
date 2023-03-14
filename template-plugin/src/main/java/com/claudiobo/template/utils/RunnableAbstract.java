package com.claudiobo.template.utils;

import org.bukkit.scheduler.BukkitRunnable;

abstract class RunnableAbstract extends BukkitRunnable {
    private final Runnable runnable;

    RunnableAbstract(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        runnable.run();
    }
}