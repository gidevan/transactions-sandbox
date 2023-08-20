package org.vsanyc.service;

import org.springframework.stereotype.Service;
import org.vsanyc.model.Speaker;
import org.vsanyc.repository.SpeakerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SpeakerService {

    private SpeakerRepository speakerRepository;
    public SpeakerService(SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    public List<Speaker> findAll() {
        return speakerRepository.findAll();
    }

    public Speaker findById(Long id) {
        var speakerOpt =  speakerRepository.findById(id);
        return speakerOpt.orElseThrow(() -> new IllegalArgumentException("No record with id" + id));
    }
}
