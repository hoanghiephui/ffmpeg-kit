package com.lgcns.ffmpegkit

/**
 *
 * Statistics entry for an FFmpeg execute session.
 */
class Statistics(
    var sessionId: Long,
    var videoFrameNumber: Int,
    var videoFps: Float,
    var videoQuality: Float,
    var size: Long,
    var time: Double,
    var bitrate: Double,
    var speed: Double
) {

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("Statistics{")
        stringBuilder.append("sessionId=")
        stringBuilder.append(sessionId)
        stringBuilder.append(", videoFrameNumber=")
        stringBuilder.append(videoFrameNumber)
        stringBuilder.append(", videoFps=")
        stringBuilder.append(videoFps)
        stringBuilder.append(", videoQuality=")
        stringBuilder.append(videoQuality)
        stringBuilder.append(", size=")
        stringBuilder.append(size)
        stringBuilder.append(", time=")
        stringBuilder.append(time)
        stringBuilder.append(", bitrate=")
        stringBuilder.append(bitrate)
        stringBuilder.append(", speed=")
        stringBuilder.append(speed)
        stringBuilder.append('}')
        return stringBuilder.toString()
    }
}