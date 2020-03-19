package com.ciprianursulean.lab5.reporters;

import com.ciprianursulean.lab5.Catalog;
import com.ciprianursulean.lab5.Document;

import com.itextpdf.text.DocumentException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.*;

public class PdfReporter {
    public static void reportToPdf(Catalog catalog) throws IOException, DocumentException {
        //Creating a blank page
        PDDocument document = new PDDocument();
        PDPage blankPage = new PDPage();

        //Adding the blank page to the document
        document.addPage( blankPage );

        //Retrieving the pages of the document
        PDPage page = document.getPage(0);
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        //Begin the Content stream
        contentStream.beginText();

        //Setting the font to the Content stream
        contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);

        //Setting the position for the line
        contentStream.newLineAtOffset(25, 750);

        contentStream.setLeading(14.5f);

        System.out.println("> Generating .pdf report...");

        // Adding text in the form of string
        contentStream.showText("Catalog name: " + catalog.getName());
        contentStream.newLine();
        contentStream.showText("Catalog path: " + catalog.getPath());
        contentStream.newLine();
        contentStream.showText("Documents:");
        contentStream.newLine();
        for (Document catalogDocument : catalog.getDocuments()) {
            // Adding the document data
            contentStream.showText("Document name: " + catalogDocument.getName());
            contentStream.newLine();
            contentStream.showText("Document id: " + catalogDocument.getId());
            contentStream.newLine();
            contentStream.showText("Document location: " + catalogDocument.getLocation());
            contentStream.newLine();
            // Adding document tags
            var tags = catalogDocument.getTags();
            for (var tag : tags.keySet()) {
                contentStream.showText(tag + " : " + tags.get(tag).toString());
                contentStream.newLine();
            }
        }

        // Ending the content stream
        contentStream.endText();

        // Closing the content stream
        contentStream.close();

        // Saving the document
        document.save(new File(catalog.getName() + ".pdf"));

        // Closing the document
        document.close();

        System.out.println("> OK!");
    }
}
