package util;

import entity.Chart;
import net.bytebuddy.agent.builder.AgentBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ChartUtil {
    private static EntityManagerFactory entityManagerFactory = PersistenceUtil.getInstance().getEntityManagerFactory();
    private static Connection connection = DatabaseUtil.getInstance().getConnection();
    private static int limit;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        ChartUtil.limit = limit;
    }

    public static void printTopWithJPA(int limit) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Chart> resultList = entityManager.createNamedQuery("Charts.printTop").setMaxResults(limit).getResultList();
        if (resultList.isEmpty()) {
            System.out.println("Nu avem date in chart!");
        }
        else {
            resultList.forEach(System.out::println);
        }
    }

    public static void printTopWithJDBC(int limit) {
        PreparedStatement preparedStatement;
        final String SELECT_TOP_STRING = "SELECT `artist_id`, `rank` FROM `charts` ORDER BY `rank` DESC LIMIT ?";
        try {
            preparedStatement = connection.prepareStatement(SELECT_TOP_STRING);
            preparedStatement.setInt(1, limit);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                System.out.println("Chart:[ " + resultSet.getInt(1) + " " + resultSet.getInt(2) + "]");
            }
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
