package com.lgcns.ffmpegkit

import android.os.Build
import android.util.Log
import com.lgcns.ffmpegkit.AbiDetect.abi
import com.lgcns.ffmpegkit.AbiDetect.isNativeLTSBuild
import com.lgcns.ffmpegkit.AbiDetect.nativeAbi
import com.lgcns.ffmpegkit.AbiDetect.setArmV7aNeonLoaded
import com.lgcns.ffmpegkit.Packages.externalLibraries
import com.lgcns.ffmpegkit.Packages.packageName
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 *
 * Responsible of loading native libraries.
 */
object NativeLoader {
    val FFMPEG_LIBRARIES =
        arrayOf("avutil", "swscale", "swresample", "avcodec", "avformat", "avfilter", "avdevice")
    val LIBRARIES_LINKED_WITH_CXX = arrayOf(
        "chromaprint",
        "openh264",
        "rubberband",
        "snappy",
        "srt",
        "tesseract",
        "x265",
        "zimg",
        "libilbc"
    )
    val isTestModeDisabled: Boolean
        get() = System.getProperty("enable.ffmpeg.kit.test.mode") == null

    private fun loadLibrary(libraryName: String) {
        if (isTestModeDisabled) {
            try {
                System.loadLibrary(libraryName)
            } catch (e: UnsatisfiedLinkError) {
                throw Error(
                    String.format(
                        "FFmpegKit failed to start on %s.",
                        deviceDebugInformation
                    ), e
                )
            }
        }
    }

    private fun loadExternalLibraries(): List<String> {
        return if (isTestModeDisabled) {
            externalLibraries
        } else {
            emptyList()
        }
    }

    private fun loadNativeAbi(): String {
        return if (isTestModeDisabled) {
            nativeAbi
        } else {
            Abi.ABI_X86_64.name
        }
    }

    @JvmStatic
    fun loadAbi(): String {
        return if (isTestModeDisabled) {
            abi
        } else {
            Abi.ABI_X86_64.name
        }
    }

    @JvmStatic
    fun loadPackageName(): String {
        return if (isTestModeDisabled) {
            packageName
        } else {
            "test"
        }
    }

    @JvmStatic
    fun loadVersion(): String {
        val version = "6.0"
        return if (isTestModeDisabled) {
            FFmpegKitConfig.getVersion()
        } else if (loadIsLTSBuild()) {
            String.format("%s-lts", version)
        } else {
            version
        }
    }

    fun loadIsLTSBuild(): Boolean {
        return if (isTestModeDisabled) {
            isNativeLTSBuild
        } else {
            true
        }
    }

    @JvmStatic
    fun loadLogLevel(): Int {
        return if (isTestModeDisabled) {
            FFmpegKitConfig.getNativeLogLevel()
        } else {
            Level.AV_LOG_DEBUG.value
        }
    }

    @JvmStatic
    fun loadBuildDate(): String {
        return if (isTestModeDisabled) {
            FFmpegKitConfig.getBuildDate()
        } else {
            SimpleDateFormat("yyyyMMdd", Locale.getDefault())
                .format(Date())
        }
    }

    fun enableRedirection() {
        if (isTestModeDisabled) {
            FFmpegKitConfig.enableRedirection()
        }
    }

    fun loadFFmpegKitAbiDetect() {
        loadLibrary("ffmpegkit_abidetect")
    }

    @JvmStatic
    fun loadFFmpeg(): Boolean {
        var nativeFFmpegLoaded = false
        var nativeFFmpegTriedAndFailed = false
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            /* LOADING LINKED LIBRARIES MANUALLY ON API < 21 */
            val externalLibrariesEnabled = loadExternalLibraries()
            for (dependantLibrary in LIBRARIES_LINKED_WITH_CXX) {
                if (externalLibrariesEnabled.contains(dependantLibrary)) {
                    loadLibrary("c++_shared")
                    break
                }
            }
            if (AbiDetect.ARM_V7A == loadNativeAbi()) {
                try {
                    for (ffmpegLibrary in FFMPEG_LIBRARIES) {
                        loadLibrary(ffmpegLibrary + "_neon")
                    }
                    nativeFFmpegLoaded = true
                } catch (e: Error) {
                    Log.i(
                        FFmpegKitConfig.TAG,
                        String.format(
                            "NEON supported armeabi-v7a ffmpeg library not found. Loading default armeabi-v7a library.%s",
                            e.message
                        )
                    )
                    nativeFFmpegTriedAndFailed = true
                }
            }
            if (!nativeFFmpegLoaded) {
                for (ffmpegLibrary in FFMPEG_LIBRARIES) {
                    loadLibrary(ffmpegLibrary)
                }
            }
        }
        return nativeFFmpegTriedAndFailed
    }

    @JvmStatic
    fun loadFFmpegKit(nativeFFmpegTriedAndFailed: Boolean) {
        var nativeFFmpegKitLoaded = false
        if (!nativeFFmpegTriedAndFailed && AbiDetect.ARM_V7A == loadNativeAbi()) {
            try {

                /*
                 * THE TRY TO LOAD ARM-V7A-NEON FIRST. IF NOT LOAD DEFAULT ARM-V7A
                 */
                loadLibrary("ffmpegkit_armv7a_neon")
                nativeFFmpegKitLoaded = true
                setArmV7aNeonLoaded()
            } catch (e: Error) {
                Log.i(
                    FFmpegKitConfig.TAG,
                    String.format(
                        "NEON supported armeabi-v7a ffmpegkit library not found. Loading default armeabi-v7a library.%s",
                        e.message
                    )
                )
            }
        }
        if (!nativeFFmpegKitLoaded) {
            loadLibrary("ffmpegkit")
        }
    }

    @get:Suppress("deprecation")
    val deviceDebugInformation: String
        get() {
            val stringBuilder = StringBuilder()
            stringBuilder.append("brand: ")
            stringBuilder.append(Build.BRAND)
            stringBuilder.append(", model: ")
            stringBuilder.append(Build.MODEL)
            stringBuilder.append(", device: ")
            stringBuilder.append(Build.DEVICE)
            stringBuilder.append(", api level: ")
            stringBuilder.append(Build.VERSION.SDK_INT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                stringBuilder.append(", abis: ")
                stringBuilder.append(FFmpegKitConfig.argumentsToString(Build.SUPPORTED_ABIS))
                stringBuilder.append(", 32bit abis: ")
                stringBuilder.append(FFmpegKitConfig.argumentsToString(Build.SUPPORTED_32_BIT_ABIS))
                stringBuilder.append(", 64bit abis: ")
                stringBuilder.append(FFmpegKitConfig.argumentsToString(Build.SUPPORTED_64_BIT_ABIS))
            } else {
                stringBuilder.append(", cpu abis: ")
                stringBuilder.append(Build.CPU_ABI)
                stringBuilder.append(", cpu abi2s: ")
                stringBuilder.append(Build.CPU_ABI2)
            }
            return stringBuilder.toString()
        }
}