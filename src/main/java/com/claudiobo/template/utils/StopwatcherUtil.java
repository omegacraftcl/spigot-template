package com.claudiobo.template.utils;

public class StopwatcherUtil {

    private long start = 0L;

    public StopwatcherUtil() {
        reset();
    }

    public long getMilliseconds() {
        return System.currentTimeMillis();
    }

    public void reset() {
        this.start = getMilliseconds();
    }

    public String getElapsedTime() {
        long stop = getMilliseconds();
        long diff = stop - this.start;
        return diff + "ms";
    }

    public String resetAndGetElapsedTime() {
        String ms = getElapsedTime();
        reset();
        return ms;
    }

}
