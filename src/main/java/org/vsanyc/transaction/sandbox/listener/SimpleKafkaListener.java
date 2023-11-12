package org.vsanyc.transaction.sandbox.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.vsanyc.transaction.sandbox.model.SimpleMessage;
import org.vsanyc.transaction.sandbox.model.SpeakerLikeData;
import org.vsanyc.transaction.sandbox.service.ConsumerService;
import org.vsanyc.transaction.sandbox.service.SpeakerService;


@Service
@Slf4j
@AllArgsConstructor
@ConditionalOnProperty(name = "connection-type", prefix = "spring.kafka", havingValue = "consumer")
public class SimpleKafkaListener {
    private final ObjectMapper objectMapper;

    private final SpeakerService speakerService;

    private final ConsumerService consumerService;

    @KafkaListener(topics = "speakerLikeTopic", groupId = "foo")
    public void listenGroupFoo(String message) throws JsonProcessingException {
        log.info("Received Message in group foo: " + message);
        var likeData = objectMapper.readValue(message, SpeakerLikeData.class);
        speakerService.likeSpeaker(likeData);
    }

    @KafkaListener(topics = "local-cluster-topic-1", groupId = "${spring.kafka.consumer-group-id}")
    public void listenSimpleMessageTopic(String message) throws JsonProcessingException {
        log.info("Received Simple Message: " + message);
        var simpleMessage = objectMapper.readValue(message, SimpleMessage.class);
        consumerService.saveSimpleMessage(simpleMessage);
    }
}
