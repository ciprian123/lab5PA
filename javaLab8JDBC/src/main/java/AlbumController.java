import com.github.javafaker.Faker;

import java.sql.*;

public class AlbumController {
    private static final String INSERT_ALBUM = "INSERT INTO albums(NAME, ARTIST_ID, RELEASE_YEAR) VALUES (?, ?, ?)";
    private static final String FIND_ALBUM_BY_NAME = "SELECT ID, NAME, ARTIST_ID, RELEASE_YEAR FROM albums WHERE ARTIST_ID = (?)";
    private Connection connection = Database.getInstance().getConnection();

    public void create(String name, int artistId, int releaseYear) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_ALBUM);
            statement.setString(1, name);
            statement.setInt(2, artistId);
            statement.setInt(3, releaseYear);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Album findByArtist(int artistId) {
        ResultSet resultSet;
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_ALBUM_BY_NAME);
            statement.setInt(1, artistId);
            statement.executeQuery();
            resultSet = statement.getResultSet();
            if (resultSet.next()) {
                return new Album(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void generateRandomAlbum(int noOfAlbums) {
        Faker fakerGenerator = new Faker();
        for (int i = 1; i < noOfAlbums; ++i) {
            int noOfAlbumsPerArtist = (int)(Math.random() * 10) + 1;
            for (int j = 1; j <= noOfAlbumsPerArtist; ++j) {
                create(fakerGenerator.funnyName().name(), i, 1980 + (int) (Math.random() * 40));
            }
        }
    }
}
