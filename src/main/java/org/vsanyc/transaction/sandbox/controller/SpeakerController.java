package org.vsanyc.transaction.sandbox.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vsanyc.transaction.sandbox.model.Speaker;
import org.vsanyc.transaction.sandbox.service.SpeakerService;

import java.util.List;

@RestController
public class SpeakerController {
    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
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
        speakerService.likeSpeaker(speakerName);
    }
}
