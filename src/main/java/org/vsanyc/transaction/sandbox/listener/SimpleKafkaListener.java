package org.vsanyc.transaction.sandbox.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SimpleKafkaListener {

    @KafkaListener(topics = "speakerLikeTopic", groupId = "foo")
    public void listenGroupFoo(String message) {
        System.out.println("Received Message in group foo: " + message);
    }
}
