package com.ciprianursulean.lab5.commands;

import com.ciprianursulean.lab5.Catalog;
import com.ciprianursulean.lab5.reporters.HtmlReporter;
import com.ciprianursulean.lab5.reporters.PdfReporter;
import com.ciprianursulean.lab5.reporters.XlsxReporter;
import com.itextpdf.text.DocumentException;

import java.io.IOException;

public class ReportCommand implements Command {
    private String reportCommand;
    private Catalog catalog;

    public ReportCommand(String reportCommand, Catalog catalog) {
        this.reportCommand = reportCommand;
        this.catalog = catalog;
    }

    public String getReportCommand() {
        return reportCommand;
    }

    public void setReportCommand(String reportCommand) {
        this.reportCommand = reportCommand;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public boolean isValid() {
        String[] tokens = reportCommand.split("\\s+");
        return tokens.length == 2 && tokens[0].equals("report") && (tokens[1].equals("html") || tokens[1].equals("pdf") || tokens[1].equals("xlsx"));
    }

    @Override
    public void printCommandHelper() {
        System.out.println("Report command syntax: report html | pdf | xls");
        System.out.println("Ex: report html");
    }

    @Override
    public void run() throws InvalidCommandFormat, IOException, DocumentException {
        if (isValid()) {
            if (reportCommand.contains("xlsx")) {
                XlsxReporter.reportToXlsx(catalog);
            }
            else if (reportCommand.contains("html")) {
                HtmlReporter.reportToHtml(catalog);
            }
            else {
                PdfReporter.reportToPdf(catalog);
            }
        }
        else {
            throw new InvalidCommandFormat();
        }
    }
}
