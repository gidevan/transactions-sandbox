package org.vsanyc.transaction.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vsanyc.transaction.sandbox.model.ProducerMessage;

@Repository
public interface ProducerMessageRepository extends JpaRepository<ProducerMessage, Long> {
}
