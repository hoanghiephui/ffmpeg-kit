package com.lgcns.ffmpegkit

/**
 *
 * Callback function that receives statistics generated for `FFmpegKit` sessions.
 */
fun interface StatisticsCallback {
    /**
     *
     * Called when a statistics entry is received.
     *
     * @param statistics statistics entry
     */
    fun apply(statistics: Statistics)
}