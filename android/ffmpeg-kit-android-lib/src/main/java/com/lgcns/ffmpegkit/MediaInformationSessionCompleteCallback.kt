package com.lgcns.ffmpegkit

/**
 *
 * Callback function that is invoked when an asynchronous `MediaInformation` session
 * has ended.
 */
fun interface MediaInformationSessionCompleteCallback {
    /**
     *
     * Called when a media information session has ended.
     *
     * @param session media information session
     */
    fun apply(session: MediaInformationSession?)
}