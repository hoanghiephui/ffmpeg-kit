#ifndef FFTOOLS_FOPEN_UTF8_H
#define FFTOOLS_FOPEN_UTF8_H

#include <stdio.h>

/* The fopen_utf8 function here is essentially equivalent to avpriv_fopen_utf8,
 * except that it doesn't set O_CLOEXEC, and that it isn't exported
 * from a different library. (On Windows, each DLL might use a different
 * CRT, and FILE* handles can't be shared across them.) */

#ifdef _WIN32
#include "libavutil/wchar_filename.h"

static inline FILE *fopen_utf8(const char *path_utf8, const char *mode)
{
    wchar_t *path_w, *mode_w;
    FILE *f;

    /* convert UTF-8 to wide chars */
    if (get_extended_win32_path(path_utf8, &path_w)) /* This sets errno on error. */
        return NULL;
    if (!path_w)
        goto fallback;

    if (utf8towchar(mode, &mode_w))
        return NULL;
    if (!mode_w) {
        /* If failing to interpret the mode string as utf8, it is an invalid
         * parameter. */
        av_freep(&path_w);
        errno = EINVAL;
        return NULL;
    }

    f = _wfopen(path_w, mode_w);
    av_freep(&path_w);
    av_freep(&mode_w);

    return f;
fallback:
    /* path may be in CP_ACP */
    return fopen(path_utf8, mode);
}

#else

static inline FILE *fopen_utf8(const char *path, const char *mode)
{
    return fopen(path, mode);
}
#endif

#endif /* FFTOOLS_FOPEN_UTF8_H */
