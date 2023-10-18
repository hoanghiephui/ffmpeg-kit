package com.lgcns.ffmpegkit;

/**
 * <p>Statistics entry for an FFmpeg execute session.
 */
public class Statistics {
    private long sessionId;
    private int videoFrameNumber;
    private float videoFps;
    private float videoQuality;
    private long size;
    private double time;
    private double bitrate;
    private double speed;

    public Statistics(final long sessionId, final int videoFrameNumber, final float videoFps, final float videoQuality, final long size, final double time, final double bitrate, final double speed) {
        this.sessionId = sessionId;
        this.videoFrameNumber = videoFrameNumber;
        this.videoFps = videoFps;
        this.videoQuality = videoQuality;
        this.size = size;
        this.time = time;
        this.bitrate = bitrate;
        this.speed = speed;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public int getVideoFrameNumber() {
        return videoFrameNumber;
    }

    public void setVideoFrameNumber(int videoFrameNumber) {
        this.videoFrameNumber = videoFrameNumber;
    }

    public float getVideoFps() {
        return videoFps;
    }

    public void setVideoFps(float videoFps) {
        this.videoFps = videoFps;
    }

    public float getVideoQuality() {
        return videoQuality;
    }

    public void setVideoQuality(float videoQuality) {
        this.videoQuality = videoQuality;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getBitrate() {
        return bitrate;
    }

    public void setBitrate(double bitrate) {
        this.bitrate = bitrate;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Statistics{");
        stringBuilder.append("sessionId=");
        stringBuilder.append(sessionId);
        stringBuilder.append(", videoFrameNumber=");
        stringBuilder.append(videoFrameNumber);
        stringBuilder.append(", videoFps=");
        stringBuilder.append(videoFps);
        stringBuilder.append(", videoQuality=");
        stringBuilder.append(videoQuality);
        stringBuilder.append(", size=");
        stringBuilder.append(size);
        stringBuilder.append(", time=");
        stringBuilder.append(time);
        stringBuilder.append(", bitrate=");
        stringBuilder.append(bitrate);
        stringBuilder.append(", speed=");
        stringBuilder.append(speed);
        stringBuilder.append('}');

        return stringBuilder.toString();
    }

}
