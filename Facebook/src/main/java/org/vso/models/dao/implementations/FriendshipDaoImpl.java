package org.vso.models.dao.implementations;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.vso.models.dao.contracts.FriendshipDao;
import org.vso.models.data.Friendship;
import org.vso.models.data.Friendship_;
import org.vso.models.data.User;
import org.vso.models.data.User_;
import org.vso.utils.contracts.Hibernate;
import org.vso.utils.implementations.HibernateImpl;

import java.util.List;
import java.util.Optional;

public class FriendshipDaoImpl implements FriendshipDao<Friendship> {

    @PersistenceUnit(name = "Facebook")
    private EntityManagerFactory entityManagerFactory;

    private final Hibernate hibernate;


    public FriendshipDaoImpl() {
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
    public Optional<Friendship> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Friendship> getAll() {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Friendship> criteria = builder.createQuery(Friendship.class);
        Root<Friendship> root = criteria.from(Friendship.class);
        criteria.select(root);

        List<Friendship> resultList = entityManager.createQuery(criteria).getResultList();

        closeEntityManagerInTransaction(entityManager);

        return resultList;
    }

    @Override
    public void save(Friendship friendRequest) {
        EntityManager entityManager = getEntityManagerInTransaction();
        entityManager.persist(friendRequest);

        closeEntityManagerInTransaction(entityManager);
    }

    @Override
    public void update(Friendship friendRequest, String[] params) {

    }

    @Override
    public void delete(long id) {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Friendship> delete = builder.createCriteriaDelete(Friendship.class);
        Root<Friendship> root = delete.from(Friendship.class);
        delete.where(builder.lessThanOrEqualTo(root.get(Friendship_.ID), id));

        entityManager.createQuery(delete).executeUpdate();

        closeEntityManagerInTransaction(entityManager);
    }

    @Override
    public void delete(Friendship friendship) {
        EntityManager entityManager = getEntityManagerInTransaction();

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaDelete<Friendship> delete = builder.createCriteriaDelete(Friendship.class);
        Root<Friendship> root = delete.from(Friendship.class);
        delete.where(builder.lessThanOrEqualTo(root.get(Friendship_.ID), friendship.getId()));

        entityManager.createQuery(delete).executeUpdate();

        closeEntityManagerInTransaction(entityManager);
    }
}
