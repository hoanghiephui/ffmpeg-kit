package com.lgcns.ffmpegkit

/**
 *
 * Enumeration type for log levels.
 */
enum class Level
/**
 * Creates a new enum.
 *
 * @param value level value
 */(
    /**
     * Returns level value.
     *
     * @return level value
     */
    @JvmField val value: Int
) {
    /**
     * This log level is defined by FFmpegKit. It is used to specify logs printed to stderr by
     * FFmpeg. Logs that has this level are not filtered and always redirected.
     */
    AV_LOG_STDERR(-16),

    /**
     * Print no output.
     */
    AV_LOG_QUIET(-8),

    /**
     * Something went really wrong and we will crash now.
     */
    AV_LOG_PANIC(0),

    /**
     * Something went wrong and recovery is not possible.
     * For example, no header was found for a format which depends
     * on headers or an illegal combination of parameters is used.
     */
    AV_LOG_FATAL(8),

    /**
     * Something went wrong and cannot losslessly be recovered.
     * However, not all future data is affected.
     */
    AV_LOG_ERROR(16),

    /**
     * Something somehow does not look correct. This may or may not
     * lead to problems. An example would be the use of '-vstrict -2'.
     */
    AV_LOG_WARNING(24),

    /**
     * Standard information.
     */
    AV_LOG_INFO(32),

    /**
     * Detailed information.
     */
    AV_LOG_VERBOSE(40),

    /**
     * Stuff which is only useful for libav* developers.
     */
    AV_LOG_DEBUG(48),

    /**
     * Extremely verbose debugging, useful for libav* development.
     */
    AV_LOG_TRACE(56);

    companion object {
        /**
         *
         * Returns the enumeration defined by provided value.
         *
         * @param value level value
         * @return enumeration defined by value
         */
        @JvmStatic
        fun from(value: Int): Level {
            return if (value == AV_LOG_STDERR.value) {
                AV_LOG_STDERR
            } else if (value == AV_LOG_QUIET.value) {
                AV_LOG_QUIET
            } else if (value == AV_LOG_PANIC.value) {
                AV_LOG_PANIC
            } else if (value == AV_LOG_FATAL.value) {
                AV_LOG_FATAL
            } else if (value == AV_LOG_ERROR.value) {
                AV_LOG_ERROR
            } else if (value == AV_LOG_WARNING.value) {
                AV_LOG_WARNING
            } else if (value == AV_LOG_INFO.value) {
                AV_LOG_INFO
            } else if (value == AV_LOG_VERBOSE.value) {
                AV_LOG_VERBOSE
            } else if (value == AV_LOG_DEBUG.value) {
                AV_LOG_DEBUG
            } else {
                AV_LOG_TRACE
            }
        }
    }
}