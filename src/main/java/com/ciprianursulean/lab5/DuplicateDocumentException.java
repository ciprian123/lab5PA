package com.ciprianursulean.lab5;

public class DuplicateDocumentException extends Exception {
    public DuplicateDocumentException() {
        super("Duplicate documents found!");
    }
}
