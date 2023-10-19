package com.lgcns.ffmpegkit

import org.json.JSONObject

class Chapter(
    /**
     * Returns all chapter properties defined.
     *
     * @return all chapter properties as a JSONObject or null if no properties are defined
     */
    private val allProperties: JSONObject
) {

    val id: Long?
        get() = getNumberProperty(KEY_ID)
    val timeBase: String?
        get() = getStringProperty(KEY_TIME_BASE)
    val start: Long?
        get() = getNumberProperty(KEY_START)
    val startTime: String?
        get() = getStringProperty(KEY_START_TIME)
    val end: Long?
        get() = getNumberProperty(KEY_END)
    val endTime: String?
        get() = getStringProperty(KEY_END_TIME)
    val tags: JSONObject?
        get() = getProperty(KEY_TAGS)

    /**
     * Returns the chapter property associated with the key.
     *
     * @param key property key
     * @return chapter property as string or null if the key is not found
     */
    private fun getStringProperty(key: String): String? {
        val allProperties = allProperties
        return if (allProperties.has(key)) {
            allProperties.optString(key)
        } else {
            null
        }
    }

    /**
     * Returns the chapter property associated with the key.
     *
     * @param key property key
     * @return chapter property as Long or null if the key is not found
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
     * Returns the chapter property associated with the key.
     *
     * @param key property key
     * @return chapter property as a JSONObject or null if the key is not found
     */
    fun getProperty(key: String): JSONObject? {
        val allProperties = allProperties
        return allProperties.optJSONObject(key)
    }

    companion object {
        /* KEYS */
        const val KEY_ID = "id"
        const val KEY_TIME_BASE = "time_base"
        const val KEY_START = "start"
        const val KEY_START_TIME = "start_time"
        const val KEY_END = "end"
        const val KEY_END_TIME = "end_time"
        const val KEY_TAGS = "tags"
    }
}