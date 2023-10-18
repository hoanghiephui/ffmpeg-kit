

#ifndef FFMPEG_KIT_EXCEPTION_H
#define FFMPEG_KIT_EXCEPTION_H

#include <stdio.h>
#include <setjmp.h>

/** Holds information to implement exception handling. */
extern __thread jmp_buf ex_buf__;

#endif // FFMPEG_KIT_EXCEPTION_H
