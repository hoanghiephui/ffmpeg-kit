package com.lgcns.ffmpegkit

/**
 *
 * A custom FFprobe session, which produces a `MediaInformation` object using the
 * FFprobe output.
 */
class MediaInformationSession
/**
 * Creates a new media information session.
 *
 * @param arguments        command arguments
 * @param completeCallback session specific complete callback
 * @param logCallback      session specific log callback
 */ private constructor(
    arguments: Array<String>,
    /**
     * Session specific complete callback.
     */
    @JvmField val completeCallback: MediaInformationSessionCompleteCallback?,
    logCallback: LogCallback?
) : AbstractSession(arguments, logCallback, LogRedirectionStrategy.NEVER_PRINT_LOGS), Session {
    /**
     * Returns the media information extracted in this session.
     *
     * @return media information extracted or null if the command failed or the output can not be
     * parsed
     */
    /**
     * Sets the media information extracted in this session.
     *
     * @param mediaInformation media information extracted
     */
    /**
     * Media information extracted in the session.
     */
    @JvmField
    var mediaInformation: MediaInformation? = null

    /**
     * Returns the session specific complete callback.
     *
     * @return session specific complete callback
     */

    override fun isFFmpeg(): Boolean {
        return false
    }

    override fun isFFprobe(): Boolean {
        return false
    }

    override fun isMediaInformation(): Boolean {
        return true
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("MediaInformationSession{")
        stringBuilder.append("sessionId=")
        stringBuilder.append(sessionId)
        stringBuilder.append(", createTime=")
        stringBuilder.append(createTime)
        stringBuilder.append(", startTime=")
        stringBuilder.append(startTime)
        stringBuilder.append(", endTime=")
        stringBuilder.append(endTime)
        stringBuilder.append(", arguments=")
        stringBuilder.append(FFmpegKitConfig.argumentsToString(arguments))
        stringBuilder.append(", logs=")
        stringBuilder.append(logsAsString)
        stringBuilder.append(", state=")
        stringBuilder.append(state)
        stringBuilder.append(", returnCode=")
        stringBuilder.append(returnCode)
        stringBuilder.append(", failStackTrace=")
        stringBuilder.append('\'')
        stringBuilder.append(failStackTrace)
        stringBuilder.append('\'')
        stringBuilder.append('}')
        return stringBuilder.toString()
    }

    companion object {
        /**
         * Creates a new media information session.
         *
         * @param arguments command arguments
         * @return created session
         */
        @JvmStatic
        fun create(arguments: Array<String>): MediaInformationSession {
            return MediaInformationSession(arguments, null, null)
        }

        /**
         * Creates a new media information session.
         *
         * @param arguments        command arguments
         * @param completeCallback session specific complete callback
         * @return created session
         */
        @JvmStatic
        fun create(
            arguments: Array<String>,
            completeCallback: MediaInformationSessionCompleteCallback?
        ): MediaInformationSession {
            return MediaInformationSession(arguments, completeCallback, null)
        }

        /**
         * Creates a new media information session.
         *
         * @param arguments        command arguments
         * @param completeCallback session specific complete callback
         * @param logCallback      session specific log callback
         * @return created session
         */
        @JvmStatic
        fun create(
            arguments: Array<String>,
            completeCallback: MediaInformationSessionCompleteCallback?,
            logCallback: LogCallback?
        ): MediaInformationSession {
            return MediaInformationSession(arguments, completeCallback, logCallback)
        }
    }
}