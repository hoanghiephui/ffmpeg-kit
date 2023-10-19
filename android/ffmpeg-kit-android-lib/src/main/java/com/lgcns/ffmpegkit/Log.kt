package com.lgcns.ffmpegkit

/**
 *
 * Log entry for an `FFmpegKit` session.
 */
class Log(val sessionId: Long, @JvmField val level: Level, @JvmField val message: String) {

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Log{")
        stringBuilder.append("sessionId=")
        stringBuilder.append(sessionId)
        stringBuilder.append(", level=")
        stringBuilder.append(level)
        stringBuilder.append(", message=")
        stringBuilder.append("\'")
        stringBuilder.append(message)
        stringBuilder.append('\'')
        stringBuilder.append('}')
        return stringBuilder.toString()
    }
}