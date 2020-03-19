package com.ciprianursulean.lab5;

import com.ciprianursulean.lab5.commands.*;
import com.itextpdf.text.DocumentException;
import org.apache.tika.exception.TikaException;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Shell {
    private Catalog catalog;

    public Shell(Catalog catalog) {
        this.catalog = catalog;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public void startShell() throws IOException,
                                    InvalidCommandFormat,
                                    TikaException,
                                    SAXException, DocumentException, InvalidPathException {
        System.out.println("Welcome to Ciprian's shell!\n");
        System.out.println("> Enter -help in order to learn how use the shell!");
        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("> ");
            String command = bufferedReader.readLine();
            if (command.equals("-help")) {
                printHelp();
            }
            else if (command.startsWith("load")) {
                LoadCommand loadCommand = new LoadCommand(command);
                loadCommand.run();
            }
            else if (command.startsWith("list")) {
                ListCommand listCommand = new ListCommand(command);
                listCommand.run();
            }
            else if (command.startsWith("view")) {
                ViewCommand viewCommand = new ViewCommand(command);
                viewCommand.run();
            }
            else if (command.startsWith("info")) {
                InfoCommand infoCommand = new InfoCommand(command);
                infoCommand.run();
            }
            else if (command.startsWith("report")) {
                ReportCommand reportCommand = new ReportCommand(command, catalog);
                reportCommand.run();
            }
            else if (command.equals("-exit")) {
                System.out.println("> Exiting...");
                break;
            }
            else {
                System.out.println("Invalid command! Use -help if necessary!");
            }
        }
    }

    private void printHelp() {
        System.out.println("\n********************************************************\n");
        System.out.println("Available commands are: ");
        System.out.println("     -help: print available commands to the console.");
        System.out.println("     load path: create object from path file.");
        System.out.println("     list path: list directory contents.");
        System.out.println("     info path: print file metadata.");
        System.out.println("     report html: create html report with catalog contents.");
        System.out.println("     report xlsx: create xlsx report with catalog contents.");
        System.out.println("     report pdf: create pdf report with catalog contents.");
        System.out.println("     -exit: exists from console");
        System.out.println("\n********************************************************\n");
    }
}
