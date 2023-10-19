package com.lgcns.ffmpegkit

/**
 *
 * Lists signals handled by FFmpegKit library.
 */
enum class Signal(@JvmField val value: Int) {
    SIGINT(2), SIGQUIT(3), SIGPIPE(13), SIGTERM(15), SIGXCPU(24)

}