package com.lgcns.ffmpegkit

/**
 *
 * Enumeration for Android ABIs.
 */
enum class Abi
/**
 * Creates a new enum.
 *
 * @param abiName ABI name
 */(
    /**
     * Returns the ABI name.
     *
     * @return ABI name as defined in Android NDK documentation
     */
    val abiName: String
) {
    /**
     * Represents armeabi-v7a ABI with NEON support
     */
    ABI_ARMV7A_NEON("armeabi-v7a-neon"),

    /**
     * Represents armeabi-v7a ABI
     */
    ABI_ARMV7A("armeabi-v7a"),

    /**
     * Represents armeabi ABI
     */
    ABI_ARM("armeabi"),

    /**
     * Represents x86 ABI
     */
    ABI_X86("x86"),

    /**
     * Represents x86_64 ABI
     */
    ABI_X86_64("x86_64"),

    /**
     * Represents arm64-v8a ABI
     */
    ABI_ARM64_V8A("arm64-v8a"),

    /**
     * Represents not supported ABIs
     */
    ABI_UNKNOWN("unknown");

    companion object {
        /**
         *
         * Returns the enumeration defined for the given ABI name.
         *
         * @param abiName ABI name
         * @return enumeration defined for the ABI name
         */
        fun from(abiName: String?): Abi {
            return when (abiName) {
                null -> {
                    ABI_UNKNOWN
                }

                ABI_ARM.name -> {
                    ABI_ARM
                }

                ABI_ARMV7A.name -> {
                    ABI_ARMV7A
                }

                ABI_ARMV7A_NEON.name -> {
                    ABI_ARMV7A_NEON
                }

                ABI_ARM64_V8A.name -> {
                    ABI_ARM64_V8A
                }

                ABI_X86.name -> {
                    ABI_X86
                }

                ABI_X86_64.name -> {
                    ABI_X86_64
                }

                else -> {
                    ABI_UNKNOWN
                }
            }
        }
    }
}