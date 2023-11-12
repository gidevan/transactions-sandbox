package org.vsanyc.transaction.sandbox.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vsanyc.transaction.sandbox.model.ProducerMessage;
import org.vsanyc.transaction.sandbox.model.SimpleMessage;
import org.vsanyc.transaction.sandbox.repository.ProducerMessageRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ProducerService {

    private final String simpleMessageTopic;

    private final String kafkaClientId;

    private final KafkaService kafkaService;

    private final ProducerMessageRepository producerMessageRepository;

    private final ObjectMapper objectMapper;

    public ProducerService(@Value("${spring.kafka.simple-message-topic}")String simpleMessageTopic,
                           @Value("${kafka-client-id}") String kafkaClientId,
                           KafkaService kafkaService,
                           ProducerMessageRepository producerMessageRepository,
                           ObjectMapper objectMapper) {
        this.simpleMessageTopic = simpleMessageTopic;
        this.kafkaClientId = kafkaClientId;
        this.kafkaService = kafkaService;
        this.producerMessageRepository = producerMessageRepository;
        this.objectMapper = objectMapper;
    }

    public void sendMessage(String message) throws JsonProcessingException {
        var kafkaMessage = SimpleMessage.builder()
                .uuid(UUID.randomUUID())
                .producerId(kafkaClientId)
                .message(message)
                .dateTime(LocalDateTime.now())
                .build();
        var producerMessage = createProducerMessage(kafkaMessage);
        producerMessageRepository.save(producerMessage);
        var kafkaMessageStr = objectMapper.writeValueAsString(kafkaMessage);
        kafkaService.sendMessage(simpleMessageTopic, kafkaMessageStr);
    }

    private ProducerMessage createProducerMessage(SimpleMessage message) {
        return ProducerMessage.builder()
                .message(message.getMessage())
                .dateTime(message.getDateTime())
                .producerId(message.getProducerId())
                .uuid(message.getUuid())
                .build();

    }
}
