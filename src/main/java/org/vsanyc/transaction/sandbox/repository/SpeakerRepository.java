package org.vsanyc.transaction.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vsanyc.transaction.sandbox.model.Speaker;

import java.util.Optional;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

    Optional<Speaker> findBySpeakerName(String speakerName);
}
