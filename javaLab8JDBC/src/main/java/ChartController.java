import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.sql.*;

public class ChartController {
    private static final String INSERT_CHART_RECORD     = "INSERT INTO charts VALUES (?, ?)";
    private static final String FIND_CHART_RECORD_BY_ID = "SELECT artist_id, rank FROM charts WHERE ID = ?";
    private static final String SELECT_ARTISTS_BY_RANK  = "SELECT `name`, `rank` " +
                                                          "FROM `charts` JOIN `artists` " +
                                                          "ON `id` = `artist_id` " +
                                                          "ORDER BY `rank` DESC " +
                                                          "LIMIT ?";

    private Connection connection = Database.getInstance().getConnection();

    public void create(int artistId, int rank) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(INSERT_CHART_RECORD);
            statement.setInt(1, artistId);
            statement.setInt(2, rank);
            statement.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Chart findByName(String name) {
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            statement = connection.prepareStatement(FIND_CHART_RECORD_BY_ID);
            statement.setString(1, name);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return new Chart(resultSet.getInt(1), resultSet.getInt(2));
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void generateRandomChartData(int noOfRecords) {
        for (int i = 1; i <= noOfRecords; ++i) {
            create(i, 1 + (int)(Math.random() * 100));
        }
    }

    public void printArtistsRank(int limit) {
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement(SELECT_ARTISTS_BY_RANK);
            statement.setInt(1, limit);
            statement.executeQuery();
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                System.out.println("Artist name: " + resultSet.getString(1) + " \nArtist points: " + resultSet.getInt(2));
                System.out.println();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void printArtistsRankTheFancyWay(int limit) throws IOException, SQLException {
        final StringBuilder fileContent = new StringBuilder();
        final BufferedWriter bufferedWriter;
        PreparedStatement statement;

        fileContent.append("<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "<head>\n" +
                            "    <meta charset=\"UTF-8\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto&display=swap\" rel=\"stylesheet\">\n" +
                            "    <title>Document</title>\n" +
                            "\n" +
                            "    <style>\n" +
                            "        table {\n" +
                            "            margin: auto;\n" +
                            "            font-family: 'Roboto', sans-serif;\n" +
                            "            position: relative;\n" +
                            "            top: 50px;\n" +
                            "            width: 65%;\n" +
                            "        }\n" +
                            "\n" +
                            "        tr:nth-of-type(odd) {\n" +
                            "            background: #69f0ae;\n" +
                            "        }\n" +
                            "\n" +
                            "        tr:nth-of-type(even) {\n" +
                            "            background: #607d8b\n" +
                            "        }\n" +
                            "\n" +
                            "        th, td {\n" +
                            "            border: none;\n" +
                            "            padding: 12px 32px;\n" +
                            "            text-align: center;\n" +
                            "        }\n" +
                            "\n" +
                            "    </style>\n" +
                            "</head>\n" +
                            "<body>\n" +
                            "    <table>" +
                            "<tr>\n" +
                            "<th>ID</th>\n" +
                            "<th>Artist name</th>\n" +
                            "<th>Artist votes</th>\n" +
                            "</tr>");

        statement = connection.prepareStatement(SELECT_ARTISTS_BY_RANK);
        statement.setInt(1, limit);
        statement.executeQuery();
        ResultSet resultSet = statement.getResultSet();

        int index = 1;
        while (resultSet.next()) {
            fileContent.append("<tr>");
            fileContent.append("<td>").append(index).append("</td>");
            fileContent.append("<td>").append(resultSet.getString(1)).append("</td>");
            fileContent.append("<td>").append(resultSet.getInt(2)).append("</td>");
            fileContent.append("</tr>");
            index++;
        }

        fileContent.append("</table>\n" + "</body>\n" + "</html>");
        bufferedWriter = new BufferedWriter(new FileWriter("raport.html"));
        bufferedWriter.write(fileContent.toString());
        bufferedWriter.close();

        Desktop desktopManager = Desktop.getDesktop();
        desktopManager.open(new File("raport.html"));
    }
}
