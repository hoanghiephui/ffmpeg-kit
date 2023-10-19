package com.lgcns.ffmpegkit

/**
 *
 * Callback function that receives logs generated for `FFmpegKit` sessions.
 */
fun interface LogCallback {
    /**
     *
     * Called when a log entry is received.
     *
     * @param log log entry
     */
    fun apply(log: Log?)
}