package com.ciprianursulean.lab5.commands;

import com.ciprianursulean.lab5.InvalidPathException;
import com.itextpdf.text.DocumentException;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.IOException;

public interface Command {
    boolean isValid();
    void printCommandHelper();
    void run() throws InvalidCommandFormat, IOException, TikaException, SAXException, DocumentException, InvalidPathException;
}
