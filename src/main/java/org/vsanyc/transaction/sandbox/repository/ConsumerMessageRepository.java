package org.vsanyc.transaction.sandbox.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vsanyc.transaction.sandbox.model.ConsumerMessage;

@Repository
public interface ConsumerMessageRepository extends JpaRepository<ConsumerMessage, Long> {
}
