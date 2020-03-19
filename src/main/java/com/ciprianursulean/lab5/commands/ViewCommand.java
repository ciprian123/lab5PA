package com.ciprianursulean.lab5.commands;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class ViewCommand implements Command {
    private String viewCommand;
    private String path;

    public ViewCommand(String viewCommand) {
        this.viewCommand = viewCommand;
    }

    @Override
    public boolean isValid() {
        if (path.startsWith("http://") || path.startsWith("https://")) {
            return true;
        }
        else {
            return new File(path).exists();
        }
    }

    @Override
    public void printCommandHelper() {
        System.out.println("View command sysntax: view \"path\"");
        System.out.println("Ex: view \"C:/Users/Admin/Desktop/Baterie_baterie_foc.mp3\"");
    }

    @Override
    public void run() throws InvalidCommandFormat, IOException {
        if (!viewCommand.startsWith("view")) {
            throw new InvalidCommandFormat();
        }
        path = viewCommand.substring(viewCommand.indexOf("view ") + 5);
        if (isValid()) {
            Desktop desktop = Desktop.getDesktop();
            if (path.startsWith("http://") || path.startsWith("https://")) {
                desktop.browse(URI.create(path));
            } else {
                File file = new File(path);
                if (file.exists()) {
                    desktop.open(file);
                }
            }
        }
    }
}
