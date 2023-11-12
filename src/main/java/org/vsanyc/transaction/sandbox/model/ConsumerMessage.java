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
@Setter
@Getter
@Builder
public class ConsumerMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONSUMER_MESSAGE_SEQ")
    @SequenceGenerator(name = "CONSUMER_MESSAGE_SEQ", sequenceName = "CONSUMER_MESSAGE_SEQUENCE", initialValue = 1000,
            allocationSize = 1)
    private Long id;
    private String producerId;
    private String consumerId;

    private UUID uuid;

    private String consumerGroupId;
    private String message;

    private LocalDateTime dateTime;

    private LocalDateTime created;

    private LocalDateTime updated;
}
