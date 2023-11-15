package org.vsanyc.transaction.sandbox.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.vsanyc.transaction.sandbox.service.ProducerService;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class SimpleController {

    private ProducerService producerService;

    @GetMapping("/simpletest/{message}")
    public String sendMessage(@PathVariable String message) throws JsonProcessingException {
        producerService.sendMessage(message);
        return "Message [" + message +"] is sent at" + LocalDateTime.now();

    }

    @GetMapping("/simpletest/generate/{messageCount}")
    public String generatedMessages(@PathVariable Integer messageCount) throws JsonProcessingException {
        producerService.generateMessages(messageCount);
        return messageCount + " messages are sent at" + LocalDateTime.now();

    }
}
