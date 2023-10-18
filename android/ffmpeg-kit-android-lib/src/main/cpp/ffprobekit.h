

#ifndef FFPROBE_KIT_H
#define FFPROBE_KIT_H

#include <jni.h>

/*
 * Class:     com_lgcns_ffmpegkit_FFmpegKitConfig
 * Method:    nativeFFprobeExecute
 * Signature: (J[Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_lgcns_ffmpegkit_FFmpegKitConfig_nativeFFprobeExecute(JNIEnv *, jclass, jlong, jobjectArray);

#endif /* FFPROBE_KIT_H */