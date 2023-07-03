package com.meusite.portfolio;

import java.io.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class WriteExcelExemplo {

    @GetMapping
    public ResponseEntity<InputStreamResource> gerarExcel() throws IOException {
        System.out.println("Teste");
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Planilha1");

            Row headerRow = sheet.createRow(0);
            Cell headerCell1 = headerRow.createCell(0);
            headerCell1.setCellValue("Nome");

            Cell headerCell2 = headerRow.createCell(1);
            headerCell2.setCellValue("Idade");

            Row dataRow = sheet.createRow(1);
            Cell dataCell1 = dataRow.createCell(0);
            dataCell1.setCellValue("João");

            Cell dataCell2 = dataRow.createCell(1);
            dataCell2.setCellValue(25);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=arquivo_excel.xlsx");

            InputStreamResource inputStreamResource = new InputStreamResource(new ByteArrayInputStream(outputStream.toByteArray()));

            return ResponseEntity.ok().headers(headers).body(inputStreamResource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
