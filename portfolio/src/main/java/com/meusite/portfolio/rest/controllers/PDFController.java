package com.meusite.portfolio.rest.controllers;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/pdf")
public class PDFController {

    private static final String FILE_NAME = "curriculo.pdf";

    private static final String ATTACHMENT = "attachment";

    @CrossOrigin
    @GetMapping
    public ResponseEntity<Object> generateFile() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            document.add(new Paragraph("Exemplo de arquivo PDF gerado com iText 7"));
            document.add(new Paragraph("Outra linha do documento PDF"));

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData(ATTACHMENT, FILE_NAME);

        return ResponseEntity.ok()
                .headers(headers)
                .body(baos.toByteArray());
    }
}
