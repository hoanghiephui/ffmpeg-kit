package com.lgcns.ffmpegkit

import android.util.Log

/**
 *
 * Executes a MediaInformation session asynchronously.
 */
class AsyncGetMediaInformationTask @JvmOverloads constructor(
    private val mediaInformationSession: MediaInformationSession,
    private val waitTimeout: Int = AbstractSession.DEFAULT_TIMEOUT_FOR_ASYNCHRONOUS_MESSAGES_IN_TRANSMIT
) : Runnable {
    private val completeCallback: MediaInformationSessionCompleteCallback? = mediaInformationSession.completeCallback

    override fun run() {
        FFmpegKitConfig.getMediaInformationExecute(mediaInformationSession, waitTimeout)
        if (completeCallback != null) {
            try {
                // NOTIFY SESSION CALLBACK DEFINED
                completeCallback.apply(mediaInformationSession)
            } catch (e: Exception) {
                Log.e(
                    FFmpegKitConfig.TAG,
                    String.format("Exception thrown inside session complete callback.%s", e.message)
                )
            }
        }
        val globalMediaInformationSessionCompleteCallback =
            FFmpegKitConfig.getMediaInformationSessionCompleteCallback()
        if (globalMediaInformationSessionCompleteCallback != null) {
            try {
                // NOTIFY GLOBAL CALLBACK DEFINEDs
                globalMediaInformationSessionCompleteCallback.apply(mediaInformationSession)
            } catch (e: Exception) {
                Log.e(
                    FFmpegKitConfig.TAG,
                    String.format("Exception thrown inside global complete callback.%s", e.message)
                )
            }
        }
    }
}