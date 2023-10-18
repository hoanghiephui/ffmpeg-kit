

#ifndef FFMPEG_KIT_ABIDETECT_H
#define FFMPEG_KIT_ABIDETECT_H

#include <jni.h>
#include "ffmpegkit.h"

/** Represents armeabi-v7a ABI with NEON support. */
#define ABI_ARMV7A_NEON "armeabi-v7a-neon"

/** Represents armeabi-v7a ABI. */
#define ABI_ARMV7A "armeabi-v7a"

/** Represents armeabi ABI. */
#define ABI_ARM "armeabi"

/** Represents x86 ABI. */
#define ABI_X86 "x86"

/** Represents x86_64 ABI. */
#define ABI_X86_64 "x86_64"

/** Represents arm64-v8a ABI. */
#define ABI_ARM64_V8A "arm64-v8a"

/** Represents not supported ABIs. */
#define ABI_UNKNOWN "unknown"

/*
 * Class:     com_lgcns_ffmpegkit_AbiDetect
 * Method:    getNativeAbi
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_lgcns_ffmpegkit_AbiDetect_getNativeAbi(JNIEnv *, jclass);

/*
 * Class:     com_lgcns_ffmpegkit_AbiDetect
 * Method:    getNativeCpuAbi
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_lgcns_ffmpegkit_AbiDetect_getNativeCpuAbi(JNIEnv *, jclass);

/**
 * Class:     com_lgcns_ffmpegkit_AbiDetect
 * Method:    isNativeLTSBuild
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_com_lgcns_ffmpegkit_AbiDetect_isNativeLTSBuild(JNIEnv *, jclass);

/*
 * Class:     com_lgcns_ffmpegkit_AbiDetect
 * Method:    getNativeBuildConf
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_lgcns_ffmpegkit_AbiDetect_getNativeBuildConf(JNIEnv *, jclass);

#endif /* FFMPEG_KIT_ABIDETECT_H */
