package org.vso.models.dao.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.*;
import org.vso.models.dao.contracts.ImageDao;
import org.vso.models.data.Image;
import org.vso.models.data.Image_;
import org.vso.utils.contracts.Hibernate;
import org.vso.utils.implementations.HibernateImpl;

import java.util.List;
import java.util.Optional;

public class ImageDaoImpl implements ImageDao<Image> {

    @PersistenceUnit(name = "Facebook")
    private EntityManagerFactory entityManagerFactory;

    private final Hibernate hibernate;

    public ImageDaoImpl() {
        this.hibernate = new HibernateImpl();
        init();
    }

    private void init() {
        try {
            entityManagerFactory = hibernate.buildEntityManagerFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private EntityManager getEntityManagerInTransaction() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        return entityManager;
    }

    private void closeEntityManagerInTransaction(EntityManager entityManager) {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public Optional<Image> get(long id) {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Image> criteria = builder.createQuery(Image.class);
        Root<Image> root = criteria.from(Image.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get(Image_.ID), id));

        Optional<Image> singleResult = Optional.ofNullable(entityManager.createQuery(criteria).getSingleResult());

        closeEntityManagerInTransaction(entityManager);

        return singleResult;
    }

    @Override
    public List<Image> getAll() {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Image> criteria = builder.createQuery(Image.class);
        Root<Image> root = criteria.from(Image.class);
        criteria.select(root);

        List<Image> resultList = entityManager.createQuery(criteria).getResultList();

        closeEntityManagerInTransaction(entityManager);

        return resultList;
    }

    @Override
    public void save(Image image) {
        EntityManager entityManager = getEntityManagerInTransaction();
        entityManager.persist(image);

        closeEntityManagerInTransaction(entityManager);
    }

    @Override
    public void update(Image image) {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaUpdate<Image> update = builder.createCriteriaUpdate(Image.class);
        Root<Image> root = update.from(Image.class);
        update.where(builder.lessThanOrEqualTo(root.get(Image_.ID), image.getId()));

        entityManager.createQuery(update).executeUpdate();

        closeEntityManagerInTransaction(entityManager);
    }

    @Override
    public void delete(long id) {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Image> delete = builder.createCriteriaDelete(Image.class);
        Root<Image> root = delete.from(Image.class);
        delete.where(builder.lessThanOrEqualTo(root.get(Image_.ID), id));

        entityManager.createQuery(delete).executeUpdate();

        closeEntityManagerInTransaction(entityManager);
    }
}
