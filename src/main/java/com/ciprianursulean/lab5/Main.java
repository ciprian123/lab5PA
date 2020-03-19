package com.ciprianursulean.lab5;

import com.ciprianursulean.lab5.commands.InvalidCommandFormat;
import com.itextpdf.text.DocumentException;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException,
            CreationParserFileException,
            InvalidCommandFormat,
            TikaException,
            SAXException, DocumentException, InvalidPathException {
//        Main app = new Main();
//        app.testCreateSaveAsPlainText();
//        app.testLoadViewAsPlainText();
        Document document1 = new Document("#1", "Doc picture", "C:\\Users\\cipri\\OneDrive\\Desktop\\Resources\\dog.jpg");
        document1.addTag("type", "picture");
        document1.addTag("about", "smol dog");

        Document document2 = new Document("#2", "ML 0", "C:\\Users\\cipri\\OneDrive\\Desktop\\Resources\\ml0.pdf");
        document2.addTag("type", "pdf");
        document2.addTag("about", "ML");

        Catalog catalog = new Catalog("CatalogDemo", "CatalogDemo.txt");
        catalog.add(document1);
        catalog.add(document2);

        CatalogParser.save(catalog);
        Shell shell = new Shell(catalog);
        shell.startShell();
    }

    private void testCreateSave() throws IOException, InvalidPathException {
        Catalog catalog = new Catalog("Java Resources", "d:/java/catalog.ser");
        Document doc = new Document("java1", "Java Course 1", "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc.addTag("type", "Slides");
        catalog.add(doc);

        CatalogUtil.save(catalog);
    }

    private void testCreateSaveAsPlainText() throws IOException, CreationParserFileException, InvalidPathException {
        Catalog catalog = new Catalog("Java Resources", "Java Resources.txt");
        Document doc = new Document("java1", "Java Course 1", "https://profs.info.uaic.ro/~acf/java/slides/en/intro_slide_en.pdf");
        doc.addTag("type", "Slides");
        catalog.add(doc);
        CatalogUtil.saveAsPlainText(catalog);
    }

    private void testLoadView() throws InvalidCatalogException,
                                       IOException,
                                       ClassNotFoundException {
        Catalog catalog = CatalogUtil.load("d:/java/catalog.ser");
        Document doc = catalog.findById("java1");
        CatalogUtil.view(doc);
    }

    private void testLoadViewAsPlainText() throws
            IOException,
            InvalidPathException {
        Catalog catalog = CatalogParser.load("Java Resources.txt");
        Document doc = catalog.findById("java1");
        CatalogUtil.view(doc);
    }
}
