package org.vsanyc.transaction.sandbox.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vsanyc.transaction.sandbox.model.Speaker;
import org.vsanyc.transaction.sandbox.service.SpeakerService;

import java.util.List;

@RestController
public class SpeakerController {

    private Counter likeCounter;
    public SpeakerController(SpeakerService speakerService, MeterRegistry meterRegistry) {
        this.speakerService = speakerService;
        this.likeCounter = Counter.builder("like_speaker_counter")
                .description("Speaker like counter")
                .register(meterRegistry);
    }

    private SpeakerService speakerService;


    @GetMapping("/simple-request")
    public String simpleRequest() {
        return "Simple response";
    }

    @GetMapping("/all")
    public List<Speaker> findAll() {
        return speakerService.findAll();
    }

    @GetMapping("/{id}")
    public Speaker findById(@PathVariable Long id) {
        return speakerService.findById(id);
    }

    @PostMapping("/{speakerName}")
    public void likeSpeaker(@PathVariable String speakerName) throws JsonProcessingException {
        likeCounter.increment();
        speakerService.likeSpeaker(speakerName);
    }
}
