package com.lgcns.ffmpegkit;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A parser that constructs {@link MediaInformation} from FFprobe's json output.
 */
public class MediaInformationJsonParser {

    public static final String KEY_STREAMS = "streams";
    public static final String KEY_CHAPTERS = "chapters";

    /**
     * Extracts <code>MediaInformation</code> from the given FFprobe json output. Note that this
     * method does not throw {@link JSONException} as {@link #fromWithError(String)} does and
     * handles errors internally.
     *
     * @param ffprobeJsonOutput FFprobe json output
     * @return created {@link MediaInformation} instance of null if a parsing error occurs
     */
    public static MediaInformation from(final String ffprobeJsonOutput) {
        try {
            return fromWithError(ffprobeJsonOutput);
        } catch (JSONException e) {
            Log.e(FFmpegKitConfig.TAG, String.format("MediaInformation parsing failed.%s", e.getMessage()));
            return null;
        }
    }

    /**
     * Extracts MediaInformation from the given FFprobe json output.
     *
     * @param ffprobeJsonOutput ffprobe json output
     * @return created {@link MediaInformation} instance
     * @throws JSONException if a parsing error occurs
     */
    public static MediaInformation fromWithError(final String ffprobeJsonOutput) throws JSONException {
        final JSONObject jsonObject = new JSONObject(ffprobeJsonOutput);
        final JSONArray streamArray = jsonObject.optJSONArray(KEY_STREAMS);
        final JSONArray chapterArray = jsonObject.optJSONArray(KEY_CHAPTERS);

        ArrayList<StreamInformation> streamList = new ArrayList<>();
        for (int i = 0; streamArray != null && i < streamArray.length(); i++) {
            JSONObject streamObject = streamArray.optJSONObject(i);
            if (streamObject != null) {
                streamList.add(new StreamInformation(streamObject));
            }
        }

        ArrayList<Chapter> chapterList = new ArrayList<>();
        for (int i = 0; chapterArray != null && i < chapterArray.length(); i++) {
            JSONObject chapterObject = chapterArray.optJSONObject(i);
            if (chapterObject != null) {
                chapterList.add(new Chapter(chapterObject));
            }
        }

        return new MediaInformation(jsonObject, streamList, chapterList);
    }

}
