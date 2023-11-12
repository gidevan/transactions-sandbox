package org.vsanyc.transaction.sandbox.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vsanyc.transaction.sandbox.model.ConsumerMessage;
import org.vsanyc.transaction.sandbox.model.SimpleMessage;
import org.vsanyc.transaction.sandbox.repository.ConsumerMessageRepository;

@Service
@Slf4j
public class ConsumerService {

    private final String kafkaClientId;

    private final String consumerGroupId;
    private final ConsumerMessageRepository consumerMessageRepository;

    public ConsumerService(@Value("${kafka-client-id}") String kafkaClientId,
                           @Value("${spring.kafka.consumer-group-id}") String consumerGroupId,
                           ConsumerMessageRepository consumerMessageRepository) {
        this.kafkaClientId = kafkaClientId;
        this.consumerGroupId = consumerGroupId;
        this.consumerMessageRepository = consumerMessageRepository;
    }

    public void saveSimpleMessage(SimpleMessage simpleMessage) {
        log.info("Save consumerGroupId: [{}], consumerId: [{}]", consumerGroupId, kafkaClientId);
        var consumerMessage = ConsumerMessage.builder()
                .consumerGroupId(consumerGroupId)
                .consumerId(kafkaClientId)
                .uuid(simpleMessage.getUuid())
                .producerId(simpleMessage.getProducerId())
                .dateTime(simpleMessage.getDateTime())
                .message(simpleMessage.getMessage())
                .build();

        consumerMessageRepository.save(consumerMessage);

    }

}
