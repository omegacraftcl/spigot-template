package com.claudiobo.template.utils;

public class StopwatcherUtil {

    private long start = 0L;

    public StopwatcherUtil() {
        restart();
    }

    public long getMilliseconds() {
        return System.currentTimeMillis();
    }

    public void restart() {
        this.start = getMilliseconds();
    }

    public String stop() {
        long stop = getMilliseconds();
        long diff = stop - this.start;
        return diff + "ms";
    }

}
