package com.meusite.portfolio.rest.services;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class PDFService {

    private static final String FILE_NAME = "curriculo.pdf";

    private static final String ATTACHMENT = "attachment";

    private static final String JAIR_LOPES_JUNIOR = "Jair Lopes Junior";

    private static final Integer VINTE = 20;

    public ResponseEntity<Object> generateFile() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document doc = new Document(pdfDocument)) {

            doc.add(this.addParagraph(JAIR_LOPES_JUNIOR, VINTE, TextAlignment.CENTER));
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

    private Paragraph addParagraph(String text, Integer fontSize, TextAlignment textAlignment) {
        Paragraph p = new Paragraph(text);
        p.setFontSize(fontSize);
        p.setTextAlignment(textAlignment);
        p.setFontColor(Color.);
        return p;
    }
}
