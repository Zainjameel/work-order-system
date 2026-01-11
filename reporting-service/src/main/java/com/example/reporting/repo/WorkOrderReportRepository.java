package com.example.reporting.repo;

import com.example.reporting.domain.WorkOrderReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderReportRepository extends JpaRepository<WorkOrderReport, Long> {}