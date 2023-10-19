package com.lgcns.ffmpegkit

import android.util.Log

/**
 *
 * Executes an FFmpeg session asynchronously.
 */
class AsyncFFmpegExecuteTask(private val ffmpegSession: FFmpegSession) : Runnable {
    private val completeCallback: FFmpegSessionCompleteCallback? = ffmpegSession.completeCallback

    override fun run() {
        FFmpegKitConfig.ffmpegExecute(ffmpegSession)
        if (completeCallback != null) {
            try {
                // NOTIFY SESSION CALLBACK DEFINED
                completeCallback.apply(ffmpegSession)
            } catch (e: Exception) {
                Log.e(
                    FFmpegKitConfig.TAG,
                    String.format("Exception thrown inside session complete callback.%s", e.message)
                )
            }
        }
        val globalFFmpegSessionCompleteCallback = FFmpegKitConfig.getFFmpegSessionCompleteCallback()
        if (globalFFmpegSessionCompleteCallback != null) {
            try {
                // NOTIFY GLOBAL CALLBACK DEFINED
                globalFFmpegSessionCompleteCallback.apply(ffmpegSession)
            } catch (e: Exception) {
                Log.e(
                    FFmpegKitConfig.TAG,
                    String.format("Exception thrown inside global complete callback.%s", e.message)
                )
            }
        }
    }
}