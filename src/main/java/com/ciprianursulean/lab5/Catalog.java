package com.ciprianursulean.lab5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Catalog implements Serializable {
    private String name;
    private String path;
    private List<Document> documents = new ArrayList<>();

    public Catalog() {}

    public Catalog(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public Catalog(String name, String path, Document ... documents) throws DuplicateDocumentException {
        this.name = name;
        this.path = path;
        if (Arrays.stream(documents).distinct().count() != documents.length) {
            throw new DuplicateDocumentException();
        }
        Collections.addAll(this.documents, documents);
    }

    public void add(Document doc) {
        documents.add(doc);
    }

    public Document findById(String id) {
        return documents.stream()
                        .filter(doc -> doc.getId()
                        .equals(id)).findFirst()
                        .orElse(null);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Catalog: ");
        stringBuilder.append("\n");
        stringBuilder.append("Name: ");
        stringBuilder.append(name);
        stringBuilder.append("\n");
        stringBuilder.append("Path: ");
        stringBuilder.append(path);
        stringBuilder.append("\n");
        for (Document document : documents) {
            stringBuilder.append(document);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}