package org.vsanyc.transaction.sandbox.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.vsanyc.transaction.sandbox.model.SpeakerLikeData;
import org.vsanyc.transaction.sandbox.service.SpeakerService;

@Service
@Slf4j
@ConditionalOnProperty(name = "connection-type", prefix = "spring.kafka", havingValue = "consumer")
public class SimpleKafkaListener {
    public SimpleKafkaListener(ObjectMapper objectMapper, SpeakerService speakerService) {
        this.objectMapper = objectMapper;
        this.speakerService = speakerService;
    }

    private ObjectMapper objectMapper;

    private SpeakerService speakerService;



    @KafkaListener(topics = "speakerLikeTopic", groupId = "foo")
    public void listenGroupFoo(String message) throws JsonProcessingException {
        log.info("Received Message in group foo: " + message);
        var likeData = objectMapper.readValue(message, SpeakerLikeData.class);
        speakerService.likeSpeaker(likeData);
    }
}
