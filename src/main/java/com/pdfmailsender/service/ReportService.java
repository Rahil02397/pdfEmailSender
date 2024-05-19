package com.pdfmailsender.service;

import com.pdfmailsender.entity.Report;
import com.pdfmailsender.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    private ReportRepository reportRepository;

    public ReportService(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public void saveReport(List<Report> reports) {
        reportRepository.saveAll(reports);
    }
}
