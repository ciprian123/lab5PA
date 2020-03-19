package com.ciprianursulean.lab5.commands;

import java.io.File;

public class ListCommand implements Command {
    private String listCommand;
    private String path;

    public ListCommand(String listCommand) {
        this.listCommand = listCommand;
    }

    @Override
    public boolean isValid() {
        if (path.startsWith("http://") || path.startsWith("https://")) {
            return false;
        }
        else {
            File file = new File(path);
            return file.exists() && file.isDirectory();
        }
    }

    @Override
    public void printCommandHelper() {
        System.out.println("List command syntax: list \"path to DIRECTORY\"");
        System.out.println("Ex: load \"C:/Users/Admin/Desktop/\"");
    }

    private void listDirectoryRecursively(File file) {
        if (file.isFile()) {
            System.out.println(file.getPath());
        }
        else if (file.isDirectory()) {
            var contents = file.listFiles();
            if (contents != null) {
                System.out.println(file.getPath());
                for (File item : contents) {
                    listDirectoryRecursively(item);
                }
            }
        }
    }

    @Override
    public void run() throws InvalidCommandFormat {
        if (!listCommand.startsWith("list")) {
            throw new InvalidCommandFormat();
        }
        path = listCommand.substring(listCommand.indexOf("list ") + 5);
        if (isValid()) {
            listDirectoryRecursively(new File(path));
        }
    }
}
