package repo;

import entity.Album;
import entity.Artist;

import javax.persistence.EntityManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumRepository implements AbstractRepository<Album> {
    public void createWithJPA(Album album) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(album);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void createWithJDBC(Album entity) {
        final String CREATE_STRING = "INSERT INTO `albums` (`name`, `release_year`, `artist_id`) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(CREATE_STRING);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setInt(2, entity.getReleaseYear());
            preparedStatement.setInt(2, entity.getArtistId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Album findByIdWithJPA(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Album album = entityManager.find(Album.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return album;
    }

    @Override
    public Album findByIdWithJDBC(int id) {
        final String SEARCH_BY_ID_STRING = "SELECT `id`, `name`, `release_year`, `artist_id` FROM `albums` WHERE `id` = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SEARCH_BY_ID_STRING);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                return new Album(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public List<Album> findByNameWithJPA(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Album> albumList = new ArrayList<>();
        try {
            albumList = entityManager.createNamedQuery("Albums.findByName").setParameter("name", name).getResultList();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        entityManager.close();
        return albumList;
    }

    @Override
    public List<Album> findByNameWithJDBC(String name) {
        List<Album> resultList = new ArrayList<>();
        final String SEARCH_BY_NAME_STRING = "SELECT `id`, `name`, `release_year`, `artist_id` FROM `albums` WHERE `name` = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SEARCH_BY_NAME_STRING);
            preparedStatement.setString(1, name);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                resultList.add(new Album(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultList;
    }

    public List<Album> findByArtistIdWithJPA(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Album> albumList = new ArrayList<>();
        try {
            albumList = entityManager.createNamedQuery("Albums.findByArtist").setParameter("id", id).getResultList();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        entityManager.close();
        return albumList;
    }
}