package com.lgcns.ffmpegkit

/**
 *
 * Detects the running ABI name natively using Google `cpu-features` library.
 */
object AbiDetect {
    const val ARM_V7A = "arm-v7a"
    const val ARM_V7A_NEON = "arm-v7a-neon"
    private var armV7aNeonLoaded = false

    init {
        armV7aNeonLoaded = false
        NativeLoader.loadFFmpegKitAbiDetect()

        /* ALL LIBRARIES LOADED AT STARTUP */FFmpegKit::class.java.name
        FFmpegKitConfig::class.java.name
        FFprobeKit::class.java.name
    }

    @JvmStatic
    fun setArmV7aNeonLoaded() {
        armV7aNeonLoaded = true
    }

    @JvmStatic
    val abi: String
        /**
         *
         * Returns the ABI name loaded.
         *
         * @return ABI name loaded
         */
        get() = if (armV7aNeonLoaded) {
            ARM_V7A_NEON
        } else {
            nativeAbi
        }
    val cpuAbi: String
        /**
         *
         * Returns the ABI name of the cpu running.
         *
         * @return ABI name of the cpu running
         */
        get() = nativeCpuAbi
    @JvmStatic
    val nativeAbi: String
        /**
         *
         * Returns the ABI name loaded natively.
         *
         * @return ABI name loaded
         */
        external get
    val nativeCpuAbi: String
        /**
         *
         * Returns the ABI name of the cpu running natively.
         *
         * @return ABI name of the cpu running
         */
        external get
    @JvmStatic
    val isNativeLTSBuild: Boolean
        /**
         *
         * Returns whether FFmpegKit release is a long term release or not natively.
         *
         * @return yes or no
         */
        external get
    @JvmStatic
    val nativeBuildConf: String?
        /**
         *
         * Returns the build configuration for `FFmpeg` natively.
         *
         * @return build configuration string
         */
        external get
}