package org.vsanyc.transaction.sandbox.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class LikeSpeakerHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPEAKER_HISTORY_SEQ")
    @SequenceGenerator(name = "SPEAKER_HISTORY_SEQ", sequenceName = "SPEAKER_HISTORY_SEQUENCE", initialValue = 1000,
            allocationSize = 1)
    private Long id;

    private String speakerName;

    private String threadName;

    private LocalDateTime created;

    private LocalDateTime updated;

}
