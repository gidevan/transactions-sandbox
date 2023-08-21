package org.vsanyc.transaction.sandbox.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vsanyc.transaction.sandbox.model.Speaker;
import org.vsanyc.transaction.sandbox.model.SpeakerLikeData;
import org.vsanyc.transaction.sandbox.repository.SpeakerRepository;

import java.util.List;

@Service
@Slf4j
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

    @Transactional
    public void likeSpeaker(SpeakerLikeData speakerLikeData) {
        var speakerOpt = speakerRepository.findBySpeakerName(speakerLikeData.getSpeakerName());
        speakerOpt.ifPresentOrElse(speaker -> {
            var likes = speaker.getLikeCount();
            speaker.setLikeCount(++likes);
            log.info("Update likes for [{}]. Likes value is [{}]", speaker.getSpeakerName(), likes);
            speakerRepository.save(speaker);
        }, () -> log.error("No data for speaker [{}]", speakerLikeData.getSpeakerName())) ;
    }
}
