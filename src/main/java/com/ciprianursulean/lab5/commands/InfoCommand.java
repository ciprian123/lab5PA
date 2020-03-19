package com.ciprianursulean.lab5.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;


public class InfoCommand implements Command {
    private String infoCommand;
    private String path;

    public InfoCommand(String infoCommand) {
        this.infoCommand = infoCommand;
    }

    @Override
    public boolean isValid() {
        if (path.startsWith("http://") || path.startsWith("https://")) {
            return false;
        }
        else {
            return new File(path).exists();
        }
    }

    @Override
    public void printCommandHelper() {
        System.out.println("Info command syntax: info \"path to file\"");
        System.out.println("Ex: info \"C:/Users/Admin/Desktop/\"");
    }

    @Override
    public void run() throws InvalidCommandFormat,
                             IOException,
                             TikaException,
                             SAXException {
        if (!infoCommand.startsWith("info")) {
            throw new InvalidCommandFormat();
        }
        path = infoCommand.substring(infoCommand.indexOf("info ") + 5);
        if (isValid()) {
            displayFileMetadata(new File(path));
        }
    }

    private void displayFileMetadata(File file) throws TikaException, SAXException, IOException {
        //Parser method parameters
        Parser parser = new AutoDetectParser();
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputStream = new FileInputStream(file);
        ParseContext context = new ParseContext();

        parser.parse(inputStream, handler, metadata, context);
        System.out.println(handler.toString());

        //getting the list of all meta data elements
        String[] metadataNames = metadata.names();
        for(String name : metadataNames) {
            System.out.println(name + ": " + metadata.get(name));
        }
    }
}
