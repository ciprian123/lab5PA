package com.ciprianursulean.lab5.reporters;

import com.ciprianursulean.lab5.Catalog;
import com.ciprianursulean.lab5.Document;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class XlsxReporter {
    public static void reportToXlsx(Catalog catalog) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Java Books");
        int rowCount = 0;

        // Put catalog name on first row
        Row row = sheet.createRow(++rowCount);
        Cell cell = row.createCell(1);
        cell.setCellValue("Catalog name: ");

        cell = row.createCell(2);
        cell.setCellValue(catalog.getName());

        // Put catalog path on second row
        row = sheet.createRow(++rowCount);
        cell = row.createCell(1);
        cell.setCellValue("Catalog path: ");

        cell = row.createCell(2);
        cell.setCellValue(catalog.getPath());

        // Put endline
        row = sheet.createRow(++rowCount);
        cell = row.createCell(1);
        cell.setCellValue(" ");

        // Put documents
        row = sheet.createRow(++rowCount);
        cell = row.createCell(1);
        cell.setCellValue("Documents: ");

        for (Document document : catalog.getDocuments()) {
            row = sheet.createRow(++rowCount);
            cell = row.createCell(1);

            // Put document name
            cell.setCellValue("Document name: ");
            cell = row.createCell(2);
            cell.setCellValue(document.getName());

            // Put document id
            row = sheet.createRow(++rowCount);
            cell = row.createCell(1);
            cell.setCellValue("Document id: ");
            cell = row.createCell(2);
            cell.setCellValue(document.getId());

            // Put document location
            row = sheet.createRow(++rowCount);
            cell = row.createCell(1);
            cell.setCellValue("Document location: ");
            cell = row.createCell(2);
            cell.setCellValue(document.getLocation());

            row = sheet.createRow(++rowCount);
            cell = row.createCell(1);
            cell.setCellValue("Tags: ");

            // Put document tags
            var tags = document.getTags();
            for (var tag : tags.keySet()) {
                row = sheet.createRow(++rowCount);
                cell = row.createCell(1);
                cell.setCellValue(tag);

                cell = row.createCell(2);
                cell.setCellValue(tags.get(tag).toString());
            }

            // Put endline
            row = sheet.createRow(++rowCount);
            cell = row.createCell(1);
            cell.setCellValue(" ");
        }

        try (FileOutputStream outputStream = new FileOutputStream(catalog.getName() + ".xlsx")) {
            System.out.println("> Generating .xlsx report...");
            workbook.write(outputStream);
            System.out.println("> OK!");
        }
    }
}
