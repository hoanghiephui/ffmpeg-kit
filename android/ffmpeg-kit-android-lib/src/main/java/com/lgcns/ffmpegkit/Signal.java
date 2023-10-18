package com.lgcns.ffmpegkit;

/**
 * <p>Lists signals handled by FFmpegKit library.
 */
public enum Signal {

    SIGINT(2),
    SIGQUIT(3),
    SIGPIPE(13),
    SIGTERM(15),
    SIGXCPU(24);

    private final int value;

    Signal(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
