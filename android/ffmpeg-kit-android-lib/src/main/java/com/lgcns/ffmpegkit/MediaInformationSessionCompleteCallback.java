package com.lgcns.ffmpegkit;

/**
 * <p>Callback function that is invoked when an asynchronous <code>MediaInformation</code> session
 * has ended.
 */
@FunctionalInterface
public interface MediaInformationSessionCompleteCallback {

    /**
     * <p>Called when a media information session has ended.
     *
     * @param session media information session
     */
    void apply(final MediaInformationSession session);

}
