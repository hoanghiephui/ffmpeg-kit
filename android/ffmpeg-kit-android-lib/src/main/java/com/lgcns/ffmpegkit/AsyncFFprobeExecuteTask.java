package com.lgcns.ffmpegkit;


/**
 * <p>Executes an FFprobe session asynchronously.
 */
public class AsyncFFprobeExecuteTask implements Runnable {
    private final FFprobeSession ffprobeSession;
    private final FFprobeSessionCompleteCallback completeCallback;

    public AsyncFFprobeExecuteTask(final FFprobeSession ffprobeSession) {
        this.ffprobeSession = ffprobeSession;
        this.completeCallback = ffprobeSession.getCompleteCallback();
    }

    @Override
    public void run() {
        FFmpegKitConfig.ffprobeExecute(ffprobeSession);

        if (completeCallback != null) {
            try {
                // NOTIFY SESSION CALLBACK DEFINED
                completeCallback.apply(ffprobeSession);
            } catch (final Exception e) {
                android.util.Log.e(FFmpegKitConfig.TAG, String.format("Exception thrown inside session complete callback.%s", e.getMessage()));
            }
        }

        final FFprobeSessionCompleteCallback globalFFprobeSessionCompleteCallback = FFmpegKitConfig.getFFprobeSessionCompleteCallback();
        if (globalFFprobeSessionCompleteCallback != null) {
            try {
                // NOTIFY GLOBAL CALLBACK DEFINED
                globalFFprobeSessionCompleteCallback.apply(ffprobeSession);
            } catch (final Exception e) {
                android.util.Log.e(FFmpegKitConfig.TAG, String.format("Exception thrown inside global complete callback.%s", e.getMessage()));
            }
        }
    }

}
