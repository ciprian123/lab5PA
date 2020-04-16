package repo;

import util.DatabaseUtil;
import util.PersistenceUtil;
import javax.persistence.EntityManagerFactory;
import java.sql.Connection;
import java.util.List;

public interface AbstractRepository<T> {
    EntityManagerFactory entityManagerFactory = PersistenceUtil.getInstance().getEntityManagerFactory();
    Connection connection = DatabaseUtil.getInstance().getConnection();

    void createWithJPA(T entity);
    void createWithJDBC(T entity);

    T findByIdWithJPA(int id);
    T findByIdWithJDBC(int id);

    List<T> findByNameWithJPA(String name);
    List<T> findByNameWithJDBC(String name);
}
