package com.lgcns.ffmpegkit

import android.util.Log
import org.json.JSONException
import org.json.JSONObject

/**
 * A parser that constructs [MediaInformation] from FFprobe's json output.
 */
object MediaInformationJsonParser {
    const val KEY_STREAMS = "streams"
    const val KEY_CHAPTERS = "chapters"

    /**
     * Extracts `MediaInformation` from the given FFprobe json output. Note that this
     * method does not throw [JSONException] as [.fromWithError] does and
     * handles errors internally.
     *
     * @param ffprobeJsonOutput FFprobe json output
     * @return created [MediaInformation] instance of null if a parsing error occurs
     */
    @JvmStatic
    fun from(ffprobeJsonOutput: String): MediaInformation? {
        return try {
            fromWithError(ffprobeJsonOutput)
        } catch (e: JSONException) {
            Log.e(
                FFmpegKitConfig.TAG,
                String.format("MediaInformation parsing failed.%s", e.message)
            )
            null
        }
    }

    /**
     * Extracts MediaInformation from the given FFprobe json output.
     *
     * @param ffprobeJsonOutput ffprobe json output
     * @return created [MediaInformation] instance
     * @throws JSONException if a parsing error occurs
     */
    @JvmStatic
    @Throws(JSONException::class)
    fun fromWithError(ffprobeJsonOutput: String): MediaInformation {
        val jsonObject = JSONObject(ffprobeJsonOutput)
        val streamArray = jsonObject.optJSONArray(KEY_STREAMS)
        val chapterArray = jsonObject.optJSONArray(KEY_CHAPTERS)
        val streamList = ArrayList<StreamInformation>()
        run {
            var i = 0
            while (streamArray != null && i < streamArray.length()) {
                val streamObject = streamArray.optJSONObject(i)
                if (streamObject != null) {
                    streamList.add(StreamInformation(streamObject))
                }
                i++
            }
        }
        val chapterList = ArrayList<Chapter>()
        var i = 0
        while (chapterArray != null && i < chapterArray.length()) {
            val chapterObject = chapterArray.optJSONObject(i)
            if (chapterObject != null) {
                chapterList.add(Chapter(chapterObject))
            }
            i++
        }
        return MediaInformation(jsonObject, streamList, chapterList)
    }
}