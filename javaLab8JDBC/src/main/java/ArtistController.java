import com.github.javafaker.Faker;

import java.sql.*;

public class ArtistController {
    private static final String INSERT_ARTIST       = "INSERT INTO artists(NAME, COUNTRY) VALUES (?, ?)";
    private static final String FIND_ARTIST_BY_NAME = "SELECT ID, NAME, COUNTRY FROM artists WHERE NAME = (?)";
    private Connection connection = Database.getInstance().getConnection();

    public void create(String name, String country) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(INSERT_ARTIST);
            statement.setString(1, name);
            statement.setString(2, country);
            statement.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Artist findByName(String name) {
        PreparedStatement statement;
        ResultSet resultSet;
        try {
            statement = connection.prepareStatement(FIND_ARTIST_BY_NAME);
            statement.setString(1, name);
            statement.executeQuery();

            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return new Artist(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void generateRandomArtists(int noOfArtists) {
        Faker fakerGenerator = new Faker();
        for (int i = 0; i < noOfArtists; ++i) {
            create(fakerGenerator.funnyName().name(), fakerGenerator.country().name());
        }
    }
}
