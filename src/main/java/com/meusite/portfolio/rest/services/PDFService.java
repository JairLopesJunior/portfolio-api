package com.meusite.portfolio.rest.services;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
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

    private static final Integer VINTE_QUATRO = 24;

    public ResponseEntity<Object> generateFile() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document doc = new Document(pdfDocument)) {

            doc.add(this.addParagraph(JAIR_LOPES_JUNIOR, VINTE_QUATRO, TextAlignment.CENTER, new DeviceRgb(47, 168, 186), true));
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

    private Paragraph addParagraph(String text, Integer fontSize, TextAlignment textAlignment, Color color, boolean isBold) {
        Paragraph p = new Paragraph(text);
        p.setFontSize(fontSize);
        p.setTextAlignment(textAlignment);
        p.setFontColor(color);
        if (isBold) {
            p.setBold();
        }
        return p;
    }
}
