package org.vsanyc.transaction.sandbox.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
public class ProducerMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCER_MESSAGE_SEQ")
    @SequenceGenerator(name = "PRODUCER_MESSAGE_SEQ", sequenceName = "PRODUCER_MESSAGE_SEQUENCE", initialValue = 1000,
            allocationSize = 1)
    private Long id;
    private String producerId;
    private String message;

    private UUID uuid;
    private LocalDateTime dateTime;

    private LocalDateTime created;

    private LocalDateTime updated;
}
