package org.vsanyc.transaction.sandbox.model;

public class SpeakerLikeData {

    private String speakerName;
    private String threadName;

    public String getSpeakerName() {
        return speakerName;
    }

    public String getThreadName() {
        return threadName;
    }

    public SpeakerLikeData(String speakerName, String threadName) {
        this.speakerName = speakerName;
        this.threadName = threadName;
    }


}
