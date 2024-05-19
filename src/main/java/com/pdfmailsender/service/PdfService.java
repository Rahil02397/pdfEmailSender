package com.pdfmailsender.service;

import com.pdfmailsender.entity.Report;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
@Component
public class PdfService {

    public byte[] generatePdf(Report report) throws IOException {
        try(PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(25, 700);
                contentStream.showText("Title: " + report.getTitle());
                contentStream.newLine();
                contentStream.showText("Description: " + report.getAuthor());
                contentStream.newLine();
                contentStream.showText("content: " + report.getContent());
                contentStream.newLine();
                contentStream.endText();
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}
