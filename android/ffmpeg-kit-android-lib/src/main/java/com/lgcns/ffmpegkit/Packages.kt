package com.lgcns.ffmpegkit

import com.lgcns.ffmpegkit.AbiDetect.nativeBuildConf
import java.util.Collections

/**
 *
 * Helper class to extract binary package information.
 */
object Packages {
    private var supportedExternalLibraries: MutableList<String> = ArrayList()

    init {
        supportedExternalLibraries.add("dav1d")
        supportedExternalLibraries.add("fontconfig")
        supportedExternalLibraries.add("freetype")
        supportedExternalLibraries.add("fribidi")
        supportedExternalLibraries.add("gmp")
        supportedExternalLibraries.add("gnutls")
        supportedExternalLibraries.add("kvazaar")
        supportedExternalLibraries.add("mp3lame")
        supportedExternalLibraries.add("libass")
        supportedExternalLibraries.add("iconv")
        supportedExternalLibraries.add("libilbc")
        supportedExternalLibraries.add("libtheora")
        supportedExternalLibraries.add("libvidstab")
        supportedExternalLibraries.add("libvorbis")
        supportedExternalLibraries.add("libvpx")
        supportedExternalLibraries.add("libwebp")
        supportedExternalLibraries.add("libxml2")
        supportedExternalLibraries.add("opencore-amr")
        supportedExternalLibraries.add("openh264")
        supportedExternalLibraries.add("openssl")
        supportedExternalLibraries.add("opus")
        supportedExternalLibraries.add("rubberband")
        supportedExternalLibraries.add("sdl2")
        supportedExternalLibraries.add("shine")
        supportedExternalLibraries.add("snappy")
        supportedExternalLibraries.add("soxr")
        supportedExternalLibraries.add("speex")
        supportedExternalLibraries.add("srt")
        supportedExternalLibraries.add("tesseract")
        supportedExternalLibraries.add("twolame")
        supportedExternalLibraries.add("x264")
        supportedExternalLibraries.add("x265")
        supportedExternalLibraries.add("xvid")
        supportedExternalLibraries.add("zimg")
    }

    @JvmStatic
    val packageName: String
        /**
         * Returns the FFmpegKit binary package name.
         *
         * @return predicted FFmpegKit binary package name
         */
        get() {
            val externalLibraryList = externalLibraries
            val speex = externalLibraryList.contains("speex")
            val fribidi = externalLibraryList.contains("fribidi")
            val gnutls = externalLibraryList.contains("gnutls")
            val xvid = externalLibraryList.contains("xvid")
            var minGpl = false
            var https = false
            var httpsGpl = false
            var audio = false
            var video = false
            var full = false
            var fullGpl = false
            if (speex && fribidi) {
                if (xvid) {
                    fullGpl = true
                } else {
                    full = true
                }
            } else if (speex) {
                audio = true
            } else if (fribidi) {
                video = true
            } else if (xvid) {
                if (gnutls) {
                    httpsGpl = true
                } else {
                    minGpl = true
                }
            } else {
                if (gnutls) {
                    https = true
                }
            }
            if (fullGpl) {
                return if (externalLibraryList.contains("dav1d") &&
                    externalLibraryList.contains("fontconfig") &&
                    externalLibraryList.contains("freetype") &&
                    externalLibraryList.contains("fribidi") &&
                    externalLibraryList.contains("gmp") &&
                    externalLibraryList.contains("gnutls") &&
                    externalLibraryList.contains("kvazaar") &&
                    externalLibraryList.contains("mp3lame") &&
                    externalLibraryList.contains("libass") &&
                    externalLibraryList.contains("iconv") &&
                    externalLibraryList.contains("libilbc") &&
                    externalLibraryList.contains("libtheora") &&
                    externalLibraryList.contains("libvidstab") &&
                    externalLibraryList.contains("libvorbis") &&
                    externalLibraryList.contains("libvpx") &&
                    externalLibraryList.contains("libwebp") &&
                    externalLibraryList.contains("libxml2") &&
                    externalLibraryList.contains("opencore-amr") &&
                    externalLibraryList.contains("opus") &&
                    externalLibraryList.contains("shine") &&
                    externalLibraryList.contains("snappy") &&
                    externalLibraryList.contains("soxr") &&
                    externalLibraryList.contains("speex") &&
                    externalLibraryList.contains("twolame") &&
                    externalLibraryList.contains("x264") &&
                    externalLibraryList.contains("x265") &&
                    externalLibraryList.contains("xvid") &&
                    externalLibraryList.contains("zimg")
                ) {
                    "full-gpl"
                } else {
                    "custom"
                }
            }
            if (full) {
                return if (externalLibraryList.contains("dav1d") &&
                    externalLibraryList.contains("fontconfig") &&
                    externalLibraryList.contains("freetype") &&
                    externalLibraryList.contains("fribidi") &&
                    externalLibraryList.contains("gmp") &&
                    externalLibraryList.contains("gnutls") &&
                    externalLibraryList.contains("kvazaar") &&
                    externalLibraryList.contains("mp3lame") &&
                    externalLibraryList.contains("libass") &&
                    externalLibraryList.contains("iconv") &&
                    externalLibraryList.contains("libilbc") &&
                    externalLibraryList.contains("libtheora") &&
                    externalLibraryList.contains("libvorbis") &&
                    externalLibraryList.contains("libvpx") &&
                    externalLibraryList.contains("libwebp") &&
                    externalLibraryList.contains("libxml2") &&
                    externalLibraryList.contains("opencore-amr") &&
                    externalLibraryList.contains("opus") &&
                    externalLibraryList.contains("shine") &&
                    externalLibraryList.contains("snappy") &&
                    externalLibraryList.contains("soxr") &&
                    externalLibraryList.contains("speex") &&
                    externalLibraryList.contains("twolame") &&
                    externalLibraryList.contains("zimg")
                ) {
                    "full"
                } else {
                    "custom"
                }
            }
            if (video) {
                return if (externalLibraryList.contains("dav1d") &&
                    externalLibraryList.contains("fontconfig") &&
                    externalLibraryList.contains("freetype") &&
                    externalLibraryList.contains("fribidi") &&
                    externalLibraryList.contains("kvazaar") &&
                    externalLibraryList.contains("libass") &&
                    externalLibraryList.contains("iconv") &&
                    externalLibraryList.contains("libtheora") &&
                    externalLibraryList.contains("libvpx") &&
                    externalLibraryList.contains("libwebp") &&
                    externalLibraryList.contains("snappy") &&
                    externalLibraryList.contains("zimg")
                ) {
                    "video"
                } else {
                    "custom"
                }
            }
            if (audio) {
                return if (externalLibraryList.contains("mp3lame") &&
                    externalLibraryList.contains("libilbc") &&
                    externalLibraryList.contains("libvorbis") &&
                    externalLibraryList.contains("opencore-amr") &&
                    externalLibraryList.contains("opus") &&
                    externalLibraryList.contains("shine") &&
                    externalLibraryList.contains("soxr") &&
                    externalLibraryList.contains("speex") &&
                    externalLibraryList.contains("twolame")
                ) {
                    "audio"
                } else {
                    "custom"
                }
            }
            if (httpsGpl) {
                return if (externalLibraryList.contains("gmp") &&
                    externalLibraryList.contains("gnutls") &&
                    externalLibraryList.contains("libvidstab") &&
                    externalLibraryList.contains("x264") &&
                    externalLibraryList.contains("x265") &&
                    externalLibraryList.contains("xvid")
                ) {
                    "https-gpl"
                } else {
                    "custom"
                }
            }
            if (https) {
                return if (externalLibraryList.contains("gmp") &&
                    externalLibraryList.contains("gnutls")
                ) {
                    "https"
                } else {
                    "custom"
                }
            }
            if (minGpl) {
                return if (externalLibraryList.contains("libvidstab") &&
                    externalLibraryList.contains("x264") &&
                    externalLibraryList.contains("x265") &&
                    externalLibraryList.contains("xvid")
                ) {
                    "min-gpl"
                } else {
                    "custom"
                }
            }
            return if (externalLibraryList.isEmpty()) {
                "min"
            } else {
                "custom"
            }
        }
    @JvmStatic
    val externalLibraries: List<String>
        /**
         * Returns enabled external libraries by FFmpeg.
         *
         * @return enabled external libraries
         */
        get() {
            val buildConfiguration = nativeBuildConf
            val enabledLibraryList: MutableList<String> = ArrayList()
            for (supportedExternalLibrary in supportedExternalLibraries) {
                if (buildConfiguration!!.contains("enable-$supportedExternalLibrary") ||
                    buildConfiguration.contains("enable-lib$supportedExternalLibrary")
                ) {
                    enabledLibraryList.add(supportedExternalLibrary)
                }
            }
            enabledLibraryList.sort()
            return enabledLibraryList
        }
}