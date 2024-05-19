package com.pdfmailsender.controller;

import com.pdfmailsender.entity.Report;
import com.pdfmailsender.service.EmailService;
import com.pdfmailsender.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pdfservice")
public class ReportController {

    private ReportService reportService;
    private EmailService emailService;

    public ReportController(ReportService reportService, EmailService emailService) {
        this.reportService = reportService;
        this.emailService = emailService;
    }

    @PostMapping("/save-report")
    public String saveReport(@RequestBody List<Report> reports){
        try {
            reportService.saveReport(reports);
            return "Report saved successfully";
        }catch(Exception e){
            return "Failed to save report"+ e.getMessage();
        }
    }

    @GetMapping("/get-report-send")
    public String sendPdfEmail(@RequestParam Long id, @RequestParam String to){
        try {
            emailService.sendEmailWithReport(id, to);
            return "Email sent successfully";
        }catch(Exception e){
            return "Failed to send email"+ e.getMessage();
        }
    }
}
