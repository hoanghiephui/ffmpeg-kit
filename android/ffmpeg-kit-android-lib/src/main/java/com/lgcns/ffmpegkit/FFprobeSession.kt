package com.lgcns.ffmpegkit

/**
 *
 * An FFprobe session.
 */
class FFprobeSession
/**
 * Builds a new FFprobe session.
 *
 * @param arguments              command arguments
 * @param completeCallback       session specific complete callback
 * @param logCallback            session specific log callback
 * @param logRedirectionStrategy session specific log redirection strategy
 */ private constructor(
    arguments: Array<String>,
    /**
     * Session specific complete callback.
     */
    @JvmField val completeCallback: FFprobeSessionCompleteCallback?,
    logCallback: LogCallback?,
    logRedirectionStrategy: LogRedirectionStrategy
) : AbstractSession(arguments, logCallback, logRedirectionStrategy), Session {
    /**
     * Returns the session specific complete callback.
     *
     * @return session specific complete callback
     */

    override fun isFFmpeg(): Boolean {
        return false
    }

    override fun isFFprobe(): Boolean {
        return true
    }

    override fun isMediaInformation(): Boolean {
        return false
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("FFprobeSession{")
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
         * Builds a new FFprobe session.
         *
         * @param arguments command arguments
         * @return created session
         */
        @JvmStatic
        fun create(arguments: Array<String>): FFprobeSession {
            return FFprobeSession(
                arguments,
                null,
                null,
                FFmpegKitConfig.getLogRedirectionStrategy()
            )
        }

        /**
         * Builds a new FFprobe session.
         *
         * @param arguments        command arguments
         * @param completeCallback session specific complete callback
         * @return created session
         */
        @JvmStatic
        fun create(
            arguments: Array<String>,
            completeCallback: FFprobeSessionCompleteCallback?
        ): FFprobeSession {
            return FFprobeSession(
                arguments,
                completeCallback,
                null,
                FFmpegKitConfig.getLogRedirectionStrategy()
            )
        }

        /**
         * Builds a new FFprobe session.
         *
         * @param arguments        command arguments
         * @param completeCallback session specific complete callback
         * @param logCallback      session specific log callback
         * @return created session
         */
        @JvmStatic
        fun create(
            arguments: Array<String>,
            completeCallback: FFprobeSessionCompleteCallback?,
            logCallback: LogCallback?
        ): FFprobeSession {
            return FFprobeSession(
                arguments,
                completeCallback,
                logCallback,
                FFmpegKitConfig.getLogRedirectionStrategy()
            )
        }

        /**
         * Builds a new FFprobe session.
         *
         * @param arguments              command arguments
         * @param completeCallback       session specific complete callback
         * @param logCallback            session specific log callback
         * @param logRedirectionStrategy session specific log redirection strategy
         * @return created session
         */
        fun create(
            arguments: Array<String>,
            completeCallback: FFprobeSessionCompleteCallback?,
            logCallback: LogCallback?,
            logRedirectionStrategy: LogRedirectionStrategy
        ): FFprobeSession {
            return FFprobeSession(arguments, completeCallback, logCallback, logRedirectionStrategy)
        }
    }
}