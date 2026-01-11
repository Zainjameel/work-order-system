package com.example.workorders.repo;

import com.example.workorders.domain.OutboxEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OutboxRepository extends JpaRepository<OutboxEvent, String> {
  List<OutboxEvent> findTop50ByStatusOrderByCreatedAtAsc(String status);
}