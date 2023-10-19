package com.lgcns.ffmpegkit

import org.json.JSONObject

/**
 * Stream information class.
 */
class StreamInformation
/**
 * Returns all stream properties defined.
 *
 * @return all stream properties as a JSONObject or null if no properties are defined
 */(
    /**
     * Stores all properties.
     */
    private val allProperties: JSONObject
) {
    val index: Long?
        /**
         * Returns stream index.
         *
         * @return stream index, starting from zero
         */
        get() = getNumberProperty(KEY_INDEX)
    val type: String?
        /**
         * Returns stream type.
         *
         * @return stream type; audio or video
         */
        get() = getStringProperty(KEY_TYPE)
    val codec: String?
        /**
         * Returns stream codec.
         *
         * @return stream codec
         */
        get() = getStringProperty(KEY_CODEC)
    val codecLong: String?
        /**
         * Returns stream codec in long format.
         *
         * @return stream codec with additional profile and mode information
         */
        get() = getStringProperty(KEY_CODEC_LONG)
    val format: String?
        /**
         * Returns stream format.
         *
         * @return stream format
         */
        get() = getStringProperty(KEY_FORMAT)
    val width: Long?
        /**
         * Returns width.
         *
         * @return width in pixels
         */
        get() = getNumberProperty(KEY_WIDTH)
    val height: Long?
        /**
         * Returns height.
         *
         * @return height in pixels
         */
        get() = getNumberProperty(KEY_HEIGHT)
    val bitrate: String?
        /**
         * Returns bitrate.
         *
         * @return bitrate in kb/s
         */
        get() = getStringProperty(KEY_BIT_RATE)
    val sampleRate: String?
        /**
         * Returns sample rate.
         *
         * @return sample rate in hz
         */
        get() = getStringProperty(KEY_SAMPLE_RATE)
    val sampleFormat: String?
        /**
         * Returns sample format.
         *
         * @return sample format
         */
        get() = getStringProperty(KEY_SAMPLE_FORMAT)
    val channelLayout: String?
        /**
         * Returns channel layout.
         *
         * @return channel layout
         */
        get() = getStringProperty(KEY_CHANNEL_LAYOUT)
    val sampleAspectRatio: String?
        /**
         * Returns sample aspect ratio.
         *
         * @return sample aspect ratio
         */
        get() = getStringProperty(KEY_SAMPLE_ASPECT_RATIO)
    val displayAspectRatio: String?
        /**
         * Returns display aspect ratio.
         *
         * @return display aspect ratio
         */
        get() = getStringProperty(KEY_DISPLAY_ASPECT_RATIO)
    val averageFrameRate: String?
        /**
         * Returns display aspect ratio.
         *
         * @return average frame rate in fps
         */
        get() = getStringProperty(KEY_AVERAGE_FRAME_RATE)
    val realFrameRate: String?
        /**
         * Returns real frame rate.
         *
         * @return real frame rate in tbr
         */
        get() = getStringProperty(KEY_REAL_FRAME_RATE)
    val timeBase: String?
        /**
         * Returns time base.
         *
         * @return time base in tbn
         */
        get() = getStringProperty(KEY_TIME_BASE)
    val codecTimeBase: String?
        /**
         * Returns codec time base.
         *
         * @return codec time base in tbc
         */
        get() = getStringProperty(KEY_CODEC_TIME_BASE)
    val tags: JSONObject?
        /**
         * Returns all tags.
         *
         * @return tags object
         */
        get() = getProperty(KEY_TAGS)

    /**
     * Returns the stream property associated with the key.
     *
     * @param key property key
     * @return stream property as string or null if the key is not found
     */
    fun getStringProperty(key: String): String? {
        val allProperties = allProperties
        return if (allProperties.has(key)) {
            allProperties.optString(key)
        } else {
            null
        }
    }

    /**
     * Returns the stream property associated with the key.
     *
     * @param key property key
     * @return stream property as Long or null if the key is not found
     */
    fun getNumberProperty(key: String): Long? {
        val allProperties = allProperties
        return if (allProperties.has(key)) {
            allProperties.optLong(key)
        } else {
            null
        }
    }

    /**
     * Returns the stream property associated with the key.
     *
     * @param key property key
     * @return stream property as a JSONObject or null if the key is not found
     */
    fun getProperty(key: String): JSONObject? {
        val allProperties = allProperties
        return allProperties.optJSONObject(key)
    }

    companion object {
        /* COMMON KEYS */
        const val KEY_INDEX = "index"
        const val KEY_TYPE = "codec_type"
        const val KEY_CODEC = "codec_name"
        const val KEY_CODEC_LONG = "codec_long_name"
        const val KEY_FORMAT = "pix_fmt"
        const val KEY_WIDTH = "width"
        const val KEY_HEIGHT = "height"
        const val KEY_BIT_RATE = "bit_rate"
        const val KEY_SAMPLE_RATE = "sample_rate"
        const val KEY_SAMPLE_FORMAT = "sample_fmt"
        const val KEY_CHANNEL_LAYOUT = "channel_layout"
        const val KEY_SAMPLE_ASPECT_RATIO = "sample_aspect_ratio"
        const val KEY_DISPLAY_ASPECT_RATIO = "display_aspect_ratio"
        const val KEY_AVERAGE_FRAME_RATE = "avg_frame_rate"
        const val KEY_REAL_FRAME_RATE = "r_frame_rate"
        const val KEY_TIME_BASE = "time_base"
        const val KEY_CODEC_TIME_BASE = "codec_time_base"
        const val KEY_TAGS = "tags"
    }
}