package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtil {
    private static EntityManagerFactory entityManagerFactory;
    private static PersistenceUtil instance;

    private PersistenceUtil() {
        entityManagerFactory = Persistence.createEntityManagerFactory("MusicAlbumsPU");
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    private void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        PersistenceUtil.entityManagerFactory = entityManagerFactory;
    }

    public static PersistenceUtil getInstance() {
        if (instance == null) {
            instance = new PersistenceUtil();
            instance.setEntityManagerFactory(entityManagerFactory);
        }
        return instance;
    }
}
