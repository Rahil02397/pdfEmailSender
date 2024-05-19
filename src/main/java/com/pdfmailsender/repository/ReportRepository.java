package com.pdfmailsender.repository;

import com.pdfmailsender.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
