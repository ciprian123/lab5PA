package com.ciprianursulean.lab5.commands;

import com.ciprianursulean.lab5.CatalogParser;
import com.ciprianursulean.lab5.InvalidPathException;

import java.io.File;
import java.io.IOException;

public class LoadCommand implements Command {
    private String loadCommand;
    private String path;

    public LoadCommand(String loadCommand) {
        this.loadCommand = loadCommand;
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
        System.out.println("Load command syntax: load \"calea catre resursa\"");
        System.out.println("Ex: load \"C:/Users/Admin/Desktop/Baterie_baterie_foc.mp3\"");
    }

    @Override
    public void run() throws InvalidCommandFormat, IOException, InvalidPathException {
        if (!loadCommand.startsWith("load")) {
            throw new InvalidCommandFormat();
        }
        path = loadCommand.substring(loadCommand.indexOf("load ") + 5);
        if (isValid()) {
            CatalogParser.load(path);
        }
    }
}
