package org.vsanyc.transaction.sandbox.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
public class SimpleMessage {

    private UUID uuid;
    private String producerId;
    private String message;
    private LocalDateTime dateTime;
}
