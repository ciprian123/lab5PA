package com.ciprianursulean.lab5;

public class InvalidCatalogException extends Exception {
    public InvalidCatalogException() {
        super("Invalid catalog file.");
    }
}
