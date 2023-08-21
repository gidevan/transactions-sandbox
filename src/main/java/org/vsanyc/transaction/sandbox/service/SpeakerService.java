package org.vsanyc.transaction.sandbox.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vsanyc.transaction.sandbox.model.Speaker;
import org.vsanyc.transaction.sandbox.model.SpeakerLikeData;
import org.vsanyc.transaction.sandbox.repository.SpeakerRepository;

import java.util.List;

@Service
public class SpeakerService {

    private SpeakerRepository speakerRepository;
    private KafkaService kafkaService;

    private ObjectMapper objectMapper;

    @Value(value = "${spring.kafka.speaker-like-topic}")
    private String speakerLikeTopic;


    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    public SpeakerService(SpeakerRepository speakerRepository, KafkaService kafkaService, ObjectMapper objectMapper) {
        this.speakerRepository = speakerRepository;
        this.kafkaService = kafkaService;
        this.objectMapper = objectMapper;
    }

    public Speaker findById(Long id) {
        var speakerOpt =  speakerRepository.findById(id);
        return speakerOpt.orElseThrow(() -> new IllegalArgumentException("No record with id" + id));
    }

    public void likeSpeaker(String speakerName) throws JsonProcessingException {
        var speakerLikeData = new SpeakerLikeData(speakerName, Thread.currentThread().getName());
        var msg = objectMapper.writeValueAsString(speakerLikeData);
        kafkaService.sendMessage(speakerLikeTopic, msg);
    }
}
