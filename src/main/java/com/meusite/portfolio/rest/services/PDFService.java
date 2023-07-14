package com.meusite.portfolio.rest.services;

import com.itextpdf.io.source.ByteArrayOutputStream;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
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

    private static final String EXPERIENCIAS_PROFISSIONAIS = "Experiências Profissionais";

    private static final String EMPRESA_VARITUS_BRASIL = "Empresa: Varitus Brasil";

    private static final String ENDERECO_VARITUS_BRASIL = "Endereço: Rua Coronel André Ulson Júnior, 250";

    private static final String CIDADE_ARARAS_SP = "Cidade: Araras - SP";

    private static final String ESTAGIO_TI_VARITUS = "Estágio em TI · Início: 10/2018 / Término: 10/2020";

    private static final String CARGO_VARITUS_BRASIL = "Programador Junior I · Início: 10/2020 / Término: 09/2021";

    private static final String EMPRESA_VR_SOFTWARE = "Empresa: VR Software";

    private static final String ENDERECO_VR_SOFTWARE = "Endereço: Rua Narciso Gonçalves, 59";

    private static final String CIDADE_LIMEIRA_SP = "Cidade: Limeira - SP";

    private static final String CARGO_VR_SOFTWARE = "Programador WEB · Início: 09/2021 / Término: 03/2022";

    private static final String EMPRESA_ELOTECH = "Empresa: Elotech Gestão Pública";

    private static final String ENDERECO_ELOTECH = "Endereço: Rua Tupã, 1643";

    private static final String CIDADE_MARINGA_PR = "Cidade: Maringá - PR";

    private static final String CARGO_ELOTECH = "Programador de Sistemas de Informação · Início: 03/2022 / Término: 06/2022";

    private static final String EMPRESA_COGNIZANT = "Empresa: Cognizant Technology Solutions";

    private static final String ENDERECO_COGNIZANT = "Endereço: Rua Jaceru, 151";

    private static final String CIDADE_SAO_PAULO_SP = "Cidade: São Paulo - SP";

    private static final String CARGO_COGNIZANT = "Programador de Software · Início: 07/2022";

    private static final String INFORMACOES_ACADEMICAS = "Informações Acadêmicas";

    private static final String TECNICO_INFORMATICA_PARA_INTERNET = "TÉCNICO | ETEC PREFEITO ALBERTO FERES | 01/2016 – 06/2017";

    private static final String GRADUACAO_SISTEMAS_PARA_INTERNET = "GRADUAÇÃO | FATEC - FACULDADE DE TECNOLOGIA DE ARARAS | 06/2017 – 06/2020";

    private static final String GRADUACAO_DESENVOLVIMENTO_SOFTWARE_MULTIPLATAFORMA = "GRADUAÇÃO | FATEC - FACULDADE DE TECNOLOGIA DE ARARAS | 06/2021 – 06/2024";

    private static final String CURSO_INFORMATICA_PARA_INTERNET = "Curso: Informática para Internet";

    private static final String CURSO_SISTEMAS_PARA_INTERNET = "Curso: Sistemas para Internet";

    private static final String CURSO_DESENVOLVIMENTO_SOFTWARE_MULTIPLATAFORMA = "Curso: Desenvolvimento de Software Multiplataforma";

    private static final String PERIODO_NOTURNO = "Período: Noturno";

    private static final String OUTROS_CURSOS = "Outros Cursos";

    private static final String CURSO_JAVA_OITO = "Curso: Java 8 - (Lambda Expressions, API de Data e Hora, Collections)";

    private static final String LINUX_BASICO = "Linux Básico";

    private static final String JAVASCRIPT = "JavaScript: Iniciante/Mestre";

    private static final String SPRING_BOOT_EXPERT = "Spring Boot Expert: JPA, RESTFul API, Security, JWT e Mais.";

    private static final String ANGULAR = "Angular";

    private static final Integer SEIS = 6;

    private static final Integer DOZE = 12;

    private static final Integer TREZE = 13;

    private static final Integer QUATORZE = 14;

    private static final Integer VINTE_QUATRO = 24;

    public ResponseEntity<Object> generateFile() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document doc = new Document(pdfDocument)) {

            this.buildEssentialInformation(doc);

            this.buildProfessionalExperience(doc);

            this.buildAcademicInformation(doc);

            this.buildOutrosCursos(doc);

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
        doc.add(new Paragraph(new Text("\n")));

        doc.add(this.buildParagraph(BRASILEIRO_SOLTEIRO, DOZE, TextAlignment.RIGHT, black, false, false));

        doc.add(this.buildParagraph(DATA_NASCIMENTO, DOZE, TextAlignment.RIGHT, black, false, false));

        doc.add(this.buildParagraph(HABILITACAO, DOZE, TextAlignment.RIGHT, black, false, false));

        doc.add(this.buildParagraph(RUA_BAIRRO, DOZE, TextAlignment.RIGHT, black, false, false));

        doc.add(this.buildParagraph(CIDADE_ESTADO_CEP, DOZE, TextAlignment.RIGHT, black, false, false));

        doc.add(this.buildParagraph(CONTATO, DOZE, TextAlignment.RIGHT, black, false, false));

        doc.add(this.buildParagraph(EMAIL, DOZE, TextAlignment.RIGHT, black, false, true));
        doc.add(new Paragraph(new Text("\n")));
    }

    private void buildProfessionalExperience(Document doc) {
        Color black = new DeviceRgb(0, 0, 0);

        doc.add(this.buildParagraph(EXPERIENCIAS_PROFISSIONAIS, QUATORZE, TextAlignment.LEFT, black, true, false));

        this.buildProfessionalExperienceVaritusBrasil(doc);
        doc.add(this.jumpLine());

        this.buildProfessionalExperienceVRSoftware(doc);
        doc.add(this.jumpLine());

        this.buildProfessionalExperienceElotech(doc);
        doc.add(this.jumpLine());

        this.buildProfessionalExperienceCognizant(doc);
        doc.add(this.jumpLine());
    }

    private void buildAcademicInformation(Document doc) {
        Color black = new DeviceRgb(0, 0, 0);

        doc.add(this.buildParagraph(INFORMACOES_ACADEMICAS, QUATORZE, TextAlignment.LEFT, black, true, false));

        doc.add(this.buildParagraph(TECNICO_INFORMATICA_PARA_INTERNET, DOZE, TextAlignment.LEFT, black, true, false));

        doc.add(this.buildList(CURSO_INFORMATICA_PARA_INTERNET));

        doc.add(this.buildList(PERIODO_NOTURNO));
        doc.add(this.jumpLine());

        doc.add(this.buildParagraph(GRADUACAO_SISTEMAS_PARA_INTERNET, DOZE, TextAlignment.LEFT, black, true, false));

        doc.add(this.buildList(CURSO_SISTEMAS_PARA_INTERNET));

        doc.add(this.buildList(PERIODO_NOTURNO));
        doc.add(this.jumpLine());

        doc.add(this.buildParagraph(GRADUACAO_DESENVOLVIMENTO_SOFTWARE_MULTIPLATAFORMA, DOZE, TextAlignment.LEFT, black, true, false));

        doc.add(this.buildList(CURSO_DESENVOLVIMENTO_SOFTWARE_MULTIPLATAFORMA));

        doc.add(this.buildList(PERIODO_NOTURNO));
        doc.add(this.jumpLine());
    }

    private void buildOutrosCursos(Document doc) {
        Color black = new DeviceRgb(0, 0, 0);

        doc.add(this.buildParagraph(OUTROS_CURSOS, QUATORZE, TextAlignment.LEFT, black, true, false));

        doc.add(this.buildList(CURSO_JAVA_OITO));

        doc.add(this.buildList(LINUX_BASICO));

        doc.add(this.buildList(JAVASCRIPT));

        doc.add(this.buildList(SPRING_BOOT_EXPERT));

        doc.add(this.buildList(ANGULAR));
        doc.add(this.jumpLine());
    }

    private void buildProfessionalExperienceVaritusBrasil(Document doc) {
        doc.add(this.buildList(EMPRESA_VARITUS_BRASIL));

        doc.add(this.buildList(ENDERECO_VARITUS_BRASIL));

        doc.add(this.buildList(CIDADE_ARARAS_SP));

        doc.add(this.buildList(ESTAGIO_TI_VARITUS));

        doc.add(this.buildList(CARGO_VARITUS_BRASIL));
    }

    private void buildProfessionalExperienceVRSoftware(Document doc) {
        doc.add(this.buildList(EMPRESA_VR_SOFTWARE));

        doc.add(this.buildList(ENDERECO_VR_SOFTWARE));

        doc.add(this.buildList(CIDADE_LIMEIRA_SP));

        doc.add(this.buildList(CARGO_VR_SOFTWARE));
    }

    private void buildProfessionalExperienceElotech(Document doc) {
        doc.add(this.buildList(EMPRESA_ELOTECH));

        doc.add(this.buildList(ENDERECO_ELOTECH));

        doc.add(this.buildList(CIDADE_MARINGA_PR));

        doc.add(this.buildList(CARGO_ELOTECH));
    }

    private void buildProfessionalExperienceCognizant(Document doc) {
        doc.add(this.buildList(EMPRESA_COGNIZANT));

        doc.add(this.buildList(ENDERECO_COGNIZANT));

        doc.add(this.buildList(CIDADE_SAO_PAULO_SP));

        doc.add(this.buildList(CARGO_COGNIZANT));
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
        return new Paragraph().setPaddingBottom(2f);
    }

    private List buildList(String text) {
        List list = new List();
        ListItem listItem = new ListItem(text);
        listItem.setMarginBottom(0);
        list.add(listItem);
        list.setFontSize(DOZE);
        return list;
    }
}
