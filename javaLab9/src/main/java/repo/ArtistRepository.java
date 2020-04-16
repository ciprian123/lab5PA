package repo;

import entity.Artist;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepository implements AbstractRepository<Artist> {
    public void createWithJPA(Artist artist) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(artist);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Artist findByIdWithJPA(int id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Artist artist = entityManager.find(Artist.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        return artist;
    }

    public List<Artist> findByNameWithJPA(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Artist> artistList = new ArrayList<>();
        try {
            artistList = entityManager.createNamedQuery("Artists.findByName").setParameter("name", name).getResultList();
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        entityManager.close();
        return artistList;
    }

    @Override
    public void createWithJDBC(Artist entity) {
        final String CREATE_STRING = "INSERT INTO `artists` (`name`, `country`) VALUES (?, ?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(CREATE_STRING);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getCountry());
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

    @Override
    public Artist findByIdWithJDBC(int id) {
        final String SEARCH_BY_ID_STRING = "SELECT `id`, `name`, `country` FROM `artists` WHERE `id` = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SEARCH_BY_ID_STRING);
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                return new Artist(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
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

    @Override
    public List<Artist> findByNameWithJDBC(String name) {
        List<Artist> resultList = new ArrayList<>();
        final String SEARCH_BY_NAME_STRING = "SELECT `id`, `name`, `country` FROM `artists` WHERE `name` = ?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SEARCH_BY_NAME_STRING);
            preparedStatement.setString(1, name);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                resultList.add(new Artist(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
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
}
