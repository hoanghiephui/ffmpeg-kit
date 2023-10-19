package com.lgcns.ffmpegkit

import android.content.Context
import android.hardware.camera2.CameraAccessException
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraManager
import android.hardware.camera2.CameraMetadata
import android.util.Log

/**
 *
 * Helper class to detect camera devices that can be used in
 * `FFmpeg`/`FFprobe` commands.
 */
internal object CameraSupport {
    /**
     *
     * Lists camera ids that can be used in `FFmpeg`/`FFprobe` commands.
     *
     * @param context application context
     * @return the list of supported camera ids on Android API Level 24+, an empty list on older
     * API levels
     */
    @JvmStatic
    fun extractSupportedCameraIds(context: Context): List<String> {
        val detectedCameraIdList: MutableList<String> = ArrayList()
        try {
            val manager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
            if (manager != null) {
                val cameraIdList = manager.cameraIdList
                for (cameraId in cameraIdList) {
                    val chars = manager.getCameraCharacteristics(cameraId)
                    val cameraSupport =
                        chars.get(CameraCharacteristics.INFO_SUPPORTED_HARDWARE_LEVEL)
                    if (cameraSupport != null && cameraSupport == CameraMetadata.INFO_SUPPORTED_HARDWARE_LEVEL_LEGACY) {
                        Log.d(
                            FFmpegKitConfig.TAG,
                            "Detected camera with id $cameraId has LEGACY hardware level which is not supported by Android Camera2 NDK API."
                        )
                    } else if (cameraSupport != null) {
                        detectedCameraIdList.add(cameraId)
                    }
                }
            }
        } catch (e: CameraAccessException) {
            Log.w(FFmpegKitConfig.TAG, "Detecting camera ids failed.", e)
        }
        return detectedCameraIdList
    }
}