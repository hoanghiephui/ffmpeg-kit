package com.lgcns.ffmpegkit

/**
 *
 * Callback function that is invoked when an asynchronous `FFprobe` session has ended.
 */
fun interface FFprobeSessionCompleteCallback {
    /**
     *
     * Called when an FFprobe session has ended.
     *
     * @param session FFprobe session
     */
    fun apply(session: FFprobeSession?)
}