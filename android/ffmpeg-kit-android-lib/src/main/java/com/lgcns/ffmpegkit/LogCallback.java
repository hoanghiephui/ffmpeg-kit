package com.lgcns.ffmpegkit;

/**
 * <p>Callback function that receives logs generated for <code>FFmpegKit</code> sessions.
 */
@FunctionalInterface
public interface LogCallback {

    /**
     * <p>Called when a log entry is received.
     *
     * @param log log entry
     */
    void apply(final Log log);

}
