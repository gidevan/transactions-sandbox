package org.vsanyc.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Speaker {
    @Id
    private Long id;

    private String speakerName;

    private Long likeCount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }
}
