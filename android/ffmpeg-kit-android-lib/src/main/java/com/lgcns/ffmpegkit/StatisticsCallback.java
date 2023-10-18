package com.lgcns.ffmpegkit;

/**
 * <p>Callback function that receives statistics generated for <code>FFmpegKit</code> sessions.
 */
@FunctionalInterface
public interface StatisticsCallback {

    /**
     * <p>Called when a statistics entry is received.
     *
     * @param statistics statistics entry
     */
    void apply(final Statistics statistics);

}
