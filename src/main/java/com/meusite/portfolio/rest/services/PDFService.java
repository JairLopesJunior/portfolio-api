package com.meusite.portfolio.rest.services;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
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

    private static final String BRASILEIRO_SOLTEIRO = "Brasileiro, Solteiro";

    private static final String DATA_NASCIMENTO = "Data de Nascimento: 08/11/1995 - 27 Anos";

    private static final String HABILITACAO = "Habilitação: Categoria A/B";

    private static final String RUA_BAIRRO = "Pedro Michelotto, 70 - Jardim Serelepe";

    private static final String CIDADE_ESTADO_CEP = "Leme, São Paulo - Cep: 13611-205";

    private static final String CONTATO = "Contato: (19) 9 9590 - 4628";

    private static final String EMAIL = "E-mail: jair.lopes@fatec.sp.gov.br";

    private static final String EXPERIENCIA_PROFISSIONAL = "Experiência Profissional";

    private static final Integer SEIS = 6;

    private static final Integer DOZE = 12;

    private static final Integer QUINZE = 15;

    private static final Integer VINTE_QUATRO = 24;

    public ResponseEntity<Object> generateFile() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document doc = new Document(pdfDocument)) {

            this.buildEssentialInformation(doc);

            this.buildProfessionalExperience(doc);

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

    private void buildEssentialInformation(Document doc) {
        Color black = new DeviceRgb(0, 0, 0);

        doc.add(this.buildParagraph(JAIR_LOPES_JUNIOR, VINTE_QUATRO, TextAlignment.CENTER, new DeviceRgb(47, 168, 186),
                true, false));
        doc.add(this.jumpLine());

        doc.add(this.buildParagraph(BRASILEIRO_SOLTEIRO, DOZE, TextAlignment.RIGHT, black, false, false));

        doc.add(this.buildParagraph(DATA_NASCIMENTO, DOZE, TextAlignment.RIGHT, black, false, false));

        doc.add(this.buildParagraph(HABILITACAO, DOZE, TextAlignment.RIGHT, black, false, false));

        doc.add(this.buildParagraph(RUA_BAIRRO, DOZE, TextAlignment.RIGHT, black, false, false));

        doc.add(this.buildParagraph(CIDADE_ESTADO_CEP, DOZE, TextAlignment.RIGHT, black, false, false));

        doc.add(this.buildParagraph(CONTATO, DOZE, TextAlignment.RIGHT, black, false, false));

        doc.add(this.buildParagraph(EMAIL, DOZE, TextAlignment.RIGHT, black, false, true));
        doc.add(this.jumpLine());
    }

    private void buildProfessionalExperience(Document doc) {
        Color black = new DeviceRgb(0, 0, 0);

        doc.add(this.buildParagraph(EXPERIENCIA_PROFISSIONAL, QUINZE, TextAlignment.LEFT, black, true, false));
    }

    private Paragraph buildParagraph(String text, Integer fontSize, TextAlignment textAlignment, Color color, boolean isBold,
                                     boolean isLink) {
        Paragraph p = new Paragraph(text);
        p.setFontSize(fontSize);
        p.setTextAlignment(textAlignment);
        p.setFontColor(color);
        p.setFixedLeading(SEIS);
        if (isLink) {
            p.setUnderline();
        }
        if (isBold) {
            p.setBold();
        }
        return p;
    }

    private Paragraph jumpLine() {
        return new Paragraph().add(new Text("\n"));
    }
}
