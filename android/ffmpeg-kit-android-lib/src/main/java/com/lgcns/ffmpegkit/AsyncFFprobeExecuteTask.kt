package com.lgcns.ffmpegkit

import android.util.Log

/**
 *
 * Executes an FFprobe session asynchronously.
 */
class AsyncFFprobeExecuteTask(private val ffprobeSession: FFprobeSession) : Runnable {
    private val completeCallback: FFprobeSessionCompleteCallback? = ffprobeSession.completeCallback

    override fun run() {
        FFmpegKitConfig.ffprobeExecute(ffprobeSession)
        if (completeCallback != null) {
            try {
                // NOTIFY SESSION CALLBACK DEFINED
                completeCallback.apply(ffprobeSession)
            } catch (e: Exception) {
                Log.e(
                    FFmpegKitConfig.TAG,
                    String.format("Exception thrown inside session complete callback.%s", e.message)
                )
            }
        }
        val globalFFprobeSessionCompleteCallback =
            FFmpegKitConfig.getFFprobeSessionCompleteCallback()
        if (globalFFprobeSessionCompleteCallback != null) {
            try {
                // NOTIFY GLOBAL CALLBACK DEFINED
                globalFFprobeSessionCompleteCallback.apply(ffprobeSession)
            } catch (e: Exception) {
                Log.e(
                    FFmpegKitConfig.TAG,
                    String.format("Exception thrown inside global complete callback.%s", e.message)
                )
            }
        }
    }
}