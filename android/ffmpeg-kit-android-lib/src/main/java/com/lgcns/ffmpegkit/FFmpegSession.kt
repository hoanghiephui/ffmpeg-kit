package com.lgcns.ffmpegkit

import android.util.Log
import java.util.LinkedList

/**
 *
 * An FFmpeg session.
 */
class FFmpegSession private constructor(
    arguments: Array<String>,
    /**
     * Session specific complete callback.
     */
    @JvmField val completeCallback: FFmpegSessionCompleteCallback?,
    logCallback: LogCallback?,
    /**
     * Session specific statistics callback.
     */
    @JvmField val statisticsCallback: StatisticsCallback?,
    logRedirectionStrategy: LogRedirectionStrategy
) : AbstractSession(arguments, logCallback, logRedirectionStrategy), Session {
    /**
     * Returns the session specific statistics callback.
     *
     * @return session specific statistics callback
     */
    /**
     * Returns the session specific complete callback.
     *
     * @return session specific complete callback
     */

    /**
     * Statistics entries received for this session.
     */
    private val statistics: MutableList<Statistics>

    /**
     * Statistics entry lock.
     */
    private val statisticsLock: Any

    /**
     * Builds a new FFmpeg session.
     *
     * @param arguments              command arguments
     * @param completeCallback       session specific complete callback
     * @param logCallback            session specific log callback
     * @param statisticsCallback     session specific statistics callback
     * @param logRedirectionStrategy session specific log redirection strategy
     */
    init {
        statistics = LinkedList()
        statisticsLock = Any()
    }

    /**
     * Returns all statistics entries generated for this session. If there are asynchronous
     * messages that are not delivered yet, this method waits for them until the given timeout.
     *
     * @param waitTimeout wait timeout for asynchronous messages in milliseconds
     * @return list of statistics entries generated for this session
     */
    fun getAllStatistics(waitTimeout: Int): List<Statistics> {
        waitForAsynchronousMessagesInTransmit(waitTimeout)
        if (thereAreAsynchronousMessagesInTransmit()) {
            Log.i(
                FFmpegKitConfig.TAG,
                String.format(
                    "getAllStatistics was called to return all statistics but there are still statistics being transmitted for session id %d.",
                    sessionId
                )
            )
        }
        return getStatistics()
    }

    val allStatistics: List<Statistics>
        /**
         * Returns all statistics entries generated for this session. If there are asynchronous
         * messages that are not delivered yet, this method waits for them until
         * [.DEFAULT_TIMEOUT_FOR_ASYNCHRONOUS_MESSAGES_IN_TRANSMIT] expires.
         *
         * @return list of statistics entries generated for this session
         */
        get() = getAllStatistics(DEFAULT_TIMEOUT_FOR_ASYNCHRONOUS_MESSAGES_IN_TRANSMIT)

    /**
     * Returns all statistics entries delivered for this session. Note that if there are
     * asynchronous messages that are not delivered yet, this method will not wait for
     * them and will return immediately.
     *
     * @return list of statistics entries received for this session
     */
    fun getStatistics(): List<Statistics> {
        synchronized(statisticsLock) { return statistics }
    }

    val lastReceivedStatistics: Statistics?
        /**
         * Returns the last received statistics entry.
         *
         * @return the last received statistics entry or null if there are not any statistics entries
         * received
         */
        get() {
            synchronized(statisticsLock) {
                return if (statistics.size > 0) {
                    statistics[statistics.size - 1]
                } else {
                    null
                }
            }
        }

    /**
     * Adds a new statistics entry for this session. It is invoked internally by
     * `FFmpegKit` library methods. Must not be used by user applications.
     *
     * @param statistics statistics entry
     */
    fun addStatistics(statistics: Statistics) {
        synchronized(statisticsLock) { this.statistics.add(statistics) }
    }

    override fun isFFmpeg(): Boolean {
        return true
    }

    override fun isFFprobe(): Boolean {
        return false
    }

    override fun isMediaInformation(): Boolean {
        return false
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("FFmpegSession{")
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
         * Builds a new FFmpeg session.
         *
         * @param arguments command arguments
         * @return created session
         */
        @JvmStatic
        fun create(arguments: Array<String>): FFmpegSession {
            return FFmpegSession(
                arguments,
                null,
                null,
                null,
                FFmpegKitConfig.getLogRedirectionStrategy()
            )
        }

        /**
         * Builds a new FFmpeg session.
         *
         * @param arguments        command arguments
         * @param completeCallback session specific complete callback
         * @return created session
         */
        @JvmStatic
        fun create(
            arguments: Array<String>,
            completeCallback: FFmpegSessionCompleteCallback?
        ): FFmpegSession {
            return FFmpegSession(
                arguments,
                completeCallback,
                null,
                null,
                FFmpegKitConfig.getLogRedirectionStrategy()
            )
        }

        /**
         * Builds a new FFmpeg session.
         *
         * @param arguments          command arguments
         * @param completeCallback   session specific complete callback
         * @param logCallback        session specific log callback
         * @param statisticsCallback session specific statistics callback
         * @return created session
         */
        @JvmStatic
        fun create(
            arguments: Array<String>,
            completeCallback: FFmpegSessionCompleteCallback?,
            logCallback: LogCallback?,
            statisticsCallback: StatisticsCallback?
        ): FFmpegSession {
            return FFmpegSession(
                arguments,
                completeCallback,
                logCallback,
                statisticsCallback,
                FFmpegKitConfig.getLogRedirectionStrategy()
            )
        }

        /**
         * Builds a new FFmpeg session.
         *
         * @param arguments              command arguments
         * @param completeCallback       session specific complete callback
         * @param logCallback            session specific log callback
         * @param statisticsCallback     session specific statistics callback
         * @param logRedirectionStrategy session specific log redirection strategy
         * @return created session
         */
        fun create(
            arguments: Array<String>,
            completeCallback: FFmpegSessionCompleteCallback?,
            logCallback: LogCallback?,
            statisticsCallback: StatisticsCallback?,
            logRedirectionStrategy: LogRedirectionStrategy
        ): FFmpegSession {
            return FFmpegSession(
                arguments,
                completeCallback,
                logCallback,
                statisticsCallback,
                logRedirectionStrategy
            )
        }
    }
}