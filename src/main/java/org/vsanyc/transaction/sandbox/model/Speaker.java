package org.vsanyc.transaction.sandbox.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SPEAKER_SEQ")
    @SequenceGenerator(name = "SPEAKER_SEQ", sequenceName = "SPEAKER_SEQUENCE", initialValue = 1000,
            allocationSize = 1)
    private Long id;

    private String speakerName;

    private Long likeCount;

    private LocalDateTime created;
    private LocalDateTime updated;

}
