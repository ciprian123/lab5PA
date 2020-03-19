package com.ciprianursulean.lab5;

import java.io.*;

public class CatalogParser {
    Catalog catalog;

    public CatalogParser(Catalog catalog) {
        this.catalog = catalog;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public static void save(Catalog catalog) throws IOException, CreationParserFileException {
        File file = new File(catalog.getName() + ".txt");
        if (file.exists() || file.createNewFile()) {
            System.out.println("File created successfully: " + file.getName());
        }
        else {
            throw new CreationParserFileException();
        }
        final PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));
        printWriter.print(catalog.getName());
        printWriter.print(",");
        printWriter.print(catalog.getPath());
        printWriter.print("\n");
        printWriter.print(catalog.getDocuments().size());
        printWriter.print("\n");
        for (Document document : catalog.getDocuments()) {
            printWriter.print(document.getId());
            printWriter.print(",");
            printWriter.print(document.getName());
            printWriter.print(",");
            printWriter.print(document.getLocation());
            printWriter.print(",");
            var tags = document.getTags();
            for (var tag : tags.keySet()) {
                printWriter.print(tag);
                printWriter.print(":");
                printWriter.print(tags.get(tag));
                printWriter.print(",");
            }
        }
        printWriter.close();
    }

    public static Catalog load(String path) throws IOException, InvalidPathException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        final BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        Catalog catalog = new Catalog();
        String[] tokens = bufferedReader.readLine().split(",");
        catalog.setName(tokens[0]);
        catalog.setPath(tokens[1]);
        int noOfDocuments = Integer.parseInt(bufferedReader.readLine());
        for (int i = 0; i < noOfDocuments; ++i) {
            tokens = bufferedReader.readLine().split(",");
            Document document = new Document();
            document.setId(tokens[0]);
            document.setName(tokens[1]);
            document.setLocation(tokens[2]);
            for (int j = 3; j < tokens.length; ++j) {
                String[] tag = tokens[j].split(":");
                document.addTag(tag[0], tag[1]);
            }
            catalog.add(document);
        }
        System.out.println(catalog);
        return catalog;
    }
}
