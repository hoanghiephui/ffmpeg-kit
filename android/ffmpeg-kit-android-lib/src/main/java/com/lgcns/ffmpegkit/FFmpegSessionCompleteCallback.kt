package com.lgcns.ffmpegkit

/**
 *
 * Callback function that is invoked when an asynchronous `FFmpeg` session has ended.
 */
fun interface FFmpegSessionCompleteCallback {
    /**
     *
     * Called when an FFmpeg session has ended.
     *
     * @param session FFmpeg session
     */
    fun apply(session: FFmpegSession?)
}