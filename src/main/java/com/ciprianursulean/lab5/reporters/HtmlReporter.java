package com.ciprianursulean.lab5.reporters;

import com.ciprianursulean.lab5.Catalog;
import com.ciprianursulean.lab5.Document;

import java.io.*;

public class HtmlReporter {
    public static void reportToHtml(Catalog catalog) throws IOException {
        final StringBuilder htmlContent = new StringBuilder(4096);
        final File file = new File(catalog.getName() + ".html");
        final PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file)));

        htmlContent.append("<html>" + "<head>" + "<title>Catalog HTML Report</title>" + "</head>" + "<body>" + "<h4 style=\"font-family: Consolas\">Catalog name: ").append(catalog.getName()).append("</h4 style=\"font-family: Consolas\">").append("<h4 style=\"font-family: Consolas\">Catalog path: ").append(catalog.getPath()).append("</h4>").append("<h4 style=\"font-family: Consolas\">Documents:</h4>");
        // Add document contents to html page
        for (Document document : catalog.getDocuments()) {
            htmlContent.append("<h4 style=\"font-family: Consolas\">Document name: ").append(document.getName()).append("</h4>");
            htmlContent.append("<h4 style=\"font-family: Consolas\">Document id: ").append(document.getId()).append("</h4>");
            htmlContent.append("<h4 style=\"font-family: Consolas\">Document location: ").append(document.getLocation()).append("</h4>");
            // Add document tags to html page
            var tags = document.getTags();
            for (var tag : tags.keySet()) {
                htmlContent.append("<h4 style=\"font-family: Consolas\">").append(tag).append(":").append(tags.get(tag)).append("</h4>");
            }
        }
        htmlContent.append("</body></html>");
        System.out.println("> Generating .html report...");
        printWriter.print(htmlContent.toString());
        System.out.println("> OK!");
        printWriter.close();
    }
}
