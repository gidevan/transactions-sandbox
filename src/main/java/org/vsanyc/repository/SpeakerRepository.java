package org.vsanyc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vsanyc.model.Speaker;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {
}
