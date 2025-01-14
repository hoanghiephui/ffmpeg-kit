#ifndef FFTOOLS_FFMPEG_MUX_H
#define FFTOOLS_FFMPEG_MUX_H

#include <stdatomic.h>
#include <stdint.h>

#include "fftools_thread_queue.h"

#include "libavformat/avformat.h"

#include "libavcodec/packet.h"

#include "libavutil/dict.h"
#include "libavutil/fifo.h"
#include "libavutil/thread.h"

#define SPECIFIER_OPT_FMT_str  "%s"
#define SPECIFIER_OPT_FMT_i    "%i"
#define SPECIFIER_OPT_FMT_i64  "%"PRId64
#define SPECIFIER_OPT_FMT_ui64 "%"PRIu64
#define SPECIFIER_OPT_FMT_f    "%f"
#define SPECIFIER_OPT_FMT_dbl  "%lf"

#define WARN_MULTIPLE_OPT_USAGE(name, type, so, st)\
{\
    char namestr[128] = "";\
    const char *spec = so->specifier && so->specifier[0] ? so->specifier : "";\
    for (int _i = 0; opt_name_##name[_i]; _i++)\
        av_strlcatf(namestr, sizeof(namestr), "-%s%s", opt_name_##name[_i], opt_name_##name[_i+1] ? (opt_name_##name[_i+2] ? ", " : " or ") : "");\
    av_log(NULL, AV_LOG_WARNING, "Multiple %s options specified for stream %d, only the last option '-%s%s%s "SPECIFIER_OPT_FMT_##type"' will be used.\n",\
           namestr, st->index, opt_name_##name[0], spec[0] ? ":" : "", spec, so->u.type);\
}

#define MATCH_PER_STREAM_OPT(name, type, outvar, fmtctx, st)\
{\
    int _ret, _matches = 0;\
    SpecifierOpt *so;\
    for (int _i = 0; _i < o->nb_ ## name; _i++) {\
        char *spec = o->name[_i].specifier;\
        if ((_ret = check_stream_specifier(fmtctx, st, spec)) > 0) {\
            outvar = o->name[_i].u.type;\
            so = &o->name[_i];\
            _matches++;\
        } else if (_ret < 0)\
            exit_program(1);\
    }\
    if (_matches > 1)\
       WARN_MULTIPLE_OPT_USAGE(name, type, so, st);\
}

#define MATCH_PER_TYPE_OPT(name, type, outvar, fmtctx, mediatype)\
{\
    int i;\
    for (i = 0; i < o->nb_ ## name; i++) {\
        char *spec = o->name[i].specifier;\
        if (!strcmp(spec, mediatype))\
            outvar = o->name[i].u.type;\
    }\
}

typedef struct MuxStream {
    OutputStream ost;

    // name used for logging
    char log_name[32];

    /* the packets are buffered here until the muxer is ready to be initialized */
    AVFifo *muxing_queue;

    AVBSFContext *bsf_ctx;

    EncStats stats;

    int64_t max_frames;

    /*
     * The size of the AVPackets' buffers in queue.
     * Updated when a packet is either pushed or pulled from the queue.
     */
    size_t muxing_queue_data_size;

    int max_muxing_queue_size;

    /* Threshold after which max_muxing_queue_size will be in effect */
    size_t muxing_queue_data_threshold;

    /* dts of the last packet sent to the muxer, in the stream timebase
     * used for making up missing dts values */
    int64_t last_mux_dts;
} MuxStream;

typedef struct Muxer {
    OutputFile of;

    // name used for logging
    char log_name[32];

    AVFormatContext *fc;

    pthread_t    thread;
    ThreadQueue *tq;

    AVDictionary *opts;

    int thread_queue_size;

    /* filesize limit expressed in bytes */
    int64_t limit_filesize;
    atomic_int_least64_t last_filesize;
    int header_written;

    SyncQueue *sq_mux;
    AVPacket *sq_pkt;
} Muxer;

typedef struct EncStatsFile {
    char        *path;
    AVIOContext *io;
} EncStatsFile;

/* whether we want to print an SDP, set in of_open() */
extern __thread int want_sdp;

int mux_check_init(Muxer *mux);

#endif /* FFTOOLS_FFMPEG_MUX_H */
