package com.lgcns.ffmpegkit

import org.json.JSONObject

/**
 * Media information class.
 */
class MediaInformation
/**
 * Returns all chapters.
 *
 * @return list of chapters
 */(
    /**
     * Stores all properties.
     */
    val allProperties: JSONObject,
    /**
     * Stores streams.
     */
    @JvmField val streams: List<StreamInformation>,
    /**
     * Stores chapters.
     */
    @JvmField val chapters: List<Chapter>
) {
    /**
     * Returns all properties defined.
     *
     * @return all properties as a JSONObject or null if no properties are defined
     */
    /**
     * Returns all streams.
     *
     * @return list of streams
     */
    val filename: String?
        /**
         * Returns file name.
         *
         * @return media file name
         */
        get() = getStringFormatProperty(KEY_FILENAME)
    val format: String?
        /**
         * Returns format.
         *
         * @return media format
         */
        get() = getStringFormatProperty(KEY_FORMAT)
    val longFormat: String?
        /**
         * Returns long format.
         *
         * @return media long format
         */
        get() = getStringFormatProperty(KEY_FORMAT_LONG)
    val duration: String?
        /**
         * Returns duration.
         *
         * @return media duration in "seconds.microseconds" format
         */
        get() = getStringFormatProperty(KEY_DURATION)
    val startTime: String?
        /**
         * Returns start time.
         *
         * @return media start time in milliseconds
         */
        get() = getStringFormatProperty(KEY_START_TIME)
    val size: String?
        /**
         * Returns size.
         *
         * @return media size in bytes
         */
        get() = getStringFormatProperty(KEY_SIZE)
    val bitrate: String?
        /**
         * Returns bitrate.
         *
         * @return media bitrate in kb/s
         */
        get() = getStringFormatProperty(KEY_BIT_RATE)
    val tags: JSONObject?
        /**
         * Returns all tags.
         *
         * @return tags as a JSONObject
         */
        get() = getFormatProperty(KEY_TAGS)

    /**
     * Returns the property associated with the key.
     *
     * @param key property key
     * @return property as string or null if the key is not found
     */
    fun getStringProperty(key: String?): String? {
        val allProperties = allProperties
        return if (allProperties.has(key)) {
            allProperties.optString(key)
        } else {
            null
        }
    }

    /**
     * Returns the property associated with the key.
     *
     * @param key property key
     * @return property as Long or null if the key is not found
     */
    fun getNumberProperty(key: String?): Long? {
        val allProperties = allProperties
        return if (allProperties.has(key)) {
            allProperties.optLong(key)
        } else {
            null
        }
    }

    /**
     * Returns the property associated with the key.
     *
     * @param key property key
     * @return property as a JSONObject or null if the key is not found
     */
    fun getProperty(key: String?): JSONObject? {
        val allProperties = allProperties
        return allProperties.optJSONObject(key)
    }

    /**
     * Returns the format property associated with the key.
     *
     * @param key property key
     * @return format property as string or null if the key is not found
     */
    fun getStringFormatProperty(key: String?): String? {
        val formatProperties = formatProperties
        return if (formatProperties?.has(key) == true) {
            formatProperties.optString(key)
        } else {
            null
        }
    }

    /**
     * Returns the format property associated with the key.
     *
     * @param key property key
     * @return format property as Long or null if the key is not found
     */
    fun getNumberFormatProperty(key: String?): Long? {
        val formatProperties = formatProperties
        return if (formatProperties?.has(key) == true) {
            formatProperties.optLong(key)
        } else {
            null
        }
    }

    /**
     * Returns the format property associated with the key.
     *
     * @param key property key
     * @return format property as a JSONObject or null if the key is not found
     */
    fun getFormatProperty(key: String): JSONObject? {
        val formatProperties = formatProperties
        return formatProperties?.optJSONObject(key)
    }

    /**
     * Returns all format properties defined.
     *
     * @return all format properties as a JSONObject or null if no format properties are defined
     */
    val formatProperties: JSONObject?
        get() = allProperties.optJSONObject(KEY_FORMAT_PROPERTIES)

    companion object {
        /* COMMON KEYS */
        const val KEY_FORMAT_PROPERTIES = "format"
        const val KEY_FILENAME = "filename"
        const val KEY_FORMAT = "format_name"
        const val KEY_FORMAT_LONG = "format_long_name"
        const val KEY_START_TIME = "start_time"
        const val KEY_DURATION = "duration"
        const val KEY_SIZE = "size"
        const val KEY_BIT_RATE = "bit_rate"
        const val KEY_TAGS = "tags"
    }
}