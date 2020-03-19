package com.ciprianursulean.lab5;

public class InvalidPathException extends Exception {
    public InvalidPathException() {
        super("Path is not valid.");
    }
}
