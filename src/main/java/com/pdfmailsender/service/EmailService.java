package com.pdfmailsender.service;

import com.pdfmailsender.entity.Report;
import com.pdfmailsender.repository.ReportRepository;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;

@Service
public class EmailService {
    private JavaMailSender mailSender;
    private PdfService pdfService;
    private ReportRepository reportRepository;

    public EmailService(JavaMailSender mailSender, PdfService pdfService, ReportRepository reportRepository) {
        this.mailSender = mailSender;
        this.pdfService = pdfService;
        this.reportRepository = reportRepository;
    }


    public void sendEmailWithReport(Long id, String to) throws IOException, MessagingException {
        Report report = reportRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid report ID"));
        byte[] pdfBytes = pdfService.generatePdf(report);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setTo(to);
        helper.setSubject("Report:"+report.getTitle());
        helper.setText("Please find the attached report",true);

        helper.addAttachment("report.pdf",new ByteArrayDataSource(pdfBytes, "application/pdf"));
        mailSender.send(message);
    }
}
